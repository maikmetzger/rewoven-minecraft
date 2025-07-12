package com.gaukh.rewoven.items.tools;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.Item;

public class CopperPickaxe extends Item {
    public static final Item COPPER_PICKAXE = ModItems.register(
            "copper_pickaxe",
            settings -> new CopperPickaxe(settings),
            createCopperPickaxeSettings()
    );

    public CopperPickaxe(Settings settings) {
        super(settings);
    }

    private static Item.Settings createCopperPickaxeSettings() {
        // Use vanilla-style pickaxe settings
        return ModToolMaterials.applyPickaxeSettings(ModToolMaterials.COPPER, new Item.Settings(), 1.0F, -2.8F);
    }

    public static void initialize() {
        // Item is already registered via the static field
    }
}