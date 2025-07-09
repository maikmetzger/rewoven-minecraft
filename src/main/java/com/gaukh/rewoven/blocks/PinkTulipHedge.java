package com.gaukh.rewoven.blocks;

import com.gaukh.rewoven.ModBlocks;
import com.gaukh.rewoven.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;

public class PinkTulipHedge extends FlowerHedgeBase {

    public static final Block PINK_TULIP_HEDGE = ModBlocks.register(
        "pink_tulip_hedge",
        PinkTulipHedge::new,
        createFlowerHedgeSettings(),
        true
    );

    public PinkTulipHedge(Settings settings) {
        super(settings, FlowerType.PINK_TULIP);
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ModItems.REWOVEN_ITEM_GROUP_KEY).register((entries) -> {
            entries.add(PINK_TULIP_HEDGE);
        });
    }
}