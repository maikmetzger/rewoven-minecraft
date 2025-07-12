package com.gaukh.rewoven.mixin;

import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ToolMaterial.class)
public class ToolMaterialsMixin {
    
    // Custom block tags for vanilla tools
    @Unique
    private static final TagKey<Block> INCORRECT_FOR_STONE_TOOL = TagKey.of(Registries.BLOCK.getKey(), Identifier.of("minecraft", "incorrect_for_stone_tool"));
    @Unique
    private static final TagKey<Block> INCORRECT_FOR_IRON_TOOL = TagKey.of(Registries.BLOCK.getKey(), Identifier.of("minecraft", "incorrect_for_iron_tool"));
    @Unique
    private static final TagKey<Block> INCORRECT_FOR_GOLD_TOOL = TagKey.of(Registries.BLOCK.getKey(), Identifier.of("minecraft", "incorrect_for_gold_tool"));
    @Unique
    private static final TagKey<Block> INCORRECT_FOR_DIAMOND_TOOL = TagKey.of(Registries.BLOCK.getKey(), Identifier.of("minecraft", "incorrect_for_diamond_tool"));

    @Mutable
    @Shadow @Final public static ToolMaterial WOOD;

    @Mutable
    @Shadow
    @Final
    public static ToolMaterial STONE;

    @Mutable
    @Shadow @Final public static ToolMaterial IRON;

    @Mutable
    @Shadow @Final public static ToolMaterial GOLD;

    @Mutable
    @Shadow @Final public static ToolMaterial DIAMOND;

    static {
        // Override vanilla materials with your custom values
        WOOD = new ToolMaterial(
                BlockTags.INCORRECT_FOR_WOODEN_TOOL,
                16,    // durability
                1.25F,  // speed
                0.0F,  // damage
                3,
                ItemTags.PLANKS
        );

        STONE = new ToolMaterial(
                INCORRECT_FOR_STONE_TOOL,
                64,    // durability
                1.75F,  // speed
                0.5F,  // damage
                5,
                ItemTags.STONE_TOOL_MATERIALS
        );

        IRON = new ToolMaterial(
                INCORRECT_FOR_IRON_TOOL,
                152,   // durability
                4.25F,  // speed
                2.0F,  // damage
                14,
                ItemTags.IRON_TOOL_MATERIALS
        );

        GOLD = new ToolMaterial(
                INCORRECT_FOR_GOLD_TOOL,
                72,    // durability
                12.0F, // speed
                0.0F,  // Gold tools have no damage bonus
                22,
                ItemTags.GOLD_TOOL_MATERIALS
        );

        DIAMOND = new ToolMaterial(
                INCORRECT_FOR_DIAMOND_TOOL,
                1240,  // durability
                8.0F,  // speed
                3.0F,  // damage
                10,
                ItemTags.DIAMOND_TOOL_MATERIALS
        );
    }
}