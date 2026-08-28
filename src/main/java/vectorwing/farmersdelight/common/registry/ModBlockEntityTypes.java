package vectorwing.farmersdelight.common.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityTypes;
import vectorwing.farmersdelight.common.block.entity.*;

import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.RegUtils.regBlockEntity;

public class ModBlockEntityTypes
{
	public static final Supplier<BlockEntityType<StoveBlockEntity>> STOVE = regBlockEntity("stove",
			() -> FabricBlockEntityTypeBuilder.create(StoveBlockEntity::new, ModBlocks.STOVE.get()).build());
	public static final Supplier<BlockEntityType<CookingPotBlockEntity>> COOKING_POT = regBlockEntity("cooking_pot",
			() -> FabricBlockEntityTypeBuilder.create(CookingPotBlockEntity::new, ModBlocks.COOKING_POT.get()).build());
	public static final Supplier<BlockEntityType<BasketBlockEntity>> BASKET = regBlockEntity("basket",
			() -> FabricBlockEntityTypeBuilder.create(BasketBlockEntity::new, ModBlocks.WOODEN_BASKET.get(), ModBlocks.BAMBOO_BASKET.get()).build());
	public static final Supplier<BlockEntityType<CuttingBoardBlockEntity>> CUTTING_BOARD = regBlockEntity("cutting_board",
			() -> FabricBlockEntityTypeBuilder.create(CuttingBoardBlockEntity::new, ModBlocks.CUTTING_BOARD.get()).build());
	public static final Supplier<BlockEntityType<SkilletBlockEntity>> SKILLET = regBlockEntity("skillet",
			() -> FabricBlockEntityTypeBuilder.create(SkilletBlockEntity::new, ModBlocks.SKILLET.get()).build());
	public static final Supplier<BlockEntityType<CabinetBlockEntity>> CABINET = regBlockEntity("cabinet",
			() -> FabricBlockEntityTypeBuilder.create(CabinetBlockEntity::new,
							ModBlocks.OAK_CABINET.get(),
							ModBlocks.BIRCH_CABINET.get(),
							ModBlocks.SPRUCE_CABINET.get(),
							ModBlocks.JUNGLE_CABINET.get(),
							ModBlocks.ACACIA_CABINET.get(),
							ModBlocks.DARK_OAK_CABINET.get(),
							ModBlocks.MANGROVE_CABINET.get(),
							ModBlocks.BAMBOO_CABINET.get(),
							ModBlocks.CHERRY_CABINET.get(),
							ModBlocks.PALE_OAK_CABINET.get(),
							ModBlocks.POPLAR_CABINET.get(),
							ModBlocks.CRIMSON_CABINET.get(),
							ModBlocks.WARPED_CABINET.get())
					.build());
	
	@SafeVarargs
	private static void addSupportedBlocks(BlockEntityType<?> type, Supplier<Block>... blocks) {
		for (Supplier<Block> block : blocks) {
			type.addValidBlock(block.get());
		}
	}

