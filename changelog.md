## Compatibility
- Added now hardcoded Wheat Dough from Water recipe to REI and EMI compatibility.

## Bugfixes
- Fixed loot modifications (primarily for Chest Loot and Knife Loot) not running if a data pack has changed the table.
  - If there is demand for it, we may add a way to disable the Knife loot changes through config.
- Fixed Generate Farmer's Delight Chest Loot config option not being respected.
- Fixed Villager Trades duplicating when relogging into singleplayer worlds.

## Development
- Re-implemented datagen classes for addon developers to extend whilst using Fabric Datagen and for parity with latest branches.
- Updated internal Fabric API to 0.116.7+1.21.1.