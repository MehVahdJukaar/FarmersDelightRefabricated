# Some Starting Notes

- If something is in all three versions, it will not be mentioned here.
- I'm not going to be mentioning bugs as differences, mainly because I feel it is unfair to compare unintended behaviour.
- I will also not be mentioning Farmer's Delight Refabricated's Skillet flipping here, because this is moreso a comparison to the original mod, I'll include this when the original has it.

To also note, the Decomposition REI integration in Farmer's Delight Fabric is bugged, it supports a block tag, however, it only shows one item from said block tag.

`*` means that it was introduced by Farmer's Delight 1.2.2 or above, which Farmer's Delight Fabric has not gotten upstream for.

# [Addons/Integrations](https://github.com/MehVahdJukaar/FarmersDelight/blob/1.20/information/Addons_And_Integrations.md)

# Content
| Feature | Farmer's Delight | Farmer's Delight Fabric | Farmer's Delight Refabricated |
|---------|------------------|-------------------------|-------------------------------|
|Bamboo/Cherry Cabinets`*`|✔|✘|✔|
|Canvas Hanging Signs`*`|✔|✘|✔|
|Cheaper Rope and New Rope/Straw Recipes`*`|✔|✘|✔|

# Gameplay
| Feature | Farmer's Delight | Farmer's Delight Fabric | Farmer's Delight Refabricated |
|---------|------------------|-------------------------|-------------------------------|
|Cooking Pot Serving Bar`*`|✔|✘|✔|

# Recipe Viewers
| Feature | Farmer's Delight | Farmer's Delight Fabric | Farmer's Delight Refabricated |
|---------|------------------|-------------------------|-------------------------------|
|Vanilla Recipe Book|✔|✘[`****`](https://modrinth.com/mod/recipe-book-delight)|✔|
|JEI|✔|✘[`****`](https://www.curseforge.com/minecraft/mc-mods/farmers-delight-jei-plugin)|✔|
|REI|✔`**`|✔|✔|
|EMI|✔`***`|✘[`****`](https://modrinth.com/mod/extra-mod-integrations)|✔|

`** Supported through JEI compat layer on Forge`

`*** Supported through JEI compat layer, some stuff may be missing.`

`**** Supported through an addon, click the link to find the addon.`

# Visual Changes
| Feature | Farmer's Delight | Farmer's Delight Fabric | Farmer's Delight Refabricated |
|---------|------------------|-------------------------|-------------------------------|
|Basket 1.20 Bamboo Consistency`*`|✔|✘|✔|
|Cooking Pot Meal Slot to Output Slot Arrow`*`|✔|✘|✔|
|Experience In Modded Recipe Viewer(s)`*`|✔|✘|✔|
|Skillet Model Contains Items|✔|✘|✔|
|Vanilla Consistent Cake Slice Texture`*`|✔|✘|✔|

## Codebase Compared to Farmer's Delight
| Farmer's Delight | Farmer's Delight Fabric | Farmer's Delight Refabricated |
|------------------|-------------------------|-------------------------------|
|The original.|Rewritten from scratch.|Only uses minor necessary rewrites compared to the original Farmer's Delight.|

### Pug Notes on This.
Keep in mind that I worked on Farmer's Delight Refabricated, which will impact my outlook on this subject, however, this'll serve as an explanation as to why it's best to work with minimal codebases when porting.

Having the minimal codebase for Farmer's Delight Refabricated is pretty much a result of making sure that we're always able to pull directly from the Forge version of the mod, this allows for far easier changes than Farmer's Delight Fabric, which had to reimplement Farmer's Delight's content, Farmer's Delight Fabric's approach lead to a lot of technical debt, and a lot more work when it came to actually updating, leading to the maintainers entirely burning out on the project.