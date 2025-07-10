package com.gaukh.rewoven.items.tools;

import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;

public class ModToolMaterials {
    
    public static final TagKey<Item> NETHERRACK_TOOL_MATERIALS = TagKey.of(Registries.ITEM.getKey(), Identifier.of("rewoven", "netherrack_tool_materials"));
    
    // Netherrack tool material - moderate stats between stone and iron
    public static final ToolMaterial NETHERRACK = new ToolMaterial(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL,  // incorrectBlocksForDrops
            94,    // durability (between stone 131 and iron 250)
            3.5F,   // speed (stone is 4.0F, iron is 6.0F)
            1.0F,   // attackDamageBonus (stone is 1.0F, iron is 2.0F)
            5,      // enchantmentValue (stone is 5, iron is 14)
            NETHERRACK_TOOL_MATERIALS // repairItems (using netherrack)
    );
}