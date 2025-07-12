package com.gaukh.rewoven.items;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.Item;

public class SteelIngot extends Item {
    private static final Settings settings = new Settings();
    public static final Item STEEL_INGOT = ModItems.register("steel_ingot", SteelIngot::new, SteelIngot.getSettings());

    public SteelIngot(Settings settings) {
        super(settings);
    }

    public static void initialize() {
        // Will be added to item group via ModItems
    }

    public static Settings getSettings() {
        return settings;
    }
}