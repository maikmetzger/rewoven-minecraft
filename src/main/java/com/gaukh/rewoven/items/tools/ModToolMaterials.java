package com.gaukh.rewoven.items.tools;

import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.item.Item;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.WeaponComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;

import java.util.List;

public class ModToolMaterials {
    public static final TagKey<Item> NETHERRACK_TOOL_MATERIALS = TagKey.of(Registries.ITEM.getKey(), Identifier.of("rewoven", "netherrack_tool_materials"));
    public static final TagKey<Item> COPPER_TOOL_MATERIALS = TagKey.of(Registries.ITEM.getKey(), Identifier.of("rewoven", "copper_tool_materials"));
    public static final TagKey<Item> BRONZE_TOOL_MATERIALS = TagKey.of(Registries.ITEM.getKey(), Identifier.of("rewoven", "bronze_tool_materials"));
    public static final TagKey<Item> STEEL_TOOL_MATERIALS = TagKey.of(Registries.ITEM.getKey(), Identifier.of("rewoven", "steel_tool_materials"));
    
    // Custom block tags for mining levels - using vanilla tag system
    public static final TagKey<Block> INCORRECT_FOR_NETHERRACK_TOOL = TagKey.of(Registries.BLOCK.getKey(), Identifier.of("minecraft", "incorrect_for_stone_tool"));
    public static final TagKey<Block> INCORRECT_FOR_COPPER_TOOL = TagKey.of(Registries.BLOCK.getKey(), Identifier.of("rewoven", "incorrect_for_copper_tool"));
    public static final TagKey<Block> INCORRECT_FOR_BRONZE_TOOL = TagKey.of(Registries.BLOCK.getKey(), Identifier.of("rewoven", "incorrect_for_bronze_tool"));
    public static final TagKey<Block> INCORRECT_FOR_STEEL_TOOL = TagKey.of(Registries.BLOCK.getKey(), Identifier.of("rewoven", "incorrect_for_steel_tool"));

    // Netherrack tool material - updated durability
    public static final ToolMaterial NETHERRACK = new ToolMaterial(
            INCORRECT_FOR_NETHERRACK_TOOL,  // incorrectBlocksForDrops
            52,    // durability (updated from 94)
            1.5F,   // speed (between wood and stone)
            0.5F,   // attackDamageBonus
            5,      // enchantmentValue
            NETHERRACK_TOOL_MATERIALS // repairItems
    );

    // Copper tool material
    public static final ToolMaterial COPPER = new ToolMaterial(
            INCORRECT_FOR_COPPER_TOOL,  // incorrectBlocksForDrops
            96,   // durability
            2.25F,   // speed (between stone and bronze)
            1.0F,   // attackDamageBonus
            8,      // enchantmentValue
            COPPER_TOOL_MATERIALS // repairItems
    );
    
    // Bronze tool material
    public static final ToolMaterial BRONZE = new ToolMaterial(
            INCORRECT_FOR_BRONZE_TOOL,   // incorrectBlocksForDrops
            206,   // durability
            3.0F,   // speed (between copper and iron)
            1.5F,   // attackDamageBonus
            12,     // enchantmentValue
            BRONZE_TOOL_MATERIALS // repairItems
    );

    // Steel tool material
    public static final ToolMaterial STEEL = new ToolMaterial(
            INCORRECT_FOR_STEEL_TOOL,   // incorrectBlocksForDrops
            775,   // durability
            6.0F,   // speed (between iron and diamond)
            2.5F,   // attackDamageBonus
            16,     // enchantmentValue
            STEEL_TOOL_MATERIALS // repairItems
    );

    /**
     * Applies pickaxe settings to Item.Settings using vanilla-style approach
     */
    public static Item.Settings applyPickaxeSettings(ToolMaterial material, Item.Settings settings, float attackDamage, float attackSpeed) {
        RegistryEntryLookup<Block> registryEntryLookup = Registries.createEntryLookup(Registries.BLOCK);
        
        return settings
                .maxDamage(material.durability())
                .repairable(material.repairItems())
                .enchantable(material.enchantmentValue())
                .component(
                    DataComponentTypes.TOOL,
                    new ToolComponent(
                        List.of(
                            ToolComponent.Rule.ofNeverDropping(registryEntryLookup.getOrThrow(material.incorrectBlocksForDrops())),
                            ToolComponent.Rule.ofAlwaysDropping(registryEntryLookup.getOrThrow(BlockTags.PICKAXE_MINEABLE), material.speed())
                        ),
                        1.0F,  // damagePerBlock
                        1,     // defaultMiningSpeed
                        true   // damageEntities
                    )
                )
                .component(
                    DataComponentTypes.ATTRIBUTE_MODIFIERS,
                    AttributeModifiersComponent.builder()
                        .add(EntityAttributes.ATTACK_DAMAGE, 
                             new EntityAttributeModifier(
                                 Identifier.ofVanilla("base_attack_damage"),
                                 attackDamage + material.attackDamageBonus(),
                                 EntityAttributeModifier.Operation.ADD_VALUE
                             ), 
                             AttributeModifierSlot.MAINHAND)
                        .add(EntityAttributes.ATTACK_SPEED,
                             new EntityAttributeModifier(
                                 Identifier.ofVanilla("base_attack_speed"),
                                 attackSpeed,
                                 EntityAttributeModifier.Operation.ADD_VALUE
                             ),
                             AttributeModifierSlot.MAINHAND)
                        .build()
                )
                .component(DataComponentTypes.WEAPON, new WeaponComponent(2, 0.0F));
    }
}