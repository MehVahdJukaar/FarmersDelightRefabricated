## Major Changes
- Removed Porting Lib as a dependency.
  - Parts of the mod have been rewritten because of this.
- The mod can now run on base 1.21, for all the retrogrades out there. Now please stop asking.

## Datapack Changes
- Due to Porting Lib's removal, `portinglib:can_item_perform_ability` no longer exists, and has been replaced with `farmersdelight:can_item_perform_ability`.
  - The options are also more limited for this. Feel free to PR more options if you need them.
- Added a new entity tag for Leather Dropping mobs. `farmersdelight:drops_leather`. Used with knives to make sure that leather dropping entities will drop an extra leather upon death.
- Added new mob effect tags for whether a mob effect is ignored by Milk Bottles and Hot Cocoa. These tags are:
  - `farmersdelight:ignored/hot_cocoa`
  - `farmersdelight:ignored/milk_bottle`
  - Hot Cocoa still follows the harmful effect ruling, Beneficial/Neutral effect clearing cannot be enabled for Hot Cocoa through these tags.

## Bugfixes
- Fixed packet decode failure upon attempting to join servers without Farmer's Delight Refabricated. [#56](https://github.com/MehVahdJukaar/FarmersDelightRefabricated/issues/56)
- Fixed a classloading issue preventing the ItemLike (Mojmap) interface from having mixins applied. [#77](https://github.com/MehVahdJukaar/FarmersDelightRefabricated/issues/77)
- Fixed Pumpkins being cut into slices when the Knife used to break the block has Silk Touch. [#141](https://github.com/MehVahdJukaar/FarmersDelightRefabricated/issues/141)
- Fixed recipes sometimes being incorrect due to the game not accounting for individual stacks in the recipe check. [#150](https://github.com/MehVahdJukaar/FarmersDelightRefabricated/issues/150)
- Fixed the inability to cut cake when using a knife on a cake.
- Fixed event based block and entity interactions being processed whilst the player is in spectator.

# Addon Migration Primer (2.x.x -> 3.x.x)

If you have an addon, you will need to update if you utilise any Porting Lib systems in your mod.

I'd suggest launching your game with Farmer's Delight 3.0.0, and seeing if your mod crashes or has inconsistent behaviour if you're unsure about whether you've used Porting Lib content or not.

## Packages
A few classes have had their packages changed around to separate our custom additions from the base Farmer's Delight where we can.
These classes can be found inside `vectorwing.farmersdelight.refabricated`.

## Configs
Referenced config values should remain the same, as Jukaar's config format is very close to the Porting Lib/Forge syntax.
If you were using Porting Lib as a config, you're best to go out and find a new config solution. There's like a million of them, write your own like I did (this post was made by Pug) if you're feeling cautious.

## Registration
Registration has been refactored a little bit, but types are the same as before. Some class definitions may have moved around to suit classloading needs, but that's a case by case scenario.

## Item Abilities
Porting Lib's Item Abilities have been replaced with an enum that references specific Minecraft or Conventional item tags.
You should probably use tag checks for loot conditions and recipes if you aren't us, simply because we hardcode our Item Abilities to only be what we personally need.

## Loot Modifications
Porting Lib's Forge inspired Loot Modification system has been replaced with native Fabric API loot modifications.

The main difference being that Fabric's system directly modifies the loot table on load, rather than applying to the returned items from a loot table each time.

https://docs.fabricmc.net/develop/events#listening-to-loot-table-loading

## Inventories
We have made our own inventory that hooks into Fabric's Transfer API to replace Porting Lib's inventory code.

Some of the classes and renames made to be closer to NeoForge's naming for parity are...
- `SlottedStackStorage` -> `ItemHandler`. A basic interface for item inventories, extends Fabric's SlottedStorage.
- `SlotItemHandler` -> `ItemHandlerSlot`. A `Slot` (Mojmap) implementation for a single `ItemHandler` slot.
- `ItemStackHandlerContainer` -> `ItemStackHandler`. - An item inventory implementation.
- `ItemStackHandlerSlot` -> `ItemStackStorage`. - An single item inside an `ItemStackHandler`.
- `RecipeWrapper` - A wrapper for `ItemStackHandler`. for recipe checks. Moved from `common.utility` to the `refabricated` package.

All of these classes are contained inside the `vectorwing.farmersdelight.refabricated.inventory` package.

Due to how Fabric's Transfer API works, you are unable to directly modify an obtained ItemStack from `ItemHandler#getStackInSlot` without running `ItemHandler#commitModifiedStacks` after you have made your changes.

## Closing Notes
If you're an addon dev and are struggling to update your mod, please reach out to me through the [Greenhouse Modding Discord](https://discord.greenhouse.house/) - Pug