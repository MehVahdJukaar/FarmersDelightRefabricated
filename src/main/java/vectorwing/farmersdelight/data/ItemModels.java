package vectorwing.farmersdelight.data;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.common.registry.ModBlocks;

/**
 * Credits to Vazkii and team for some references on mass-reading blocks to datagen!
 */

public class ItemModels extends FabricModelProvider
{
	public ItemModels(FabricPackOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators generators) {
		sign(generators, ModBlocks.CANVAS_SIGN.get(), ModBlocks.CANVAS_WALL_SIGN.get());
		hangingSign(generators, ModBlocks.HANGING_CANVAS_SIGN.get(), ModBlocks.HANGING_CANVAS_WALL_SIGN.get());

		sign(generators, ModBlocks.WHITE_CANVAS_SIGN.get(), ModBlocks.WHITE_CANVAS_WALL_SIGN.get());
		hangingSign(generators, ModBlocks.WHITE_HANGING_CANVAS_SIGN.get(), ModBlocks.WHITE_HANGING_CANVAS_WALL_SIGN.get());
		sign(generators, ModBlocks.ORANGE_CANVAS_SIGN.get(), ModBlocks.ORANGE_CANVAS_WALL_SIGN.get());
		hangingSign(generators, ModBlocks.ORANGE_HANGING_CANVAS_SIGN.get(), ModBlocks.ORANGE_HANGING_CANVAS_WALL_SIGN.get());
		sign(generators, ModBlocks.MAGENTA_CANVAS_SIGN.get(), ModBlocks.MAGENTA_CANVAS_WALL_SIGN.get());
		hangingSign(generators, ModBlocks.MAGENTA_HANGING_CANVAS_SIGN.get(), ModBlocks.MAGENTA_HANGING_CANVAS_WALL_SIGN.get());
		sign(generators, ModBlocks.LIGHT_BLUE_CANVAS_SIGN.get(), ModBlocks.LIGHT_BLUE_CANVAS_WALL_SIGN.get());
		hangingSign(generators, ModBlocks.LIGHT_BLUE_HANGING_CANVAS_SIGN.get(), ModBlocks.LIGHT_BLUE_HANGING_CANVAS_WALL_SIGN.get());
		sign(generators, ModBlocks.YELLOW_CANVAS_SIGN.get(), ModBlocks.YELLOW_CANVAS_WALL_SIGN.get());
		hangingSign(generators, ModBlocks.YELLOW_HANGING_CANVAS_SIGN.get(), ModBlocks.YELLOW_HANGING_CANVAS_WALL_SIGN.get());
		sign(generators, ModBlocks.LIME_CANVAS_SIGN.get(), ModBlocks.LIME_CANVAS_WALL_SIGN.get());
		hangingSign(generators, ModBlocks.LIME_HANGING_CANVAS_SIGN.get(), ModBlocks.LIME_HANGING_CANVAS_WALL_SIGN.get());
		sign(generators, ModBlocks.PINK_CANVAS_SIGN.get(), ModBlocks.PINK_CANVAS_WALL_SIGN.get());
		hangingSign(generators, ModBlocks.PINK_HANGING_CANVAS_SIGN.get(), ModBlocks.PINK_HANGING_CANVAS_WALL_SIGN.get());
		sign(generators, ModBlocks.GRAY_CANVAS_SIGN.get(), ModBlocks.GRAY_CANVAS_WALL_SIGN.get());
		hangingSign(generators, ModBlocks.GRAY_HANGING_CANVAS_SIGN.get(), ModBlocks.GRAY_HANGING_CANVAS_WALL_SIGN.get());
		sign(generators, ModBlocks.LIGHT_GRAY_CANVAS_SIGN.get(), ModBlocks.LIGHT_GRAY_CANVAS_WALL_SIGN.get());
		hangingSign(generators, ModBlocks.LIGHT_GRAY_HANGING_CANVAS_SIGN.get(), ModBlocks.LIGHT_GRAY_HANGING_CANVAS_WALL_SIGN.get());
		sign(generators, ModBlocks.CYAN_CANVAS_SIGN.get(), ModBlocks.CYAN_CANVAS_WALL_SIGN.get());
		hangingSign(generators, ModBlocks.CYAN_HANGING_CANVAS_SIGN.get(), ModBlocks.CYAN_HANGING_CANVAS_WALL_SIGN.get());
		sign(generators, ModBlocks.PURPLE_CANVAS_SIGN.get(), ModBlocks.PURPLE_CANVAS_WALL_SIGN.get());
		hangingSign(generators, ModBlocks.PURPLE_HANGING_CANVAS_SIGN.get(), ModBlocks.PURPLE_HANGING_CANVAS_WALL_SIGN.get());
		sign(generators, ModBlocks.BLUE_CANVAS_SIGN.get(), ModBlocks.BLUE_CANVAS_WALL_SIGN.get());
		hangingSign(generators, ModBlocks.BLUE_HANGING_CANVAS_SIGN.get(), ModBlocks.BLUE_HANGING_CANVAS_WALL_SIGN.get());
		sign(generators, ModBlocks.BROWN_CANVAS_SIGN.get(), ModBlocks.BROWN_CANVAS_WALL_SIGN.get());
		hangingSign(generators, ModBlocks.BROWN_HANGING_CANVAS_SIGN.get(), ModBlocks.BROWN_HANGING_CANVAS_WALL_SIGN.get());
		sign(generators, ModBlocks.GREEN_CANVAS_SIGN.get(), ModBlocks.GREEN_CANVAS_WALL_SIGN.get());
		hangingSign(generators, ModBlocks.GREEN_HANGING_CANVAS_SIGN.get(), ModBlocks.GREEN_HANGING_CANVAS_WALL_SIGN.get());
		sign(generators, ModBlocks.RED_CANVAS_SIGN.get(), ModBlocks.RED_CANVAS_WALL_SIGN.get());
		hangingSign(generators, ModBlocks.RED_HANGING_CANVAS_SIGN.get(), ModBlocks.RED_HANGING_CANVAS_WALL_SIGN.get());
		sign(generators, ModBlocks.BLACK_CANVAS_SIGN.get(), ModBlocks.BLACK_CANVAS_WALL_SIGN.get());
		hangingSign(generators, ModBlocks.BLACK_HANGING_CANVAS_SIGN.get(), ModBlocks.BLACK_HANGING_CANVAS_WALL_SIGN.get());
	}

