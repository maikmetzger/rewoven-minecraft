package com.gaukh.rewoven.blocks;

import com.gaukh.rewoven.ModBlocks;
import net.minecraft.block.Block;

public class AzureBluetHedge extends FlowerHedgeBase {

    public static final Block AZURE_BLUET_HEDGE = ModBlocks.register(
        "azure_bluet_hedge",
        AzureBluetHedge::new,
        createFlowerHedgeSettings(),
        true
    );

    public AzureBluetHedge(Settings settings) {
        super(settings, FlowerType.AZURE_BLUET);
    }
}