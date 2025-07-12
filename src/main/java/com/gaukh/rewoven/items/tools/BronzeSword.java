package com.gaukh.rewoven.items.tools;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.Item;

public class BronzeSword extends BaseSwordItem {
    public static final Item BRONZE_SWORD = ModItems.register(
            "bronze_sword",
            settings -> new BronzeSword(ModToolMaterials.BRONZE, 3.0F, -2.4F, settings),
            new Item.Settings()
    );

    public BronzeSword(net.minecraft.item.ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public static void initialize() {
        // Item is already registered via the static field
    }
}