	@Override
	public void generateItemModels(ItemModelGenerators generators) {

	}

	private static void sign(final BlockModelGenerators generators, final Block sign, final Block wallSign) {
		TextureMapping mapping = new TextureMapping()
			.put(TextureSlot.ALL, TextureMapping.getBlockTexture(sign))
			.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(ModBlocks.TATAMI.get()));
		MultiVariant standingRot0 = BlockModelGenerators.plainVariant(
			ModelTemplates.SIGN_ROT_0.create(ModelLocationUtils.getModelLocation(sign, "_rot_0"), mapping, generators.modelOutput)
		);
		MultiVariant standingRot1 = BlockModelGenerators.plainVariant(
			ModelTemplates.SIGN_ROT_1.create(ModelLocationUtils.getModelLocation(sign, "_rot_1"), mapping, generators.modelOutput)
		);
		MultiVariant standingRot2 = BlockModelGenerators.plainVariant(
			ModelTemplates.SIGN_ROT_2.create(ModelLocationUtils.getModelLocation(sign, "_rot_2"), mapping, generators.modelOutput)
		);
		MultiVariant standingRot3 = BlockModelGenerators.plainVariant(
			ModelTemplates.SIGN_ROT_3.create(ModelLocationUtils.getModelLocation(sign, "_rot_3"), mapping, generators.modelOutput)
		);
		generators.blockStateOutput.accept(BlockModelGenerators.createSign(sign, standingRot0, standingRot1, standingRot2, standingRot3));
		MultiVariant wallModel = BlockModelGenerators.plainVariant(ModelTemplates.WALL_SIGN.create(wallSign, mapping, generators.modelOutput));
		generators.blockStateOutput
			.accept(MultiVariantGenerator.dispatch(wallSign, wallModel).with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING_ALT));
		generators.registerSimpleFlatItemModel(sign.asItem());
	}

	private static void hangingSign(final BlockModelGenerators generators, final Block hangingSign, final Block wallSign) {
		TextureMapping mapping = new TextureMapping()
			.put(TextureSlot.ALL, TextureMapping.getBlockTexture(hangingSign))
			.put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(ModBlocks.TATAMI.get()));
		generators.blockStateOutput
			.accept(
				BlockModelGenerators.createHangingSign(
					hangingSign,
					BlockModelGenerators.plainVariant(
						ModelTemplates.HANGING_SIGN_ROT_0.create(ModelLocationUtils.getModelLocation(hangingSign, "_rot_0"), mapping, generators.modelOutput)
					),
					BlockModelGenerators.plainVariant(
						ModelTemplates.HANGING_SIGN_ROT_1.create(ModelLocationUtils.getModelLocation(hangingSign, "_rot_1"), mapping, generators.modelOutput)
					),
					BlockModelGenerators.plainVariant(
						ModelTemplates.HANGING_SIGN_ROT_2.create(ModelLocationUtils.getModelLocation(hangingSign, "_rot_2"), mapping, generators.modelOutput)
					),
					BlockModelGenerators.plainVariant(
						ModelTemplates.HANGING_SIGN_ROT_3.create(ModelLocationUtils.getModelLocation(hangingSign, "_rot_3"), mapping, generators.modelOutput)
					),
					BlockModelGenerators.plainVariant(
						ModelTemplates.ATTACHED_HANGING_SIGN_ROT_0
							.create(ModelLocationUtils.getModelLocation(hangingSign, "_attached_rot_0"), mapping, generators.modelOutput)
					),
					BlockModelGenerators.plainVariant(
						ModelTemplates.ATTACHED_HANGING_SIGN_ROT_1
							.create(ModelLocationUtils.getModelLocation(hangingSign, "_attached_rot_1"), mapping, generators.modelOutput)
					),
					BlockModelGenerators.plainVariant(
						ModelTemplates.ATTACHED_HANGING_SIGN_ROT_2
							.create(ModelLocationUtils.getModelLocation(hangingSign, "_attached_rot_2"), mapping, generators.modelOutput)
					),
					BlockModelGenerators.plainVariant(
						ModelTemplates.ATTACHED_HANGING_SIGN_ROT_3
							.create(ModelLocationUtils.getModelLocation(hangingSign, "_attached_rot_3"), mapping, generators.modelOutput)
					)
				)
			);
		MultiVariant wallModel = BlockModelGenerators.plainVariant(ModelTemplates.WALL_HANGING_SIGN.create(wallSign, mapping, generators.modelOutput));
		generators.blockStateOutput
			.accept(MultiVariantGenerator.dispatch(wallSign, wallModel).with(BlockModelGenerators.ROTATION_HORIZONTAL_FACING_ALT));
		generators.registerSimpleFlatItemModel(hangingSign.asItem());
	}

