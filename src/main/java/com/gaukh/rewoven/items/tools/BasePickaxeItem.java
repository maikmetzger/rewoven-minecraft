package com.gaukh.rewoven.items.tools;

import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;

import java.util.List;

/**
 * Base pickaxe class for Minecraft 1.21.6 since PickaxeItem was removed.
 * Provides proper tool behavior for enchantment table compatibility.
 */
public class BasePickaxeItem extends Item {
    
    private final ToolMaterial material;
    
    public BasePickaxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(settings
                .component(DataComponentTypes.TOOL, createToolComponent(material))
                .component(DataComponentTypes.ATTRIBUTE_MODIFIERS, createAttributeModifiers(attackDamage, attackSpeed))
                .maxDamage(material.durability())
                .repairable(material.repairItems())
                .enchantable(material.enchantmentValue())
        );
        this.material = material;
    }
    
    public ToolMaterial getMaterial() {
        return this.material;
    }
    
    private static ToolComponent createToolComponent(ToolMaterial material) {
        RegistryEntryLookup<Block> registryEntryLookup = Registries.createEntryLookup(Registries.BLOCK);
        return new ToolComponent(
            List.of(
                ToolComponent.Rule.ofAlwaysDropping(
                    registryEntryLookup.getOrThrow(BlockTags.PICKAXE_MINEABLE),
                    material.speed()
                )
            ),
            1.0F, // Default speed for unspecified blocks
            1,    // Damage per block
            true  // Damage entities
        );
    }
    
    private static AttributeModifiersComponent createAttributeModifiers(float attackDamage, float attackSpeed) {
        return AttributeModifiersComponent.builder()
                .add(EntityAttributes.ATTACK_DAMAGE, 
                     new EntityAttributeModifier(
                         Identifier.ofVanilla("base_attack_damage"),
                         attackDamage,
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
                .build();
    }
}