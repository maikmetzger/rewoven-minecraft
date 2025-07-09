package com.gaukh.rewoven.blocks;

import com.gaukh.rewoven.ModBlocks;
import net.minecraft.block.Block;

public class PoppyHedge extends FlowerHedgeBase {

    public static final Block POPPY_HEDGE = ModBlocks.register(
        "poppy_hedge",
        PoppyHedge::new,
        createFlowerHedgeSettings(),
        true
    );

    public PoppyHedge(Settings settings) {
        super(settings, FlowerType.POPPY);
    }
}