# Rewoven

A Minecraft Fabric mod that starts with having some egg functionality and hedges!

![Minecraft](https://img.shields.io/badge/Minecraft-1.21.6-green.svg)
![Fabric](https://img.shields.io/badge/Fabric-0.16.14-blue.svg)
![License](https://img.shields.io/badge/License-GPL--3.0-yellow.svg)

## Features

Current **Rewoven** features revolve around:

### 🥚 More Egg Stuff!
- **Cooked Eggs** - Cook eggs in a furnace for a quick snack
- **Cracked Egg Shells** - Collect shells from thrown eggs to craft decorative lanterns
- **Egg Lanterns** - Beautiful, dyeable light sources perfect for cozy builds

### 🌿 Hedges
- **Hedge Blocks** - Craft decorative hedge walls from any leaves
- **13 Flowering Hedge Variants** - Transform hedges with your favorite flowers
- **Natural Spreading** - Watch flower hedges naturally spread their beauty to nearby hedges
- **Interactive Mechanics** - Use shears to trim flowering hedges back to regular ones

### ⛏️Tools
- **Netherrack Tools** - New tools crafted from netherrack, bridging the gap between wooden and stone tools, allowing for more nether possibilities with unique balancing.

## Automatic Installation

1. Use a mod launcher, in this case Modrinth.
2. Search for "Rewoven" in the Modrinth app or website.
3. Select the latest version compatible with Minecraft 1.21.6.
4. Click "Install" to add the mod to your Minecraft instance.
5. Launch Minecraft with the correct profile.

## Manual Installation

1. Install [Fabric Loader](https://fabricmc.net/use/installer/) (version 0.16.14 or higher)
2. Install [Fabric API](https://modrinth.com/mod/fabric-api) (version 0.127.1 or higher for Minecraft 1.21.6)
3. Download the latest Rewoven mod
4. Place the mod jar in your `.minecraft/mods` folder
5. Launch Minecraft with the Fabric profile

## In-depth Feature Info

### Egg

#### Cooked Egg
- Cook regular eggs in furnace
- Faster to eat than regular food
- Restores 1 hunger point

#### Cracked Egg Shell
- 50% chance to drop when throwing eggs
- Eggs cooked over campfires will burst, 100% shell drop
- Used as crafting ingredient for Egg Lanterns

#### Egg Lantern
- **Crafting Recipe:**
  ```
  [Shell] [Shell] [Shell]
  [Slime] [Torch] [Slime]
  [Shell] [Shell] [Shell]
  ```
- Emits light level 6
- Can be dyed any color by right-clicking with dyes, just some deco

### Hedge System

#### Basic Hedge
- **Crafting Recipe:** 6 leaves (any type) arranged like walls in a crafting table
- Behaves like walls with proper connections
- Inherits leaf properties (shears/silk touch for harvesting)
- Drops 1-2 sticks when broken without proper tools with original leaf chance
- Fortune enchantment increases stick drops

#### Flowering Hedges
Create flowering hedges with any of these flowers:
- Dandelion Hedge
- Poppy Hedge  
- Blue Orchid Hedge
- Allium Hedge
- Azure Bluet Hedge
- Red Tulip Hedge
- Orange Tulip Hedge
- White Tulip Hedge
- Pink Tulip Hedge
- Oxeye Daisy Hedge
- Cornflower Hedge
- Lily of the Valley Hedge
- Wither Rose Hedge

**Mechanics:**
- Right-click a regular hedge with any flower to convert it
- Flowering hedges spread to adjacent regular hedges (like grass spreading)
- Requires light level 9 or higher to spread
- Right-click with shears to revert to regular hedge
- Each flower type has the color that approximately matches the original flower

## License

This mod is licensed under the GNU General Public License v3.0.