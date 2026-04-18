package vectorwing.farmersdelight.common.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import vectorwing.farmersdelight.common.block.entity.*;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.RegUtils.regBlockEntity;

public class ModBlockEntityTypes
{
	public static final Supplier<BlockEntityType<StoveBlockEntity>> STOVE = regBlockEntity("stove",
			() -> BlockEntityType.Builder.of(StoveBlockEntity::new, ModBlocks.STOVE.get()).build());
	public static final Supplier<BlockEntityType<CookingPotBlockEntity>> COOKING_POT = regBlockEntity("cooking_pot",
			() -> BlockEntityType.Builder.of(CookingPotBlockEntity::new, ModBlocks.COOKING_POT.get()).build());
	public static final Supplier<BlockEntityType<BasketBlockEntity>> BASKET = regBlockEntity("basket",
			() -> BlockEntityType.Builder.of(BasketBlockEntity::new, ModBlocks.WOODEN_BASKET.get(), ModBlocks.BAMBOO_BASKET.get()).build());
	public static final Supplier<BlockEntityType<CuttingBoardBlockEntity>> CUTTING_BOARD = regBlockEntity("cutting_board",
			() -> BlockEntityType.Builder.of(CuttingBoardBlockEntity::new, ModBlocks.CUTTING_BOARD.get()).build());
	public static final Supplier<BlockEntityType<SkilletBlockEntity>> SKILLET = regBlockEntity("skillet",
			() -> BlockEntityType.Builder.of(SkilletBlockEntity::new, ModBlocks.SKILLET.get()).build());
	public static final Supplier<BlockEntityType<CabinetBlockEntity>> CABINET = regBlockEntity("cabinet",
			() -> BlockEntityType.Builder.of(CabinetBlockEntity::new,
							ModBlocks.OAK_CABINET.get(),
							ModBlocks.BIRCH_CABINET.get(),
							ModBlocks.SPRUCE_CABINET.get(),
							ModBlocks.JUNGLE_CABINET.get(),
							ModBlocks.ACACIA_CABINET.get(),
							ModBlocks.DARK_OAK_CABINET.get(),
							ModBlocks.MANGROVE_CABINET.get(),
							ModBlocks.BAMBOO_CABINET.get(),
							ModBlocks.CHERRY_CABINET.get(),
							ModBlocks.CRIMSON_CABINET.get(),
							ModBlocks.WARPED_CABINET.get())
					.build());
	public static final Supplier<BlockEntityType<CanvasSignBlockEntity>> CANVAS_SIGN = regBlockEntity("canvas_sign",
			() -> BlockEntityType.Builder.of(CanvasSignBlockEntity::new,
							ModBlocks.CANVAS_SIGN.get(),
							ModBlocks.WHITE_CANVAS_SIGN.get(),
							ModBlocks.ORANGE_CANVAS_SIGN.get(),
							ModBlocks.MAGENTA_CANVAS_SIGN.get(),
							ModBlocks.LIGHT_BLUE_CANVAS_SIGN.get(),
							ModBlocks.YELLOW_CANVAS_SIGN.get(),
							ModBlocks.LIME_CANVAS_SIGN.get(),
							ModBlocks.PINK_CANVAS_SIGN.get(),
							ModBlocks.GRAY_CANVAS_SIGN.get(),
							ModBlocks.LIGHT_GRAY_CANVAS_SIGN.get(),
							ModBlocks.CYAN_CANVAS_SIGN.get(),
							ModBlocks.PURPLE_CANVAS_SIGN.get(),
							ModBlocks.BLUE_CANVAS_SIGN.get(),
							ModBlocks.BROWN_CANVAS_SIGN.get(),
							ModBlocks.GREEN_CANVAS_SIGN.get(),
							ModBlocks.RED_CANVAS_SIGN.get(),
							ModBlocks.BLACK_CANVAS_SIGN.get(),
							ModBlocks.CANVAS_WALL_SIGN.get(),
							ModBlocks.WHITE_CANVAS_WALL_SIGN.get(),
							ModBlocks.ORANGE_CANVAS_WALL_SIGN.get(),
							ModBlocks.MAGENTA_CANVAS_WALL_SIGN.get(),
							ModBlocks.LIGHT_BLUE_CANVAS_WALL_SIGN.get(),
							ModBlocks.YELLOW_CANVAS_WALL_SIGN.get(),
							ModBlocks.LIME_CANVAS_WALL_SIGN.get(),
							ModBlocks.PINK_CANVAS_WALL_SIGN.get(),
							ModBlocks.GRAY_CANVAS_WALL_SIGN.get(),
							ModBlocks.LIGHT_GRAY_CANVAS_WALL_SIGN.get(),
							ModBlocks.CYAN_CANVAS_WALL_SIGN.get(),
							ModBlocks.PURPLE_CANVAS_WALL_SIGN.get(),
							ModBlocks.BLUE_CANVAS_WALL_SIGN.get(),
							ModBlocks.BROWN_CANVAS_WALL_SIGN.get(),
							ModBlocks.GREEN_CANVAS_WALL_SIGN.get(),
							ModBlocks.RED_CANVAS_WALL_SIGN.get(),
							ModBlocks.BLACK_CANVAS_WALL_SIGN.get())
					.build());
	public static final Supplier<BlockEntityType<HangingCanvasSignBlockEntity>> HANGING_CANVAS_SIGN = regBlockEntity("hanging_canvas_sign",
			() -> BlockEntityType.Builder.of(HangingCanvasSignBlockEntity::new,
							ModBlocks.HANGING_CANVAS_SIGN.get(),
							ModBlocks.WHITE_HANGING_CANVAS_SIGN.get(),
							ModBlocks.ORANGE_HANGING_CANVAS_SIGN.get(),
							ModBlocks.MAGENTA_HANGING_CANVAS_SIGN.get(),
							ModBlocks.LIGHT_BLUE_HANGING_CANVAS_SIGN.get(),
							ModBlocks.YELLOW_HANGING_CANVAS_SIGN.get(),
							ModBlocks.LIME_HANGING_CANVAS_SIGN.get(),
							ModBlocks.PINK_HANGING_CANVAS_SIGN.get(),
							ModBlocks.GRAY_HANGING_CANVAS_SIGN.get(),
							ModBlocks.LIGHT_GRAY_HANGING_CANVAS_SIGN.get(),
							ModBlocks.CYAN_HANGING_CANVAS_SIGN.get(),
							ModBlocks.PURPLE_HANGING_CANVAS_SIGN.get(),
							ModBlocks.BLUE_HANGING_CANVAS_SIGN.get(),
							ModBlocks.BROWN_HANGING_CANVAS_SIGN.get(),
							ModBlocks.GREEN_HANGING_CANVAS_SIGN.get(),
							ModBlocks.RED_HANGING_CANVAS_SIGN.get(),
							ModBlocks.BLACK_HANGING_CANVAS_SIGN.get(),
							ModBlocks.HANGING_CANVAS_WALL_SIGN.get(),
							ModBlocks.WHITE_HANGING_CANVAS_WALL_SIGN.get(),
							ModBlocks.ORANGE_HANGING_CANVAS_WALL_SIGN.get(),
							ModBlocks.MAGENTA_HANGING_CANVAS_WALL_SIGN.get(),
							ModBlocks.LIGHT_BLUE_HANGING_CANVAS_WALL_SIGN.get(),
							ModBlocks.YELLOW_HANGING_CANVAS_WALL_SIGN.get(),
							ModBlocks.LIME_HANGING_CANVAS_WALL_SIGN.get(),
							ModBlocks.PINK_HANGING_CANVAS_WALL_SIGN.get(),
							ModBlocks.GRAY_HANGING_CANVAS_WALL_SIGN.get(),
							ModBlocks.LIGHT_GRAY_HANGING_CANVAS_WALL_SIGN.get(),
							ModBlocks.CYAN_HANGING_CANVAS_WALL_SIGN.get(),
							ModBlocks.PURPLE_HANGING_CANVAS_WALL_SIGN.get(),
							ModBlocks.BLUE_HANGING_CANVAS_WALL_SIGN.get(),
							ModBlocks.BROWN_HANGING_CANVAS_WALL_SIGN.get(),
							ModBlocks.GREEN_HANGING_CANVAS_WALL_SIGN.get(),
							ModBlocks.RED_HANGING_CANVAS_WALL_SIGN.get(),
							ModBlocks.BLACK_HANGING_CANVAS_WALL_SIGN.get())
					.build());

	public static void touch() {

	}
}
