package com.gaukh.rewoven.items.tools;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.Item;

public class SteelPickaxe extends Item {
    public static final Item STEEL_PICKAXE = ModItems.register(
            "steel_pickaxe",
            settings -> new SteelPickaxe(settings),
            createSteelPickaxeSettings()
    );

    public SteelPickaxe(Settings settings) {
        super(settings);
    }

    private static Item.Settings createSteelPickaxeSettings() {
        // Use vanilla-style pickaxe settings
        return ModToolMaterials.applyPickaxeSettings(ModToolMaterials.STEEL, new Item.Settings(), 1.0F, -2.8F);
    }

    public static void initialize() {
        // Item is already registered via the static field
    }
}