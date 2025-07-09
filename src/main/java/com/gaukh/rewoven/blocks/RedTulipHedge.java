package com.gaukh.rewoven.blocks;

import com.gaukh.rewoven.ModBlocks;
import net.minecraft.block.Block;

public class RedTulipHedge extends FlowerHedgeBase {

    public static final Block RED_TULIP_HEDGE = ModBlocks.register(
        "red_tulip_hedge",
        RedTulipHedge::new,
        createFlowerHedgeSettings(),
        true
    );

    public RedTulipHedge(Settings settings) {
        super(settings, FlowerType.RED_TULIP);
    }
}