package com.gaukh.rewoven.blocks;

import com.gaukh.rewoven.ModBlocks;
import com.gaukh.rewoven.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;

public class WitherRoseHedge extends FlowerHedgeBase {

    public static final Block WITHER_ROSE_HEDGE = ModBlocks.register(
        "wither_rose_hedge",
        WitherRoseHedge::new,
        createFlowerHedgeSettings(),
        true
    );

    public WitherRoseHedge(Settings settings) {
        super(settings, FlowerType.WITHER_ROSE);
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ModItems.REWOVEN_ITEM_GROUP_KEY).register((entries) -> {
            entries.add(WITHER_ROSE_HEDGE);
        });
    }
}