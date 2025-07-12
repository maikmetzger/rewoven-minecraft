package com.gaukh.rewoven.items;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.Item;

public class TinIngot extends Item {
    private static final Settings settings = new Settings();
    public static final Item TIN_INGOT = ModItems.register("tin_ingot", TinIngot::new, TinIngot.getSettings());

    public TinIngot(Settings settings) {
        super(settings);
    }

    public static void initialize() {
        // Will be added to item group via ModItems
    }

    public static Settings getSettings() {
        return settings;
    }
}