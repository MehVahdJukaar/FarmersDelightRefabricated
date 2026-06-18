package vectorwing.farmersdelight.common.references;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ColorCollection;
import vectorwing.farmersdelight.FarmersDelight;

public class ModBlockIds {
	// Building
	public static final ResourceKey<Block> CANVAS_WALL_SIGN = key("canvas_wall_sign");
	public static final ColorCollection<ResourceKey<Block>> DYED_CANVAS_WALL_SIGN = createSimpleColored("canvas_wall_sign");

	public static final ResourceKey<Block> HANGING_CANVAS_WALL_SIGN = key("wall_hanging_canvas_sign");
	public static final ColorCollection<ResourceKey<Block>> DYED_HANGING_CANVAS_WALL_SIGN = createSimpleColored("wall_hanging_canvas_sign");

	// Pastries
	public static final ResourceKey<Block> PUMPKIN_PIE = key("pumpkin_pie");

	// Crops
	public static final ResourceKey<Block> BUDDING_TOMATO_CROP = key("budding_tomatoes");
	public static final ResourceKey<Block> TOMATO_CROP_ON_ROPE = key("tomatoes_on_rope");

	private static ColorCollection<ResourceKey<Block>> createSimpleColored(final String baseName) {
		return ColorCollection.prefixWithColor(ColorCollection.create(baseName)).map(ModBlockIds::key);
	}

	private static ResourceKey<Block> key(final String name) {
		return ResourceKey.create(Registries.BLOCK, FarmersDelight.id(name));
	}
}
