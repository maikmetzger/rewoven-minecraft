package com.gaukh.rewoven.blocks;

import com.gaukh.rewoven.ModBlocks;
import com.gaukh.rewoven.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;

public class WhiteTulipHedge extends FlowerHedgeBase {

    public static final Block WHITE_TULIP_HEDGE = ModBlocks.register(
        "white_tulip_hedge",
        WhiteTulipHedge::new,
        createFlowerHedgeSettings(),
        true
    );

    public WhiteTulipHedge(Settings settings) {
        super(settings, FlowerType.WHITE_TULIP);
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ModItems.REWOVEN_ITEM_GROUP_KEY).register((entries) -> {
            entries.add(WHITE_TULIP_HEDGE);
        });
    }
}