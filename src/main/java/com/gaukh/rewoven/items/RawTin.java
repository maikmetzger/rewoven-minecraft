package com.gaukh.rewoven.items;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.Item;

public class RawTin extends Item {
    private static final Settings settings = new Settings();
    public static final Item RAW_TIN = ModItems.register("raw_tin", RawTin::new, RawTin.getSettings());

    public RawTin(Settings settings) {
        super(settings);
    }

    public static void initialize() {
        // Will be added to item group via ModItems
    }

    public static Settings getSettings() {
        return settings;
    }
}