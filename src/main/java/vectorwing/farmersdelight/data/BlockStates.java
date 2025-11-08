package vectorwing.farmersdelight.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;

/**
 * Nope. Not even trying with this one!
 */
@Deprecated
public abstract class BlockStates extends FabricModelProvider
{
	public BlockStates(FabricDataOutput output) {
		super(output);
	}

//
//	private static final int DEFAULT_ANGLE_OFFSET = 180;
//


//	public BlockStates(FabricDataOutput output) {
//
//		super(output);
//
//	}
//


//	private String blockName(Block block) {
//
//		return BuiltInRegistries.BLOCK.getKey(block).getPath();
//
//	}
//


//	public ResourceLocation resourceBlock(String path) {
//
//		return ResourceLocation.fromNamespaceAndPath(FarmersDelight.MODID, "block/" + path);
//
//	}
//

//
//	@Override

//	public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
//
//		this.simpleBlock(ModBlocks.RICH_SOIL.get(), cubeRandomRotation(ModBlocks.RICH_SOIL.get(), ""));
//
//		this.simpleBlock(ModBlocks.SAFETY_NET.get(), existingModel(ModBlocks.SAFETY_NET.get()));
//

//
//		Set<Block> canvasSigns = Sets.newHashSet(
//
// Standard
//
//				ModBlocks.CANVAS_SIGN.get(),
//
//				ModBlocks.HANGING_CANVAS_SIGN.get(),
//
//				ModBlocks.WHITE_CANVAS_SIGN.get(),
//
//				ModBlocks.WHITE_HANGING_CANVAS_SIGN.get(),
//
//				ModBlocks.ORANGE_CANVAS_SIGN.get(),
//
//				ModBlocks.ORANGE_HANGING_CANVAS_SIGN.get(),
//
//				ModBlocks.MAGENTA_CANVAS_SIGN.get(),
//
//				ModBlocks.MAGENTA_HANGING_CANVAS_SIGN.get(),
//
//				ModBlocks.LIGHT_BLUE_CANVAS_SIGN.get(),
//
//				ModBlocks.LIGHT_BLUE_HANGING_CANVAS_SIGN.get(),
//
//				ModBlocks.YELLOW_CANVAS_SIGN.get(),
//
//				ModBlocks.YELLOW_HANGING_CANVAS_SIGN.get(),
//
//				ModBlocks.LIME_CANVAS_SIGN.get(),
//
//				ModBlocks.LIME_HANGING_CANVAS_SIGN.get(),
//
//				ModBlocks.PINK_CANVAS_SIGN.get(),
//
//				ModBlocks.PINK_HANGING_CANVAS_SIGN.get(),
//
//				ModBlocks.GRAY_CANVAS_SIGN.get(),
//
//				ModBlocks.GRAY_HANGING_CANVAS_SIGN.get(),
//
//				ModBlocks.LIGHT_GRAY_CANVAS_SIGN.get(),
//
//				ModBlocks.LIGHT_GRAY_HANGING_CANVAS_SIGN.get(),
//
//				ModBlocks.CYAN_CANVAS_SIGN.get(),
//
//				ModBlocks.CYAN_HANGING_CANVAS_SIGN.get(),
//
//				ModBlocks.PURPLE_CANVAS_SIGN.get(),
//
//				ModBlocks.PURPLE_HANGING_CANVAS_SIGN.get(),
//
//				ModBlocks.BLUE_CANVAS_SIGN.get(),
//
//				ModBlocks.BLUE_HANGING_CANVAS_SIGN.get(),
//
//				ModBlocks.BROWN_CANVAS_SIGN.get(),
//
//				ModBlocks.BROWN_HANGING_CANVAS_SIGN.get(),
//
//				ModBlocks.GREEN_CANVAS_SIGN.get(),
//
//				ModBlocks.GREEN_HANGING_CANVAS_SIGN.get(),
//
//				ModBlocks.RED_CANVAS_SIGN.get(),
//
//				ModBlocks.RED_HANGING_CANVAS_SIGN.get(),
//
//				ModBlocks.BLACK_CANVAS_SIGN.get(),
//
//				ModBlocks.BLACK_HANGING_CANVAS_SIGN.get(),
//
// Wall
//
//				ModBlocks.CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.HANGING_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.WHITE_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.WHITE_HANGING_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.ORANGE_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.ORANGE_HANGING_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.MAGENTA_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.MAGENTA_HANGING_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.LIGHT_BLUE_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.LIGHT_BLUE_HANGING_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.YELLOW_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.YELLOW_HANGING_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.LIME_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.LIME_HANGING_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.PINK_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.PINK_HANGING_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.GRAY_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.GRAY_HANGING_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.LIGHT_GRAY_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.LIGHT_GRAY_HANGING_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.CYAN_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.CYAN_HANGING_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.PURPLE_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.PURPLE_HANGING_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.BLUE_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.BLUE_HANGING_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.BROWN_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.BROWN_HANGING_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.GREEN_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.GREEN_HANGING_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.RED_CANVAS_WALL_SIGN.get(),

//				ModBlocks.RED_HANGING_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.BLACK_CANVAS_WALL_SIGN.get(),
//
//				ModBlocks.BLACK_HANGING_CANVAS_WALL_SIGN.get());
//


//		for (Block sign : canvasSigns) {
//
//			this.simpleBlock(sign, existingModel(ModBlocks.CANVAS_SIGN.get()));
//
//		}
//

//
//		String riceBag = blockName(ModBlocks.RICE_BAG.get());
//
//		this.simpleBlock(ModBlocks.RICE_BAG.get(), models().withExistingParent(riceBag, "cube")
//
//				.texture("particle", resourceBlock(riceBag + "_top"))
//
//				.texture("down", resourceBlock(riceBag + "_bottom"))
//
//				.texture("up", resourceBlock(riceBag + "_top"))
//
//				.texture("north", resourceBlock(riceBag + "_side_tied"))
//
//				.texture("south", resourceBlock(riceBag + "_side_tied"))

//				.texture("east", resourceBlock(riceBag + "_side"))
//
//				.texture("west", resourceBlock(riceBag + "_side"))
//
//		);
//

//
//		customDirectionalBlock(ModBlocks.BASKET.get(),
//
//				$ -> existingModel(ModBlocks.BASKET.get()), BasketBlock.ENABLED, BasketBlock.WATERLOGGED);
//
//		customDirectionalBlock(ModBlocks.RICE_BALE.get(),

//				$ -> existingModel(ModBlocks.RICE_BALE.get()));
//
//		customHorizontalBlock(ModBlocks.CUTTING_BOARD.get(),
//
//				$ -> existingModel(ModBlocks.CUTTING_BOARD.get()), BasketBlock.WATERLOGGED);
//

//
//		this.horizontalBlock(ModBlocks.HALF_TATAMI_MAT.get(), existingModel("tatami_mat_half"));

//		this.horizontalBlock(ModBlocks.STOVE.get(), state -> {
//
//			String name = blockName(ModBlocks.STOVE.get());
//
//			String suffix = state.getValue(StoveBlock.LIT) ? "_on" : "";
//

//
//			return models().orientableWithBottom(name + suffix,
//
//					resourceBlock(name + "_side"),
//
//					resourceBlock(name + "_front" + suffix),

//					resourceBlock(name + "_bottom"),
//
//					resourceBlock(name + "_top" + suffix));
//
//		});
//

//
//		this.stageBlock(ModBlocks.BROWN_MUSHROOM_COLONY.get(), MushroomColonyBlock.COLONY_AGE);
//
//		this.stageBlock(ModBlocks.RED_MUSHROOM_COLONY.get(), MushroomColonyBlock.COLONY_AGE);
//
//		this.stageBlock(ModBlocks.RICE_CROP_PANICLES.get(), RicePaniclesBlock.RICE_AGE);

//		this.customStageBlock(ModBlocks.CABBAGE_CROP.get(), resourceBlock("crop_cross"), "cross", CabbageBlock.AGE, new ArrayList<>());
//
//		this.customStageBlock(ModBlocks.ONION_CROP.get(), mcLoc("crop"), "crop", OnionBlock.AGE, Arrays.asList(0, 0, 1, 1, 2, 2, 2, 3));
//
//		this.customStageBlock(ModBlocks.BUDDING_TOMATO_CROP.get(), resourceBlock("crop_cross"), "cross", BuddingTomatoBlock.AGE, Arrays.asList(0, 1, 2, 3, 3));
//

//
//		this.crateBlock(ModBlocks.CARROT_CRATE.get(), "carrot");
//
//		this.crateBlock(ModBlocks.POTATO_CRATE.get(), "potato");
//
//		this.crateBlock(ModBlocks.BEETROOT_CRATE.get(), "beetroot");

//		this.crateBlock(ModBlocks.CABBAGE_CRATE.get(), "cabbage");
//
//		this.crateBlock(ModBlocks.TOMATO_CRATE.get(), "tomato");

//		this.crateBlock(ModBlocks.ONION_CRATE.get(), "onion");
//

//
//		this.axisBlock((RotatedPillarBlock) ModBlocks.STRAW_BALE.get());
//

//
//		this.cabinetBlock(ModBlocks.OAK_CABINET.get(), "oak");
//
//		this.cabinetBlock(ModBlocks.BIRCH_CABINET.get(), "birch");
//
//		this.cabinetBlock(ModBlocks.SPRUCE_CABINET.get(), "spruce");
//
//		this.cabinetBlock(ModBlocks.JUNGLE_CABINET.get(), "jungle");
//
//		this.cabinetBlock(ModBlocks.ACACIA_CABINET.get(), "acacia");
//
//		this.cabinetBlock(ModBlocks.DARK_OAK_CABINET.get(), "dark_oak");
//
//		this.cabinetBlock(ModBlocks.MANGROVE_CABINET.get(), "mangrove");
//
//		this.cabinetBlock(ModBlocks.CHERRY_CABINET.get(), "cherry");

//		this.cabinetBlock(ModBlocks.BAMBOO_CABINET.get(), "bamboo");
//
//		this.cabinetBlock(ModBlocks.CRIMSON_CABINET.get(), "crimson");
//
//		this.cabinetBlock(ModBlocks.WARPED_CABINET.get(), "warped");
//


//		this.pieBlock(ModBlocks.APPLE_PIE.get());
//
//		this.pieBlock(ModBlocks.CHOCOLATE_PIE.get());
//
//		this.pieBlock(ModBlocks.SWEET_BERRY_CHEESECAKE.get());
//

//
//		this.feastBlock((FeastBlock) ModBlocks.STUFFED_PUMPKIN_BLOCK.get());
//
//		this.feastBlock((FeastBlock) ModBlocks.ROAST_CHICKEN_BLOCK.get());

//		this.feastBlock((FeastBlock) ModBlocks.HONEY_GLAZED_HAM_BLOCK.get());
//
//		this.feastBlock((FeastBlock) ModBlocks.SHEPHERDS_PIE_BLOCK.get());
//
//		this.feastBlock((FeastBlock) ModBlocks.RICE_ROLL_MEDLEY_BLOCK.get());
//

//
//		this.wildCropBlock(ModBlocks.SANDY_SHRUB.get());
//
//		this.wildCropBlock(ModBlocks.WILD_BEETROOTS.get());
//
//		this.wildCropBlock(ModBlocks.WILD_CABBAGES.get());
//
//		this.wildCropBlock(ModBlocks.WILD_POTATOES.get());
//
//		this.wildCropBlock(ModBlocks.WILD_TOMATOES.get());
//
//		this.wildCropBlock(ModBlocks.WILD_CARROTS.get());

//		this.wildCropBlock(ModBlocks.WILD_ONIONS.get());
//
//		this.doublePlantBlock(ModBlocks.WILD_RICE.get());
//
//	}
//

//
//	@Override

//	public void generateItemModels(ItemModelGenerators itemModelGenerator) {
//

//
//	}
//

//
//	public ConfiguredModel[] cubeRandomRotation(Block block, String suffix) {

//		String formattedName = blockName(block) + (suffix.isEmpty() ? "" : "_" + suffix);
//
//		return ConfiguredModel.allYRotations(models().cubeAll(formattedName, resourceBlock(formattedName)), 0, false);
//
//	}
//

//
//	public void customDirectionalBlock(Block block, Function<BlockState, ModelFile> modelFunc, Property<?>... ignored) {
//
//		getVariantBuilder(block)
//
//				.forAllStatesExcept(state -> {
//
//					Direction dir = state.getValue(BlockStateProperties.FACING);
//
//					return ConfiguredModel.builder()
//
//							.modelFile(modelFunc.apply(state))
//
//							.rotationX(dir == Direction.DOWN ? 180 : dir.getAxis().isHorizontal() ? 90 : 0)
//
//							.rotationY(dir.getAxis().isVertical() ? 0 : ((int) dir.toYRot() + DEFAULT_ANGLE_OFFSET) % 360)

//							.build();
//
//				}, ignored);
//
//	}
//

//
//	public void customHorizontalBlock(Block block, Function<BlockState, ModelFile> modelFunc, Property<?>... ignored) {
//
//		getVariantBuilder(block)
//
//				.forAllStatesExcept(state -> ConfiguredModel.builder()
//
//						.modelFile(modelFunc.apply(state))

//						.rotationY(((int) state.getValue(BlockStateProperties.HORIZONTAL_FACING).toYRot() + DEFAULT_ANGLE_OFFSET) % 360)
//
//						.build(), ignored);
//
//	}
//

//
//	public void stageBlock(Block block, IntegerProperty ageProperty, Property<?>... ignored) {
//
//		getVariantBuilder(block)
//
//				.forAllStatesExcept(state -> {
//
//					int ageSuffix = state.getValue(ageProperty);
//
//					String stageName = blockName(block) + "_stage" + ageSuffix;
//
//					return ConfiguredModel.builder()

//							.modelFile(models().cross(stageName, resourceBlock(stageName)).renderType("cutout")).build();
//
//				}, ignored);
//
//	}
//

//
// I am not proud of this method... But hey, it's runData. Only I shall have to deal with it.
//
//	public void customStageBlock(Block block, @Nullable ResourceLocation parent, String textureKey, IntegerProperty ageProperty, List<Integer> suffixes, Property<?>... ignored) {
//
//		getVariantBuilder(block)
//
//				.forAllStatesExcept(state -> {
//
//					int ageSuffix = state.getValue(ageProperty);
//
//					String stageName = blockName(block) + "_stage";
//
//					stageName += suffixes.isEmpty() ? ageSuffix : suffixes.get(Math.min(suffixes.size(), ageSuffix));
//
//					if (parent == null) {
//
//						return ConfiguredModel.builder()
//
//								.modelFile(models().cross(stageName, resourceBlock(stageName)).renderType("cutout")).build();
//
//					}
//
//					return ConfiguredModel.builder()

//							.modelFile(models().singleTexture(stageName, parent, textureKey, resourceBlock(stageName)).renderType("cutout")).build();
//
//				}, ignored);
//
//	}
//


//	public void wildCropBlock(Block block) {
//
//		this.wildCropBlock(block, false);
//
//	}
//

//
//	public void wildCropBlock(Block block, boolean isBushCrop) {
//
//		if (isBushCrop) {
//
//			this.simpleBlock(block, models().singleTexture(blockName(block), resourceBlock("bush_crop"), "crop", resourceBlock(blockName(block))).renderType("cutout"));
//
//		} else {

//			this.simpleBlock(block, models().cross(blockName(block), resourceBlock(blockName(block))).renderType("cutout"));
//
//		}
//
//	}
//

//
//	public void crateBlock(Block block, String cropName) {

//		this.simpleBlock(block,
//
//				models().cubeBottomTop(blockName(block), resourceBlock(cropName + "_crate_side"), resourceBlock("crate_bottom"), resourceBlock(cropName + "_crate_top")));
//
//	}
//

//
//	public void cabinetBlock(Block block, String woodType) {
//
//		this.horizontalBlock(block, state -> {
//
//			String suffix = state.getValue(CabinetBlock.OPEN) ? "_open" : "";
//
//			return models().orientable(blockName(block) + suffix,
//
//					resourceBlock(woodType + "_cabinet_side"),
//
//					resourceBlock(woodType + "_cabinet_front" + suffix),

//					resourceBlock(woodType + "_cabinet_top"));
//
//		});
//
//	}
//

//
//	public void feastBlock(FeastBlock block) {
//
//		getVariantBuilder(block)

//				.forAllStates(state -> {
//
//					IntegerProperty servingsProperty = block.getServingsProperty();

//					int servings = state.getValue(servingsProperty);
//

//
//					String suffix = "_stage" + (block.getMaxServings() - servings);
//


//					if (servings == 0) {
//
//						suffix = block.hasLeftovers ? "_leftover" : "_stage" + (servingsProperty.getPossibleValues().toArray().length - 2);
//
//					}
//

//
//					return ConfiguredModel.builder()
//
//							.modelFile(existingModel(blockName(block) + suffix))
//
//							.rotationY(((int) state.getValue(FeastBlock.FACING).toYRot() + DEFAULT_ANGLE_OFFSET) % 360)

//							.build();
//
//				});
//
//	}
//

//
//	public void doublePlantBlock(Block block) {
//
//		getVariantBuilder(block)
//
//				.partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.LOWER)
//
//				.modelForState().modelFile(models().cross(blockName(block) + "_bottom", resourceBlock(blockName(block) + "_bottom")).renderType("cutout")).addModel()

//				.partialState().with(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER)
//
//				.modelForState().modelFile(models().cross(blockName(block) + "_top", resourceBlock(blockName(block) + "_top")).renderType("cutout")).addModel();
//
//	}
//

//
//	public void pieBlock(Block block) {
//
//		getVariantBuilder(block)
//
//				.forAllStates(state -> {
//
//							int bites = state.getValue(PieBlock.BITES);
//
//							String suffix = bites > 0 ? "_slice" + bites : "";
//
//							return ConfiguredModel.builder()
//
//									.modelFile(existingModel(blockName(block) + suffix))
//
//									.rotationY(((int) state.getValue(PieBlock.FACING).toYRot() + DEFAULT_ANGLE_OFFSET) % 360)
//
//									.build();
//						}
//				);
//	}
}
