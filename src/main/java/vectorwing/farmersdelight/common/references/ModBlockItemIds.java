package vectorwing.farmersdelight.common.references;

import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.ColorCollection;
import vectorwing.farmersdelight.FarmersDelight;

public class ModBlockItemIds {
	// Workstations
	public static final BlockItemId STOVE = id("stove");
	public static final BlockItemId COOKING_POT = id("cooking_pot");
	public static final BlockItemId SKILLET = id("skillet");
	public static final BlockItemId WOODEN_BASKET = id("wooden_basket");
	public static final BlockItemId BAMBOO_BASKET = id("bamboo_basket");
	public static final BlockItemId CUTTING_BOARD = id("cutting_board");

	// Crop Storage
	public static final BlockItemId CARROT_CRATE = id("carrot_crate");
	public static final BlockItemId POTATO_CRATE = id("potato_crate");
	public static final BlockItemId BEETROOT_CRATE = id("beetroot_crate");
	public static final BlockItemId CABBAGE_CRATE = id("cabbage_crate");
	public static final BlockItemId TOMATO_CRATE = id("tomato_crate");
	public static final BlockItemId ONION_CRATE = id("onion_crate");
	public static final BlockItemId RICE_BALE = id("rice_bale");
	public static final BlockItemId RICE_BAG = id("rice_bag");
	public static final BlockItemId STRAW_BALE = id("straw_bale");

	// Building
	public static final BlockItemId ROPE = id("rope");
	public static final BlockItemId SAFETY_NET = id("safety_net");
	public static final BlockItemId ROPE_FENCE = id("rope_fence");
	public static final BlockItemId ROPE_FENCE_GATE = id("rope_fence_gate");
	public static final BlockItemId OAK_CABINET = id("oak_cabinet");
	public static final BlockItemId SPRUCE_CABINET = id("spruce_cabinet");
	public static final BlockItemId BIRCH_CABINET = id("birch_cabinet");
	public static final BlockItemId JUNGLE_CABINET = id("jungle_cabinet");
	public static final BlockItemId ACACIA_CABINET = id("acacia_cabinet");
	public static final BlockItemId DARK_OAK_CABINET = id("dark_oak_cabinet");
	public static final BlockItemId MANGROVE_CABINET = id("mangrove_cabinet");
	public static final BlockItemId CHERRY_CABINET = id("cherry_cabinet");
	public static final BlockItemId BAMBOO_CABINET = id("bamboo_cabinet");
	public static final BlockItemId PALE_OAK_CABINET = id("pale_oak_cabinet");
	public static final BlockItemId POPLAR_CABINET = id("poplar_cabinet");
	public static final BlockItemId CRIMSON_CABINET = id("crimson_cabinet");
	public static final BlockItemId WARPED_CABINET = id("warped_cabinet");
	public static final BlockItemId CANVAS_RUG = id("canvas_rug");
	public static final BlockItemId TATAMI = id("tatami");
	public static final BlockItemId FULL_TATAMI_MAT = id("full_tatami_mat");
	public static final BlockItemId HALF_TATAMI_MAT = id("half_tatami_mat");

	public static final BlockItemId CANVAS_SIGN = id("canvas_sign");
	public static final ColorCollection<BlockItemId> DYED_CANVAS_SIGN = createSimpleColored("canvas_sign");

	public static final BlockItemId HANGING_CANVAS_SIGN = id("hanging_canvas_sign");
	public static final ColorCollection<BlockItemId> DYED_HANGING_CANVAS_SIGN = createSimpleColored("hanging_canvas_sign");

	// Composting
	public static final BlockItemId BROWN_MUSHROOM_COLONY = id("brown_mushroom_colony");
	public static final BlockItemId RED_MUSHROOM_COLONY = id("red_mushroom_colony");
	public static final BlockItemId ORGANIC_COMPOST = id("organic_compost");
	public static final BlockItemId RICH_SOIL = id("rich_soil");
	public static final BlockItemId RICH_SOIL_FARMLAND = id("rich_soil_farmland");

	// Pastries
	public static final BlockItemId APPLE_PIE = id("apple_pie");
	public static final BlockItemId SWEET_BERRY_CHEESECAKE = id("sweet_berry_cheesecake");
	public static final BlockItemId CHOCOLATE_PIE = id("chocolate_pie");

	// Wild Crops
	public static final BlockItemId SANDY_SHRUB = id("sandy_shrub");
	public static final BlockItemId WILD_CABBAGES = id("wild_cabbages");
	public static final BlockItemId WILD_ONIONS = id("wild_onions");
	public static final BlockItemId WILD_TOMATOES = id("wild_tomatoes");
	public static final BlockItemId WILD_CARROTS = id("wild_carrots");
	public static final BlockItemId WILD_POTATOES = id("wild_potatoes");
	public static final BlockItemId WILD_BEETROOTS = id("wild_beetroots");
	public static final BlockItemId WILD_RICE = id("wild_rice");

	// Crops
	public static final BlockItemId CABBAGE_CROP = id("cabbages", "cabbage_seeds");
	public static final BlockItemId ONION_CROP = id("onions", "onion");
	public static final BlockItemId TOMATO_CROP = id("tomatoes", "tomato_seeds");
	public static final BlockItemId RICE_CROP = id("rice");
	public static final BlockItemId RICE_CROP_PANICLES = id("rice_panicles", "rice_panicle");

	// Feasts
	public static final BlockItemId ROAST_CHICKEN_BLOCK = id("roast_chicken_block");
	public static final BlockItemId STUFFED_PUMPKIN_BLOCK = id("stuffed_pumpkin_block");
	public static final BlockItemId HONEY_GLAZED_HAM_BLOCK = id("honey_glazed_ham_block");
	public static final BlockItemId SHEPHERDS_PIE_BLOCK = id("shepherds_pie_block");
	public static final BlockItemId GLEAMING_SALAD_BLOCK = id("gleaming_salad_block");
	public static final BlockItemId RICE_ROLL_MEDLEY_BLOCK = id("rice_roll_medley_block");

	private static ColorCollection<BlockItemId> createSimpleColored(final String baseName) {
		return ColorCollection.prefixWithColor(ColorCollection.create(baseName)).map(ModBlockItemIds::id);
	}

	private static BlockItemId id(final String blockName, final String itemName) {
		Identifier blockId = FarmersDelight.id(blockName);
		Identifier itemId = FarmersDelight.id(itemName);
		return BlockItemId.create(blockId, itemId);
	}

	private static BlockItemId id(final String name) {
		return id(name, name);
	}
}
