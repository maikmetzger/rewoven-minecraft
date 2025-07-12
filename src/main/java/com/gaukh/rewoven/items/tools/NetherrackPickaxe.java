package com.gaukh.rewoven.items.tools;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.Item;

public class NetherrackPickaxe extends Item {
    public static final Item NETHERRACK_PICKAXE = ModItems.register(
            "netherrack_pickaxe",
            settings -> new NetherrackPickaxe(settings),
            createNetherrackPickaxeSettings()
    );

    public NetherrackPickaxe(Settings settings) {
        super(settings);
    }

    private static Item.Settings createNetherrackPickaxeSettings() {
        return ModToolMaterials.applyPickaxeSettings(ModToolMaterials.NETHERRACK, new Item.Settings(), 1.0F, -2.8F);
    }

    public static void initialize() {
        // Item is already registered via the static field
    }
}