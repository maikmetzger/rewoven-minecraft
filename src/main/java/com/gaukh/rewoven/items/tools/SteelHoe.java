package com.gaukh.rewoven.items.tools;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class SteelHoe extends HoeItem {
    public static final Item STEEL_HOE = ModItems.register(
            "steel_hoe",
            settings -> new SteelHoe(ModToolMaterials.STEEL, -2.5F, -0.5F, settings),
            new Item.Settings().enchantable(ModToolMaterials.STEEL.enchantmentValue())
    );

    public SteelHoe(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public static void initialize() {
        // Item is already registered via the static field
    }
}