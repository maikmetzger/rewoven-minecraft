package com.gaukh.rewoven.blocks;

import com.gaukh.rewoven.ModBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;

public class TinOre extends Block {
    public static final Block TIN_ORE = ModBlocks.register(
            "tin_ore",
            TinOre::new,
            AbstractBlock.Settings.copy(Blocks.IRON_ORE)
                    .mapColor(MapColor.LIGHT_GRAY)
                    .sounds(BlockSoundGroup.STONE),
            true
    );

    public TinOre(Settings settings) {
        super(settings);
    }

    public static void initialize() {
        // Block is automatically registered via ModBlocks.register()
    }
}