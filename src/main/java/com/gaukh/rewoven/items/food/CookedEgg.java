package com.gaukh.rewoven.items.food;

import com.gaukh.rewoven.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ItemStack;

public class CookedEgg extends Item {
    private static final Settings settings = new Settings().food(
            new FoodComponent.Builder()
                    .nutrition(1)
                    .saturationModifier(0.1f)
                    .build()
    );
    public static final Item COOKED_EGG = ModItems.register("cooked_egg", CookedEgg::new, CookedEgg.getSettings());

    private static final int useTime = 15;


    public CookedEgg(Settings settings) {
        super(settings);
    }

    public static void initialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK)
                .register((itemGroup) -> itemGroup.add(COOKED_EGG));
        CompostingChanceRegistry.INSTANCE.add(COOKED_EGG, 0.1f);
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return useTime;
    }

    public static Settings getSettings() {
        return settings;
    }

}
