# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Rewoven is a Minecraft 1.21.6 Fabric mod that adds enhanced egg mechanics and a comprehensive hedge system with flowering variants. The mod extends vanilla Minecraft with new blocks, items, and interactive mechanics.

## Build and Development Commands

### Essential Commands
- **Build the mod**: `./gradlew build --no-daemon`
- **Run client for testing**: `./gradlew runClient`
- **Clean build artifacts**: `./gradlew clean`

### Development Environment
- **Minecraft Version**: 1.21.6
- **Fabric Loader**: 0.16.14
- **Fabric API**: 0.127.1+1.21.6
- **Java**: 21 (required)
- **Yarn Mappings**: 1.21.6+build.1

## Architecture Overview

### Core Structure
The mod follows Fabric's standard patterns with modern Minecraft 1.21.6 conventions:

- **Main Class**: `Rewoven.java` - Entry point, initializes all mod components
- **Registration Pattern**: Uses `ModBlocks.register()` utility with `RegistryKey` system
- **Client-Server Split**: Separate client-side initialization in `RewovenClient.java`

### Key Components

#### Hedge System (Primary Feature)
The hedge system is the mod's main feature, implementing a flowering hedge mechanic inspired by vanilla flowering azalea:

**Core Classes:**
- `FlowerHedgeBase` - Abstract base class for all flower hedge variants
- `FlowerType` - Enum defining 13 flower types with colors and items
- `Hedge` - Regular hedge that can receive flowers and convert to flowering hedges
- Individual hedge classes (`DandelionHedge`, `PoppyHedge`, etc.) - 13 separate blocks extending `FlowerHedgeBase`

**Key Mechanics:**
- **Flower Spreading**: Flower hedges spread to adjacent regular hedges via random ticks (like grass spreading)
- **Conversion**: Right-clicking regular hedges with flowers converts them to that flower type
- **Shears Interaction**: Right-clicking flower hedges with shears converts back to regular hedge and drops the flower
- **Wall Connectivity**: All hedges use `WallBlock` for proper wall connections

#### Block Entity System
- `EggLantern` + `EggLanternEntity` - Dyeable light-emitting blocks using NBT persistence
- Modern 1.21.6 NBT patterns with `WriteView`/`ReadView`

#### Client-Side Rendering
- **Color Providers**: Dual-layer tinting system for flower hedges (oak leaves base + flower overlay)
- **Item Models**: Uses Minecraft 1.21.6 JSON-based tinting system with constant color values
- **Inventory Models**: Custom dual-layer models for proper wall inventory shape

### Modern Minecraft 1.21.6 Patterns

#### Block Registration
```java
public static final Block HEDGE = ModBlocks.register(
    "hedge",
    Hedge::new,
    AbstractBlock.Settings.copy(Blocks.COBBLESTONE_WALL)
        .mapColor(MapColor.DARK_GREEN)
        .registryKey(keyOfBlock("hedge")),
    true
);
```

#### Client-Side Color Registration
```java
ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
    if (tintIndex == 0) return BiomeColors.getFoliageColor(world, pos);
    else if (tintIndex == 1) return flowerType.getColor();
    return 0xFFFFFF;
}, block);
```

#### Item Model Tinting (1.21.6 JSON System)
```json
{
  "model": {
    "type": "minecraft:model", 
    "model": "rewoven:block/dandelion_hedge_inventory",
    "tints": [
      {"type": "minecraft:constant", "value": 4764696},
      {"type": "minecraft:constant", "value": 16769845}
    ]
  }
}
```

## Important Development Notes

### Hedge System Implementation Details
- **No ColorProviderRegistry.ITEM**: Minecraft 1.21.4+ removed this API; use JSON tint system instead
- **Property Copying**: Use generic property copying when converting between hedge types to preserve wall connection states
- **Dual-Layer Models**: Inventory models need both oak leaves base (tintindex 0) and wildflower overlay (tintindex 1)
- **Wall Connectivity**: All hedge blocks must be in `data/minecraft/tags/block/walls.json` for proper connections

### Resource File Structure
- **Blockstates**: Multipart system for wall connections with 4 variants per hedge type
- **Loot Tables**: Complex multi-pool system for hedge blocks:
  - Pool 1: Block drop with shears or silk touch
  - Pool 2: Stick drops (1-2) when broken without proper tools, affected by Fortune enchantment
  - Pool 3: Additional stick drops (1-2) with 2% base chance when broken without proper tools
  - Each loot table uses custom random sequence (e.g., `rewoven:blocks/hedge`)
- **Language Files**: Both English and German translations
- **Item Models**: Point to `*_hedge_inventory.json` block models for proper wall shape

### Version-Specific Considerations
- Use `RegistryKey` system for all block/item registration
- NBT handling uses `WriteView`/`ReadView` pattern
- Equipment break status requires `EquipmentSlot` parameter
- `ActionResult.success()` no longer takes boolean parameter

## Features Summary

### Egg System
- **Cooked Egg**: Furnace-cookable eggs with faster consumption
- **Cracked Egg Shell**: 50% drop chance from thrown eggs, used in Egg Lantern crafting
- **Egg Lantern**: Dyeable light source (level 6) with color persistence

### Hedge System  
- **13 Flower Hedge Types**: Individual blocks for each small flower type
- **Spreading Mechanics**: Random tick spreading between hedges in light level 9+
- **Interactive Conversion**: Right-click mechanics for flower application and shears trimming
- **Wall Connectivity**: Full wall connection system matching vanilla walls