package com.gaukh.rewoven.items.tools;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;

public class CopperShovel extends ShovelItem {
    public static final Item COPPER_SHOVEL = ModItems.register(
            "copper_shovel",
            settings -> new CopperShovel(ModToolMaterials.COPPER, 1.5F, -3.0F, settings),
            new Item.Settings().enchantable(ModToolMaterials.COPPER.enchantmentValue())
    );

    public CopperShovel(net.minecraft.item.ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public static void initialize() {
        // Item is already registered via the static field
    }
}