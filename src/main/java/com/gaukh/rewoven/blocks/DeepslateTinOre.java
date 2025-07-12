package com.gaukh.rewoven.blocks;

import com.gaukh.rewoven.ModBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;

public class DeepslateTinOre extends Block {
    public static final Block DEEPSLATE_TIN_ORE = ModBlocks.register(
            "deepslate_tin_ore",
            DeepslateTinOre::new,
            AbstractBlock.Settings.copy(Blocks.DEEPSLATE_IRON_ORE)
                    .mapColor(MapColor.LIGHT_GRAY)
                    .sounds(BlockSoundGroup.DEEPSLATE),
            true
    );

    public DeepslateTinOre(Settings settings) {
        super(settings);
    }

    public static void initialize() {
        // Block is automatically registered via ModBlocks.register()
    }
}