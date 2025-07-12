package com.gaukh.rewoven.items.tools;

import com.gaukh.rewoven.ModItems;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class NetherrackHoe extends HoeItem {
    public static final Item NETHERRACK_HOE = ModItems.register(
            "netherrack_hoe",
            settings -> new NetherrackHoe(ModToolMaterials.NETHERRACK, -0.5F, -3.0F, settings),
            new Item.Settings().enchantable(ModToolMaterials.NETHERRACK.enchantmentValue())
    );

    public NetherrackHoe(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    /**
     * Initializes the netherrack hoe item.
     * This method should be called during mod initialization.
     */
    public static void initialize() {
        // Item is already registered via the static field
    }
}