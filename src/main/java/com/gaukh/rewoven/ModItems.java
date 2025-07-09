package com.gaukh.rewoven;

import com.gaukh.rewoven.items.food.CookedEgg;
import com.gaukh.rewoven.items.food.CrackedEggShell;
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
    public static final Item COOKED_EGG = CookedEgg.COOKED_EGG;
    public static final Item CRACKED_EGG_SHELL = CrackedEggShell.CRACKED_EGG_SHELL;

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
        CookedEgg.initialize();
        CrackedEggShell.initialize();

        Registry.register(Registries.ITEM_GROUP, REWOVEN_ITEM_GROUP_KEY, REWOVEN_ITEM_GROUP);

        ItemGroupEvents.modifyEntriesEvent(REWOVEN_ITEM_GROUP_KEY).register(itemGroup -> {
            itemGroup.add(ModItems.COOKED_EGG);
            itemGroup.add(ModItems.CRACKED_EGG_SHELL);
            // ...
        });
    }

}
