package com.gaukh.rewoven.world;

import com.gaukh.rewoven.Rewoven;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> LIMESTONE_ORE_UPPER_KEY = registerKey("limestone_ore_upper");
    public static final RegistryKey<PlacedFeature> LIMESTONE_ORE_LOWER_KEY = registerKey("limestone_ore_lower");
    
    // Tin ore - Common biomes (mountains, hills, taiga)
    public static final RegistryKey<PlacedFeature> TIN_ORE_SHALLOW_COMMON_KEY = registerKey("tin_ore_shallow_common");
    public static final RegistryKey<PlacedFeature> TIN_ORE_MEDIUM_COMMON_KEY = registerKey("tin_ore_medium_common");
    public static final RegistryKey<PlacedFeature> TIN_ORE_DEEP_COMMON_KEY = registerKey("tin_ore_deep_common");
    
    // Tin ore - Moderate biomes (forest, river valleys)
    public static final RegistryKey<PlacedFeature> TIN_ORE_SHALLOW_MODERATE_KEY = registerKey("tin_ore_shallow_moderate");
    public static final RegistryKey<PlacedFeature> TIN_ORE_MEDIUM_MODERATE_KEY = registerKey("tin_ore_medium_moderate");
    public static final RegistryKey<PlacedFeature> TIN_ORE_DEEP_MODERATE_KEY = registerKey("tin_ore_deep_moderate");
    
    // Tin ore - Rare biomes (desert, ocean, plains)
    public static final RegistryKey<PlacedFeature> TIN_ORE_SHALLOW_RARE_KEY = registerKey("tin_ore_shallow_rare");
    public static final RegistryKey<PlacedFeature> TIN_ORE_MEDIUM_RARE_KEY = registerKey("tin_ore_medium_rare");
    public static final RegistryKey<PlacedFeature> TIN_ORE_DEEP_RARE_KEY = registerKey("tin_ore_deep_rare");

    public static void bootstrap(Registerable<PlacedFeature> context) {
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        // Upper layer - 1/3 chance per chunk, Y -64 to 0 (deep underground)
        register(context, LIMESTONE_ORE_UPPER_KEY, 
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.LIMESTONE_ORE_KEY),
                ModOrePlacement.modifiersWithRarity(2, // 1/3 chance per chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(0))));

        // Lower layer - 1/8 chance per chunk, Y 0 to 64 (near surface)
        register(context, LIMESTONE_ORE_LOWER_KEY, 
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.LIMESTONE_ORE_KEY),
                ModOrePlacement.modifiersWithRarity(7, // 1/8 chance per chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(64))));

        // Tin Ore - Biome-specific geological distribution
        // Corrected depth ranges: Y 40-80 (shallow), Y 20-40 (medium), Y -64 to 20 (deep)
        
        // === COMMON BIOMES (Mountains, Hills, Taiga) ===
        // Shallow deposits - Y 40-80 (most common in granite-associated areas)
        register(context, TIN_ORE_SHALLOW_COMMON_KEY, 
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.TIN_ORE_SMALL_KEY),
                ModOrePlacement.modifiersWithCount(9, // 6 attempts per chunk (common, larger veins)
                        HeightRangePlacementModifier.uniform(YOffset.fixed(40), YOffset.fixed(80))));

        // Medium depth - Y 20-40 (less common)
        register(context, TIN_ORE_MEDIUM_COMMON_KEY, 
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.TIN_ORE_SMALL_KEY),
                ModOrePlacement.modifiersWithCount(5, // 4 attempts per chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(20), YOffset.fixed(40))));

        // Deep deposits - Y -64 to 20 (rare but larger veins)
        register(context, TIN_ORE_DEEP_COMMON_KEY, 
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.TIN_ORE_LARGE_KEY),
                ModOrePlacement.modifiersWithCount(3, // 3 attempts per chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(20))));
        
        // === MODERATE BIOMES (Forest, River Valleys) ===
        register(context, TIN_ORE_SHALLOW_MODERATE_KEY, 
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.TIN_ORE_SMALL_KEY),
                ModOrePlacement.modifiersWithCount(4, // 4 attempts per chunk (moderate)
                        HeightRangePlacementModifier.uniform(YOffset.fixed(40), YOffset.fixed(80))));

        register(context, TIN_ORE_MEDIUM_MODERATE_KEY, 
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.TIN_ORE_SMALL_KEY),
                ModOrePlacement.modifiersWithCount(3, // 3 attempts per chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(20), YOffset.fixed(40))));

        register(context, TIN_ORE_DEEP_MODERATE_KEY, 
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.TIN_ORE_LARGE_KEY),
                ModOrePlacement.modifiersWithCount(2, // 2 attempts per chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(20))));
        
        // === RARE BIOMES (Desert, Ocean, Plains) ===
        register(context, TIN_ORE_SHALLOW_RARE_KEY, 
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.TIN_ORE_SMALL_KEY),
                ModOrePlacement.modifiersWithCount(2, // 3 attempts per chunk (accessible even in rare biomes)
                        HeightRangePlacementModifier.uniform(YOffset.fixed(40), YOffset.fixed(80))));

        register(context, TIN_ORE_MEDIUM_RARE_KEY, 
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.TIN_ORE_SMALL_KEY),
                ModOrePlacement.modifiersWithCount(1, // 2 attempts per chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(20), YOffset.fixed(40))));

        register(context, TIN_ORE_DEEP_RARE_KEY, 
                configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.TIN_ORE_LARGE_KEY),
                ModOrePlacement.modifiersWithCount(1, // 1 attempt per chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-64), YOffset.fixed(20))));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(Rewoven.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, 
                                RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}