	public static void touch() {
		addSupportedBlocks(BlockEntityTypes.SIGN,
			ModBlocks.CANVAS_SIGN,
			ModBlocks.WHITE_CANVAS_SIGN,
			ModBlocks.ORANGE_CANVAS_SIGN,
			ModBlocks.MAGENTA_CANVAS_SIGN,
			ModBlocks.LIGHT_BLUE_CANVAS_SIGN,
			ModBlocks.YELLOW_CANVAS_SIGN,
			ModBlocks.LIME_CANVAS_SIGN,
			ModBlocks.PINK_CANVAS_SIGN,
			ModBlocks.GRAY_CANVAS_SIGN,
			ModBlocks.LIGHT_GRAY_CANVAS_SIGN,
			ModBlocks.CYAN_CANVAS_SIGN,
			ModBlocks.PURPLE_CANVAS_SIGN,
			ModBlocks.BLUE_CANVAS_SIGN,
			ModBlocks.BROWN_CANVAS_SIGN,
			ModBlocks.GREEN_CANVAS_SIGN,
			ModBlocks.RED_CANVAS_SIGN,
			ModBlocks.BLACK_CANVAS_SIGN,
			ModBlocks.CANVAS_WALL_SIGN,
			ModBlocks.WHITE_CANVAS_WALL_SIGN,
			ModBlocks.ORANGE_CANVAS_WALL_SIGN,
			ModBlocks.MAGENTA_CANVAS_WALL_SIGN,
			ModBlocks.LIGHT_BLUE_CANVAS_WALL_SIGN,
			ModBlocks.YELLOW_CANVAS_WALL_SIGN,
			ModBlocks.LIME_CANVAS_WALL_SIGN,
			ModBlocks.PINK_CANVAS_WALL_SIGN,
			ModBlocks.GRAY_CANVAS_WALL_SIGN,
			ModBlocks.LIGHT_GRAY_CANVAS_WALL_SIGN,
			ModBlocks.CYAN_CANVAS_WALL_SIGN,
			ModBlocks.PURPLE_CANVAS_WALL_SIGN,
			ModBlocks.BLUE_CANVAS_WALL_SIGN,
			ModBlocks.BROWN_CANVAS_WALL_SIGN,
			ModBlocks.GREEN_CANVAS_WALL_SIGN,
			ModBlocks.RED_CANVAS_WALL_SIGN,
			ModBlocks.BLACK_CANVAS_WALL_SIGN);
		addSupportedBlocks(BlockEntityTypes.HANGING_SIGN,
			ModBlocks.HANGING_CANVAS_SIGN,
			ModBlocks.WHITE_HANGING_CANVAS_SIGN,
			ModBlocks.ORANGE_HANGING_CANVAS_SIGN,
			ModBlocks.MAGENTA_HANGING_CANVAS_SIGN,
			ModBlocks.LIGHT_BLUE_HANGING_CANVAS_SIGN,
			ModBlocks.YELLOW_HANGING_CANVAS_SIGN,
			ModBlocks.LIME_HANGING_CANVAS_SIGN,
			ModBlocks.PINK_HANGING_CANVAS_SIGN,
			ModBlocks.GRAY_HANGING_CANVAS_SIGN,
			ModBlocks.LIGHT_GRAY_HANGING_CANVAS_SIGN,
			ModBlocks.CYAN_HANGING_CANVAS_SIGN,
			ModBlocks.PURPLE_HANGING_CANVAS_SIGN,
			ModBlocks.BLUE_HANGING_CANVAS_SIGN,
			ModBlocks.BROWN_HANGING_CANVAS_SIGN,
			ModBlocks.GREEN_HANGING_CANVAS_SIGN,
			ModBlocks.RED_HANGING_CANVAS_SIGN,
			ModBlocks.BLACK_HANGING_CANVAS_SIGN,
			ModBlocks.HANGING_CANVAS_WALL_SIGN,
			ModBlocks.WHITE_HANGING_CANVAS_WALL_SIGN,
			ModBlocks.ORANGE_HANGING_CANVAS_WALL_SIGN,
			ModBlocks.MAGENTA_HANGING_CANVAS_WALL_SIGN,
			ModBlocks.LIGHT_BLUE_HANGING_CANVAS_WALL_SIGN,
			ModBlocks.YELLOW_HANGING_CANVAS_WALL_SIGN,
			ModBlocks.LIME_HANGING_CANVAS_WALL_SIGN,
			ModBlocks.PINK_HANGING_CANVAS_WALL_SIGN,
			ModBlocks.GRAY_HANGING_CANVAS_WALL_SIGN,
			ModBlocks.LIGHT_GRAY_HANGING_CANVAS_WALL_SIGN,
			ModBlocks.CYAN_HANGING_CANVAS_WALL_SIGN,
			ModBlocks.PURPLE_HANGING_CANVAS_WALL_SIGN,
			ModBlocks.BLUE_HANGING_CANVAS_WALL_SIGN,
			ModBlocks.BROWN_HANGING_CANVAS_WALL_SIGN,
			ModBlocks.GREEN_HANGING_CANVAS_WALL_SIGN,
			ModBlocks.RED_HANGING_CANVAS_WALL_SIGN,
			ModBlocks.BLACK_HANGING_CANVAS_WALL_SIGN);
	}
}
