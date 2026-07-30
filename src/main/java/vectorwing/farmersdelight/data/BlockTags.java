package vectorwing.farmersdelight.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.BlockItemTagAppender;
import net.minecraft.references.BlockIds;
import net.minecraft.references.BlockItemId;
import net.minecraft.references.BlockItemIds;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ColorCollection;
import org.jspecify.annotations.NonNull;
import vectorwing.farmersdelight.common.references.ModBlockIds;
import vectorwing.farmersdelight.common.references.ModBlockItemIds;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.common.tag.CompatibilityTags;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.refabricated.FDRefabricatedTags;

import java.util.concurrent.CompletableFuture;

public class BlockTags extends FabricTagsProvider.BlockTagsProvider
{
	public BlockTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
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
			ModBlockItemIds.WOODEN_BASKET,
			ModBlockItemIds.BAMBOO_BASKET,
			ModBlockItemIds.ROPE_FENCE,
			ModBlockItemIds.ROPE_FENCE_GATE,
			ModBlockItemIds.CUTTING_BOARD,
			ModBlockItemIds.CARROT_CRATE,
			ModBlockItemIds.POTATO_CRATE,
			ModBlockItemIds.BEETROOT_CRATE,
			ModBlockItemIds.CABBAGE_CRATE,
			ModBlockItemIds.TOMATO_CRATE,
			ModBlockItemIds.ONION_CRATE,
			ModBlockItemIds.OAK_CABINET,
			ModBlockItemIds.BIRCH_CABINET,
			ModBlockItemIds.SPRUCE_CABINET,
			ModBlockItemIds.JUNGLE_CABINET,
			ModBlockItemIds.ACACIA_CABINET,
			ModBlockItemIds.DARK_OAK_CABINET,
			ModBlockItemIds.MANGROVE_CABINET,
			ModBlockItemIds.PALE_OAK_CABINET,	
			ModBlockItemIds.CHERRY_CABINET,
			ModBlockItemIds.BAMBOO_CABINET,
			ModBlockItemIds.CRIMSON_CABINET,
			ModBlockItemIds.WARPED_CABINET,
			ModBlockItemIds.SANDY_SHRUB,
			ModBlockItemIds.ROAST_CHICKEN_BLOCK,
			ModBlockItemIds.STUFFED_PUMPKIN_BLOCK,
			ModBlockItemIds.SHEPHERDS_PIE_BLOCK,
			ModBlockItemIds.HONEY_GLAZED_HAM_BLOCK,
			ModBlockItemIds.GLEAMING_SALAD_BLOCK,
			ModBlockItemIds.RICE_ROLL_MEDLEY_BLOCK
		);
		tagBuilder(net.minecraft.tags.BlockTags.MINEABLE_WITH_HOE).add(
			ModBlockItemIds.RICE_BALE,
			ModBlockItemIds.STRAW_BALE
		);
		tagBuilder(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE).add(
			ModBlockItemIds.STOVE,
			ModBlockItemIds.COOKING_POT,
			ModBlockItemIds.SKILLET
		);
		tagBuilder(net.minecraft.tags.BlockTags.MINEABLE_WITH_SHOVEL).add(
			ModBlockItemIds.ORGANIC_COMPOST,
			ModBlockItemIds.RICH_SOIL,
			ModBlockItemIds.RICH_SOIL_FARMLAND
		);
		tagBuilder(ModTags.Blocks.MINEABLE_WITH_KNIFE).add(
				BlockItemIds.CACTUS,
				BlockItemIds.MELON,
				BlockItemIds.PUMPKIN,
				BlockItemIds.CARVED_PUMPKIN,
				BlockItemIds.JACK_O_LANTERN,
				BlockItemIds.COBWEB,
				BlockItemIds.CAKE,
				ModBlockItemIds.RICE_BAG,
				ModBlockItemIds.APPLE_PIE,
				ModBlockItemIds.SWEET_BERRY_CHEESECAKE,
				ModBlockItemIds.CHOCOLATE_PIE).add(
				ModBlockIds.PUMPKIN_PIE)
			.addTag(net.minecraft.tags.BlockTags.WOOL_CARPETS)
			.addTag(net.minecraft.tags.BlockTags.WOOL)
			.addTag(net.minecraft.tags.BlockTags.CANDLE_CAKES)
			.addTag(ModTags.Blocks.STRAW_BLOCKS)
			.addTag(CommonTags.Blocks.MINEABLE_WITH_KNIFE);
		tagBuilder(CommonTags.Blocks.MINEABLE_WITH_KNIFE);
	}

	protected void registerMinecraftTags() {
		tagBuilder(net.minecraft.tags.BlockTags.CLIMBABLE).add(
			ModBlockItemIds.ROPE).add(
			ModBlockIds.TOMATO_CROP_ON_ROPE);
		tagBuilder(net.minecraft.tags.BlockTags.FENCES).add(ModBlockItemIds.ROPE_FENCE);
		tagBuilder(net.minecraft.tags.BlockTags.FENCE_GATES).add(ModBlockItemIds.ROPE_FENCE_GATE);
		tagBuilder(net.minecraft.tags.BlockTags.REPLACEABLE).add(
				ModBlockItemIds.SANDY_SHRUB);
		tagBuilder(net.minecraft.tags.BlockTags.REPLACEABLE_BY_TREES).add(
				ModBlockItemIds.SANDY_SHRUB);
		tagBuilder(net.minecraft.tags.BlockTags.GROWS_CROPS).add(
			ModBlockItemIds.RICH_SOIL_FARMLAND);
		tagBuilder(net.minecraft.tags.BlockTags.SUPPORTS_AZALEA).add(
			ModBlockItemIds.RICH_SOIL,
			ModBlockItemIds.RICH_SOIL_FARMLAND
		);
		tagBuilder(net.minecraft.tags.BlockTags.SUPPORTS_BAMBOO).add(
			ModBlockItemIds.RICH_SOIL);
		tagBuilder(net.minecraft.tags.BlockTags.SUPPORTS_BIG_DRIPLEAF).add(
			ModBlockItemIds.RICH_SOIL,
			ModBlockItemIds.RICH_SOIL_FARMLAND);
		tagBuilder(net.minecraft.tags.BlockTags.SUPPORT_OVERRIDE_CACTUS_FLOWER).add(
			ModBlockItemIds.RICH_SOIL_FARMLAND);
		tagBuilder(net.minecraft.tags.BlockTags.SUPPORTS_CROPS).add(
			ModBlockItemIds.RICH_SOIL_FARMLAND);
		tagBuilder(net.minecraft.tags.BlockTags.SUPPORTS_SMALL_DRIPLEAF).add(
			ModBlockItemIds.RICH_SOIL);
		tagBuilder(net.minecraft.tags.BlockTags.SUPPORTS_SUGAR_CANE).add(
			ModBlockItemIds.RICH_SOIL);
		tagBuilder(net.minecraft.tags.BlockTags.SUPPORTS_VEGETATION).add(
			ModBlockItemIds.RICH_SOIL,
			ModBlockItemIds.RICH_SOIL_FARMLAND);
		tagBuilder(net.minecraft.tags.BlockTags.OVERRIDES_MUSHROOM_LIGHT_REQUIREMENT).add(
			ModBlockItemIds.ORGANIC_COMPOST,
			ModBlockItemIds.RICH_SOIL);
		tagBuilder(net.minecraft.tags.BlockTags.CROPS).add(
			ModBlockItemIds.CABBAGE_CROP,
			ModBlockItemIds.ONION_CROP,
			ModBlockItemIds.RICE_CROP_PANICLES,
			ModBlockItemIds.TOMATO_CROP).add(
			ModBlockIds.BUDDING_TOMATO_CROP,
			ModBlockIds.TOMATO_CROP_ON_ROPE
		);
		tagBuilder(net.minecraft.tags.BlockTags.STANDING_SIGNS).add(
				ModBlockItemIds.CANVAS_SIGN).addColorId(
				ModBlockItemIds.DYED_CANVAS_SIGN
		);
		tagBuilder(net.minecraft.tags.BlockTags.WALL_SIGNS).add(
				ModBlockIds.CANVAS_WALL_SIGN).addColorKey(
				ModBlockIds.DYED_CANVAS_WALL_SIGN
		);
		tagBuilder(net.minecraft.tags.BlockTags.CEILING_HANGING_SIGNS).add(
				ModBlockItemIds.HANGING_CANVAS_SIGN).addColorId(
				ModBlockItemIds.DYED_HANGING_CANVAS_SIGN
		);
		tagBuilder(net.minecraft.tags.BlockTags.WALL_HANGING_SIGNS).add(
				ModBlockIds.HANGING_CANVAS_WALL_SIGN).addColorKey(
				ModBlockIds.DYED_HANGING_CANVAS_WALL_SIGN
		);
		tagBuilder(net.minecraft.tags.BlockTags.SMALL_FLOWERS).add(
				ModBlockItemIds.WILD_CARROTS,
				ModBlockItemIds.WILD_POTATOES,
				ModBlockItemIds.WILD_BEETROOTS,
				ModBlockItemIds.WILD_CABBAGES,
				ModBlockItemIds.WILD_TOMATOES,
				ModBlockItemIds.WILD_ONIONS
		);
		tagBuilder(ConventionalBlockTags.TALL_FLOWERS).add(ModBlockItemIds.WILD_RICE);
		tagBuilder(net.minecraft.tags.BlockTags.DIRT).add(
				ModBlockItemIds.RICH_SOIL);
		tagBuilder(net.minecraft.tags.BlockTags.MAINTAINS_FARMLAND).add(
				ModBlockItemIds.CABBAGE_CROP,
				ModBlockItemIds.TOMATO_CROP,
				ModBlockItemIds.ONION_CROP,
				ModBlockItemIds.RICE_CROP).add(
				ModBlockIds.BUDDING_TOMATO_CROP
		);
		tagBuilder(net.minecraft.tags.BlockTags.COMBINATION_STEP_SOUND_BLOCKS).add(
				ModBlockItemIds.CANVAS_RUG,
				ModBlockItemIds.FULL_TATAMI_MAT,
				ModBlockItemIds.HALF_TATAMI_MAT,
				ModBlockItemIds.CUTTING_BOARD
		);
	}

	protected void registerNeoForgeTags() {
		tagBuilder(ConventionalBlockTags.ROPES).add(ModBlockItemIds.ROPE);
		//This is a neoforge tag. i think we alreadey have a mixin that takes care of this
		//tagBuilder(ConventionalBlockTags.VILLAGER_FARMLANDS).add(ModBlockItemIds.RICH_SOIL_FARMLAND);
		tagBuilder(ConventionalBlockTags.FENCES).add(ModBlockItemIds.ROPE_FENCE);
		tagBuilder(ConventionalBlockTags.FENCE_GATES).add(ModBlockItemIds.ROPE_FENCE_GATE);

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
        tagBuilder(CommonTags.Blocks.STORAGE_BLOCKS_CARROT).add(ModBlockItemIds.CARROT_CRATE);
        tagBuilder(CommonTags.Blocks.STORAGE_BLOCKS_POTATO).add(ModBlockItemIds.POTATO_CRATE);
        tagBuilder(CommonTags.Blocks.STORAGE_BLOCKS_BEETROOT).add(ModBlockItemIds.BEETROOT_CRATE);
        tagBuilder(CommonTags.Blocks.STORAGE_BLOCKS_CABBAGE).add(ModBlockItemIds.CABBAGE_CRATE);
        tagBuilder(CommonTags.Blocks.STORAGE_BLOCKS_TOMATO).add(ModBlockItemIds.TOMATO_CRATE);
        tagBuilder(CommonTags.Blocks.STORAGE_BLOCKS_ONION).add(ModBlockItemIds.ONION_CRATE);
        tagBuilder(CommonTags.Blocks.STORAGE_BLOCKS_RICE).add(ModBlockItemIds.RICE_BAG);
        tagBuilder(CommonTags.Blocks.STORAGE_BLOCKS_RICE_PANICLE).add(ModBlockItemIds.RICE_BALE);
        tagBuilder(CommonTags.Blocks.STORAGE_BLOCKS_STRAW).add(ModBlockItemIds.STRAW_BALE);
    }

	protected void registerModTags() {
		tagBuilder(ModTags.Blocks.FEASTS).add(
			ModBlockItemIds.ROAST_CHICKEN_BLOCK,
			ModBlockItemIds.STUFFED_PUMPKIN_BLOCK,
			ModBlockItemIds.SHEPHERDS_PIE_BLOCK,
			ModBlockItemIds.HONEY_GLAZED_HAM_BLOCK,
			ModBlockItemIds.GLEAMING_SALAD_BLOCK,
			ModBlockItemIds.RICE_ROLL_MEDLEY_BLOCK
		);
		tagBuilder(ModTags.Blocks.PIES).add(
			ModBlockItemIds.APPLE_PIE,
			ModBlockItemIds.SWEET_BERRY_CHEESECAKE,
			ModBlockItemIds.CHOCOLATE_PIE).add(
			ModBlockIds.PUMPKIN_PIE
		);
		tagBuilder(ModTags.Blocks.TERRAIN)
				.addTag(net.minecraft.tags.BlockTags.DIRT)
				.addTag(net.minecraft.tags.BlockTags.SAND);
		tagBuilder(ModTags.Blocks.STRAW_BLOCKS).add(
			ModBlockItemIds.ROPE,
			ModBlockItemIds.SAFETY_NET,
			ModBlockItemIds.CANVAS_RUG,
			ModBlockItemIds.TATAMI,
			ModBlockItemIds.FULL_TATAMI_MAT,
			ModBlockItemIds.HALF_TATAMI_MAT
		);
		tagBuilder(ModTags.Blocks.WILD_CROPS).add(
			ModBlockItemIds.WILD_CARROTS,
			ModBlockItemIds.WILD_POTATOES,
			ModBlockItemIds.WILD_BEETROOTS,
			ModBlockItemIds.WILD_CABBAGES,
			ModBlockItemIds.WILD_TOMATOES,
			ModBlockItemIds.WILD_ONIONS,
			ModBlockItemIds.WILD_RICE);
		tagBuilder(ModTags.Blocks.CABINETS_WOODEN)
				.add(ModBlockItemIds.OAK_CABINET)
				.add(ModBlockItemIds.SPRUCE_CABINET)
				.add(ModBlockItemIds.BIRCH_CABINET)
				.add(ModBlockItemIds.JUNGLE_CABINET)
				.add(ModBlockItemIds.ACACIA_CABINET)
				.add(ModBlockItemIds.DARK_OAK_CABINET)
				.add(ModBlockItemIds.MANGROVE_CABINET)
				.add(ModBlockItemIds.CHERRY_CABINET)
				.add(ModBlockItemIds.BAMBOO_CABINET)
				.add(ModBlockItemIds.CRIMSON_CABINET)
				.add(ModBlockItemIds.WARPED_CABINET)
				.add(ModBlockItemIds.PALE_OAK_CABINET);
		tagBuilder(ModTags.Blocks.CABINETS).addTag(ModTags.Blocks.CABINETS_WOODEN);
		tagBuilder(ModTags.Blocks.MUSHROOM_COLONIES)
			.add(ModBlockItemIds.BROWN_MUSHROOM_COLONY)
			.add(ModBlockItemIds.RED_MUSHROOM_COLONY);
		tagBuilder(ModTags.Blocks.ROPES).add(ModBlockItemIds.ROPE)
				.addOptional(Identifier.parse("quark:rope"))
				.addOptional(Identifier.parse("supplementaries:rope"));
		tagBuilder(ModTags.Blocks.TRAY_HEAT_SOURCES).add(
				BlockIds.LAVA)
			.addTag(net.minecraft.tags.BlockTags.CAMPFIRES)
			.addTag(net.minecraft.tags.BlockTags.FIRE);
		tagBuilder(ModTags.Blocks.HEAT_SOURCES).add(
				BlockItemIds.MAGMA_BLOCK,
				ModBlockItemIds.STOVE).add(
				BlockIds.LAVA_CAULDRON)
			.addTag(ModTags.Blocks.TRAY_HEAT_SOURCES);
		tagBuilder(ModTags.Blocks.HEAT_CONDUCTORS).add(
						BlockItemIds.HOPPER)
			.addOptional(Identifier.parse("create:chute"));
		tagBuilder(ModTags.Blocks.COMPOST_ACTIVATORS).add(
				BlockItemIds.BROWN_MUSHROOM,
				BlockItemIds.RED_MUSHROOM,
				BlockItemIds.PODZOL,
				BlockItemIds.MYCELIUM,
				ModBlockItemIds.ORGANIC_COMPOST,
				ModBlockItemIds.RICH_SOIL,
				ModBlockItemIds.RICH_SOIL_FARMLAND)
			.addTag(ModTags.Blocks.MUSHROOM_COLONIES);
		tagBuilder(ModTags.Blocks.UNAFFECTED_BY_RICH_SOIL).add(
				BlockItemIds.GRASS_BLOCK,
				BlockItemIds.SHORT_GRASS,
				BlockItemIds.MOSS_BLOCK,
				BlockItemIds.CRIMSON_NYLIUM,
				BlockItemIds.WARPED_NYLIUM,
				BlockItemIds.FERN,
				BlockItemIds.TWISTING_VINES,
				BlockItemIds.BIG_DRIPLEAF,
				BlockItemIds.PINK_PETALS,
				ModBlockItemIds.SANDY_SHRUB).add(
				BlockIds.TWISTING_VINES_PLANT,
				BlockIds.BIG_DRIPLEAF_STEM)
			.addTag(ModTags.Blocks.MUSHROOM_COLONIES)
			.addTag(ModTags.Blocks.WILD_CROPS)
			.addTag(ConventionalBlockTags.TALL_FLOWERS);
		tagBuilder(ModTags.Blocks.DROPS_CAKE_SLICE).add(
			BlockIds.CANDLE_CAKE).addColorKey(
			BlockIds.DYED_CANDLE_CAKE
		);
		tagBuilder(ModTags.Blocks.CAMPFIRE_SIGNAL_SMOKE).add(ModBlockItemIds.STRAW_BALE).add(ModBlockItemIds.RICE_BALE);
		tagBuilder(ModTags.Blocks.PLANTED_FROM_BELOW).add(BlockItemIds.GLOW_BERRY_CROP).add(BlockIds.CAVE_VINES_PLANT);
	}

	private void registerRefabricatedTags() {
		tagBuilder(FDRefabricatedTags.Blocks.GROWS_MUSHROOM_COLONIES)
			.add(ModBlockItemIds.RICH_SOIL)
			.addTag(ModTags.Blocks.MUSHROOM_COLONY_GROWABLE_ON);
		tagBuilder(FDRefabricatedTags.Blocks.GROWS_WILD_CROPS)
			.addTag(net.minecraft.tags.BlockTags.SUBSTRATE_OVERWORLD)
			.addTag(net.minecraft.tags.BlockTags.SAND);
		tagBuilder(FDRefabricatedTags.Blocks.KNIFE_INSTANTLY_MINES)
			.addTag(net.minecraft.tags.BlockTags.SWORD_INSTANTLY_MINES);
	}

	private void registerCompatibilityTags() {
		tagBuilder(CompatibilityTags.CREATE_FAN_TRANSPARENT).add(ModBlockItemIds.SAFETY_NET);
		tagBuilder(CompatibilityTags.CREATE_PASSIVE_BOILER_HEATERS).add(ModBlockItemIds.STOVE);
		tagBuilder(CompatibilityTags.CREATE_BRITTLE).add(
				ModBlockItemIds.CUTTING_BOARD,
				ModBlockItemIds.FULL_TATAMI_MAT,
				ModBlockItemIds.HALF_TATAMI_MAT)
			.addTag(ModTags.Blocks.FEASTS)
			.addTag(ModTags.Blocks.PIES);

		tagBuilder(CompatibilityTags.SABLE_SUPER_LIGHT)
			.add(ModBlockItemIds.CUTTING_BOARD)
			.add(ModBlockItemIds.CANVAS_RUG)
			.add(ModBlockItemIds.FULL_TATAMI_MAT)
			.add(ModBlockItemIds.HALF_TATAMI_MAT)
			.add(ModBlockItemIds.SAFETY_NET);
		tagBuilder(CompatibilityTags.SABLE_LIGHT)
			.addTag(ModTags.Blocks.CABINETS)
			.add(ModBlockItemIds.WOODEN_BASKET)
			.add(ModBlockItemIds.BAMBOO_BASKET);

		tagBuilder(CompatibilityTags.SERENE_SEASONS_AUTUMN_CROPS_BLOCK).add(
			ModBlockItemIds.CABBAGE_CROP,
			ModBlockItemIds.ONION_CROP,
			ModBlockItemIds.RICE_CROP,
			ModBlockItemIds.RICE_CROP_PANICLES);
		tagBuilder(CompatibilityTags.SERENE_SEASONS_SPRING_CROPS_BLOCK).add(
			ModBlockItemIds.ONION_CROP);
		tagBuilder(CompatibilityTags.SERENE_SEASONS_SUMMER_CROPS_BLOCK).add(
			ModBlockItemIds.TOMATO_CROP,
			ModBlockItemIds.RICE_CROP,
			ModBlockItemIds.RICE_CROP_PANICLES).add(
			ModBlockIds.BUDDING_TOMATO_CROP,
			ModBlockIds.TOMATO_CROP_ON_ROPE
		);
		tagBuilder(CompatibilityTags.SERENE_SEASONS_WINTER_CROPS_BLOCK).add(
			ModBlockItemIds.CABBAGE_CROP);
		tagBuilder(CompatibilityTags.SERENE_SEASONS_UNBREAKABLE_FERTILE_CROPS).add(
			ModBlockItemIds.ONION_CROP);
	}

	private BlockTags.RefabricatedTagBuilder tagBuilder(TagKey<Block> tagKey) {
		return new BlockTags.RefabricatedTagBuilder(tagKey);
	}

	public class RefabricatedTagBuilder {
		private final BlockItemTagAppender<Block> builder;

		private TagBuilder rawBuilder;

		public RefabricatedTagBuilder(TagKey<Block> tag) {
			this.builder = builder(tag);

			this.rawBuilder = getOrCreateRawBuilder(tag);
		}

		public BlockTags.RefabricatedTagBuilder addOptionalTag(TagKey<Block> itemTagKey) {
			builder.addOptionalTag(itemTagKey);
			return this;
		}

		public BlockTags.RefabricatedTagBuilder add(BlockItemId... items) {
			builder.add(items);
			return this;
		}

		@SafeVarargs
		public final BlockTags.RefabricatedTagBuilder add(ResourceKey<Block>... items) {
			builder.add(items);
			return this;
		}

		public BlockTags.RefabricatedTagBuilder addColorKey(ColorCollection<ResourceKey<Block>> items) {
			builder.addAll(items);
			return this;
		}

		public BlockTags.RefabricatedTagBuilder addColorId(ColorCollection<BlockItemId> items) {
			builder.addAll(items.map(BlockItemId::block));
			return this;
		}

		public BlockTags.RefabricatedTagBuilder addOptional(Identifier item) {
			rawBuilder = rawBuilder.addOptionalElement(item);
			return this;
		}

		@SafeVarargs
		public final void addTags(TagKey<Block>... tags) {
			for (TagKey<Block> tag : tags) {
				builder.addTag(tag);
			}
		}

		public BlockTags.RefabricatedTagBuilder addTag(TagKey<Block> tagKey) {
			builder.forceAddTag(tagKey);
			return this;
		}
	}
}
