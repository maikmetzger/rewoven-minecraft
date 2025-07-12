package com.gaukh.rewoven.items.tools;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;

public class SteelShovel extends ShovelItem {
    public static final Item STEEL_SHOVEL = ModItems.register(
            "steel_shovel",
            settings -> new SteelShovel(ModToolMaterials.STEEL, 1.5F, -3.0F, settings),
            new Item.Settings().enchantable(ModToolMaterials.STEEL.enchantmentValue())
    );

    public SteelShovel(net.minecraft.item.ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public static void initialize() {
        // Item is already registered via the static field
    }
}