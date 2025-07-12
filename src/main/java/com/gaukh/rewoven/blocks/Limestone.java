package com.gaukh.rewoven.blocks;

import com.gaukh.rewoven.ModBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;

public class Limestone extends Block {
    public static final Block LIMESTONE = ModBlocks.register(
            "limestone",
            Limestone::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.OFF_WHITE)
                    .strength(0.4f, 0.4f) // Same as netherrack - soft stone
                    .requiresTool() // Requires pickaxe
                    .sounds(BlockSoundGroup.STONE),
            true
    );

    public Limestone(Settings settings) {
        super(settings);
    }

    public static void initialize() {
        // Block is automatically registered via ModBlocks.register()
        // Drops 1-2 limestone pieces - handled via loot table
    }
}