//	public static final String GENERATED = "item/generated";
//	public static final String HANDHELD = "item/handheld";
//	public static final Identifier MUG = Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "item/mug");
//
//	public ItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
//		super(output, FarmersDelight.MODID, existingFileHelper);
//	}
//
//	@Override
//	protected void registerModels() {
//		Set<Item> items = BuiltInRegistries.ITEM.stream().filter(i -> FarmersDelight.MODID.equals(BuiltInRegistries.ITEM.getKey(i).getNamespace()))
//				.collect(Collectors.toSet());
//
//		// Specific cases
//		items.remove(ModItems.SKILLET.get());
//
//		itemGeneratedModel(ModItems.WILD_RICE.get(), resourceBlock(itemName(ModItems.WILD_RICE.get()) + "_top"));
//		items.remove(ModItems.WILD_RICE.get());
//
//		itemGeneratedModel(ModItems.BROWN_MUSHROOM_COLONY.get(), resourceBlock(itemName(ModItems.BROWN_MUSHROOM_COLONY.get()) + "_stage3"));
//		items.remove(ModItems.BROWN_MUSHROOM_COLONY.get());
//
//		itemGeneratedModel(ModItems.RED_MUSHROOM_COLONY.get(), resourceBlock(itemName(ModItems.RED_MUSHROOM_COLONY.get()) + "_stage3"));
//		items.remove(ModItems.RED_MUSHROOM_COLONY.get());
//
//		blockBasedModel(ModItems.TATAMI.get(), "_half");
//		items.remove(ModItems.TATAMI.get());
//
//		blockBasedModel(ModItems.ORGANIC_COMPOST.get(), "_0");
//		items.remove(ModItems.ORGANIC_COMPOST.get());
//
//		// Items that should be held like a mug
//		Set<Item> mugItems = Sets.newHashSet(
//				ModItems.HOT_COCOA.get(),
//				ModItems.APPLE_CIDER.get(),
//				ModItems.MELON_JUICE.get());
//		takeAll(items, mugItems.toArray(new Item[0])).forEach(item -> itemMugModel(item, resourceItem(itemName(item))));
//
//		// Blocks with special item sprites
//		Set<Item> spriteBlockItems = Sets.newHashSet(
//				ModItems.FULL_TATAMI_MAT.get(),
//				ModItems.HALF_TATAMI_MAT.get(),
//				ModItems.ROPE.get(),
//				ModItems.CANVAS_SIGN.get(),
//				ModItems.HANGING_CANVAS_SIGN.get(),
//				ModItems.WHITE_CANVAS_SIGN.get(),
//				ModItems.WHITE_HANGING_CANVAS_SIGN.get(),
//				ModItems.ORANGE_CANVAS_SIGN.get(),
//				ModItems.ORANGE_HANGING_CANVAS_SIGN.get(),
//				ModItems.MAGENTA_CANVAS_SIGN.get(),
//				ModItems.MAGENTA_HANGING_CANVAS_SIGN.get(),
//				ModItems.LIGHT_BLUE_CANVAS_SIGN.get(),
//				ModItems.LIGHT_BLUE_HANGING_CANVAS_SIGN.get(),
//				ModItems.YELLOW_CANVAS_SIGN.get(),
//				ModItems.YELLOW_HANGING_CANVAS_SIGN.get(),
//				ModItems.LIME_CANVAS_SIGN.get(),
//				ModItems.LIME_HANGING_CANVAS_SIGN.get(),
//				ModItems.PINK_CANVAS_SIGN.get(),
//				ModItems.PINK_HANGING_CANVAS_SIGN.get(),
//				ModItems.GRAY_CANVAS_SIGN.get(),
//				ModItems.GRAY_HANGING_CANVAS_SIGN.get(),
//				ModItems.LIGHT_GRAY_CANVAS_SIGN.get(),
//				ModItems.LIGHT_GRAY_HANGING_CANVAS_SIGN.get(),
//				ModItems.CYAN_CANVAS_SIGN.get(),
//				ModItems.CYAN_HANGING_CANVAS_SIGN.get(),
//				ModItems.PURPLE_CANVAS_SIGN.get(),
//				ModItems.PURPLE_HANGING_CANVAS_SIGN.get(),
//				ModItems.BLUE_CANVAS_SIGN.get(),
//				ModItems.BLUE_HANGING_CANVAS_SIGN.get(),
//				ModItems.BROWN_CANVAS_SIGN.get(),
//				ModItems.BROWN_HANGING_CANVAS_SIGN.get(),
//				ModItems.GREEN_CANVAS_SIGN.get(),
//				ModItems.GREEN_HANGING_CANVAS_SIGN.get(),
//				ModItems.RED_CANVAS_SIGN.get(),
//				ModItems.RED_HANGING_CANVAS_SIGN.get(),
//				ModItems.BLACK_CANVAS_SIGN.get(),
//				ModItems.BLACK_HANGING_CANVAS_SIGN.get(),
//				ModItems.APPLE_PIE.get(),
//				ModItems.SWEET_BERRY_CHEESECAKE.get(),
//				ModItems.CHOCOLATE_PIE.get(),
//				ModItems.CABBAGE_SEEDS.get(),
//				ModItems.TOMATO_SEEDS.get(),
//				ModItems.ONION.get(),
//				ModItems.RICE.get(),
//				ModItems.ROAST_CHICKEN_BLOCK.get(),
//				ModItems.STUFFED_PUMPKIN_BLOCK.get(),
//				ModItems.HONEY_GLAZED_HAM_BLOCK.get(),
//				ModItems.SHEPHERDS_PIE_BLOCK.get(),
//				ModItems.RICE_ROLL_MEDLEY_BLOCK.get()
//		);
//		takeAll(items, spriteBlockItems.toArray(new Item[0])).forEach(item -> withExistingParent(itemName(item), GENERATED).texture("layer0", resourceItem(itemName(item))));
//
//		// Blocks with flat block textures for their items
//		Set<Item> flatBlockItems = Sets.newHashSet(
//				ModItems.SAFETY_NET.get(),
//				ModItems.SANDY_SHRUB.get(),
//				ModItems.WILD_BEETROOTS.get(),
//				ModItems.WILD_CABBAGES.get(),
//				ModItems.WILD_CARROTS.get(),
//				ModItems.WILD_ONIONS.get(),
//				ModItems.WILD_POTATOES.get(),
//				ModItems.WILD_TOMATOES.get()
//		);
//		takeAll(items, flatBlockItems.toArray(new Item[0])).forEach(item -> itemGeneratedModel(item, resourceBlock(itemName(item))));
//
//		// Blocks whose items look alike
//		takeAll(items, i -> i instanceof BlockItem).forEach(item -> blockBasedModel(item, ""));
//
//		// Handheld items
//		Set<Item> handheldItems = Sets.newHashSet(
//				ModItems.BARBECUE_STICK.get(),
//				ModItems.HAM.get(),
//				ModItems.SMOKED_HAM.get(),
//				ModItems.FLINT_KNIFE.get(),
//				ModItems.IRON_KNIFE.get(),
//				ModItems.DIAMOND_KNIFE.get(),
//				ModItems.GOLDEN_KNIFE.get(),
//				ModItems.NETHERITE_KNIFE.get()
//		);
//		takeAll(items, handheldItems.toArray(new Item[0])).forEach(item -> itemHandheldModel(item, resourceItem(itemName(item))));
//
//		// Generated items
//		items.forEach(item -> itemGeneratedModel(item, resourceItem(itemName(item))));
//	}
//
//	public void blockBasedModel(Item item, String suffix) {
//		withExistingParent(itemName(item), resourceBlock(itemName(item) + suffix));
//	}
//
//	public void itemHandheldModel(Item item, Identifier texture) {
//		withExistingParent(itemName(item), HANDHELD).texture("layer0", texture);
//	}
//
//	public void itemGeneratedModel(Item item, Identifier texture) {
//		withExistingParent(itemName(item), GENERATED).texture("layer0", texture);
//	}
//
//	public void itemMugModel(Item item, Identifier texture) {
//		withExistingParent(itemName(item), MUG).texture("layer0", texture);
//	}
//
//	private String itemName(Item item) {
//		return BuiltInRegistries.ITEM.getKey(item).getPath();
//	}
//
//	public Identifier resourceBlock(String path) {
//		return Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "block/" + path);
//	}
//
//	public Identifier resourceItem(String path) {
//		return Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "item/" + path);
//	}
//
//	@SafeVarargs
//	@SuppressWarnings("varargs")
//	public static <T> Collection<T> takeAll(Set<? extends T> src, T... items) {
//		List<T> ret = Arrays.asList(items);
//		for (T item : items) {
//			if (!src.contains(item)) {
//				FarmersDelight.LOGGER.warn("Item {} not found in set", item);
//			}
//		}
//		if (!src.removeAll(ret)) {
//			FarmersDelight.LOGGER.warn("takeAll array didn't yield anything ({})", Arrays.toString(items));
//		}
//		return ret;
//	}
//
//	public static <T> Collection<T> takeAll(Set<T> src, Predicate<T> pred) {
//		List<T> ret = new ArrayList<>();
//
//		Iterator<T> iter = src.iterator();
//		while (iter.hasNext()) {
//			T item = iter.next();
//			if (pred.test(item)) {
//				iter.remove();
//				ret.add(item);
//			}
//		}
//
//		if (ret.isEmpty()) {
//			FarmersDelight.LOGGER.warn("takeAll predicate yielded nothing", new Throwable());
//		}
//		return ret;
//	}
}