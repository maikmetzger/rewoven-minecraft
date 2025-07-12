package com.gaukh.rewoven.items.tools;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;

public class SteelAxe extends AxeItem {
    public static final Item STEEL_AXE = ModItems.register(
            "steel_axe",
            settings -> new SteelAxe(ModToolMaterials.STEEL, 5.5F, -3.0F, settings),
            new Item.Settings().enchantable(ModToolMaterials.STEEL.enchantmentValue())
    );

    public SteelAxe(net.minecraft.item.ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public static void initialize() {
        // Item is already registered via the static field
    }
}