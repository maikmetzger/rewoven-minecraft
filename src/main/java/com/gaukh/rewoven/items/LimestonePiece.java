package com.gaukh.rewoven.items;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.Item;

public class LimestonePiece extends Item {
    private static final Settings settings = new Settings();
    public static final Item LIMESTONE_PIECE = ModItems.register("limestone_piece", LimestonePiece::new, LimestonePiece.getSettings());

    public LimestonePiece(Settings settings) {
        super(settings);
    }

    public static void initialize() {
        // Will be added to item group via ModItems
    }

    public static Settings getSettings() {
        return settings;
    }
}