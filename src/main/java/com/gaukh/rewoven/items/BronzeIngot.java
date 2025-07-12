package com.gaukh.rewoven.items;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.Item;

public class BronzeIngot extends Item {
    private static final Settings settings = new Settings();
    public static final Item BRONZE_INGOT = ModItems.register("bronze_ingot", BronzeIngot::new, BronzeIngot.getSettings());

    public BronzeIngot(Settings settings) {
        super(settings);
    }

    public static void initialize() {
        // Will be added to item group via ModItems
    }

    public static Settings getSettings() {
        return settings;
    }
}