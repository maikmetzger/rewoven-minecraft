package com.gaukh.rewoven.blocks;

import com.gaukh.rewoven.ModBlocks;
import com.gaukh.rewoven.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;

public class CornflowerHedge extends FlowerHedgeBase {

    public static final Block CORNFLOWER_HEDGE = ModBlocks.register(
        "cornflower_hedge",
        CornflowerHedge::new,
        createFlowerHedgeSettings(),
        true
    );

    public CornflowerHedge(Settings settings) {
        super(settings, FlowerType.CORNFLOWER);
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ModItems.REWOVEN_ITEM_GROUP_KEY).register((entries) -> {
            entries.add(CORNFLOWER_HEDGE);
        });
    }
}