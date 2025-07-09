package com.gaukh.rewoven.blocks;

import com.gaukh.rewoven.ModBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.block.enums.WallShape;
import static net.minecraft.state.property.Properties.*;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public abstract class FlowerHedgeBase extends Hedge {

    private final FlowerType flowerType;

    public FlowerHedgeBase(Settings settings, FlowerType flowerType) {
        super(settings);
        this.flowerType = flowerType;
    }

    public FlowerType getFlowerType() {
        return flowerType;
    }



    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int lightLevel = world.getLightLevel(pos.up());
        if (lightLevel >= 9) {
            if (random.nextInt(4) == 0) {
                spreadFlowers(world, pos, random);
            }
        }
    }

    private void spreadFlowers(ServerWorld world, BlockPos pos, Random random) {
        for (int i = 0; i < 4; i++) {
            BlockPos targetPos = pos.add(
                random.nextInt(3) - 1,
                random.nextInt(2) - random.nextInt(2),
                random.nextInt(3) - 1
            );

            BlockState targetState = world.getBlockState(targetPos);
            
            if (targetState.getBlock() == ModBlocks.HEDGE && world.getLightLevel(targetPos.up()) >= 9) {
                BlockState newState = this.getDefaultState();
                for (Property<?> property : targetState.getProperties()) {
                    if (newState.contains(property)) {
                        newState = copyProperty(newState, targetState, property);
                    }
                }
                world.setBlockState(targetPos, newState);
            }
        }
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        ItemStack heldItem = player.getStackInHand(player.getActiveHand());
        
        if (heldItem.getItem() instanceof ShearsItem) {
            if (!world.isClient) {
                BlockState newState = ModBlocks.HEDGE.getDefaultState();
                for (Property<?> property : state.getProperties()) {
                    if (newState.contains(property)) {
                        newState = copyProperty(newState, state, property);
                    }
                }
                world.setBlockState(pos, newState);
                
                world.playSound(null, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
                
                if (heldItem.isDamageable()) {
                    heldItem.setDamage(heldItem.getDamage() + 1);
                    if (heldItem.getDamage() >= heldItem.getMaxDamage()) {
                        player.sendEquipmentBreakStatus(heldItem.getItem(), net.minecraft.entity.EquipmentSlot.MAINHAND);
                        heldItem.decrement(1);
                    }
                }
            }
            return world.isClient ? ActionResult.SUCCESS : ActionResult.CONSUME;
        }
        
        return ActionResult.PASS;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> BlockState copyProperty(BlockState newState, BlockState oldState, Property<T> property) {
        return newState.with(property, oldState.get(property));
    }

    public static Settings createFlowerHedgeSettings() {
        // Create a fresh copy each time to avoid settings mutation issues
        return AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)
            .mapColor(MapColor.DARK_GREEN)
            .sounds(BlockSoundGroup.AZALEA_LEAVES)
            .jumpVelocityMultiplier(0.5f)
            .velocityMultiplier(0.5f);
    }
}