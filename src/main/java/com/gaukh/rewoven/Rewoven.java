package com.gaukh.rewoven;

import com.gaukh.rewoven.world.ModPlacedFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;

public class Rewoven implements ModInitializer {
    public static final String MOD_ID = "rewoven";

    @Override
    public void onInitialize() {
        ModItems.initialize();
        ModBlocks.initialize();
        ModBlockEntities.initialize();
        
        // Register limestone blob generation (dual-layer like granite/diorite/andesite)
        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatures.LIMESTONE_ORE_UPPER_KEY
        );
        
        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatures.LIMESTONE_ORE_LOWER_KEY
        );

        // Register biome-specific tin ore generation based on geological realism
        // Common biomes: Mountains/Hills/Taiga (granite-associated tin deposits)
        BiomeModifications.addFeature(
            BiomeSelectors.tag(BiomeTags.IS_MOUNTAIN).or(BiomeSelectors.tag(BiomeTags.IS_HILL)).or(BiomeSelectors.tag(BiomeTags.IS_TAIGA)),
            GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatures.TIN_ORE_SHALLOW_COMMON_KEY
        );
        BiomeModifications.addFeature(
            BiomeSelectors.tag(BiomeTags.IS_MOUNTAIN).or(BiomeSelectors.tag(BiomeTags.IS_HILL)).or(BiomeSelectors.tag(BiomeTags.IS_TAIGA)),
            GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatures.TIN_ORE_MEDIUM_COMMON_KEY
        );
        BiomeModifications.addFeature(
            BiomeSelectors.tag(BiomeTags.IS_MOUNTAIN).or(BiomeSelectors.tag(BiomeTags.IS_HILL)).or(BiomeSelectors.tag(BiomeTags.IS_TAIGA)),
            GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatures.TIN_ORE_DEEP_COMMON_KEY
        );
        
        // Moderate biomes: Forest/River (moderate tin potential)  
        BiomeModifications.addFeature(
            BiomeSelectors.tag(BiomeTags.IS_FOREST).or(BiomeSelectors.tag(BiomeTags.IS_RIVER)),
            GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatures.TIN_ORE_SHALLOW_MODERATE_KEY
        );
        BiomeModifications.addFeature(
            BiomeSelectors.tag(BiomeTags.IS_FOREST).or(BiomeSelectors.tag(BiomeTags.IS_RIVER)),
            GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatures.TIN_ORE_MEDIUM_MODERATE_KEY
        );
        BiomeModifications.addFeature(
            BiomeSelectors.tag(BiomeTags.IS_FOREST).or(BiomeSelectors.tag(BiomeTags.IS_RIVER)),
            GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatures.TIN_ORE_DEEP_MODERATE_KEY
        );
        
        // Rare biomes: All remaining biomes (desert, ocean, plains, etc.)
        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld()
                .and(BiomeSelectors.tag(BiomeTags.IS_MOUNTAIN).negate())
                .and(BiomeSelectors.tag(BiomeTags.IS_HILL).negate())
                .and(BiomeSelectors.tag(BiomeTags.IS_TAIGA).negate())
                .and(BiomeSelectors.tag(BiomeTags.IS_FOREST).negate())
                .and(BiomeSelectors.tag(BiomeTags.IS_RIVER).negate()),
            GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatures.TIN_ORE_SHALLOW_RARE_KEY
        );
        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld()
                .and(BiomeSelectors.tag(BiomeTags.IS_MOUNTAIN).negate())
                .and(BiomeSelectors.tag(BiomeTags.IS_HILL).negate())
                .and(BiomeSelectors.tag(BiomeTags.IS_TAIGA).negate())
                .and(BiomeSelectors.tag(BiomeTags.IS_FOREST).negate())
                .and(BiomeSelectors.tag(BiomeTags.IS_RIVER).negate()),
            GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatures.TIN_ORE_MEDIUM_RARE_KEY
        );
        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld()
                .and(BiomeSelectors.tag(BiomeTags.IS_MOUNTAIN).negate())
                .and(BiomeSelectors.tag(BiomeTags.IS_HILL).negate())
                .and(BiomeSelectors.tag(BiomeTags.IS_TAIGA).negate())
                .and(BiomeSelectors.tag(BiomeTags.IS_FOREST).negate())
                .and(BiomeSelectors.tag(BiomeTags.IS_RIVER).negate()),
            GenerationStep.Feature.UNDERGROUND_ORES,
            ModPlacedFeatures.TIN_ORE_DEEP_RARE_KEY
        );
    }

    public static String getModId() {
        return MOD_ID;
    }
}
