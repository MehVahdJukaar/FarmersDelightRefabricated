# Changelog
- Updated to match upstream version 1.21.1-1.3.0, please read the below. (MehVahdJukaar, ChrysanthCow).
- Farmer's Delight Refabricated now requires loader version 0.19.0 or higher, and must be depended on using Fabric Loom 1.16 or higher.
  - This is due to the below change.
- Replaced Fabric ASM as a dependency with a enum extending class tweaker.
- Moved Farmer's Delight specific tags to `vectorwing.farmersdelight.refabricated.FDRefabricatedTags` package. The original fields are now deprecated and redirect over to this class.
  - The deprecated fields will be removed when the `26.1` branch is updated.
- Swapped out the Farmer's Delight Refabricated config lang entries to use the same lang entries as Farmer's Delight NeoForge.
- Removed the `farmersdelight:survives/*` and `farmersdelight:does_not_survive/*` block tags in favor of vanilla's supports plant block tags.
- Added `farmersdelight:grows_mushroom_colonies` block tag, deprecating the original `farmersdelight:mushroom_colonies_growable_on` tag.

*Minor note: This version is unrelated to any of the latest branches' 3.3.0, treat versioning as separate between branches.*

## 1.3.0

### Additions
- New items:
  - **Onion Soup** - A simple dish made with Onions, Milk and Bread;
- New blocks:
  - **Gleaming Salad** - A salad that shines like a Lush Cave! Made with fresh Glow Berries and a rich selection of vegetables, it will (literally) illuminate your dinner table;
  - **Rope Fence** - A thin and sleek flavor of fence, complete with its own **Rope Fence Gate**!
    - Crafted by replacing planks with rope in the standard fence and fence gate recipes;
  - The **Basket** has gained a **wooden variant**, made with Sticks instead of Bamboo! It functions exactly the same as the existing Basket:
    - This was done to make the block easier to obtain; Bamboo has unpredictable/unreliable world gen conditions, so locking it behind this material felt pointless;
    - The old Basket has been renamed to **Bamboo Basket**, and can still be crafted, using Bamboo instead of Sticks;
    - Both blocks can be salvaged for Canvas and scraps on the Cutting Board, using an axe;
- Pumpkin Pie can now be **placed as a block**, like the other pies!
  - By default, it is placeable without secondary action. A config exists to require sneaking, if preferred;
- Farmer's Delight now has a native **EMI plugin**! (thanks, ChrysanthCow!)
  - It works similarly to the built-in JEI plugin, but offering compatibility with EMI's unique features, such as chances and recipe trees;
  - With it, tags have been integrated into translation files. Both JEI and EMI now use them to display tags in-game.
  - Default recipes have been configured for most FD recipes. I'll try to keep this updated for pack makers, but I may forget now and then.
- (1.21+) You can now edit the mod's configuration from in-game (thanks, TheGridExpert!):
  - From the main screen, click "Mods", select Farmer's Delight, and click the "Config" button at the bottom left to open the config screen;
  - All config options are now translatable, and changes will take effect right away, without the need to relaunch the game;
- New block tags:
  - `farmersdelight:planted_from_below`: Plants which are rooted beneath the soil, rather than above. Includes Cave Vines;
  - `farmersdelight:feasts`: Blocks which represent a placeable feast;
  - `farmersdelight:pies`: Blocks which represent a placeable pie;
- New item tags:
  - `farmersdelight:snacks`: Items which represent a multi-ingredient food which doesn't need a container (backported from 1.21.1);
  - `farmersdelight:sweets`: Items which represent sweet prepared foods, usually desserts;
  - `farmersdelight:pies`: Items which represent a whole pie;

### Updates
- The Cutting Board has been redesigned:
  - It can now hold a **full stack of items**, instead of one at a time. This allows players to process their stacks in half the time it took before;
  - If the board's stack isn't full yet, players can top it up with more items;
  - When cutting, the status bar will show how many items are left on the board;
  - Comparator signal will now be based on the fill percentage for the item's max stack size;
  - All off-hand interaction has been removed. Placing, removing and processing items is done entirely with the main hand;
