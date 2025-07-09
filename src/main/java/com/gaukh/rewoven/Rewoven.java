package com.gaukh.rewoven;

import net.fabricmc.api.ModInitializer;

public class Rewoven implements ModInitializer {
    public static final String MOD_ID = "rewoven";

    @Override
    public void onInitialize() {
        ModItems.initialize();
        ModBlocks.initialize();
        ModBlockEntities.initialize();
    }

    public static String getModId() {
        return MOD_ID;
    }
}
