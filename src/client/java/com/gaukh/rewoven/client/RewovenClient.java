package com.gaukh.rewoven.client;

import com.gaukh.rewoven.ModBlocks;
import com.gaukh.rewoven.blocks.FlowerType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.world.biome.FoliageColors;

import java.awt.image.ColorModel;

public class RewovenClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> {
            if (view != null && pos != null && state.getBlock() == ModBlocks.EGG_LANTERN) {
                Object data = view.getBlockEntityRenderData(pos);
                if (data instanceof Integer color) {
                    return color;
                }
            }
            return 0xFFFFFF; // Standardfarbe (weiß)
        }, ModBlocks.EGG_LANTERN);

        ColorProviderRegistry.BLOCK.register(
            (state, world, pos, tintIndex) -> world != null && pos != null
                ? BiomeColors.getFoliageColor(world, pos)
                : FoliageColors.DEFAULT,
            ModBlocks.HEDGE
        );
        
        // Color providers for flower hedges - base layer (tintIndex 0) uses foliage color, overlay (tintIndex 1) uses flower color
        registerFlowerHedgeColors(ModBlocks.DANDELION_HEDGE, FlowerType.DANDELION);
        registerFlowerHedgeColors(ModBlocks.POPPY_HEDGE, FlowerType.POPPY);
        registerFlowerHedgeColors(ModBlocks.BLUE_ORCHID_HEDGE, FlowerType.BLUE_ORCHID);
        registerFlowerHedgeColors(ModBlocks.ALLIUM_HEDGE, FlowerType.ALLIUM);
        registerFlowerHedgeColors(ModBlocks.AZURE_BLUET_HEDGE, FlowerType.AZURE_BLUET);
        registerFlowerHedgeColors(ModBlocks.RED_TULIP_HEDGE, FlowerType.RED_TULIP);
        registerFlowerHedgeColors(ModBlocks.ORANGE_TULIP_HEDGE, FlowerType.ORANGE_TULIP);
        registerFlowerHedgeColors(ModBlocks.WHITE_TULIP_HEDGE, FlowerType.WHITE_TULIP);
        registerFlowerHedgeColors(ModBlocks.PINK_TULIP_HEDGE, FlowerType.PINK_TULIP);
        registerFlowerHedgeColors(ModBlocks.OXEYE_DAISY_HEDGE, FlowerType.OXEYE_DAISY);
        registerFlowerHedgeColors(ModBlocks.CORNFLOWER_HEDGE, FlowerType.CORNFLOWER);
        registerFlowerHedgeColors(ModBlocks.LILY_OF_THE_VALLEY_HEDGE, FlowerType.LILY_OF_THE_VALLEY);
        registerFlowerHedgeColors(ModBlocks.WITHER_ROSE_HEDGE, FlowerType.WITHER_ROSE);

        // Set render layers for all flower hedge blocks
        BlockRenderLayerMap.putBlock(ModBlocks.HEDGE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.DANDELION_HEDGE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.POPPY_HEDGE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.BLUE_ORCHID_HEDGE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.ALLIUM_HEDGE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.AZURE_BLUET_HEDGE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.RED_TULIP_HEDGE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.ORANGE_TULIP_HEDGE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.WHITE_TULIP_HEDGE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.PINK_TULIP_HEDGE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.OXEYE_DAISY_HEDGE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.CORNFLOWER_HEDGE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.LILY_OF_THE_VALLEY_HEDGE, BlockRenderLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.WITHER_ROSE_HEDGE, BlockRenderLayer.CUTOUT);
    }
    
    private void registerFlowerHedgeColors(net.minecraft.block.Block block, FlowerType flowerType) {
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (tintIndex == 0) {
                // Base layer (oak leaves) - use biome foliage color
                return world != null && pos != null
                    ? BiomeColors.getFoliageColor(world, pos)
                    : FoliageColors.DEFAULT;
            } else if (tintIndex == 1) {
                // Overlay layer (wildflowers) - use flower-specific color
                return flowerType.getColor();
            }
            return 0xFFFFFF;
        }, block);
    }
}