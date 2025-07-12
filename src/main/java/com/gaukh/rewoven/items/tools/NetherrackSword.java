package com.gaukh.rewoven.items.tools;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.Item;

public class NetherrackSword extends BaseSwordItem {
    public static final Item NETHERRACK_SWORD = ModItems.register(
            "netherrack_sword",
            settings -> new NetherrackSword(ModToolMaterials.NETHERRACK, 1.5F, -1.8F, settings),
            new Item.Settings()
    );

    public NetherrackSword(net.minecraft.item.ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    /**
     * Initializes the netherrack sword item.
     * This method should be called during mod initialization.
     */
    public static void initialize() {
        // Item is already registered via the static field
    }
}