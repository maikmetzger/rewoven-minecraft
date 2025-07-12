package com.gaukh.rewoven.items.tools;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;

public class BronzeShovel extends ShovelItem {
    public static final Item BRONZE_SHOVEL = ModItems.register(
            "bronze_shovel",
            settings -> new BronzeShovel(ModToolMaterials.BRONZE, 1.5F, -3.0F, settings),
            new Item.Settings().enchantable(ModToolMaterials.BRONZE.enchantmentValue())
    );

    public BronzeShovel(net.minecraft.item.ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public static void initialize() {
        // Item is already registered via the static field
    }
}