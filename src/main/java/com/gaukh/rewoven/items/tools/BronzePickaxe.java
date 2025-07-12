package com.gaukh.rewoven.items.tools;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.Item;

public class BronzePickaxe extends Item {
    public static final Item BRONZE_PICKAXE = ModItems.register(
            "bronze_pickaxe",
            settings -> new BronzePickaxe(settings),
            createBronzePickaxeSettings()
    );

    public BronzePickaxe(Settings settings) {
        super(settings);
    }

    private static Item.Settings createBronzePickaxeSettings() {
        // Use vanilla-style pickaxe settings
        return ModToolMaterials.applyPickaxeSettings(ModToolMaterials.BRONZE, new Item.Settings(), 1.0F, -2.8F);
    }

    public static void initialize() {
        // Item is already registered via the static field
    }
}