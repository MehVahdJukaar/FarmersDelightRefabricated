- Include `+refabricated` in the version schema to allow for an easier time detecting which Farmer's Delight port is loaded.

Below contains an example of how you may detect Farmer's Delight Refabricated following this change.
```java
public static boolean isFDRefabricated() {
    return FabricLoader.getInstance().getModContainer("farmersdelight").map(container -> container.getMetadata().getVersion().getFriendlyString().split("\\+")[1].contains("refabricated")).orElse(false);
}
``` 