- Update `es_mx` lang file. ([#15](https://github.com/MehVahdJukaar/FarmersDelight/pull/15) - TheLegendOfSaram) 
- Include `+refabricated` in the version schema to allow for an easier time detecting which Farmer's Delight port is loaded.
    - Modrinth version numbers are unaffected by this, but maven versions are.

Below contains an example of how you may detect Farmer's Delight Refabricated following this change.
```java
public static boolean isFDRefabricated() {
    // Use Objects#equals to make sure it's null safe for Farmer's Delight Fabric, which should not contain a +.
    return FabricLoader.getInstance().getModContainer("farmersdelight").map(container -> Objects.equals(container.getMetadata().getVersion().getFriendlyString().split("\\+")[1], "refabricated")).orElse(false);
}
```