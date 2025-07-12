package com.gaukh.rewoven.world;

import com.gaukh.rewoven.ModBlocks;
import com.gaukh.rewoven.Rewoven;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> LIMESTONE_ORE_KEY = registerKey("limestone_ore");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TIN_ORE_SMALL_KEY = registerKey("tin_ore_small");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TIN_ORE_LARGE_KEY = registerKey("tin_ore_large");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> overworldLimestoneOres = List.of(
                OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.LIMESTONE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.LIMESTONE.getDefaultState())
        );

        List<OreFeatureConfig.Target> overworldTinOres = List.of(
                OreFeatureConfig.createTarget(stoneReplaceables, ModBlocks.TIN_ORE.getDefaultState()),
                OreFeatureConfig.createTarget(deepslateReplaceables, ModBlocks.DEEPSLATE_TIN_ORE.getDefaultState())
        );

        register(context, LIMESTONE_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldLimestoneOres, 14));
        register(context, TIN_ORE_SMALL_KEY, Feature.ORE, new OreFeatureConfig(overworldTinOres, 2)); // Small veins (4-8 blocks)
        register(context, TIN_ORE_LARGE_KEY, Feature.ORE, new OreFeatureConfig(overworldTinOres, 5)); // Large deposits (8-16 blocks)
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Rewoven.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(
            Registerable<ConfiguredFeature<?, ?>> context,
            RegistryKey<ConfiguredFeature<?, ?>> key,
            F feature,
            FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}