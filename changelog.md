# Changelog
- Updated to match upstream 1.3.2. Changes are below.
- Fixed Knives being unable to mine Bamboo instantly. [#288](https://github.com/MehVahdJukaar/FarmersDelightRefabricated/issues/288)
- Fixed Colored Canvas Signs not rendering properly. [#302](https://github.com/MehVahdJukaar/FarmersDelightRefabricated/issues/302)
- Fixed Wild Crops being unable to grow on Grass Blocks, Mud Blocks and Moss Blocks. [#314](https://github.com/MehVahdJukaar/FarmersDelightRefabricated/issues/314)
- Fixed Copper Knives being missing from the Knives tag. [#317](https://github.com/MehVahdJukaar/FarmersDelightRefabricated/issues/317)
- Fixed Root Farmer's Delight advancement having a missing translation key. [#318](https://github.com/MehVahdJukaar/FarmersDelightRefabricated/issues/318)
- Fixed Legacy Baskets not being corrected into Bamboo Baskets. [#319](https://github.com/MehVahdJukaar/FarmersDelightRefabricated/issues/319)
- Fixed Comfort advancement still existing after Comfort's removal. [#326](https://github.com/MehVahdJukaar/FarmersDelightRefabricated/issues/326)
- Fixed Gleaming Salad not counting towards the Place Feast advancement. [#327](https://github.com/MehVahdJukaar/FarmersDelightRefabricated/issues/327)
- Fixed Tomato on Rope Block interactions when removed.

## 1.3.2

### Updates
- Fried Rice and Chicken Soup have been nerfed down to the 3-minute tier of meals, due to their ease of cooking;
- Rice Bag has been removed from the `straw_blocks` tag, due to not being actually made with Straw.
  - Instead, it has been directly added to `mineable/knife`, which is the main use of the tag above.
- Added fuel values to the following items:
  - Straw Bale: Smelts 5 items;
  - Rope Fence and Rope Fence Gate: Smelts 1 item each;
- (1.21+) Added Sable weight tagging to FD blocks which aren't covered by default tagging;
- (1.21+) TomatoBlock now has broader compatibility checks for integration with different supports (thanks, MehVahdJukaar!);
- (1.21+) Fuel values have been moved into a NeoForge datamap, `furnace_fuels.json`:
  - The `FuelItem` and `FuelBlockItem` classes have been deprecated, and will be removed in the next major/minor update;

### Fixes
- Fixed occasional crash with a tag reference load order;
- (1.21+) Fixed Rope Fences not being able to hold leashed animals in some circumstances;
- (1.21+) Fixed Pumpkins having inverted slicing logic with the Silk Touch enchantment;
- (1.21+) Fixed Cutting Board crash when the Fortune enchantment is disabled through datapacks;

### Translations
- Added:
  - bg_bg (thanks, OmegaSleepy and friends!);
- Updated:
  - es_ar (thanks, MilanesaGG!);
  - ja_jp (thanks, Abbage230!);
  - ko_kr (thanks, Kardane!);
  - ru_ru (thanks, mpustovoi!);
  - zh_cn, zh_tw, zh_hk and lzh;
    - Thanks to BoredYukolin, deemsss2, MechtaSnezhevna and Kaohaaa for this translation batch!;