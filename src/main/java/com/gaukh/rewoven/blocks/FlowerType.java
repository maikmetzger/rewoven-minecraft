package com.gaukh.rewoven.blocks;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

public enum FlowerType {
    NONE(null, 0x48B518),  // Default hedge color (oak leaves)
    DANDELION(Items.DANDELION, 0xFFE135),  // Bright yellow
    POPPY(Items.POPPY, 0xFF3333),  // Bright red
    BLUE_ORCHID(Items.BLUE_ORCHID, 0x3366FF),  // Blue
    ALLIUM(Items.ALLIUM, 0xB266FF),  // Purple
    AZURE_BLUET(Items.AZURE_BLUET, 0xE6F2FF),  // Very light blue
    RED_TULIP(Items.RED_TULIP, 0xFF4D4D),  // Red tulip
    ORANGE_TULIP(Items.ORANGE_TULIP, 0xFF8533),  // Orange
    WHITE_TULIP(Items.WHITE_TULIP, 0xF5F5F5),  // White
    PINK_TULIP(Items.PINK_TULIP, 0xFF99CC),  // Pink
    OXEYE_DAISY(Items.OXEYE_DAISY, 0xFFFFCC),  // Light yellow-white
    CORNFLOWER(Items.CORNFLOWER, 0x6699FF),  // Cornflower blue
    LILY_OF_THE_VALLEY(Items.LILY_OF_THE_VALLEY, 0xF0F8F0),  // Very light green-white
    WITHER_ROSE(Items.WITHER_ROSE, 0x4D2D2D);  // Dark red-brown

    private final Item item;
    private final int color;

    FlowerType(Item item, int color) {
        this.item = item;
        this.color = color;
    }

    public Item getItem() {
        return item;
    }

    public int getColor() {
        return color;
    }

    public static FlowerType fromItem(Item item) {
        for (FlowerType type : values()) {
            if (type.item == item) {
                return type;
            }
        }
        return NONE;
    }

    public static FlowerType fromString(String name) {
        try {
            return valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            return NONE;
        }
    }

    public boolean hasFlower() {
        return this != NONE;
    }
}