- The Nourishment effect has been updated:
  - The effect will no longer pause to consume hunger, only saturation. This means Nourishment can grant indefinite slow healing, so long as the bar itself is full;
  - Players are now able to "always eat" when under Nourishment, letting them get more saturation to rapid-heal if they want;
- The Comfort effect has been retired, as Nourishment now fulfills its role:
  - All foods which previously granted Comfort now grant an equivalent amount of Nourishment instead;
  - The effect is still registered in-game to avoid add-ons breaking, but it will be removed in the next major/minor version of the mod;
- The Skillet's handheld functions have been updated (thanks to MehVahdJukaar, ChrysanthCow and the Refabricated team!):
  - The Skillet can render foods in handheld mode again;
  - When cooking handheld, the durability meter will become an orange "frying meter", showing you the cooking progress;
  - When cooking handheld, you can press the "Attack" button to toss and flip the food inside the skillet. This has no gameplay effect, it's just for fun!;
- Rich Soil can now boost plants which are `planted_from_below`:
  - Any bonemealable plant tagged as such will only receive boosts if planted below Rich Soil. Otherwise, they will only be boosted from the top side;
  - Rich Soil prioritizes boosting a plant above before boosting a plant below, so the bottom plant will only be chosen if the one above cannot be boosted further;
- Tomato crops have been updated:
  - Instead of a blockstate, grown tomato vines are now divided between `tomatoes` (old block) and `tomatoes_on_rope` (new):
    - This was done to fix broken behaviours with vanilla bees, modded autofarming solutions and other things. It should be more resilient to bugs now (hopefully)!;
    - Existing `tomatoes` with `ropelogged=true` will have a "pale" appearance, and convert into `tomatoes_on_rope` on random ticks;
    - Ropes left behind by `tomatoes_on_rope` should now properly update their connections when placed;
  - The crop no longer uses the standard growth speed checks of `CropBlock`, which was slowing down hanging vines. They grow at a fixed rate now, regardless of height;
  - Applying bone meal to a mature tomato vine (sneaking, rich soil etc) will pass the boost to the vine above it, if possible;
  - Tomato seeds can now be planted on any farmland block;
- Rope has been updated:
  - When placing Rope horizontally, it will try to connect to any solid faces horizontally, but won't reattach if they're modified;
  - When placing Rope vertically, or when deploying it from one spot, it won't connect to side faces (loose rope), except for other ropes and bars;
- Feasts and Pies have been updated:
  - All Feast models received a visual update, with new textures/models, proper cullfaces and UV optimization;
  - They will now emit crumb particles when taking portions from them;
  - They now have more precise hitboxes, matching their shapes as they are consumed (thanks, TheGridExpert!);
  - They now require a fully solid surface to be placed on, instead of floating over blocks like Cakes;
  - They now have "Placeable" in their tooltip, to better indicate their role;
    - Pumpkin Pie will say "Placeable when sneaking" if the sneak-to-place config is enabled;
    - Skillets will also display the above tooltip;
- Wild Crops have been updated:
  - Wild Tomatoes now check for the `farmersdelight:terrain` tag when generating;
  - Wild Rice now checks for the `minecraft:dirt` tag when generating;
- The following recipes were updated:
  - Kelp Rolls now accept any `forge:vegetables` (1.20.1) or `c:foods/vegetable` (1.21+) as filling, instead of just Carrot;
  - Vegetable Noodles now accept any mushroom;
  - Dog Food can now be prepared with any `forge:raw_meat` (1.20.1) or `c:foods/raw_meat` (1.21+), both of which exclude raw fish;
  - Hoes can now pry blocks out of vehicles (Minecarts and Boats) on a Cutting Board;
  - Cutting recipes now check for both a tag and a "tool action" (1.20.1) or "item ability" (1.21+), broadening compatibility with modded tools;
  - Cutting recipes where you "salvage" items are now condensed together, making the Cutting recipe list less bloated in item viewers:
    - Wood furniture salvaging became a single `<wood>_furniture` recipe, includes several new items, and has a 75% chance to return a unit of that plank type;
- Knives now have tool actions (1.20.1), or item abilities (1.21+):
  - `knife_dig`: Checked together with the `forge:tools/knives` tag for crafting (Cutting Board);
  - `knife_harvest`: Checked together with the `farmersdelight:tools/knives` tag for gameplay actions;
