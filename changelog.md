## Major Changes
- Removed Porting Lib as a dependency.
  - Parts of the mod have been rewritten because of this.

## Datapack Changes
- Due to Porting Lib's removal, `portinglib:can_item_perform_ability` no longer exists, and has been replaced with `farmersdelight:can_item_perform_ability`.
  - The options are also more limited for this. Feel free to PR more options if you need them.
- Added a new entity tag for Leather Dropping mobs. `farmersdelight:drops_leather`. Used with knives to make sure that leather dropping entities will drop an extra leather upon death.
- Added new mob effect tags for whether a mob effect is ignored by Milk Bottles and Hot Cocoa. These tags are:
  - `farmersdelight:ignored/hot_cocoa`
  - `farmersdelight:ignored/milk_bottle`
  - Hot Cocoa still follows the harmful effect ruling, Beneficial/Neutral effect clearing cannot be enabled for Hot Cocoa through these tags.

## Bugfixes
- Fixed a classloading issue preventing the ItemLike (Mojmap) interface from having mixins applied. [#77](https://github.com/MehVahdJukaar/FarmersDelightRefabricated/issues/77)
- Fixed Pumpkins being cut into slices when the Knife used to break the block has Silk Touch. [#141](https://github.com/MehVahdJukaar/FarmersDelightRefabricated/issues/141)