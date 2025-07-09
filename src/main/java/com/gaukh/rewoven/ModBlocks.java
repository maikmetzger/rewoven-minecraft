package com.gaukh.rewoven;

import com.gaukh.rewoven.blocks.EggLantern;
import com.gaukh.rewoven.blocks.Hedge;
import com.gaukh.rewoven.blocks.DandelionHedge;
import com.gaukh.rewoven.blocks.PoppyHedge;
import com.gaukh.rewoven.blocks.BlueOrchidHedge;
import com.gaukh.rewoven.blocks.AlliumHedge;
import com.gaukh.rewoven.blocks.AzureBluetHedge;
import com.gaukh.rewoven.blocks.RedTulipHedge;
import com.gaukh.rewoven.blocks.OrangeTulipHedge;
import com.gaukh.rewoven.blocks.WhiteTulipHedge;
import com.gaukh.rewoven.blocks.PinkTulipHedge;
import com.gaukh.rewoven.blocks.OxeyeDaisyHedge;
import com.gaukh.rewoven.blocks.CornflowerHedge;
import com.gaukh.rewoven.blocks.LilyOfTheValleyHedge;
import com.gaukh.rewoven.blocks.WitherRoseHedge;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
   public static final Block EGG_LANTERN = EggLantern.EGG_LANTERN;
    public static final Block HEDGE = Hedge.HEDGE;
    public static final Block DANDELION_HEDGE = DandelionHedge.DANDELION_HEDGE;
    public static final Block POPPY_HEDGE = PoppyHedge.POPPY_HEDGE;
    public static final Block BLUE_ORCHID_HEDGE = BlueOrchidHedge.BLUE_ORCHID_HEDGE;
    public static final Block ALLIUM_HEDGE = AlliumHedge.ALLIUM_HEDGE;
    public static final Block AZURE_BLUET_HEDGE = AzureBluetHedge.AZURE_BLUET_HEDGE;
    public static final Block RED_TULIP_HEDGE = RedTulipHedge.RED_TULIP_HEDGE;
    public static final Block ORANGE_TULIP_HEDGE = OrangeTulipHedge.ORANGE_TULIP_HEDGE;
    public static final Block WHITE_TULIP_HEDGE = WhiteTulipHedge.WHITE_TULIP_HEDGE;
    public static final Block PINK_TULIP_HEDGE = PinkTulipHedge.PINK_TULIP_HEDGE;
    public static final Block OXEYE_DAISY_HEDGE = OxeyeDaisyHedge.OXEYE_DAISY_HEDGE;
    public static final Block CORNFLOWER_HEDGE = CornflowerHedge.CORNFLOWER_HEDGE;
    public static final Block LILY_OF_THE_VALLEY_HEDGE = LilyOfTheValleyHedge.LILY_OF_THE_VALLEY_HEDGE;
    public static final Block WITHER_ROSE_HEDGE = WitherRoseHedge.WITHER_ROSE_HEDGE;

    public static Block register(String name, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings settings, boolean shouldRegisterItem) {
        // Create a registry key for the block
        RegistryKey<Block> blockKey = keyOfBlock(name);
        // Create the block instance
        Block block = blockFactory.apply(settings.registryKey(blockKey));

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:moving_piston` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            // Items need to be registered with a different type of registry key, but the ID
            // can be the same.
            RegistryKey<Item> itemKey = keyOfItem(name);

            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(itemKey));
            Registry.register(Registries.ITEM, itemKey, blockItem);
        }

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static RegistryKey<Block> keyOfBlock(String name) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Rewoven.getModId(), name));
    }

    private static RegistryKey<Item> keyOfItem(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Rewoven.getModId(), name));
    }

    public static void initialize() {
        EggLantern.initialize();
        Hedge.initialize();
        
        // Flower hedges are automatically registered in their static blocks
        // and added to item groups via FlowerHedgeBase initialization
        initializeFlowerHedges();
    }
    
    private static void initializeFlowerHedges() {
        // Register all flower hedge blocks to the creative item group
        ItemGroupEvents.modifyEntriesEvent(ModItems.REWOVEN_ITEM_GROUP_KEY).register((entries) -> {
            entries.add(DANDELION_HEDGE);
            entries.add(POPPY_HEDGE);
            entries.add(BLUE_ORCHID_HEDGE);
            entries.add(ALLIUM_HEDGE);
            entries.add(AZURE_BLUET_HEDGE);
            entries.add(RED_TULIP_HEDGE);
            entries.add(ORANGE_TULIP_HEDGE);
            entries.add(WHITE_TULIP_HEDGE);
            entries.add(PINK_TULIP_HEDGE);
            entries.add(OXEYE_DAISY_HEDGE);
            entries.add(CORNFLOWER_HEDGE);
            entries.add(LILY_OF_THE_VALLEY_HEDGE);
            entries.add(WITHER_ROSE_HEDGE);
        });
    }
}
