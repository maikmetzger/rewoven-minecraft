package com.gaukh.rewoven.blocks;

import com.gaukh.rewoven.ModBlocks;
import net.minecraft.block.Block;

public class DandelionHedge extends FlowerHedgeBase {

    public static final Block DANDELION_HEDGE = ModBlocks.register(
        "dandelion_hedge",
        DandelionHedge::new,
        createFlowerHedgeSettings(),
        true
    );

    public DandelionHedge(Settings settings) {
        super(settings, FlowerType.DANDELION);
    }
}