package com.gaukh.rewoven.blocks;

import com.gaukh.rewoven.ModBlocks;
import com.gaukh.rewoven.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;

public class LilyOfTheValleyHedge extends FlowerHedgeBase {

    public static final Block LILY_OF_THE_VALLEY_HEDGE = ModBlocks.register(
        "lily_of_the_valley_hedge",
        LilyOfTheValleyHedge::new,
        createFlowerHedgeSettings(),
        true
    );

    public LilyOfTheValleyHedge(Settings settings) {
        super(settings, FlowerType.LILY_OF_THE_VALLEY);
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ModItems.REWOVEN_ITEM_GROUP_KEY).register((entries) -> {
            entries.add(LILY_OF_THE_VALLEY_HEDGE);
        });
    }
}