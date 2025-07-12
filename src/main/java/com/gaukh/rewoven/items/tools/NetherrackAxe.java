package com.gaukh.rewoven.items.tools;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;

public class NetherrackAxe extends AxeItem {
    public static final Item NETHERRACK_AXE = ModItems.register(
            "netherrack_axe",
            settings -> new NetherrackAxe(ModToolMaterials.NETHERRACK, 6.0F, -3.2F, settings),
            new Item.Settings().enchantable(ModToolMaterials.NETHERRACK.enchantmentValue())
    );

    public NetherrackAxe(net.minecraft.item.ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    /**
     * Initializes the netherrack axe item.
     * This method should be called during mod initialization.
     */
    public static void initialize() {
        // Item is already registered via the static field
    }
}