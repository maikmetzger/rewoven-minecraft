package com.gaukh.rewoven.items.tools;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.Item;

public class CopperSword extends BaseSwordItem {
    public static final Item COPPER_SWORD = ModItems.register(
            "copper_sword",
            settings -> new CopperSword(ModToolMaterials.COPPER, 3.0F, -2.4F, settings),
            new Item.Settings()
    );

    public CopperSword(net.minecraft.item.ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public static void initialize() {
        // Item is already registered via the static field
    }
}