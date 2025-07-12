package com.gaukh.rewoven.items;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.Item;

public class Lime extends Item {
    private static final Settings settings = new Settings();
    public static final Item LIME = ModItems.register("lime", Lime::new, Lime.getSettings());

    public Lime(Settings settings) {
        super(settings);
    }

    public static void initialize() {
        // Will be added to item group via ModItems
    }

    public static Settings getSettings() {
        return settings;
    }
}