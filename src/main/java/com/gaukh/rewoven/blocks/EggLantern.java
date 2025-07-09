package com.gaukh.rewoven.blocks;

import com.gaukh.rewoven.ModBlocks;
import com.gaukh.rewoven.ModItems;
import com.gaukh.rewoven.blockentities.EggLanternEntity;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class EggLantern extends BlockWithEntity {
    private static final VoxelShape SHAPE = Block.createCuboidShape(5.0d, 0.0d, 5.0d, 11.0d, 10.0d, 11.0d); // 8.0d == 8 pixels

    public static final Block EGG_LANTERN = ModBlocks.register(
            "egg_lantern",
            EggLantern::new,
            AbstractBlock.Settings.create()
                    .sounds(BlockSoundGroup.CALCITE)
                    .luminance((blockState -> 6))
                    .emissiveLighting((blockState, blockView, blockPos) -> true)
                    .strength(0.3f)
                    .nonOpaque(),
            true
    );


    public EggLantern(Settings settings) {
        super(settings);
    }

    public static int getColor(BlockState state, BlockView world, BlockPos pos) {
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof EggLanternEntity eggLanternEntity) {
            return eggLanternEntity.getColor();
        }
        return 0xFFFFFF; // Default color (white) if no entity or color is set
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(EggLantern::new);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new EggLanternEntity(pos, state);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!(player.getStackInHand(player.getActiveHand()).getItem() instanceof net.minecraft.item.DyeItem dyeItem)) {
            return ActionResult.PASS;
        }

        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof EggLanternEntity eggLanternEntity) {
            int color = dyeItem.getColor().getFireworkColor(); // oder getSignColor(), je nach gewünschtem Farbwert
            eggLanternEntity.setColor(color);
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // Visual + hitbox outline (mouse hover box)
        return SHAPE;
    }


    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // Physical collision (what you walk on)
        return SHAPE;
    }



    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ModItems.REWOVEN_ITEM_GROUP_KEY).register((entries) -> {
            entries.add(EGG_LANTERN);
        });
    }
}

