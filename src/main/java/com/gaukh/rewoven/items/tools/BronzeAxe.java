package com.gaukh.rewoven.items.tools;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;

public class BronzeAxe extends AxeItem {
    public static final Item BRONZE_AXE = ModItems.register(
            "bronze_axe",
            settings -> new BronzeAxe(ModToolMaterials.BRONZE, 6.0F, -3.1F, settings),
            new Item.Settings().enchantable(ModToolMaterials.BRONZE.enchantmentValue())
    );

    public BronzeAxe(net.minecraft.item.ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public static void initialize() {
        // Item is already registered via the static field
    }
}