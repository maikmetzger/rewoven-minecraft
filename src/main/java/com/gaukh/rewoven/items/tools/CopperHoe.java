package com.gaukh.rewoven.items.tools;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class CopperHoe extends HoeItem {
    public static final Item COPPER_HOE = ModItems.register(
            "copper_hoe",
            settings -> new CopperHoe(ModToolMaterials.COPPER, -1.25F, -2.5F, settings),
            new Item.Settings().enchantable(ModToolMaterials.COPPER.enchantmentValue())
    );

    public CopperHoe(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public static void initialize() {
        // Item is already registered via the static field
    }
}