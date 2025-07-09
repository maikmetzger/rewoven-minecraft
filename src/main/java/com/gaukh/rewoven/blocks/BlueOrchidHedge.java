package com.gaukh.rewoven.blocks;

import com.gaukh.rewoven.ModBlocks;
import net.minecraft.block.Block;

public class BlueOrchidHedge extends FlowerHedgeBase {

    public static final Block BLUE_ORCHID_HEDGE = ModBlocks.register(
        "blue_orchid_hedge",
        BlueOrchidHedge::new,
        createFlowerHedgeSettings(),
        true
    );

    public BlueOrchidHedge(Settings settings) {
        super(settings, FlowerType.BLUE_ORCHID);
    }
}