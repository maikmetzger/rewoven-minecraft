package com.gaukh.rewoven.items.tools;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ShovelItem;

public class NetherrackShovel extends ShovelItem {
    public static final Item NETHERRACK_SHOVEL = ModItems.register(
            "netherrack_shovel",
            settings -> new NetherrackShovel(ModToolMaterials.NETHERRACK, 0.0F, -2.4F, settings),
            new Item.Settings().enchantable(ModToolMaterials.NETHERRACK.enchantmentValue())
    );

    public NetherrackShovel(net.minecraft.item.ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    /**
     * Initializes the netherrack shovel item.
     * This method should be called during mod initialization.
     */
    public static void initialize() {
        // Item is already registered via the static field
    }
}