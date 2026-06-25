package vectorwing.farmersdelight.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jspecify.annotations.NonNull;
import vectorwing.farmersdelight.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.common.tag.CompatibilityTags;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.refabricated.FDRefabricatedTags;
import vectorwing.farmersdelight.refabricated.FDRefabricatedTags;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class BlockTags extends FabricTagProvider.BlockTagProvider
{
	public BlockTags(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, lookupProvider);
	}

	@Override
	protected void addTags(HolderLookup.@NonNull Provider provider) {
		this.registerModTags();
		this.registerRefabricatedTags();
		this.registerMinecraftTags();
		this.registerNeoForgeTags();
		this.registerCommonTags();
		this.registerCompatibilityTags();

		this.registerBlockMineables();
	}

	protected void registerBlockMineables() {
		tagBuilder(net.minecraft.tags.BlockTags.MINEABLE_WITH_AXE).add(
			ModBlocks.WOODEN_BASKET.get(),
			ModBlocks.BAMBOO_BASKET.get(),
			ModBlocks.ROPE_FENCE.get(),
			ModBlocks.ROPE_FENCE_GATE.get(),
			ModBlocks.CUTTING_BOARD.get(),
			ModBlocks.CARROT_CRATE.get(),
			ModBlocks.POTATO_CRATE.get(),
			ModBlocks.BEETROOT_CRATE.get(),
			ModBlocks.CABBAGE_CRATE.get(),
			ModBlocks.TOMATO_CRATE.get(),
			ModBlocks.ONION_CRATE.get(),
			ModBlocks.OAK_CABINET.get(),
			ModBlocks.BIRCH_CABINET.get(),
			ModBlocks.SPRUCE_CABINET.get(),
			ModBlocks.JUNGLE_CABINET.get(),
			ModBlocks.ACACIA_CABINET.get(),
			ModBlocks.DARK_OAK_CABINET.get(),
			ModBlocks.MANGROVE_CABINET.get(),
			ModBlocks.CHERRY_CABINET.get(),
			ModBlocks.BAMBOO_CABINET.get(),
			ModBlocks.CRIMSON_CABINET.get(),
			ModBlocks.WARPED_CABINET.get(),
			ModBlocks.SANDY_SHRUB.get(),
			ModBlocks.ROAST_CHICKEN_BLOCK.get(),
			ModBlocks.STUFFED_PUMPKIN_BLOCK.get(),
			ModBlocks.SHEPHERDS_PIE_BLOCK.get(),
			ModBlocks.HONEY_GLAZED_HAM_BLOCK.get(),
			ModBlocks.GLEAMING_SALAD_BLOCK.get(),
			ModBlocks.RICE_ROLL_MEDLEY_BLOCK.get()
		);
		tagBuilder(net.minecraft.tags.BlockTags.MINEABLE_WITH_HOE).add(
			ModBlocks.RICE_BALE.get(),
			ModBlocks.STRAW_BALE.get()
		);
		tagBuilder(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE).add(
			ModBlocks.STOVE.get(),
			ModBlocks.COOKING_POT.get(),
			ModBlocks.SKILLET.get()
		);
		tagBuilder(net.minecraft.tags.BlockTags.MINEABLE_WITH_SHOVEL).add(
			ModBlocks.ORGANIC_COMPOST.get(),
			ModBlocks.RICH_SOIL.get(),
			ModBlocks.RICH_SOIL_FARMLAND.get()
		);
		tagBuilder(ModTags.Blocks.MINEABLE_WITH_KNIFE).add(
				Blocks.CACTUS,
				Blocks.MELON,
				Blocks.PUMPKIN,
				Blocks.CARVED_PUMPKIN,
				Blocks.JACK_O_LANTERN,
				Blocks.COBWEB,
				Blocks.CAKE,
				ModBlocks.RICE_BAG.get(),
				ModBlocks.APPLE_PIE.get(),
				ModBlocks.SWEET_BERRY_CHEESECAKE.get(),
				ModBlocks.CHOCOLATE_PIE.get(),
				ModBlocks.PUMPKIN_PIE.get())
			.addTag(net.minecraft.tags.BlockTags.WOOL_CARPETS)
			.addTag(net.minecraft.tags.BlockTags.WOOL)
			.addTag(net.minecraft.tags.BlockTags.CANDLE_CAKES)
			.addTag(ModTags.Blocks.STRAW_BLOCKS)
			.addTag(CommonTags.Blocks.MINEABLE_WITH_KNIFE);
		tagBuilder(CommonTags.Blocks.MINEABLE_WITH_KNIFE);
	}

	protected void registerMinecraftTags() {
		tagBuilder(net.minecraft.tags.BlockTags.CLIMBABLE).add(
				ModBlocks.ROPE.get(),
				ModBlocks.TOMATO_CROP_ON_ROPE.get());
		tagBuilder(net.minecraft.tags.BlockTags.FENCES).add(ModBlocks.ROPE_FENCE.get());
		tagBuilder(net.minecraft.tags.BlockTags.FENCE_GATES).add(ModBlocks.ROPE_FENCE_GATE.get());
		tagBuilder(net.minecraft.tags.BlockTags.REPLACEABLE).add(
				ModBlocks.SANDY_SHRUB.get());
		tagBuilder(net.minecraft.tags.BlockTags.REPLACEABLE_BY_TREES).add(
				ModBlocks.SANDY_SHRUB.get());
		valueLookupBuilder(net.minecraft.tags.BlockTags.BAMBOO_PLANTABLE_ON).add(
			ModBlocks.RICH_SOIL.get());
		tagBuilder(net.minecraft.tags.BlockTags.DIRT).add(
			ModBlocks.RICH_SOIL.get(),
			ModBlocks.RICH_SOIL_FARMLAND.get());
		tagBuilder(net.minecraft.tags.BlockTags.MUSHROOM_GROW_BLOCK).add(
			ModBlocks.ORGANIC_COMPOST.get(),
			ModBlocks.RICH_SOIL.get());
		tagBuilder(net.minecraft.tags.BlockTags.CROPS).add(
				ModBlocks.CABBAGE_CROP.get(),
				ModBlocks.ONION_CROP.get(),
				ModBlocks.RICE_CROP_PANICLES.get(),
				ModBlocks.BUDDING_TOMATO_CROP.get(),
				ModBlocks.TOMATO_CROP.get(),
				ModBlocks.TOMATO_CROP_ON_ROPE.get());
		tagBuilder(net.minecraft.tags.BlockTags.STANDING_SIGNS).add(
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
				ModBlocks.BLACK_CANVAS_SIGN.get());
		tagBuilder(net.minecraft.tags.BlockTags.WALL_SIGNS).add(
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
				ModBlocks.BLACK_CANVAS_WALL_SIGN.get());
		tagBuilder(net.minecraft.tags.BlockTags.CEILING_HANGING_SIGNS).add(
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
				ModBlocks.BLACK_HANGING_CANVAS_SIGN.get());
		tagBuilder(net.minecraft.tags.BlockTags.WALL_HANGING_SIGNS).add(
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
				ModBlocks.BLACK_HANGING_CANVAS_WALL_SIGN.get());
		tagBuilder(net.minecraft.tags.BlockTags.SMALL_FLOWERS).add(
				ModBlocks.WILD_CARROTS.get(),
				ModBlocks.WILD_POTATOES.get(),
				ModBlocks.WILD_BEETROOTS.get(),
				ModBlocks.WILD_CABBAGES.get(),
				ModBlocks.WILD_TOMATOES.get(),
				ModBlocks.WILD_ONIONS.get()
		);
		tagBuilder(ConventionalBlockTags.TALL_FLOWERS).add(ModBlocks.WILD_RICE.get());
		tagBuilder(net.minecraft.tags.BlockTags.DIRT).add(
				ModBlocks.RICH_SOIL.get());
		tagBuilder(net.minecraft.tags.BlockTags.MAINTAINS_FARMLAND).add(
				ModBlocks.CABBAGE_CROP.get(),
				ModBlocks.BUDDING_TOMATO_CROP.get(),
				ModBlocks.TOMATO_CROP.get(),
				ModBlocks.ONION_CROP.get(),
				ModBlocks.RICE_CROP.get()
		);
		tagBuilder(net.minecraft.tags.BlockTags.COMBINATION_STEP_SOUND_BLOCKS).add(
				ModBlocks.CANVAS_RUG.get(),
				ModBlocks.FULL_TATAMI_MAT.get(),
				ModBlocks.HALF_TATAMI_MAT.get(),
				ModBlocks.CUTTING_BOARD.get()
		);
	}

	protected void registerNeoForgeTags() {
		tagBuilder(ConventionalBlockTags.ROPES).add(ModBlocks.ROPE.get());
		//This is a neoforge tag. i think we alreadey have a mixin that takes care of this
		//tagBuilder(ConventionalBlockTags.VILLAGER_FARMLANDS).add(ModBlocks.RICH_SOIL_FARMLAND.get());
		tagBuilder(ConventionalBlockTags.FENCES).add(ModBlocks.ROPE_FENCE.get());
		tagBuilder(ConventionalBlockTags.FENCE_GATES).add(ModBlocks.ROPE_FENCE_GATE.get());

		tagBuilder(ConventionalBlockTags.STORAGE_BLOCKS).addTags(
                CommonTags.Blocks.STORAGE_BLOCKS_CARROT,
			CommonTags.Blocks.STORAGE_BLOCKS_POTATO,
			CommonTags.Blocks.STORAGE_BLOCKS_BEETROOT,
			CommonTags.Blocks.STORAGE_BLOCKS_CABBAGE,
			CommonTags.Blocks.STORAGE_BLOCKS_TOMATO,
			CommonTags.Blocks.STORAGE_BLOCKS_ONION,
			CommonTags.Blocks.STORAGE_BLOCKS_RICE,
			CommonTags.Blocks.STORAGE_BLOCKS_RICE_PANICLE,
			CommonTags.Blocks.STORAGE_BLOCKS_STRAW
        );
    }

    protected void registerCommonTags() {
        tagBuilder(CommonTags.Blocks.MINEABLE_WITH_KNIFE);
        tagBuilder(CommonTags.Blocks.STORAGE_BLOCKS_CARROT).add(ModBlocks.CARROT_CRATE.get());
        tagBuilder(CommonTags.Blocks.STORAGE_BLOCKS_POTATO).add(ModBlocks.POTATO_CRATE.get());
        tagBuilder(CommonTags.Blocks.STORAGE_BLOCKS_BEETROOT).add(ModBlocks.BEETROOT_CRATE.get());
        tagBuilder(CommonTags.Blocks.STORAGE_BLOCKS_CABBAGE).add(ModBlocks.CABBAGE_CRATE.get());
        tagBuilder(CommonTags.Blocks.STORAGE_BLOCKS_TOMATO).add(ModBlocks.TOMATO_CRATE.get());
        tagBuilder(CommonTags.Blocks.STORAGE_BLOCKS_ONION).add(ModBlocks.ONION_CRATE.get());
        tagBuilder(CommonTags.Blocks.STORAGE_BLOCKS_RICE).add(ModBlocks.RICE_BAG.get());
        tagBuilder(CommonTags.Blocks.STORAGE_BLOCKS_RICE_PANICLE).add(ModBlocks.RICE_BALE.get());
        tagBuilder(CommonTags.Blocks.STORAGE_BLOCKS_STRAW).add(ModBlocks.STRAW_BALE.get());
    }

	protected void registerModTags() {
		tagBuilder(ModTags.Blocks.FEASTS).add(
			ModBlocks.ROAST_CHICKEN_BLOCK.get(),
			ModBlocks.STUFFED_PUMPKIN_BLOCK.get(),
			ModBlocks.SHEPHERDS_PIE_BLOCK.get(),
			ModBlocks.HONEY_GLAZED_HAM_BLOCK.get(),
			ModBlocks.GLEAMING_SALAD_BLOCK.get(),
			ModBlocks.RICE_ROLL_MEDLEY_BLOCK.get()
		);
		tagBuilder(ModTags.Blocks.PIES).add(
			ModBlocks.APPLE_PIE.get(),
			ModBlocks.SWEET_BERRY_CHEESECAKE.get(),
			ModBlocks.CHOCOLATE_PIE.get(),
			ModBlocks.PUMPKIN_PIE.get()
		);
		tagBuilder(ModTags.Blocks.TERRAIN)
				.addTag(net.minecraft.tags.BlockTags.DIRT)
				.addTag(net.minecraft.tags.BlockTags.SAND);
		tagBuilder(ModTags.Blocks.STRAW_BLOCKS).add(
			ModBlocks.ROPE.get(),
			ModBlocks.SAFETY_NET.get(),
			ModBlocks.CANVAS_RUG.get(),
			ModBlocks.TATAMI.get(),
			ModBlocks.FULL_TATAMI_MAT.get(),
			ModBlocks.HALF_TATAMI_MAT.get()
		);
		tagBuilder(ModTags.Blocks.WILD_CROPS).add(
			ModBlocks.WILD_CARROTS.get(),
			ModBlocks.WILD_POTATOES.get(),
			ModBlocks.WILD_BEETROOTS.get(),
			ModBlocks.WILD_CABBAGES.get(),
			ModBlocks.WILD_TOMATOES.get(),
			ModBlocks.WILD_ONIONS.get(),
			ModBlocks.WILD_RICE.get());
		tagBuilder(ModTags.Blocks.CABINETS_WOODEN)
				.add(ModBlocks.OAK_CABINET.get())
				.add(ModBlocks.SPRUCE_CABINET.get())
				.add(ModBlocks.BIRCH_CABINET.get())
				.add(ModBlocks.JUNGLE_CABINET.get())
				.add(ModBlocks.ACACIA_CABINET.get())
				.add(ModBlocks.DARK_OAK_CABINET.get())
				.add(ModBlocks.MANGROVE_CABINET.get())
				.add(ModBlocks.CHERRY_CABINET.get())
				.add(ModBlocks.BAMBOO_CABINET.get())
				.add(ModBlocks.CRIMSON_CABINET.get())
				.add(ModBlocks.WARPED_CABINET.get())
				.add(ModBlocks.PALE_OAK_CABINET.get());
		tagBuilder(ModTags.Blocks.CABINETS).addTag(ModTags.Blocks.CABINETS_WOODEN);
		tagBuilder(ModTags.Blocks.MUSHROOM_COLONIES)
			.add(ModBlocks.BROWN_MUSHROOM_COLONY.get())
			.add(ModBlocks.RED_MUSHROOM_COLONY.get());
		tagBuilder(ModTags.Blocks.ROPES).add(ModBlocks.ROPE.get())
				.addOptional(Identifier.parse("quark:rope"))
				.addOptional(Identifier.parse("supplementaries:rope"));
		tagBuilder(ModTags.Blocks.TRAY_HEAT_SOURCES).add(
				Blocks.LAVA)
			.addTag(net.minecraft.tags.BlockTags.CAMPFIRES)
			.addTag(net.minecraft.tags.BlockTags.FIRE);
		tagBuilder(ModTags.Blocks.HEAT_SOURCES).add(
				Blocks.MAGMA_BLOCK,
				Blocks.LAVA_CAULDRON,
				ModBlocks.STOVE.get())
			.addTag(ModTags.Blocks.TRAY_HEAT_SOURCES);
		tagBuilder(ModTags.Blocks.HEAT_CONDUCTORS).add(
						Blocks.HOPPER)
				.addOptional(Identifier.parse("create:chute"));
		tagBuilder(ModTags.Blocks.COMPOST_ACTIVATORS).add(
				Blocks.BROWN_MUSHROOM,
				Blocks.RED_MUSHROOM,
				Blocks.PODZOL,
				Blocks.MYCELIUM,
				ModBlocks.ORGANIC_COMPOST.get(),
				ModBlocks.RICH_SOIL.get(),
				ModBlocks.RICH_SOIL_FARMLAND.get())
			.addTag(ModTags.Blocks.MUSHROOM_COLONIES);
		tagBuilder(ModTags.Blocks.UNAFFECTED_BY_RICH_SOIL).add(
				Blocks.GRASS_BLOCK,
				Blocks.SHORT_GRASS,
				Blocks.MOSS_BLOCK,
				Blocks.CRIMSON_NYLIUM,
				Blocks.WARPED_NYLIUM,
				Blocks.FERN,
				Blocks.TWISTING_VINES,
				Blocks.TWISTING_VINES_PLANT,
				Blocks.BIG_DRIPLEAF,
				Blocks.BIG_DRIPLEAF_STEM,
				Blocks.PINK_PETALS,
				ModBlocks.SANDY_SHRUB.get())
			.addTag(ModTags.Blocks.MUSHROOM_COLONIES)
			.addTag(ModTags.Blocks.WILD_CROPS)
			.addTag(ConventionalBlockTags.TALL_FLOWERS);
		tagBuilder(ModTags.Blocks.DROPS_CAKE_SLICE).add(
			Blocks.CANDLE_CAKE,
			Blocks.WHITE_CANDLE_CAKE,
			Blocks.ORANGE_CANDLE_CAKE,
			Blocks.MAGENTA_CANDLE_CAKE,
			Blocks.LIGHT_BLUE_CANDLE_CAKE,
			Blocks.YELLOW_CANDLE_CAKE,
			Blocks.LIME_CANDLE_CAKE,
			Blocks.PINK_CANDLE_CAKE,
			Blocks.GRAY_CANDLE_CAKE,
			Blocks.LIGHT_GRAY_CANDLE_CAKE,
			Blocks.CYAN_CANDLE_CAKE,
			Blocks.PURPLE_CANDLE_CAKE,
			Blocks.BLUE_CANDLE_CAKE,
			Blocks.BROWN_CANDLE_CAKE,
			Blocks.GREEN_CANDLE_CAKE,
			Blocks.RED_CANDLE_CAKE,
			Blocks.BLACK_CANDLE_CAKE);
		tagBuilder(ModTags.Blocks.CAMPFIRE_SIGNAL_SMOKE).add(ModBlocks.STRAW_BALE.get()).add(ModBlocks.RICE_BALE.get());
		tagBuilder(ModTags.Blocks.PLANTED_FROM_BELOW).add(Blocks.CAVE_VINES, Blocks.CAVE_VINES_PLANT);
	}

	private void registerRefabricatedTags() {
		tagBuilder(FDRefabricatedTags.Blocks.GROWS_MUSHROOM_COLONIES)
			.add(ModBlocks.RICH_SOIL.get())
			.addTag(ModTags.Blocks.MUSHROOM_COLONY_GROWABLE_ON);
		tagBuilder(FDRefabricatedTags.Blocks.GROWS_WILD_CROPS)
			.addTag(net.minecraft.tags.BlockTags.DIRT)
			.addTag(net.minecraft.tags.BlockTags.SAND);
		tagBuilder(FDRefabricatedTags.Blocks.KNIFE_INSTANTLY_MINES)
			.addTag(net.minecraft.tags.BlockTags.SWORD_INSTANTLY_MINES);
	}

	private void registerCompatibilityTags() {
		tagBuilder(CompatibilityTags.CREATE_FAN_TRANSPARENT).add(ModBlocks.SAFETY_NET.get());
		tagBuilder(CompatibilityTags.CREATE_PASSIVE_BOILER_HEATERS).add(ModBlocks.STOVE.get());
		tagBuilder(CompatibilityTags.CREATE_BRITTLE).add(
				ModBlocks.CUTTING_BOARD.get(),
				ModBlocks.FULL_TATAMI_MAT.get(),
				ModBlocks.HALF_TATAMI_MAT.get())
			.addTag(ModTags.Blocks.FEASTS)
			.addTag(ModTags.Blocks.PIES);

		tagBuilder(CompatibilityTags.SABLE_SUPER_LIGHT)
			.add(ModBlocks.CUTTING_BOARD.get())
			.add(ModBlocks.CANVAS_RUG.get())
			.add(ModBlocks.FULL_TATAMI_MAT.get())
			.add(ModBlocks.HALF_TATAMI_MAT.get())
			.add(ModBlocks.SAFETY_NET.get());
		tagBuilder(CompatibilityTags.SABLE_LIGHT)
			.addTag(ModTags.Blocks.CABINETS)
			.add(ModBlocks.WOODEN_BASKET.get())
			.add(ModBlocks.BAMBOO_BASKET.get());

		tagBuilder(CompatibilityTags.SERENE_SEASONS_AUTUMN_CROPS_BLOCK).add(
			ModBlocks.CABBAGE_CROP.get(),
			ModBlocks.ONION_CROP.get(),
			ModBlocks.RICE_CROP.get(),
			ModBlocks.RICE_CROP_PANICLES.get());
		tagBuilder(CompatibilityTags.SERENE_SEASONS_SPRING_CROPS_BLOCK).add(
			ModBlocks.ONION_CROP.get());
		tagBuilder(CompatibilityTags.SERENE_SEASONS_SUMMER_CROPS_BLOCK).add(
			ModBlocks.BUDDING_TOMATO_CROP.get(),
			ModBlocks.TOMATO_CROP.get(),
			ModBlocks.TOMATO_CROP_ON_ROPE.get(),
			ModBlocks.RICE_CROP.get(),
			ModBlocks.RICE_CROP_PANICLES.get());
		tagBuilder(CompatibilityTags.SERENE_SEASONS_WINTER_CROPS_BLOCK).add(
			ModBlocks.CABBAGE_CROP.get());
		tagBuilder(CompatibilityTags.SERENE_SEASONS_UNBREAKABLE_FERTILE_CROPS).add(
			ModBlocks.ONION_CROP.get());
	}

	private BlockTags.RefabricatedTagBuilder tagBuilder(TagKey<Block> tagKey) {
		return new BlockTags.RefabricatedTagBuilder(tagKey);
	}

	public class RefabricatedTagBuilder {
		private TagAppender<Block, Block> valueLookupBuilder;

		private TagBuilder rawBuilder;

		public RefabricatedTagBuilder(TagKey<Block> tag) {
			this.valueLookupBuilder = valueLookupBuilder(tag);

			this.rawBuilder = getOrCreateRawBuilder(tag);
		}

		public BlockTags.RefabricatedTagBuilder add(Block item) {
			valueLookupBuilder = valueLookupBuilder.add(item);
			return this;
		}

		public BlockTags.RefabricatedTagBuilder add(Supplier<Block> item) {
			valueLookupBuilder = valueLookupBuilder.add(item.get());
			return this;
		}

		public BlockTags.RefabricatedTagBuilder addOptionalTag(TagKey<Block> itemTagKey) {
			valueLookupBuilder = valueLookupBuilder.addOptionalTag(itemTagKey);
			return this;
		}

		public BlockTags.RefabricatedTagBuilder add(Block... items) {
			valueLookupBuilder = valueLookupBuilder.add(items);
			return this;
		}

		public BlockTags.RefabricatedTagBuilder addOptional(Identifier item) {
			rawBuilder = rawBuilder.addOptionalElement(item);
			return this;
		}

		@SafeVarargs
		public final void addTags(TagKey<Block>... tags) {
			for (TagKey<Block> tag : tags) {
				valueLookupBuilder.addTag(tag);
			}
		}

		public BlockTags.RefabricatedTagBuilder addTag(TagKey<Block> tagKey) {
			valueLookupBuilder.forceAddTag(tagKey);
			return this;
		}
	}
}
