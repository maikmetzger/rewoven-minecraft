package com.gaukh.rewoven.blocks;

import com.gaukh.rewoven.ModBlocks;
import net.minecraft.block.Block;

public class OrangeTulipHedge extends FlowerHedgeBase {

    public static final Block ORANGE_TULIP_HEDGE = ModBlocks.register(
        "orange_tulip_hedge",
        OrangeTulipHedge::new,
        createFlowerHedgeSettings(),
        true
    );

    public OrangeTulipHedge(Settings settings) {
        super(settings, FlowerType.ORANGE_TULIP);
    }
}