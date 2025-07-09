package com.gaukh.rewoven.items.food;

import com.gaukh.rewoven.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;

public class CrackedEggShell extends Item {
    private static final Settings settings = new Settings();
    public static final Item CRACKED_EGG_SHELL = ModItems.register("cracked_egg_shell", CrackedEggShell::new, CrackedEggShell.getSettings());


    public CrackedEggShell(Settings settings) {
        super(settings);
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
                .register((itemGroup) -> itemGroup.add(CRACKED_EGG_SHELL));
        CompostingChanceRegistry.INSTANCE.add(CRACKED_EGG_SHELL, 0.1f);
    }

    public static Settings getSettings() {
        return settings;
    }
}
