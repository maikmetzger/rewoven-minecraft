package com.gaukh.rewoven.items;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.Item;

public class CarbonDust extends Item {
    private static final Settings settings = new Settings();
    public static final Item CARBON_DUST = ModItems.register("carbon_dust", CarbonDust::new, CarbonDust.getSettings());

    public CarbonDust(Settings settings) {
        super(settings);
    }

    public static void initialize() {
        // Will be added to item group via ModItems
    }

    public static Settings getSettings() {
        return settings;
    }
}