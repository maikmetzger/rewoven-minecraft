package com.gaukh.rewoven.items.tools;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.Item;

public class NetherrackPickaxe extends BasePickaxeItem {
    public static final Item NETHERRACK_PICKAXE = ModItems.register(
            "netherrack_pickaxe",
            settings -> new NetherrackPickaxe(ModToolMaterials.NETHERRACK, 1.0F, -2.4F, settings),
            new Item.Settings()
    );

    public NetherrackPickaxe(net.minecraft.item.ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    /**
     * Initializes the netherrack pickaxe item.
     * This method should be called during mod initialization.
     */
    public static void initialize() {
        // Item is already registered via the static field
    }
}