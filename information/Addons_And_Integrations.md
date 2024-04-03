This is a comprehensive list of supported addons, integrations as well as other bits of content relating to Farmer's Delight Fabric or Refabricated.

The provided links through the mod names will be the Modrinth pages for each individual mod, or the CurseForge page if there is no Modrinth page.

`*` means that Refabricated partially supports it through its compat layer. This may be because of mixins into the other codebase, or other bits of content that cannot realistically be handled through Refabricated.

# Addons
|Mod Name|Farmer's Delight Fabric|Farmer's Delight Refabricated|Comments|
|---|---|---|---|
|[Autochef's Delight](https://modrinth.com/mod/autochefs-delight)|Always|Potentially planned?|There are branches for `fabric` and `nhoryzon`
|[Brewin' And Chewin' (Fabric)](https://modrinth.com/mod/brewin-and-chewin-fabric)|Always|Planned|Dev will get to it, MrSterner_ has just been busy.
|[Cake Delight](https://modrinth.com/mod/cakedelight)|Always|?|No Contact
|[Casualness Delight](https://modrinth.com/mod/casualness-delight)|Always|?|[Linked GitHub Issue](https://github.com/TsukimiRen/Casualness-Delight/issues/11)
|[Coffee Delight](https://modrinth.com/mod/coffee-delight)|Always|?|No Contact
|[Corn Delight [Fabric/Quilt]](https://legacy.curseforge.com/minecraft/mc-mods/corn-delight-fabric)|Always|?|No Contact
|[Crate Delight](https://modrinth.com/mod/crate-delight)|Always|Always|
|[Chef's Delight](https://modrinth.com/mod/chefs-delight)|Always|Supported through compat layer|Planned|Was blocked by the class structure being different, now wants to multiloader the mod.
|[Create Central Kitchen](https://modrinth.com/mod/create-central-kitchen/)|No|Planned|Previously blocked by Farmer's Delight Fabric not using the same package names, planning on going Multiloader.|
|[Create: Food](https://modrinth.com/mod/create-food/)|Always|?|No Contact
|[Create's Delight](https://modrinth.com/mod/creates-delight)|Always|?|No Contact
|[Cultural Creators](https://legacy.curseforge.com/minecraft/mc-mods/cultural-creators-fabric-create-and-cultural/)|Always|?|No Contact
|[Cultural Delights (Fabric)](https://modrinth.com/mod/cultural-delights-fabric)|Always|?|Same as Brewin' And Chewin' (Fabric).
[Delightful Creators](https://modrinth.com/mod/delightful-creators-fabric/)|Always|?|No Contact
|[End's Delight](https://modrinth.com/mod/ends-delight/)|Has specific FDF versions|Has specific FDRF versions|
|[Expanded Delight](https://modrinth.com/mod/expanded-delight)|Always|Supported through compat layer|
|[Fabric Seasons: Delight Compat](https://modrinth.com/mod/fabric-seasons-delight-compat)|Always|Supported through compat layer `*`|The developer has been contacted, but has not responded.|
|[Farmer's Knives](https://modrinth.com/mod/farmers-knives)|Always|Supported through compat layer|
|[Farmer's Respite (Fabric)](https://modrinth.com/mod/farmers-respite-fabric)|Always|Planned|Same as Brewin' And Chewin' (Fabric).
|[Fright's Delight](https://modrinth.com/mod/frights-delight/version/fabric-1.20.1-1.0.2)|Always|As of 1.0.2|
|[Just Enough Farmer's Recipes](https://legacy.curseforge.com/minecraft/mc-mods/farmers-delight-jei-plugin)|Always|No|JEI is supported by default in Refabricated.
|[Kebab's Delight](https://legacy.curseforge.com/minecraft/mc-mods/kebabs-delight)|Always|?|No Contact
|[More Delight](https://modrinth.com/mod/more-delight)|Always|Supported through compat layer| 
|[Nether's Delight (Fabric)](https://legacy.curseforge.com/minecraft/mc-mods/nethers-delight-fabric/)|Always|Supported through compat layer|
|[Ocean's Delight](https://modrinth.com/mod/oceans-delight)|Has specific FDF version|Has specific FDRF versions|
|[Recipe Book Delight](https://modrinth.com/mod/recipe-book-delight)|Always|No|The Recipe Book is supported by default in Refabricated.
|[Respite Creators](https://modrinth.com/mod/respite-creators-fabric)|Always|?|No Contact
|[Shakshuka Delight](https://modrinth.com/mod/shakshuka-delight)|Always|?|No Contact
|[Ube's Delight](https://modrinth.com/mod/ubes-delight)|Always|Planned|ChefMooon has stated directly to Jukaar that they'll be supporting Refabricated.

# Integrations
|Mod Name|Farmer's Delight Fabric|Farmer's Delight Refabricated|Comments|
|---|---|---|---|
[EMI Addon: Extra Mod Integrations](https://modrinth.com/mod/extra-mod-integrations)|Always|No|EMI is supported by default in Refabricated. Does not crash with Refabricated >2.0.13 since 0.4.4.
|[Every Compat (Wood Good)](https://modrinth.com/mod/supplementaries/)|Prior to 2.6.40|Since 2.6.40|
|[Fruitful Fun](https://modrinth.com/mod/fruitful-fun)|Always|Since 7.3.1|
|[Hearty Meals](https://modrinth.com/mod/hearty-meals/)|Always|Since 1.20-6|
|[Supplementaries](https://modrinth.com/mod/supplementaries/)|Prior to 2.8.8|Since 2.8.8|

# Checking Which Farmer's Delight Fabric Port You Have.
As of 1.20.1-2.0.13+refabricated, you are able to check which version of Farmer's Delight you have by utilising a version check like so.

This is the safest way to do so, because codebase changes to Refabricated or Fabric will not be affected.

You can do whatever with this, you can crash the client, you can disable/enable certain things, whatever floats your boat...
```java
public static boolean isFDRefabricated() {
    // Use Objects#equals to make sure it's null safe for Farmer's Delight Fabric, which should not contain a +.
    return FabricLoader.getInstance().getModContainer("farmersdelight").map(container -> Objects.equals(container.getMetadata().getVersion().getFriendlyString().split("\\+")[1], "refabricated")).orElse(false);
}
```