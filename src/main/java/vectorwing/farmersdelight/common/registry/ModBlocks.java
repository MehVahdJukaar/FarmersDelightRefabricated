package vectorwing.farmersdelight.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jspecify.annotations.Nullable;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.BlockShapes;
import vectorwing.farmersdelight.common.block.*;
import vectorwing.farmersdelight.common.references.ModBlockIds;
import vectorwing.farmersdelight.common.references.ModBlockItemIds;
import vectorwing.farmersdelight.refabricated.RegUtils;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class ModBlocks
{
	private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
		return (state) -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
	}

	private static ToIntFunction<BlockState> glowingFeastBlockEmission() {
		return (state) -> state.getValue(FeastBlock.SERVINGS) * 3;
	}

	private static ResourceKey<LootTable> lootTableKey(String path) {
		return ResourceKey.create(Registries.LOOT_TABLE, FarmersDelight.id("blocks/"+path));
	}

	private static Supplier<Block> regCanvasSign(BlockItemId id, @Nullable DyeColor color) {
		return regBlock(id, properties -> new StandingCanvasSignBlock(properties, color), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SIGN));
	}

	private static Supplier<Block> regWallCanvasSign(ResourceKey<Block> key, @Nullable DyeColor color) {
		String basePath = (color != null ? (color + "_") : "") + "canvas_sign";
		return regBlock(key, properties -> new WallCanvasSignBlock(properties, color), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SIGN)
				.overrideLootTable(Optional.of(lootTableKey(basePath))));
	}

	private static Supplier<Block> regHangingCanvasSign(BlockItemId id, @Nullable DyeColor color) {
		return regBlock(id, properties -> new CeilingHangingCanvasSignBlock(properties, color), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_HANGING_SIGN));
	}

	private static Supplier<Block> regWallHangingCanvasSign(ResourceKey<Block> key, @Nullable DyeColor color) {
		String basePath = (color != null ? (color + "_") : "") + "hanging_canvas_sign";
		return regBlock(key, properties -> new WallHangingCanvasSignBlock(properties, color), BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_HANGING_SIGN)
				.overrideLootTable(Optional.of(lootTableKey(basePath))));
	}

	private static Supplier<Block> regBlock(final ResourceKey<Block> key, final Function<BlockBehaviour.Properties, Block> function, final BlockBehaviour.Properties properties) {
		properties.setId(key);
		return RegUtils.regBlock(key, () -> function.apply(properties));
	}

	private static Supplier<Block> regBlock(final BlockItemId id, final Function<BlockBehaviour.Properties, Block> function, final BlockBehaviour.Properties properties) {
		return regBlock(id.block(), function, properties);
	}

	// Workstations
	public static final Supplier<Block> STOVE = regBlock(ModBlockItemIds.STOVE,
			StoveBlock::new, Block.Properties.ofFullCopy(Blocks.BRICKS).lightLevel(litBlockEmission(13)));
	public static final Supplier<Block> COOKING_POT = regBlock(ModBlockItemIds.COOKING_POT,
			CookingPotBlock::new, Block.Properties.of().mapColor(MapColor.METAL).strength(0.5F, 6.0F).sound(SoundType.LANTERN));
	public static final Supplier<Block> SKILLET = regBlock(ModBlockItemIds.SKILLET,
			SkilletBlock::new, Block.Properties.of().mapColor(MapColor.METAL).strength(0.5F, 6.0F).sound(SoundType.LANTERN));
    public static final Supplier<Block> WOODEN_BASKET = regBlock(ModBlockItemIds.WOODEN_BASKET,
            BasketBlock::new, Block.Properties.of().strength(1.5F).sound(SoundType.WOOD));
    public static final Supplier<Block> BAMBOO_BASKET = regBlock(ModBlockItemIds.BAMBOO_BASKET,
			BasketBlock::new, Block.Properties.of().strength(1.5F).sound(SoundType.BAMBOO_WOOD));
	public static final Supplier<Block> CUTTING_BOARD = regBlock(ModBlockItemIds.CUTTING_BOARD,
			CuttingBoardBlock::new, Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F).sound(SoundType.WOOD));

	/**
	 * Deprecated reference added for backwards compatibility. Use BAMBOO_BASKET instead.
	 */
	@Deprecated(forRemoval = true)
	public static final Supplier<Block> BASKET = BAMBOO_BASKET;

	// Crop Storage
	public static final Supplier<Block> CARROT_CRATE = regBlock(ModBlockItemIds.CARROT_CRATE,
			Block::new, Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final Supplier<Block> POTATO_CRATE = regBlock(ModBlockItemIds.POTATO_CRATE,
			Block::new, Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final Supplier<Block> BEETROOT_CRATE = regBlock(ModBlockItemIds.BEETROOT_CRATE,
			Block::new, Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final Supplier<Block> CABBAGE_CRATE = regBlock(ModBlockItemIds.CABBAGE_CRATE,
			Block::new, Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final Supplier<Block> TOMATO_CRATE = regBlock(ModBlockItemIds.TOMATO_CRATE,
			Block::new, Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final Supplier<Block> ONION_CRATE = regBlock(ModBlockItemIds.ONION_CRATE,
			Block::new, Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).strength(2.0F, 3.0F).sound(SoundType.WOOD));
	public static final Supplier<Block> RICE_BALE = regBlock(ModBlockItemIds.RICE_BALE,
			RiceBaleBlock::new, Block.Properties.ofFullCopy(Blocks.HAY_BLOCK));
	public static final Supplier<Block> RICE_BAG = regBlock(ModBlockItemIds.RICE_BAG,
			Block::new, Block.Properties.ofFullCopy(Blocks.WOOL.white()));
	public static final Supplier<Block> STRAW_BALE = regBlock(ModBlockItemIds.STRAW_BALE,
			StrawBaleBlock::new, Block.Properties.ofFullCopy(Blocks.HAY_BLOCK));

	// Building
	public static final Supplier<Block> ROPE = regBlock(ModBlockItemIds.ROPE,
			RopeBlock::new, Block.Properties.ofFullCopy(Blocks.CARPET.brown()).noCollision().noOcclusion().strength(0.2F).sound(SoundType.WOOL));
	public static final Supplier<Block> SAFETY_NET = regBlock(ModBlockItemIds.SAFETY_NET,
			SafetyNetBlock::new, Block.Properties.ofFullCopy(Blocks.CARPET.brown()).strength(0.2F).sound(SoundType.WOOL).bounceRestitution(0.6F));
	public static final Supplier<Block> ROPE_FENCE = regBlock(ModBlockItemIds.ROPE_FENCE,
			RopeFenceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE).strength(1.0F));
	public static final Supplier<Block> ROPE_FENCE_GATE = regBlock(ModBlockItemIds.ROPE_FENCE_GATE,
			RopeFenceGateBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE).strength(1.0F));
	public static final Supplier<Block> OAK_CABINET = regBlock(ModBlockItemIds.OAK_CABINET,
			CabinetBlock::new, Block.Properties.ofFullCopy(Blocks.BARREL));
	public static final Supplier<Block> SPRUCE_CABINET = regBlock(ModBlockItemIds.SPRUCE_CABINET,
			CabinetBlock::new, Block.Properties.ofFullCopy(Blocks.BARREL));
	public static final Supplier<Block> BIRCH_CABINET = regBlock(ModBlockItemIds.BIRCH_CABINET,
			CabinetBlock::new, Block.Properties.ofFullCopy(Blocks.BARREL));
	public static final Supplier<Block> JUNGLE_CABINET = regBlock(ModBlockItemIds.JUNGLE_CABINET,
			CabinetBlock::new, Block.Properties.ofFullCopy(Blocks.BARREL));
	public static final Supplier<Block> ACACIA_CABINET = regBlock(ModBlockItemIds.ACACIA_CABINET,
			CabinetBlock::new, Block.Properties.ofFullCopy(Blocks.BARREL));
	public static final Supplier<Block> DARK_OAK_CABINET = regBlock(ModBlockItemIds.DARK_OAK_CABINET,
			CabinetBlock::new, Block.Properties.ofFullCopy(Blocks.BARREL));
	public static final Supplier<Block> MANGROVE_CABINET = regBlock(ModBlockItemIds.MANGROVE_CABINET,
			CabinetBlock::new, Block.Properties.ofFullCopy(Blocks.BARREL));
	public static final Supplier<Block> CHERRY_CABINET = regBlock(ModBlockItemIds.CHERRY_CABINET,
			CabinetBlock::new, Block.Properties.ofFullCopy(Blocks.BARREL).sound(SoundType.CHERRY_WOOD));
	public static final Supplier<Block> BAMBOO_CABINET = regBlock(ModBlockItemIds.BAMBOO_CABINET,
			CabinetBlock::new, Block.Properties.ofFullCopy(Blocks.BARREL).sound(SoundType.BAMBOO_WOOD));
	public static final Supplier<Block> PALE_OAK_CABINET = regBlock(ModBlockItemIds.PALE_OAK_CABINET,
			CabinetBlock::new, Block.Properties.ofFullCopy(Blocks.BARREL));
	public static final Supplier<Block> CRIMSON_CABINET = regBlock(ModBlockItemIds.CRIMSON_CABINET,
			CabinetBlock::new, Block.Properties.ofFullCopy(Blocks.BARREL).sound(SoundType.NETHER_WOOD));
	public static final Supplier<Block> WARPED_CABINET = regBlock(ModBlockItemIds.WARPED_CABINET,
			CabinetBlock::new, Block.Properties.ofFullCopy(Blocks.BARREL).sound(SoundType.NETHER_WOOD));
	public static final Supplier<Block> CANVAS_RUG = regBlock(ModBlockItemIds.CANVAS_RUG,
			CanvasRugBlock::new, Block.Properties.ofFullCopy(Blocks.CARPET.white()).sound(SoundType.GRASS).strength(0.2F));
	public static final Supplier<Block> TATAMI = regBlock(ModBlockItemIds.TATAMI,
			TatamiBlock::new, Block.Properties.ofFullCopy(Blocks.WOOL.white()));
	public static final Supplier<Block> FULL_TATAMI_MAT = regBlock(ModBlockItemIds.FULL_TATAMI_MAT,
			TatamiMatBlock::new, Block.Properties.ofFullCopy(Blocks.WOOL.white()).strength(0.3F));
	public static final Supplier<Block> HALF_TATAMI_MAT = regBlock(ModBlockItemIds.HALF_TATAMI_MAT,
			TatamiHalfMatBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WOOL.white()).strength(0.3F).pushReaction(PushReaction.DESTROY));

	public static final Supplier<Block> CANVAS_SIGN = regCanvasSign(ModBlockItemIds.CANVAS_SIGN, null);
	public static final Supplier<Block> WHITE_CANVAS_SIGN = regCanvasSign(ModBlockItemIds.DYED_CANVAS_SIGN.white(), DyeColor.WHITE);
	public static final Supplier<Block> ORANGE_CANVAS_SIGN = regCanvasSign(ModBlockItemIds.DYED_CANVAS_SIGN.orange(), DyeColor.ORANGE);
	public static final Supplier<Block> MAGENTA_CANVAS_SIGN = regCanvasSign(ModBlockItemIds.DYED_CANVAS_SIGN.magenta(), DyeColor.MAGENTA);
	public static final Supplier<Block> LIGHT_BLUE_CANVAS_SIGN = regCanvasSign(ModBlockItemIds.DYED_CANVAS_SIGN.lightBlue(), DyeColor.LIGHT_BLUE);
	public static final Supplier<Block> YELLOW_CANVAS_SIGN = regCanvasSign(ModBlockItemIds.DYED_CANVAS_SIGN.yellow(), DyeColor.YELLOW);
	public static final Supplier<Block> LIME_CANVAS_SIGN = regCanvasSign(ModBlockItemIds.DYED_CANVAS_SIGN.lime(), DyeColor.LIME);
	public static final Supplier<Block> PINK_CANVAS_SIGN = regCanvasSign(ModBlockItemIds.DYED_CANVAS_SIGN.pink(), DyeColor.PINK);
	public static final Supplier<Block> GRAY_CANVAS_SIGN = regCanvasSign(ModBlockItemIds.DYED_CANVAS_SIGN.gray(), DyeColor.GRAY);
	public static final Supplier<Block> LIGHT_GRAY_CANVAS_SIGN = regCanvasSign(ModBlockItemIds.DYED_CANVAS_SIGN.lightGray(), DyeColor.LIGHT_GRAY);
	public static final Supplier<Block> CYAN_CANVAS_SIGN = regCanvasSign(ModBlockItemIds.DYED_CANVAS_SIGN.cyan(), DyeColor.CYAN);
	public static final Supplier<Block> PURPLE_CANVAS_SIGN = regCanvasSign(ModBlockItemIds.DYED_CANVAS_SIGN.purple(), DyeColor.PURPLE);
	public static final Supplier<Block> BLUE_CANVAS_SIGN = regCanvasSign(ModBlockItemIds.DYED_CANVAS_SIGN.blue(), DyeColor.BLUE);
	public static final Supplier<Block> BROWN_CANVAS_SIGN = regCanvasSign(ModBlockItemIds.DYED_CANVAS_SIGN.brown(), DyeColor.BROWN);
	public static final Supplier<Block> GREEN_CANVAS_SIGN = regCanvasSign(ModBlockItemIds.DYED_CANVAS_SIGN.green(), DyeColor.GREEN);
	public static final Supplier<Block> RED_CANVAS_SIGN = regCanvasSign(ModBlockItemIds.DYED_CANVAS_SIGN.red(), DyeColor.RED);
	public static final Supplier<Block> BLACK_CANVAS_SIGN = regCanvasSign(ModBlockItemIds.DYED_CANVAS_SIGN.black(), DyeColor.BLACK);

	public static final Supplier<Block> CANVAS_WALL_SIGN = regWallCanvasSign(ModBlockIds.CANVAS_WALL_SIGN, null);
	public static final Supplier<Block> WHITE_CANVAS_WALL_SIGN = regWallCanvasSign(ModBlockIds.DYED_CANVAS_WALL_SIGN.white(), DyeColor.WHITE);
	public static final Supplier<Block> ORANGE_CANVAS_WALL_SIGN = regWallCanvasSign(ModBlockIds.DYED_CANVAS_WALL_SIGN.orange(), DyeColor.ORANGE);
	public static final Supplier<Block> MAGENTA_CANVAS_WALL_SIGN = regWallCanvasSign(ModBlockIds.DYED_CANVAS_WALL_SIGN.magenta(), DyeColor.MAGENTA);
	public static final Supplier<Block> LIGHT_BLUE_CANVAS_WALL_SIGN = regWallCanvasSign(ModBlockIds.DYED_CANVAS_WALL_SIGN.lightBlue(), DyeColor.LIGHT_BLUE);
	public static final Supplier<Block> YELLOW_CANVAS_WALL_SIGN = regWallCanvasSign(ModBlockIds.DYED_CANVAS_WALL_SIGN.yellow(), DyeColor.YELLOW);
	public static final Supplier<Block> LIME_CANVAS_WALL_SIGN = regWallCanvasSign(ModBlockIds.DYED_CANVAS_WALL_SIGN.lime(), DyeColor.LIME);
	public static final Supplier<Block> PINK_CANVAS_WALL_SIGN = regWallCanvasSign(ModBlockIds.DYED_CANVAS_WALL_SIGN.pink(), DyeColor.PINK);
	public static final Supplier<Block> GRAY_CANVAS_WALL_SIGN = regWallCanvasSign(ModBlockIds.DYED_CANVAS_WALL_SIGN.gray(), DyeColor.GRAY);
	public static final Supplier<Block> LIGHT_GRAY_CANVAS_WALL_SIGN = regWallCanvasSign(ModBlockIds.DYED_CANVAS_WALL_SIGN.lightGray(), DyeColor.LIGHT_GRAY);
	public static final Supplier<Block> CYAN_CANVAS_WALL_SIGN = regWallCanvasSign(ModBlockIds.DYED_CANVAS_WALL_SIGN.cyan(), DyeColor.CYAN);
	public static final Supplier<Block> PURPLE_CANVAS_WALL_SIGN = regWallCanvasSign(ModBlockIds.DYED_CANVAS_WALL_SIGN.purple(), DyeColor.PURPLE);
	public static final Supplier<Block> BLUE_CANVAS_WALL_SIGN = regWallCanvasSign(ModBlockIds.DYED_CANVAS_WALL_SIGN.blue(), DyeColor.BLUE);
	public static final Supplier<Block> BROWN_CANVAS_WALL_SIGN = regWallCanvasSign(ModBlockIds.DYED_CANVAS_WALL_SIGN.brown(), DyeColor.BROWN);
	public static final Supplier<Block> GREEN_CANVAS_WALL_SIGN = regWallCanvasSign(ModBlockIds.DYED_CANVAS_WALL_SIGN.green(), DyeColor.GREEN);
	public static final Supplier<Block> RED_CANVAS_WALL_SIGN = regWallCanvasSign(ModBlockIds.DYED_CANVAS_WALL_SIGN.red(), DyeColor.RED);
	public static final Supplier<Block> BLACK_CANVAS_WALL_SIGN = regWallCanvasSign(ModBlockIds.DYED_CANVAS_WALL_SIGN.black(), DyeColor.BLACK);

	public static final Supplier<Block> HANGING_CANVAS_SIGN = regHangingCanvasSign(ModBlockItemIds.HANGING_CANVAS_SIGN, null);
	public static final Supplier<Block> WHITE_HANGING_CANVAS_SIGN = regHangingCanvasSign(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.white(), DyeColor.WHITE);
	public static final Supplier<Block> ORANGE_HANGING_CANVAS_SIGN = regHangingCanvasSign(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.orange(), DyeColor.ORANGE);
	public static final Supplier<Block> MAGENTA_HANGING_CANVAS_SIGN = regHangingCanvasSign(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.magenta(), DyeColor.MAGENTA);
	public static final Supplier<Block> LIGHT_BLUE_HANGING_CANVAS_SIGN = regHangingCanvasSign(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.lightBlue(), DyeColor.LIGHT_BLUE);
	public static final Supplier<Block> YELLOW_HANGING_CANVAS_SIGN = regHangingCanvasSign(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.yellow(), DyeColor.YELLOW);
	public static final Supplier<Block> LIME_HANGING_CANVAS_SIGN = regHangingCanvasSign(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.lime(), DyeColor.LIME);
	public static final Supplier<Block> PINK_HANGING_CANVAS_SIGN = regHangingCanvasSign(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.pink(), DyeColor.PINK);
	public static final Supplier<Block> GRAY_HANGING_CANVAS_SIGN = regHangingCanvasSign(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.gray(), DyeColor.GRAY);
	public static final Supplier<Block> LIGHT_GRAY_HANGING_CANVAS_SIGN = regHangingCanvasSign(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.lightGray(), DyeColor.LIGHT_GRAY);
	public static final Supplier<Block> CYAN_HANGING_CANVAS_SIGN = regHangingCanvasSign(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.cyan(), DyeColor.CYAN);
	public static final Supplier<Block> PURPLE_HANGING_CANVAS_SIGN = regHangingCanvasSign(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.purple(), DyeColor.PURPLE);
	public static final Supplier<Block> BLUE_HANGING_CANVAS_SIGN = regHangingCanvasSign(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.blue(), DyeColor.BLUE);
	public static final Supplier<Block> BROWN_HANGING_CANVAS_SIGN = regHangingCanvasSign(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.brown(), DyeColor.BROWN);
	public static final Supplier<Block> GREEN_HANGING_CANVAS_SIGN = regHangingCanvasSign(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.green(), DyeColor.GREEN);
	public static final Supplier<Block> RED_HANGING_CANVAS_SIGN = regHangingCanvasSign(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.red(), DyeColor.RED);
	public static final Supplier<Block> BLACK_HANGING_CANVAS_SIGN = regHangingCanvasSign(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.black(), DyeColor.BLACK);

	public static final Supplier<Block> HANGING_CANVAS_WALL_SIGN = regWallHangingCanvasSign(ModBlockIds.HANGING_CANVAS_WALL_SIGN, null);
	public static final Supplier<Block> WHITE_HANGING_CANVAS_WALL_SIGN = regWallHangingCanvasSign(ModBlockIds.DYED_HANGING_CANVAS_WALL_SIGN.white(), DyeColor.WHITE);
	public static final Supplier<Block> ORANGE_HANGING_CANVAS_WALL_SIGN = regWallHangingCanvasSign(ModBlockIds.DYED_HANGING_CANVAS_WALL_SIGN.orange(), DyeColor.ORANGE);
	public static final Supplier<Block> MAGENTA_HANGING_CANVAS_WALL_SIGN = regWallHangingCanvasSign(ModBlockIds.DYED_HANGING_CANVAS_WALL_SIGN.magenta(), DyeColor.MAGENTA);
	public static final Supplier<Block> LIGHT_BLUE_HANGING_CANVAS_WALL_SIGN = regWallHangingCanvasSign(ModBlockIds.DYED_HANGING_CANVAS_WALL_SIGN.lightBlue(), DyeColor.LIGHT_BLUE);
	public static final Supplier<Block> YELLOW_HANGING_CANVAS_WALL_SIGN = regWallHangingCanvasSign(ModBlockIds.DYED_HANGING_CANVAS_WALL_SIGN.yellow(), DyeColor.YELLOW);
	public static final Supplier<Block> LIME_HANGING_CANVAS_WALL_SIGN = regWallHangingCanvasSign(ModBlockIds.DYED_HANGING_CANVAS_WALL_SIGN.lime(), DyeColor.LIME);
	public static final Supplier<Block> PINK_HANGING_CANVAS_WALL_SIGN = regWallHangingCanvasSign(ModBlockIds.DYED_HANGING_CANVAS_WALL_SIGN.pink(), DyeColor.PINK);
	public static final Supplier<Block> GRAY_HANGING_CANVAS_WALL_SIGN = regWallHangingCanvasSign(ModBlockIds.DYED_HANGING_CANVAS_WALL_SIGN.gray(), DyeColor.GRAY);
	public static final Supplier<Block> LIGHT_GRAY_HANGING_CANVAS_WALL_SIGN = regWallHangingCanvasSign(ModBlockIds.DYED_HANGING_CANVAS_WALL_SIGN.lightGray(), DyeColor.LIGHT_GRAY);
	public static final Supplier<Block> CYAN_HANGING_CANVAS_WALL_SIGN = regWallHangingCanvasSign(ModBlockIds.DYED_HANGING_CANVAS_WALL_SIGN.cyan(), DyeColor.CYAN);
	public static final Supplier<Block> PURPLE_HANGING_CANVAS_WALL_SIGN = regWallHangingCanvasSign(ModBlockIds.DYED_HANGING_CANVAS_WALL_SIGN.purple(), DyeColor.PURPLE);
	public static final Supplier<Block> BLUE_HANGING_CANVAS_WALL_SIGN = regWallHangingCanvasSign(ModBlockIds.DYED_HANGING_CANVAS_WALL_SIGN.blue(), DyeColor.BLUE);
	public static final Supplier<Block> BROWN_HANGING_CANVAS_WALL_SIGN = regWallHangingCanvasSign(ModBlockIds.DYED_HANGING_CANVAS_WALL_SIGN.brown(), DyeColor.BROWN);
	public static final Supplier<Block> GREEN_HANGING_CANVAS_WALL_SIGN = regWallHangingCanvasSign(ModBlockIds.DYED_HANGING_CANVAS_WALL_SIGN.green(), DyeColor.GREEN);
	public static final Supplier<Block> RED_HANGING_CANVAS_WALL_SIGN = regWallHangingCanvasSign(ModBlockIds.DYED_HANGING_CANVAS_WALL_SIGN.red(), DyeColor.RED);
	public static final Supplier<Block> BLACK_HANGING_CANVAS_WALL_SIGN = regWallHangingCanvasSign(ModBlockIds.DYED_HANGING_CANVAS_WALL_SIGN.black(), DyeColor.BLACK);

	// Composting
	public static final Supplier<Block> BROWN_MUSHROOM_COLONY = regBlock(ModBlockItemIds.BROWN_MUSHROOM_COLONY,
			(properties) -> new MushroomColonyBlock(Items.BROWN_MUSHROOM.builtInRegistryHolder(), properties),
			Block.Properties.ofFullCopy(Blocks.BROWN_MUSHROOM));
	public static final Supplier<Block> RED_MUSHROOM_COLONY = regBlock(ModBlockItemIds.RED_MUSHROOM_COLONY,
			(properties) -> new MushroomColonyBlock(Items.RED_MUSHROOM.builtInRegistryHolder(), properties),
			Block.Properties.ofFullCopy(Blocks.RED_MUSHROOM));
	public static final Supplier<Block> ORGANIC_COMPOST = regBlock(ModBlockItemIds.ORGANIC_COMPOST,
            OrganicCompostBlock::new, Block.Properties.ofFullCopy(Blocks.DIRT).strength(1.2F).sound(SoundType.CROP));
	public static final Supplier<Block> RICH_SOIL = regBlock(ModBlockItemIds.RICH_SOIL,
			RichSoilBlock::new, Block.Properties.ofFullCopy(Blocks.DIRT).randomTicks());
	public static final Supplier<Block> RICH_SOIL_FARMLAND = regBlock(ModBlockItemIds.RICH_SOIL_FARMLAND,
		RichSoilFarmlandBlock::new, Block.Properties.ofFullCopy(Blocks.FARMLAND));

	// Pastries
	public static final Supplier<Block> APPLE_PIE = regBlock(ModBlockItemIds.APPLE_PIE,
			(properties) -> new PieBlock(properties, () -> ModItems.APPLE_PIE_SLICE.get()), //dont kill double lambda
			Block.Properties.ofFullCopy(Blocks.CAKE));
	public static final Supplier<Block> SWEET_BERRY_CHEESECAKE = regBlock(ModBlockItemIds.SWEET_BERRY_CHEESECAKE,
			(properties) -> new PieBlock(properties, () -> ModItems.SWEET_BERRY_CHEESECAKE_SLICE.get()),
			Block.Properties.ofFullCopy(Blocks.CAKE));
	public static final Supplier<Block> CHOCOLATE_PIE = regBlock(ModBlockItemIds.CHOCOLATE_PIE,
			(properties) -> new PieBlock(properties, () -> ModItems.CHOCOLATE_PIE_SLICE.get()),
			Block.Properties.ofFullCopy(Blocks.CAKE));
    public static final Supplier<Block> PUMPKIN_PIE = regBlock(ModBlockIds.PUMPKIN_PIE,
            (properties) -> new PieBlock(properties, () -> ModItems.PUMPKIN_PIE_SLICE.get()),
            Block.Properties.ofFullCopy(Blocks.CAKE));

	// Wild Crops
	public static final Supplier<Block> SANDY_SHRUB = regBlock(ModBlockItemIds.SANDY_SHRUB,
            SandyShrubBlock::new, Block.Properties.ofFullCopy(Blocks.TALL_GRASS));

	public static final Supplier<Block> WILD_CABBAGES = regBlock(ModBlockItemIds.WILD_CABBAGES,
			(properties) -> new WildCropBlock(MobEffects.STRENGTH, 6, properties),
			Block.Properties.ofFullCopy(Blocks.TALL_GRASS));
	public static final Supplier<Block> WILD_ONIONS = regBlock(ModBlockItemIds.WILD_ONIONS,
			(properties) -> new WildCropBlock(MobEffects.FIRE_RESISTANCE, 6, properties),
			Block.Properties.ofFullCopy(Blocks.TALL_GRASS));
	public static final Supplier<Block> WILD_TOMATOES = regBlock(ModBlockItemIds.WILD_TOMATOES,
			(properties) -> new WildCropBlock(MobEffects.POISON, 10, properties),
			Block.Properties.ofFullCopy(Blocks.TALL_GRASS));
	public static final Supplier<Block> WILD_CARROTS = regBlock(ModBlockItemIds.WILD_CARROTS,
			(properties) -> new WildCropBlock(MobEffects.MINING_FATIGUE, 6, properties),
			Block.Properties.ofFullCopy(Blocks.TALL_GRASS));
	public static final Supplier<Block> WILD_POTATOES = regBlock(ModBlockItemIds.WILD_POTATOES,
			(properties) -> new WildCropBlock(MobEffects.NAUSEA, 8, properties),
			Block.Properties.ofFullCopy(Blocks.TALL_GRASS));
	public static final Supplier<Block> WILD_BEETROOTS = regBlock(ModBlockItemIds.WILD_BEETROOTS,
			(properties) -> new WildCropBlock(MobEffects.WATER_BREATHING, 8, properties),
			Block.Properties.ofFullCopy(Blocks.TALL_GRASS));
	public static final Supplier<Block> WILD_RICE = regBlock(ModBlockItemIds.WILD_RICE,
            WildRiceBlock::new, Block.Properties.ofFullCopy(Blocks.TALL_GRASS));

	// Crops
	public static final Supplier<Block> CABBAGE_CROP = regBlock(ModBlockItemIds.CABBAGE_CROP,
			CabbageBlock::new, Block.Properties.ofFullCopy(Blocks.WHEAT));
	public static final Supplier<Block> ONION_CROP = regBlock(ModBlockItemIds.ONION_CROP,
			OnionBlock::new, Block.Properties.ofFullCopy(Blocks.WHEAT));
	public static final Supplier<Block> BUDDING_TOMATO_CROP = regBlock(ModBlockIds.BUDDING_TOMATO_CROP,
			BuddingTomatoBlock::new, Block.Properties.ofFullCopy(Blocks.WHEAT).mapColor(MapColor.PLANT));
	public static final Supplier<Block> TOMATO_CROP = regBlock(ModBlockItemIds.TOMATO_CROP,
			TomatoBlock::new, Block.Properties.ofFullCopy(Blocks.WHEAT).mapColor(MapColor.PLANT));
	public static final Supplier<Block> TOMATO_CROP_ON_ROPE = regBlock(ModBlockIds.TOMATO_CROP_ON_ROPE,
			HangingTomatoBlock::new, Block.Properties.ofFullCopy(TOMATO_CROP.get()).pushReaction(PushReaction.NORMAL));
	public static final Supplier<Block> RICE_CROP = regBlock(ModBlockItemIds.RICE_CROP,
			RiceBlock::new, Block.Properties.ofFullCopy(Blocks.WHEAT).mapColor(MapColor.PLANT).strength(0.2F));
	public static final Supplier<Block> RICE_CROP_PANICLES = regBlock(ModBlockItemIds.RICE_CROP_PANICLES,
			RicePaniclesBlock::new, Block.Properties.ofFullCopy(Blocks.WHEAT).mapColor(MapColor.PLANT));

	// Feasts
	public static final Supplier<Block> ROAST_CHICKEN_BLOCK = regBlock(ModBlockItemIds.ROAST_CHICKEN_BLOCK,
			(properties) -> new RotatedFeastBlock(properties, () -> ModItems.ROAST_CHICKEN.get(), true, BlockShapes.ROAST_CHICKEN_SHAPES, BlockShapes.TRAY_SHAPE),
			Block.Properties.ofFullCopy(Blocks.CAKE));
	public static final Supplier<Block> STUFFED_PUMPKIN_BLOCK = regBlock(ModBlockItemIds.STUFFED_PUMPKIN_BLOCK,
			(properties) -> new FeastBlock(properties, () -> ModItems.STUFFED_PUMPKIN.get(), false, true),
			Block.Properties.ofFullCopy(Blocks.PUMPKIN));
	public static final Supplier<Block> HONEY_GLAZED_HAM_BLOCK = regBlock(ModBlockItemIds.HONEY_GLAZED_HAM_BLOCK,
			(properties) -> new RotatedFeastBlock(properties, () -> ModItems.HONEY_GLAZED_HAM.get(), true, BlockShapes.HONEY_GLAZED_HAM_SHAPES, BlockShapes.TRAY_SHAPE),
			Block.Properties.ofFullCopy(Blocks.CAKE));
	public static final Supplier<Block> SHEPHERDS_PIE_BLOCK = regBlock(ModBlockItemIds.SHEPHERDS_PIE_BLOCK,
			(properties) -> new RotatedFeastBlock(properties, () -> ModItems.SHEPHERDS_PIE.get(), true, BlockShapes.SHEPHERDS_PIE_SHAPES, BlockShapes.TRAY_SHAPE),
			Block.Properties.ofFullCopy(Blocks.CAKE));
    public static final Supplier<Block> GLEAMING_SALAD_BLOCK = regBlock(ModBlockItemIds.GLEAMING_SALAD_BLOCK,
            (properties) -> new GleamingSaladBlock(properties, () -> ModItems.GLEAMING_SALAD.get(), true),
            Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).lightLevel(glowingFeastBlockEmission()));
    public static final Supplier<Block> RICE_ROLL_MEDLEY_BLOCK = regBlock(ModBlockItemIds.RICE_ROLL_MEDLEY_BLOCK,
            RiceRollMedleyBlock::new, Block.Properties.ofFullCopy(Blocks.CAKE));

	public static void touch() {

	}
}
