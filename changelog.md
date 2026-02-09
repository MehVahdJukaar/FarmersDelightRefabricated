Merged Farmer's Delight v1.2.10, detailed below:
### Updates
- The JEI widget for the Cooking Pot now has its own image file, to allow custom widget editing;
  - The UI icons are still located in the main Cooking Pot UI file;
- Villagers can now consume FD crops to become willing to breed (thanks, isErenG!);
- (1.21+) Tag changes:
  - New mod tags:
    - `farmersdelight:snacks`: Non-sweet foods made of multiple ingredients, which don't need containers;
    - `farmersdelight:sweets`: Sweet foods made of multiple ingredients, such as desserts;
  - Properly tagged all FD foods inside `c:foods`, as well as adding them to relevant sub-tags:
    - Wheat Dough, Raw Pasta and Nether Salad were added to `c:foods/food_poisoning`;
    - All pie slices were added to `c:foods/pie`. The full pies already exist in `c:foods/edible_when_placed`;
  - Added `c:foods/dough/wheat` to accomodate Wheat Dough within `c:foods/dough`, as per NeoForge specs;
    - FD recipes still use `c:foods/dough`, as any type of grain is allowed;
  - `c:foods/milk` is being sunsetted in favor of the official `c:drinks/milk` tag;
    - All FD recipes now use `c:drinks/milk`;
    - `c:foods/milk` still exists for backwards compatibility, but will be removed in the next minor release.

### Fixes
- Fixed `foodEffectTooltip` config not affecting the tooltip of vanilla foods with FD effect overrides;
- Fixed Cutting Board playing sounds at the zero corner, instead of at the center of itself;
- Fixed non-contained meals (example: Dumplings) being deleted if sitting on the meal slot, and using the same meal against the pot to serve it (thanks, VBlackCAT!);
- (1.21+) Create Integration - Fixed Chocolate Pie spouting recipe to use correct fluid tag;

### Translations
- Added:
  - kk_kz (thanks, ninsent!);
  - zh_hk (thanks, Duppy-Conqueror!);
- Updated:
  - hu_hu (thanks, bayi!);
  - ja_jp (thanks, Abbage230!);
  - lzh (thanks, BeiDou114514!);
  - ru_ru (thanks, pansangg!);
