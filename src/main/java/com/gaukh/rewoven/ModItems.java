package com.gaukh.rewoven;

import com.gaukh.rewoven.items.food.CookedEgg;
import com.gaukh.rewoven.items.food.CrackedEggShell;
import com.gaukh.rewoven.items.*;
import com.gaukh.rewoven.items.tools.*;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    // Food Items
    public static final Item COOKED_EGG = CookedEgg.COOKED_EGG;
    public static final Item CRACKED_EGG_SHELL = CrackedEggShell.CRACKED_EGG_SHELL;
    
    // Material Items
    public static final Item RAW_TIN = RawTin.RAW_TIN;
    public static final Item TIN_INGOT = TinIngot.TIN_INGOT;
    public static final Item BRONZE_INGOT = BronzeIngot.BRONZE_INGOT;
    public static final Item STEEL_INGOT = SteelIngot.STEEL_INGOT;
    public static final Item LIMESTONE_PIECE = LimestonePiece.LIMESTONE_PIECE;
    public static final Item LIME = Lime.LIME;
    public static final Item CARBON_DUST = CarbonDust.CARBON_DUST;
    
    // Netherrack Tools
    public static final Item NETHERRACK_PICKAXE = NetherrackPickaxe.NETHERRACK_PICKAXE;
    public static final Item NETHERRACK_SHOVEL = NetherrackShovel.NETHERRACK_SHOVEL;
    public static final Item NETHERRACK_AXE = NetherrackAxe.NETHERRACK_AXE;
    public static final Item NETHERRACK_SWORD = NetherrackSword.NETHERRACK_SWORD;
    public static final Item NETHERRACK_HOE = NetherrackHoe.NETHERRACK_HOE;
    
    // Copper Tools
    public static final Item COPPER_PICKAXE = CopperPickaxe.COPPER_PICKAXE;
    public static final Item COPPER_SHOVEL = CopperShovel.COPPER_SHOVEL;
    public static final Item COPPER_AXE = CopperAxe.COPPER_AXE;
    public static final Item COPPER_SWORD = CopperSword.COPPER_SWORD;
    public static final Item COPPER_HOE = CopperHoe.COPPER_HOE;
    
    // Bronze Tools
    public static final Item BRONZE_PICKAXE = BronzePickaxe.BRONZE_PICKAXE;
    public static final Item BRONZE_SHOVEL = BronzeShovel.BRONZE_SHOVEL;
    public static final Item BRONZE_AXE = BronzeAxe.BRONZE_AXE;
    public static final Item BRONZE_SWORD = BronzeSword.BRONZE_SWORD;
    public static final Item BRONZE_HOE = BronzeHoe.BRONZE_HOE;
    
    // Steel Tools
    public static final Item STEEL_PICKAXE = SteelPickaxe.STEEL_PICKAXE;
    public static final Item STEEL_SHOVEL = SteelShovel.STEEL_SHOVEL;
    public static final Item STEEL_AXE = SteelAxe.STEEL_AXE;
    public static final Item STEEL_SWORD = SteelSword.STEEL_SWORD;
    public static final Item STEEL_HOE = SteelHoe.STEEL_HOE;

    public static final RegistryKey<ItemGroup> REWOVEN_ITEM_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Rewoven.getModId(), "item_group"));
    public static final ItemGroup REWOVEN_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.COOKED_EGG))
            .displayName(Text.translatable("itemGroup.rewoven"))
            .build();

    /**
     * Registers an item with the given name, item factory, and settings.
     * @param name The name of the item to register.
     * @param itemFactory A function that takes Item.Settings and returns an Item instance.
     * @param settings The settings for the item, including properties like durability, stack size, etc.
     * @return The registered item instance.
     */
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // Create the item key.
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Rewoven.getModId(), name));

        // Create the item instance.
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }

    /**
     * Initializes the items in this mod.
     * This method should be called during the mod initialization phase.
     */
    public static void initialize() {
        // Food Items
        CookedEgg.initialize();
        CrackedEggShell.initialize();
        
        // Material Items
        RawTin.initialize();
        TinIngot.initialize();
        BronzeIngot.initialize();
        SteelIngot.initialize();
        LimestonePiece.initialize();
        Lime.initialize();
        CarbonDust.initialize();
        
        // Netherrack Tools
        NetherrackPickaxe.initialize();
        NetherrackShovel.initialize();
        NetherrackAxe.initialize();
        NetherrackSword.initialize();
        NetherrackHoe.initialize();
        
        // Copper Tools
        CopperPickaxe.initialize();
        CopperShovel.initialize();
        CopperAxe.initialize();
        CopperSword.initialize();
        CopperHoe.initialize();
        
        // Bronze Tools
        BronzePickaxe.initialize();
        BronzeShovel.initialize();
        BronzeAxe.initialize();
        BronzeSword.initialize();
        BronzeHoe.initialize();
        
        // Steel Tools
        SteelPickaxe.initialize();
        SteelShovel.initialize();
        SteelAxe.initialize();
        SteelSword.initialize();
        SteelHoe.initialize();

        Registry.register(Registries.ITEM_GROUP, REWOVEN_ITEM_GROUP_KEY, REWOVEN_ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(REWOVEN_ITEM_GROUP_KEY).register(itemGroup -> {
            // Food Items
            itemGroup.add(ModItems.COOKED_EGG);
            itemGroup.add(ModItems.CRACKED_EGG_SHELL);
            
            // Material Items
            itemGroup.add(ModItems.RAW_TIN);
            itemGroup.add(ModItems.TIN_INGOT);
            itemGroup.add(ModItems.BRONZE_INGOT);
            itemGroup.add(ModItems.STEEL_INGOT);
            itemGroup.add(ModItems.LIMESTONE_PIECE);
            itemGroup.add(ModItems.LIME);
            itemGroup.add(ModItems.CARBON_DUST);
            
            // Netherrack Tools
            itemGroup.add(ModItems.NETHERRACK_PICKAXE);
            itemGroup.add(ModItems.NETHERRACK_SHOVEL);
            itemGroup.add(ModItems.NETHERRACK_AXE);
            itemGroup.add(ModItems.NETHERRACK_SWORD);
            itemGroup.add(ModItems.NETHERRACK_HOE);
            
            // Copper Tools
            itemGroup.add(ModItems.COPPER_PICKAXE);
            itemGroup.add(ModItems.COPPER_SHOVEL);
            itemGroup.add(ModItems.COPPER_AXE);
            itemGroup.add(ModItems.COPPER_SWORD);
            itemGroup.add(ModItems.COPPER_HOE);
            
            // Bronze Tools
            itemGroup.add(ModItems.BRONZE_PICKAXE);
            itemGroup.add(ModItems.BRONZE_SHOVEL);
            itemGroup.add(ModItems.BRONZE_AXE);
            itemGroup.add(ModItems.BRONZE_SWORD);
            itemGroup.add(ModItems.BRONZE_HOE);
            
            // Steel Tools
            itemGroup.add(ModItems.STEEL_PICKAXE);
            itemGroup.add(ModItems.STEEL_SHOVEL);
            itemGroup.add(ModItems.STEEL_AXE);
            itemGroup.add(ModItems.STEEL_SWORD);
            itemGroup.add(ModItems.STEEL_HOE);
        });
    }

}