- All sounds in FD should now have accurate subtitles. Recently added:
  - `food.take_portion`: Used when blocks provide food when used (Cooking Pot, Feasts etc);
  - `food.slice`: Used when sweet food blocks, such as Pies, are sliced with a Knife;
- Farmer Villagers can now detect Rich Soil Farmland as a valid point of interest for crop harvesting (thanks, MehVahdJukaar!);
- Mushroom Colonies can now be snipped all at once when using a Knife on them;
- Stoves now only inflict burn in a small "grilling area" on top of them; the edges are safe to step on;
- Zombies and Illagers now have a very small chance of dropping an Onion when killed by a player, similar to Carrots and Potatoes;
- Cats can now be tamed with Salmon Slice and Cod Slice;
- Various block and item textures were retouched here and there;

### Fixes
- Fix container items spilling into the ingredient slots when shift-clicking an excess amount into the Cooking Pot's container slot;
- (1.21+) Fix Skillet crashing when placed, if the server has disabled/removed the Fire Aspect enchantment;
- (1.21+) Fix Rich Soil not emitting bone meal particles when boosting plants;
- (1.21+) Fix Rope being non-extendable when hanging under a Bell;

### Removals
- The following tags have been removed:
  - `cabbage_roll_ingredients`: Replaced by an array of equivalent tags: `raw_meat`, `raw_fishes`, `mushrooms` and `vegetables`;
  - `offhand_equipment`: Deprecated with the new Cutting Board mechanics;
  - `wolf_prey`: Replaced by `forge:raw_meat` when making Dog Food;
  - (1.21+) `c:foods/milk`: Deprecated in favor of `c:drinks/milk`;

### Translations
- Updated:
  - id_id (thanks, barabestfriend!);
  - ru_ru (thanks, TheGridExpert!);
  - uk_ua (thanks, TheGridExpert!);
  - zh_cn (thanks, Artoria2e5!);
  - zh_tw (thanks, DEEMsss!);

### Technical
- Added data generation for most things which were still manually written (thanks, GizmoTheMoonPig and Abbie5!);
- The Stove now has a pair of "abstract" classes, which are extendable for defining your own stove (thanks, LordFirespeed!);
- All models have been standardized, cleaned up and optimized across the board:
  - If you make a resource pack for FD, you may have to update to conform to these changes;
- FD's internal recipe builders have been updated, and should now be more usable for add-on developers:
  - They now implement `RecipeBuilder`, which standardizes several methods in regards to file saving and naming (thanks, Lance5057!);
  - Added `setNamespace(string)` to both builders, to allow users to specify a custom namespace (mod ID) for the recipe. Use it if the result isn't registered under your mod ID;
  - The `save(consumer)` method override no longer hardcodes FD's mod ID in the recipe. Instead, it will use either the result's namespace by default, or a value set through `setNamespace(string)`;
    - Thanks to LordFirespeed for helping me realize the issue with the method override!;
- FD's recipes now call for `assemble()` in most places, allowing extenders of most workstations to use the inventory when creating their result (thanks, ColonelPanic!);
- Cutting recipes now accept arrays of ingredients in the `tool` field (thanks, BobVarioa!);
- ConsumableItem's constructor now defaults the `hasFoodEffectTooltip` parameter to `true`, as nearly all cases in vanilla FD had it enabled. This may affect add-ons which do not explicitly set it;
- Food blocks, such as Pies and Feasts, now have new class overrides for when their VoxelShapes can rotate horizontally (thanks, TheGridExpert!):
  - `RotatedFeastBlock` is an extension of `FeastBlock` for feasts with directional consumption shapes. You provide an array of shapes, and `ShapeUtils` will calculate and cache rotations for them;
  - If your feast's hitbox does not change horizontally as servings are taken (example: Stuffed Pumpkin), you can still use `FeastBlock`.
- The following model files were renamed, to make it clearer they're meant to be templates:
  - `bush_crop` -> `template_bush_crop`
  - `crop_cross` -> `template_crop_cross`;
  - `crop_with_rope` -> `template_crop_with_rope`;
  - `pie` -> `template_pie`;
  - `pie_slice[1-3]` -> `template_pie_slice[1-3]`;
