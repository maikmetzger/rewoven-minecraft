package com.gaukh.rewoven.blocks;

import com.gaukh.rewoven.ModBlocks;
import net.minecraft.block.Block;

public class AlliumHedge extends FlowerHedgeBase {

    public static final Block ALLIUM_HEDGE = ModBlocks.register(
        "allium_hedge",
        AlliumHedge::new,
        createFlowerHedgeSettings(),
        true
    );

    public AlliumHedge(Settings settings) {
        super(settings, FlowerType.ALLIUM);
    }
}