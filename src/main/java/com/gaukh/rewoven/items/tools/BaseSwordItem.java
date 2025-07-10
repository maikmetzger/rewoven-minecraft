package com.gaukh.rewoven.items.tools;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Identifier;

import java.util.List;

/**
 * Base sword class for Minecraft 1.21.6 since SwordItem was removed.
 * Provides proper tool behavior for enchantment table compatibility.
 */
public class BaseSwordItem extends Item {
    
    private final ToolMaterial material;
    
    public BaseSwordItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(settings
                .component(DataComponentTypes.TOOL, createToolComponent())
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
    
    private static ToolComponent createToolComponent() {
        return new ToolComponent(
            List.of(), // Swords don't have specific block mining rules
            1.0F, // Default speed for unspecified blocks
            2,    // Damage per block (higher for combat weapon)
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