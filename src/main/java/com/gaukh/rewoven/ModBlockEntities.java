package com.gaukh.rewoven;

import com.gaukh.rewoven.blockentities.EggLanternEntity;
import com.gaukh.rewoven.blocks.EggLantern;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities
{
    public static final BlockEntityType<EggLanternEntity> EGG_LANTERN_ENTITY =
            register("egg_lantern", EggLanternEntity::new, ModBlocks.EGG_LANTERN);

    public static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
            Block... blocks
    ) {
        Identifier id = Identifier.of(Rewoven.getModId(), name);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }

    public static void initialize() {
    }
}
