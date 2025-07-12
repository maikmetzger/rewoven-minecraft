package com.gaukh.rewoven.items.tools;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;

public class CopperAxe extends AxeItem {
    public static final Item COPPER_AXE = ModItems.register(
            "copper_axe",
            settings -> new CopperAxe(ModToolMaterials.COPPER, 6.5F, -3.2F, settings),
            new Item.Settings().enchantable(ModToolMaterials.COPPER.enchantmentValue())
    );

    public CopperAxe(net.minecraft.item.ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public static void initialize() {
        // Item is already registered via the static field
    }
}