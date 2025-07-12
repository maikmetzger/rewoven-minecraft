package com.gaukh.rewoven.items.tools;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class BronzeHoe extends HoeItem {
    public static final Item BRONZE_HOE = ModItems.register(
            "bronze_hoe",
            settings -> new BronzeHoe(ModToolMaterials.BRONZE, -1.5F, -1.5F, settings),
            new Item.Settings().enchantable(ModToolMaterials.BRONZE.enchantmentValue())
    );

    public BronzeHoe(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public static void initialize() {
        // Item is already registered via the static field
    }
}