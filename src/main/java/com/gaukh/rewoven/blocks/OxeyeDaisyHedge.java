package com.gaukh.rewoven.blocks;

import com.gaukh.rewoven.ModBlocks;
import com.gaukh.rewoven.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;

public class OxeyeDaisyHedge extends FlowerHedgeBase {

    public static final Block OXEYE_DAISY_HEDGE = ModBlocks.register(
        "oxeye_daisy_hedge",
        OxeyeDaisyHedge::new,
        createFlowerHedgeSettings(),
        true
    );

    public OxeyeDaisyHedge(Settings settings) {
        super(settings, FlowerType.OXEYE_DAISY);
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ModItems.REWOVEN_ITEM_GROUP_KEY).register((entries) -> {
            entries.add(OXEYE_DAISY_HEDGE);
        });
    }
}