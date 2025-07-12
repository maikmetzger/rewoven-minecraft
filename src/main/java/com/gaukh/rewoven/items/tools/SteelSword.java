package com.gaukh.rewoven.items.tools;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.Item;

public class SteelSword extends BaseSwordItem {
    public static final Item STEEL_SWORD = ModItems.register(
            "steel_sword",
            settings -> new SteelSword(ModToolMaterials.STEEL, 3.0F, -2.4F, settings),
            new Item.Settings()
    );

    public SteelSword(net.minecraft.item.ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public static void initialize() {
        // Item is already registered via the static field
    }
}