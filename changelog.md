# Changelog

## 1.4.0

### Additions
- New blocks:
  - Added a multitude of **storage blocks** for various farmable items. This includes:
    - Apple, Chorus Fruit, Brown Mushroom, Red Mushroom, Sweet Berries, Glow Berries, Cocoa Beans and Eggs;
- New items:
  - **Skewers!** The old Barbecue on a Stick has been converted into two separate items, to add variety to your barbecue:
    - **Meat Skewer**: Can be made from any 2 pieces of meat or fish. Can be either crafted raw to cook later, or crafted from cooked meats/fishes directly;
    - **Vegetable Skewer:**  A vegetarian variant, made from any 2 vegetables or mushrooms. Must be crafted raw and then cooked;
    - Raw skewers can be used near heat sources to roast them over it. It takes 6 seconds to fully roast, and you do so one-by-one;

### Updates
- Tweaked the Egg Sandwich texture slightly;

### Fixes
- Fix broken Skillet interactions when swapping slots while cooking an item;
  - Due to this fix, the Skillet no longer "stores" an ingredient within itself on handheld mode. The ingredient is only subtracted once cooking finishes;

## 1.3.3

### Fixes
- Fixed Rope reeling not working if the rope is touching the void layer (minimum build height);
- Fixed suppressed packet error due to an unsafe property check in TomatoBlock;
- Fixed Canvas Signs not keeping text color when NBT-copied (such as CTRL + middle-click);