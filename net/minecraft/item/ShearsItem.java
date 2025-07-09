package net.minecraft.item;

import java.util.List;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class ShearsItem extends Item {
	public ShearsItem(Item.Settings settings) {
		super(settings);
	}

	public static ToolComponent createToolComponent() {
		RegistryEntryLookup<Block> registryEntryLookup = Registries.createEntryLookup(Registries.BLOCK);
		return new ToolComponent(
			List.of(
				ToolComponent.Rule.ofAlwaysDropping(RegistryEntryList.of(Blocks.COBWEB.getRegistryEntry()), 15.0F),
				ToolComponent.Rule.of(registryEntryLookup.getOrThrow(BlockTags.LEAVES), 15.0F),
				ToolComponent.Rule.of(registryEntryLookup.getOrThrow(BlockTags.WOOL), 5.0F),
				ToolComponent.Rule.of(RegistryEntryList.of(Blocks.VINE.getRegistryEntry(), Blocks.GLOW_LICHEN.getRegistryEntry()), 2.0F)
			),
			1.0F,
			1,
			true
		);
	}

	@Override
	public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
		ToolComponent toolComponent = stack.get(DataComponentTypes.TOOL);
		if (toolComponent == null) {
			return false;
		} else {
			if (!world.isClient() && !state.isIn(BlockTags.FIRE) && toolComponent.damagePerBlock() > 0) {
				stack.damage(toolComponent.damagePerBlock(), miner, EquipmentSlot.MAINHAND);
			}

			return true;
		}
	}

	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		BlockPos blockPos = context.getBlockPos();
		BlockState blockState = world.getBlockState(blockPos);
		if (blockState.getBlock() instanceof AbstractPlantStemBlock abstractPlantStemBlock && !abstractPlantStemBlock.hasMaxAge(blockState)) {
			PlayerEntity playerEntity = context.getPlayer();
			ItemStack itemStack = context.getStack();
			if (playerEntity instanceof ServerPlayerEntity) {
				Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)playerEntity, blockPos, itemStack);
			}

			world.playSound(playerEntity, blockPos, SoundEvents.BLOCK_GROWING_PLANT_CROP, SoundCategory.BLOCKS, 1.0F, 1.0F);
			BlockState blockState2 = abstractPlantStemBlock.withMaxAge(blockState);
			world.setBlockState(blockPos, blockState2);
			world.emitGameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Emitter.of(context.getPlayer(), blockState2));
			if (playerEntity != null) {
				itemStack.damage(1, playerEntity, LivingEntity.getSlotForHand(context.getHand()));
			}

			return ActionResult.SUCCESS;
		} else {
			return super.useOnBlock(context);
		}
	}
}
