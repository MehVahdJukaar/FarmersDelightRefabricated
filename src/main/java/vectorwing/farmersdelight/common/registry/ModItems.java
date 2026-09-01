package vectorwing.farmersdelight.common.registry;

import com.google.common.collect.Sets;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.VillagerFood;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import org.jspecify.annotations.Nullable;
import vectorwing.farmersdelight.common.FoodValues;
import vectorwing.farmersdelight.common.item.*;
import vectorwing.farmersdelight.common.references.ModBlockItemIds;
import vectorwing.farmersdelight.common.references.ModItemIds;
import vectorwing.farmersdelight.common.tag.ModTags;
import vectorwing.farmersdelight.common.utility.TextUtils;
import vectorwing.farmersdelight.refabricated.FDRefabricatedTags;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static vectorwing.farmersdelight.refabricated.RegUtils.regItem;

@SuppressWarnings("unused")
public class ModItems
{
	public static LinkedHashSet<Supplier<Item>> CREATIVE_TAB_ITEMS = Sets.newLinkedHashSet();

	private static Supplier<Item> registerWithTab(final ResourceKey<Item> key, final Function<Item.Properties, Item> function, final Item.Properties properties) {
		properties.setId(key);
		Supplier<Item> item = regItem(key, () -> function.apply(properties));
		CREATIVE_TAB_ITEMS.add(item);
		return item;
	}

    public static Supplier<Item> registerHidden(final ResourceKey<Item> key, final Function<Item.Properties, Item> function, final Item.Properties properties) {
        properties.setId(key);
		return regItem(key, () -> function.apply(properties));
    }

    private static Supplier<Item> registerFuelWithTab(final ResourceKey<Item> key, final Item.Properties properties, final int burnTime) {
		properties.setId(key);
		Supplier<Item> item = regItem(key, () -> new FuelItem(properties, burnTime));
		CREATIVE_TAB_ITEMS.add(item);
		return item;
	}

	private static Supplier<Item> registerBlockWithTab(final BlockItemId id, final BiFunction<Block, Item.Properties, Item> function, final Block block, final Item.Properties properties) {
		properties.setId(id.item());
		properties.useBlockDescriptionPrefix();
		Supplier<Item> item = regItem(id.item(), () -> function.apply(block, properties));
		CREATIVE_TAB_ITEMS.add(item);
		return item;
	}

	private static Supplier<Item> registerItemNameBlockWithTab(final BlockItemId id, final BiFunction<Block, Item.Properties, Item> function, final Block block, final Item.Properties properties) {
		properties.setId(id.item());
		Supplier<Item> item = regItem(id.item(), () -> function.apply(block, properties));
		CREATIVE_TAB_ITEMS.add(item);
		return item;
	}

	private static Supplier<Item> registerFuelBlockWithTab(final BlockItemId id, final Block block, final Item.Properties properties, final int burnTime) {
		properties.setId(id.item());
		properties.useBlockDescriptionPrefix();
		Supplier<Item> item = regItem(id.item(), () -> new FuelBlockItem(block, properties, burnTime));
		CREATIVE_TAB_ITEMS.add(item);
		return item;
	}

	// Helper methods
	public static Item.Properties basicItem() {
		return new Item.Properties();
	}

	public static Item.Properties knifeItem(ToolMaterial material) {
		HolderGetter<Block> holderGetter = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
		return new Item.Properties()
				.durability(material.durability())
				.repairable(material.repairItems())
				.enchantable(material.enchantmentValue())
				.attributes(KnifeItem.createAttributes(material, 0.5F, -2.0F))
				.component(DataComponents.TOOL, new Tool(
						List.of(
							Tool.Rule.deniesDrops(holderGetter.getOrThrow(material.incorrectBlocksForDrops())),
							Tool.Rule.minesAndDrops(holderGetter.getOrThrow(ModTags.Blocks.MINEABLE_WITH_KNIFE), material.speed()),
							Tool.Rule.overrideSpeed(holderGetter.getOrThrow(FDRefabricatedTags.Blocks.KNIFE_INSTANTLY_MINES), Float.MAX_VALUE)
						), 1.0F, 1, false))
				.component(DataComponents.WEAPON, new Weapon(2));
	}

	public static Item.Properties foodItem(FoodProperties food) {
		return foodItem(food, null);
	}

	public static Item.Properties foodItem(FoodProperties food, @Nullable Consumable consumable) {
		return new Item.Properties().food(food)
				.component(DataComponents.CONSUMABLE, consumable != null ? consumable : Consumables.DEFAULT_FOOD);
	}

	public static Item.Properties bowlFoodItem(FoodProperties food) {
		return bowlFoodItem(food, null);
	}

	public static Item.Properties bowlFoodItem(FoodProperties food, @Nullable Consumable consumable) {
		return new Item.Properties().food(food)
				.component(DataComponents.CONSUMABLE, consumable != null ? consumable : Consumables.DEFAULT_FOOD)
				.craftRemainder(Items.BOWL)
				.stacksTo(16);
	}

	public static Item.Properties drinkItem() {
		return drinkItem(null);
	}

	public static Item.Properties drinkItem(@Nullable Consumable consumable) {
		return new Item.Properties().craftRemainder(Items.GLASS_BOTTLE)
				.component(DataComponents.CONSUMABLE, consumable != null ? consumable : Consumables.DEFAULT_DRINK)
				.stacksTo(16);
	}

	// Blocks
	public static final Supplier<Item> STOVE = registerBlockWithTab(ModBlockItemIds.STOVE,
			BlockItem::new, ModBlocks.STOVE.get(), basicItem());
	public static final Supplier<Item> COOKING_POT = registerBlockWithTab(ModBlockItemIds.COOKING_POT,
			CookingPotItem::new, ModBlocks.COOKING_POT.get(), basicItem().stacksTo(1));
	public static final Supplier<Item> SKILLET = registerBlockWithTab(ModBlockItemIds.SKILLET,
			SkilletItem::new, ModBlocks.SKILLET.get(), basicItem().stacksTo(1)
					.durability(SkilletItem.SKILLET_MATERIAL.durability())
					.repairable(SkilletItem.SKILLET_MATERIAL.repairItems())
					.enchantable(SkilletItem.SKILLET_MATERIAL.enchantmentValue())
					.attributes(SkilletItem.createAttributes(SkilletItem.SKILLET_MATERIAL, 5.0F, -3.1F))
					.component(DataComponents.TOOL, new Tool(Collections.emptyList(), 1.0F, 1, false))
					.component(DataComponents.WEAPON, new Weapon(1)));
	public static final Supplier<Item> CUTTING_BOARD = registerFuelBlockWithTab(ModBlockItemIds.CUTTING_BOARD,
			ModBlocks.CUTTING_BOARD.get(), basicItem(), 200);
	public static final Supplier<Item> WOODEN_BASKET = registerFuelBlockWithTab(ModBlockItemIds.WOODEN_BASKET,
			ModBlocks.WOODEN_BASKET.get(), basicItem(), 300);
	public static final Supplier<Item> BAMBOO_BASKET = registerFuelBlockWithTab(ModBlockItemIds.BAMBOO_BASKET,
			ModBlocks.BAMBOO_BASKET.get(), basicItem(), 300);

	/**
	 * Deprecated reference added for backwards compatibility. Use BAMBOO_BASKET instead.
	 */
	@Deprecated(forRemoval = true)
	public static final Supplier<Item> BASKET = BAMBOO_BASKET;

	public static final Supplier<Item> CARROT_CRATE = registerBlockWithTab(ModBlockItemIds.CARROT_CRATE,
			BlockItem::new, ModBlocks.CARROT_CRATE.get(), basicItem());
	public static final Supplier<Item> POTATO_CRATE = registerBlockWithTab(ModBlockItemIds.POTATO_CRATE,
			BlockItem::new, ModBlocks.POTATO_CRATE.get(), basicItem());
	public static final Supplier<Item> BEETROOT_CRATE = registerBlockWithTab(ModBlockItemIds.BEETROOT_CRATE,
			BlockItem::new, ModBlocks.BEETROOT_CRATE.get(), basicItem());
	public static final Supplier<Item> CABBAGE_CRATE = registerBlockWithTab(ModBlockItemIds.CABBAGE_CRATE,
			BlockItem::new, ModBlocks.CABBAGE_CRATE.get(), basicItem());
	public static final Supplier<Item> TOMATO_CRATE = registerBlockWithTab(ModBlockItemIds.TOMATO_CRATE,
			BlockItem::new, ModBlocks.TOMATO_CRATE.get(), basicItem());
	public static final Supplier<Item> ONION_CRATE = registerBlockWithTab(ModBlockItemIds.ONION_CRATE,
			BlockItem::new, ModBlocks.ONION_CRATE.get(), basicItem());
	public static final Supplier<Item> RICE_BALE = registerBlockWithTab(ModBlockItemIds.RICE_BALE,
			BlockItem::new, ModBlocks.RICE_BALE.get(), basicItem());
	public static final Supplier<Item> RICE_BAG = registerBlockWithTab(ModBlockItemIds.RICE_BAG,
			BlockItem::new, ModBlocks.RICE_BAG.get(), basicItem());
	public static final Supplier<Item> STRAW_BALE = registerBlockWithTab(ModBlockItemIds.STRAW_BALE,
			BlockItem::new, ModBlocks.STRAW_BALE.get(), basicItem());

	public static final Supplier<Item> SAFETY_NET = registerFuelBlockWithTab(ModBlockItemIds.SAFETY_NET,
			ModBlocks.SAFETY_NET.get(), basicItem(), 200);
	public static final Supplier<Item> OAK_CABINET = registerFuelBlockWithTab(ModBlockItemIds.OAK_CABINET,
			ModBlocks.OAK_CABINET.get(), basicItem(), 300);
	public static final Supplier<Item> SPRUCE_CABINET = registerFuelBlockWithTab(ModBlockItemIds.SPRUCE_CABINET,
			ModBlocks.SPRUCE_CABINET.get(), basicItem(), 300);
	public static final Supplier<Item> BIRCH_CABINET = registerFuelBlockWithTab(ModBlockItemIds.BIRCH_CABINET,
			ModBlocks.BIRCH_CABINET.get(), basicItem(), 300);
	public static final Supplier<Item> JUNGLE_CABINET = registerFuelBlockWithTab(ModBlockItemIds.JUNGLE_CABINET,
			ModBlocks.JUNGLE_CABINET.get(), basicItem(), 300);
	public static final Supplier<Item> ACACIA_CABINET = registerFuelBlockWithTab(ModBlockItemIds.ACACIA_CABINET,
			ModBlocks.ACACIA_CABINET.get(), basicItem(), 300);
	public static final Supplier<Item> DARK_OAK_CABINET = registerFuelBlockWithTab(ModBlockItemIds.DARK_OAK_CABINET,
			ModBlocks.DARK_OAK_CABINET.get(), basicItem(), 300);
	public static final Supplier<Item> MANGROVE_CABINET = registerFuelBlockWithTab(ModBlockItemIds.MANGROVE_CABINET,
			ModBlocks.MANGROVE_CABINET.get(), basicItem(), 300);
	public static final Supplier<Item> CHERRY_CABINET = registerFuelBlockWithTab(ModBlockItemIds.CHERRY_CABINET,
			ModBlocks.CHERRY_CABINET.get(), basicItem(), 300);
	public static final Supplier<Item> BAMBOO_CABINET = registerFuelBlockWithTab(ModBlockItemIds.BAMBOO_CABINET,
			ModBlocks.BAMBOO_CABINET.get(), basicItem(), 300);
	public static final Supplier<Item> PALE_OAK_CABINET = registerFuelBlockWithTab(ModBlockItemIds.PALE_OAK_CABINET,
			ModBlocks.PALE_OAK_CABINET.get(), basicItem(), 300);
	public static final Supplier<Item> POPLAR_CABINET = registerFuelBlockWithTab(ModBlockItemIds.POPLAR_CABINET,
		ModBlocks.POPLAR_CABINET.get(), basicItem(), 300);
	public static final Supplier<Item> CRIMSON_CABINET = registerBlockWithTab(ModBlockItemIds.CRIMSON_CABINET,
			BlockItem::new, ModBlocks.CRIMSON_CABINET.get(), basicItem());
	public static final Supplier<Item> WARPED_CABINET = registerBlockWithTab(ModBlockItemIds.WARPED_CABINET,
			BlockItem::new, ModBlocks.WARPED_CABINET.get(), basicItem());
	public static final Supplier<Item> TATAMI = registerFuelBlockWithTab(ModBlockItemIds.TATAMI,
			ModBlocks.TATAMI.get(), basicItem(), 400);
	public static final Supplier<Item> FULL_TATAMI_MAT = registerFuelBlockWithTab(ModBlockItemIds.FULL_TATAMI_MAT,
			ModBlocks.FULL_TATAMI_MAT.get(), basicItem(), 200);
	public static final Supplier<Item> HALF_TATAMI_MAT = registerFuelBlockWithTab(ModBlockItemIds.HALF_TATAMI_MAT,
			ModBlocks.HALF_TATAMI_MAT.get(), basicItem(), 100);
	public static final Supplier<Item> CANVAS_RUG = registerFuelBlockWithTab(ModBlockItemIds.CANVAS_RUG,
			ModBlocks.CANVAS_RUG.get(), basicItem(), 200);
    public static final Supplier<Item> ROPE_FENCE = registerBlockWithTab(ModBlockItemIds.ROPE_FENCE,
            BlockItem::new, ModBlocks.ROPE_FENCE.get(), basicItem());
    public static final Supplier<Item> ROPE_FENCE_GATE = registerBlockWithTab(ModBlockItemIds.ROPE_FENCE_GATE,
            BlockItem::new, ModBlocks.ROPE_FENCE_GATE.get(), basicItem());
	public static final Supplier<Item> ORGANIC_COMPOST = registerBlockWithTab(ModBlockItemIds.ORGANIC_COMPOST,
			BlockItem::new, ModBlocks.ORGANIC_COMPOST.get(), basicItem());
	public static final Supplier<Item> RICH_SOIL = registerBlockWithTab(ModBlockItemIds.RICH_SOIL,
			BlockItem::new, ModBlocks.RICH_SOIL.get(), basicItem());
	public static final Supplier<Item> RICH_SOIL_FARMLAND = registerBlockWithTab(ModBlockItemIds.RICH_SOIL_FARMLAND,
			BlockItem::new, ModBlocks.RICH_SOIL_FARMLAND.get(), basicItem());
	public static final Supplier<Item> ROPE = registerBlockWithTab(ModBlockItemIds.ROPE,
			RopeItem::new, ModBlocks.ROPE.get(), basicItem());

	// Canvas Signs...
	public static final Supplier<Item> CANVAS_SIGN = registerWithTab(ModBlockItemIds.CANVAS_SIGN.item(),
			(properties) -> new StandingAndWallBlockItem(ModBlocks.CANVAS_SIGN.get(), ModBlocks.CANVAS_WALL_SIGN.get(), Direction.DOWN, properties), basicItem().useBlockDescriptionPrefix());
	public static final Supplier<Item> HANGING_CANVAS_SIGN = registerWithTab(ModBlockItemIds.HANGING_CANVAS_SIGN.item(),
			(properties) -> new HangingSignItem(ModBlocks.HANGING_CANVAS_SIGN.get(), ModBlocks.HANGING_CANVAS_WALL_SIGN.get(), properties), basicItem().useBlockDescriptionPrefix());

	public static final Supplier<Item> WHITE_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_CANVAS_SIGN.white().item(),
			(properties) -> new StandingAndWallBlockItem(ModBlocks.WHITE_CANVAS_SIGN.get(), ModBlocks.WHITE_CANVAS_WALL_SIGN.get(), Direction.DOWN, properties), basicItem().useBlockDescriptionPrefix());
	public static final Supplier<Item> WHITE_HANGING_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.white().item(),
			(properties) -> new HangingSignItem(ModBlocks.WHITE_HANGING_CANVAS_SIGN.get(), ModBlocks.WHITE_HANGING_CANVAS_WALL_SIGN.get(), properties), basicItem().useBlockDescriptionPrefix());

	public static final Supplier<Item> LIGHT_GRAY_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_CANVAS_SIGN.lightGray().item(),
			(properties) -> new StandingAndWallBlockItem(ModBlocks.LIGHT_GRAY_CANVAS_SIGN.get(), ModBlocks.LIGHT_GRAY_CANVAS_WALL_SIGN.get(),Direction.DOWN, properties), basicItem().useBlockDescriptionPrefix());
	public static final Supplier<Item> LIGHT_GRAY_HANGING_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.lightGray().item(),
			(properties) -> new HangingSignItem(ModBlocks.LIGHT_GRAY_HANGING_CANVAS_SIGN.get(), ModBlocks.LIGHT_GRAY_HANGING_CANVAS_WALL_SIGN.get(), properties), basicItem().useBlockDescriptionPrefix());

	public static final Supplier<Item> GRAY_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_CANVAS_SIGN.gray().item(),
			(properties) -> new StandingAndWallBlockItem(ModBlocks.GRAY_CANVAS_SIGN.get(), ModBlocks.GRAY_CANVAS_WALL_SIGN.get(),Direction.DOWN, properties), basicItem().useBlockDescriptionPrefix());
	public static final Supplier<Item> GRAY_HANGING_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.gray().item(),
			(properties) -> new HangingSignItem(ModBlocks.GRAY_HANGING_CANVAS_SIGN.get(), ModBlocks.GRAY_HANGING_CANVAS_WALL_SIGN.get(), properties), basicItem().useBlockDescriptionPrefix());

	public static final Supplier<Item> BLACK_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_CANVAS_SIGN.black().item(),
			(properties) -> new StandingAndWallBlockItem(ModBlocks.BLACK_CANVAS_SIGN.get(), ModBlocks.BLACK_CANVAS_WALL_SIGN.get(),Direction.DOWN, properties), basicItem().useBlockDescriptionPrefix());
	public static final Supplier<Item> BLACK_HANGING_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.black().item(),
			(properties) -> new HangingSignItem(ModBlocks.BLACK_HANGING_CANVAS_SIGN.get(), ModBlocks.BLACK_HANGING_CANVAS_WALL_SIGN.get(), properties), basicItem().useBlockDescriptionPrefix());

	public static final Supplier<Item> BROWN_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_CANVAS_SIGN.brown().item(),
			(properties) -> new StandingAndWallBlockItem(ModBlocks.BROWN_CANVAS_SIGN.get(), ModBlocks.BROWN_CANVAS_WALL_SIGN.get(),Direction.DOWN, properties), basicItem().useBlockDescriptionPrefix());
	public static final Supplier<Item> BROWN_HANGING_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.brown().item(),
			(properties) -> new HangingSignItem(ModBlocks.BROWN_HANGING_CANVAS_SIGN.get(), ModBlocks.BROWN_HANGING_CANVAS_WALL_SIGN.get(), properties), basicItem().useBlockDescriptionPrefix());

	public static final Supplier<Item> RED_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_CANVAS_SIGN.red().item(),
			(properties) -> new StandingAndWallBlockItem(ModBlocks.RED_CANVAS_SIGN.get(), ModBlocks.RED_CANVAS_WALL_SIGN.get(),Direction.DOWN, properties), basicItem().useBlockDescriptionPrefix());
	public static final Supplier<Item> RED_HANGING_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.red().item(),
			(properties) -> new HangingSignItem(ModBlocks.RED_HANGING_CANVAS_SIGN.get(), ModBlocks.RED_HANGING_CANVAS_WALL_SIGN.get(), properties), basicItem().useBlockDescriptionPrefix());

	public static final Supplier<Item> ORANGE_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_CANVAS_SIGN.orange().item(),
			(properties) -> new StandingAndWallBlockItem(ModBlocks.ORANGE_CANVAS_SIGN.get(), ModBlocks.ORANGE_CANVAS_WALL_SIGN.get(),Direction.DOWN, properties), basicItem().useBlockDescriptionPrefix());
	public static final Supplier<Item> ORANGE_HANGING_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.orange().item(),
			(properties) -> new HangingSignItem(ModBlocks.ORANGE_HANGING_CANVAS_SIGN.get(), ModBlocks.ORANGE_HANGING_CANVAS_WALL_SIGN.get(), properties), basicItem().useBlockDescriptionPrefix());

	public static final Supplier<Item> YELLOW_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_CANVAS_SIGN.yellow().item(),
			(properties) -> new StandingAndWallBlockItem(ModBlocks.YELLOW_CANVAS_SIGN.get(), ModBlocks.YELLOW_CANVAS_WALL_SIGN.get(),Direction.DOWN, properties), basicItem().useBlockDescriptionPrefix());
	public static final Supplier<Item> YELLOW_HANGING_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.yellow().item(),
			(properties) -> new HangingSignItem(ModBlocks.YELLOW_HANGING_CANVAS_SIGN.get(), ModBlocks.YELLOW_HANGING_CANVAS_WALL_SIGN.get(), properties), basicItem().useBlockDescriptionPrefix());

	public static final Supplier<Item> LIME_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_CANVAS_SIGN.lime().item(),
			(properties) -> new StandingAndWallBlockItem(ModBlocks.LIME_CANVAS_SIGN.get(), ModBlocks.LIME_CANVAS_WALL_SIGN.get(),Direction.DOWN, properties), basicItem().useBlockDescriptionPrefix());
	public static final Supplier<Item> LIME_HANGING_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.lime().item(),
			(properties) -> new HangingSignItem(ModBlocks.LIME_HANGING_CANVAS_SIGN.get(), ModBlocks.LIME_HANGING_CANVAS_WALL_SIGN.get(), properties), basicItem().useBlockDescriptionPrefix());

	public static final Supplier<Item> GREEN_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_CANVAS_SIGN.green().item(),
			(properties) -> new StandingAndWallBlockItem(ModBlocks.GREEN_CANVAS_SIGN.get(), ModBlocks.GREEN_CANVAS_WALL_SIGN.get(),Direction.DOWN, properties), basicItem().useBlockDescriptionPrefix());
	public static final Supplier<Item> GREEN_HANGING_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.green().item(),
			(properties) -> new HangingSignItem(ModBlocks.GREEN_HANGING_CANVAS_SIGN.get(), ModBlocks.GREEN_HANGING_CANVAS_WALL_SIGN.get(), properties), basicItem().useBlockDescriptionPrefix());

	public static final Supplier<Item> CYAN_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_CANVAS_SIGN.cyan().item(),
			(properties) -> new StandingAndWallBlockItem(ModBlocks.CYAN_CANVAS_SIGN.get(), ModBlocks.CYAN_CANVAS_WALL_SIGN.get(),Direction.DOWN, properties), basicItem().useBlockDescriptionPrefix());
	public static final Supplier<Item> CYAN_HANGING_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.cyan().item(),
			(properties) -> new HangingSignItem(ModBlocks.CYAN_HANGING_CANVAS_SIGN.get(), ModBlocks.CYAN_HANGING_CANVAS_WALL_SIGN.get(), properties), basicItem().useBlockDescriptionPrefix());

	public static final Supplier<Item> LIGHT_BLUE_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_CANVAS_SIGN.lightBlue().item(),
			(properties) -> new StandingAndWallBlockItem(ModBlocks.LIGHT_BLUE_CANVAS_SIGN.get(), ModBlocks.LIGHT_BLUE_CANVAS_WALL_SIGN.get(),Direction.DOWN, properties), basicItem().useBlockDescriptionPrefix());
	public static final Supplier<Item> LIGHT_BLUE_HANGING_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.lightBlue().item(),
			(properties) -> new HangingSignItem(ModBlocks.LIGHT_BLUE_HANGING_CANVAS_SIGN.get(), ModBlocks.LIGHT_BLUE_HANGING_CANVAS_WALL_SIGN.get(), properties), basicItem().useBlockDescriptionPrefix());

	public static final Supplier<Item> BLUE_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_CANVAS_SIGN.blue().item(),
			(properties) -> new StandingAndWallBlockItem(ModBlocks.BLUE_CANVAS_SIGN.get(), ModBlocks.BLUE_CANVAS_WALL_SIGN.get(),Direction.DOWN, properties), basicItem().useBlockDescriptionPrefix());
	public static final Supplier<Item> BLUE_HANGING_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.blue().item(),
			(properties) -> new HangingSignItem(ModBlocks.BLUE_HANGING_CANVAS_SIGN.get(), ModBlocks.BLUE_HANGING_CANVAS_WALL_SIGN.get(), properties), basicItem().useBlockDescriptionPrefix());

	public static final Supplier<Item> PURPLE_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_CANVAS_SIGN.purple().item(),
			(properties) -> new StandingAndWallBlockItem(ModBlocks.PURPLE_CANVAS_SIGN.get(), ModBlocks.PURPLE_CANVAS_WALL_SIGN.get(),Direction.DOWN, properties), basicItem().useBlockDescriptionPrefix());
	public static final Supplier<Item> PURPLE_HANGING_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.purple().item(),
			(properties) -> new HangingSignItem(ModBlocks.PURPLE_HANGING_CANVAS_SIGN.get(), ModBlocks.PURPLE_HANGING_CANVAS_WALL_SIGN.get(), properties), basicItem().useBlockDescriptionPrefix());

	public static final Supplier<Item> MAGENTA_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_CANVAS_SIGN.magenta().item(),
			(properties) -> new StandingAndWallBlockItem(ModBlocks.MAGENTA_CANVAS_SIGN.get(), ModBlocks.MAGENTA_CANVAS_WALL_SIGN.get(),Direction.DOWN, properties), basicItem().useBlockDescriptionPrefix());
	public static final Supplier<Item> MAGENTA_HANGING_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.magenta().item(),
			(properties) -> new HangingSignItem(ModBlocks.MAGENTA_HANGING_CANVAS_SIGN.get(), ModBlocks.MAGENTA_HANGING_CANVAS_WALL_SIGN.get(), properties), basicItem().useBlockDescriptionPrefix());

	public static final Supplier<Item> PINK_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_CANVAS_SIGN.pink().item(),
			(properties) -> new StandingAndWallBlockItem(ModBlocks.PINK_CANVAS_SIGN.get(), ModBlocks.PINK_CANVAS_WALL_SIGN.get(),Direction.DOWN, properties), basicItem().useBlockDescriptionPrefix());
	public static final Supplier<Item> PINK_HANGING_CANVAS_SIGN = registerWithTab(ModBlockItemIds.DYED_HANGING_CANVAS_SIGN.pink().item(),
			(properties) -> new HangingSignItem(ModBlocks.PINK_HANGING_CANVAS_SIGN.get(), ModBlocks.PINK_HANGING_CANVAS_WALL_SIGN.get(), properties), basicItem().useBlockDescriptionPrefix());

	// Tools
	public static final Supplier<Item> FLINT_KNIFE = registerWithTab(ModItemIds.FLINT_KNIFE,
			KnifeItem::new, knifeItem(ModToolMaterials.FLINT));
    public static final Supplier<Item> COPPER_KNIFE = registerWithTab(ModItemIds.COPPER_KNIFE,
            KnifeItem::new, knifeItem(ToolMaterial.COPPER));
    public static final Supplier<Item> IRON_KNIFE = registerWithTab(ModItemIds.IRON_KNIFE,
            KnifeItem::new, knifeItem(ToolMaterial.IRON));
	public static final Supplier<Item> DIAMOND_KNIFE = registerWithTab(ModItemIds.DIAMOND_KNIFE,
			KnifeItem::new, knifeItem(ToolMaterial.DIAMOND));
	public static final Supplier<Item> NETHERITE_KNIFE = registerWithTab(ModItemIds.NETHERITE_KNIFE,
			KnifeItem::new, knifeItem(ToolMaterial.NETHERITE).fireResistant());
	public static final Supplier<Item> GOLDEN_KNIFE = registerWithTab(ModItemIds.GOLDEN_KNIFE,
			KnifeItem::new, knifeItem(ToolMaterial.GOLD));

	public static final Supplier<Item> STRAW = registerFuelWithTab(ModItemIds.STRAW, basicItem(), 100);
	public static final Supplier<Item> CANVAS = registerFuelWithTab(ModItemIds.CANVAS, basicItem(), 400);
	public static final Supplier<Item> TREE_BARK = registerFuelWithTab(ModItemIds.TREE_BARK, basicItem(), 200);

	// Wild Crops
	public static final Supplier<Item> SANDY_SHRUB = registerBlockWithTab(ModBlockItemIds.SANDY_SHRUB,
			BlockItem::new, ModBlocks.SANDY_SHRUB.get(), basicItem());
	public static final Supplier<Item> WILD_CABBAGES = registerBlockWithTab(ModBlockItemIds.WILD_CABBAGES,
			BlockItem::new, ModBlocks.WILD_CABBAGES.get(), basicItem());
	public static final Supplier<Item> WILD_ONIONS = registerBlockWithTab(ModBlockItemIds.WILD_ONIONS,
			BlockItem::new, ModBlocks.WILD_ONIONS.get(), basicItem());
	public static final Supplier<Item> WILD_TOMATOES = registerBlockWithTab(ModBlockItemIds.WILD_TOMATOES,
			BlockItem::new, ModBlocks.WILD_TOMATOES.get(), basicItem());
	public static final Supplier<Item> WILD_CARROTS = registerBlockWithTab(ModBlockItemIds.WILD_CARROTS,
			BlockItem::new, ModBlocks.WILD_CARROTS.get(), basicItem());
	public static final Supplier<Item> WILD_POTATOES = registerBlockWithTab(ModBlockItemIds.WILD_POTATOES,
			BlockItem::new, ModBlocks.WILD_POTATOES.get(), basicItem());
	public static final Supplier<Item> WILD_BEETROOTS = registerBlockWithTab(ModBlockItemIds.WILD_BEETROOTS,
			BlockItem::new, ModBlocks.WILD_BEETROOTS.get(), basicItem());
	public static final Supplier<Item> WILD_RICE = registerBlockWithTab(ModBlockItemIds.WILD_RICE,
			DoubleHighBlockItem::new, ModBlocks.WILD_RICE.get(), basicItem());

	public static final Supplier<Item> BROWN_MUSHROOM_COLONY = registerBlockWithTab(ModBlockItemIds.BROWN_MUSHROOM_COLONY,
			MushroomColonyItem::new, ModBlocks.BROWN_MUSHROOM_COLONY.get(), basicItem());
	public static final Supplier<Item> RED_MUSHROOM_COLONY = registerBlockWithTab(ModBlockItemIds.RED_MUSHROOM_COLONY,
			MushroomColonyItem::new, ModBlocks.RED_MUSHROOM_COLONY.get(), basicItem());

	// Basic Crops
	public static final Supplier<Item> CABBAGE = registerWithTab(ModItemIds.CABBAGE,
			Item::new, foodItem(FoodValues.CABBAGE).component(DataComponents.VILLAGER_FOOD, new VillagerFood(1)));
	public static final Supplier<Item> TOMATO = registerWithTab(ModItemIds.TOMATO,
			Item::new, foodItem(FoodValues.TOMATO).component(DataComponents.VILLAGER_FOOD, new VillagerFood(1)));
	public static final Supplier<Item> ONION = registerItemNameBlockWithTab(ModBlockItemIds.ONION_CROP,
			BlockItem::new, ModBlocks.ONION_CROP.get(), foodItem(FoodValues.ONION).component(DataComponents.VILLAGER_FOOD, new VillagerFood(1)));
	public static final Supplier<Item> RICE_PANICLE = registerWithTab(ModBlockItemIds.RICE_CROP_PANICLES.item(),
			Item::new, basicItem());
	public static final Supplier<Item> RICE = registerItemNameBlockWithTab(ModBlockItemIds.RICE_CROP,
			RiceItem::new, ModBlocks.RICE_CROP.get(), basicItem().component(DataComponents.VILLAGER_FOOD, new VillagerFood(2)));
	public static final Supplier<Item> CABBAGE_SEEDS = registerItemNameBlockWithTab(ModBlockItemIds.CABBAGE_CROP,
			BlockItem::new, ModBlocks.CABBAGE_CROP.get(), basicItem());
	public static final Supplier<Item> TOMATO_SEEDS = registerItemNameBlockWithTab(ModBlockItemIds.TOMATO_CROP,
			(block, properties) -> new BlockItem(block, properties) {
				@Override
				public void registerBlocks(Map<Block, Item> blockToItemMap, Item item) {
					super.registerBlocks(blockToItemMap, item);
					blockToItemMap.put(ModBlocks.TOMATO_CROP.get(), item);
				}
			}, ModBlocks.BUDDING_TOMATO_CROP.get(), basicItem());
	public static final Supplier<Item> ROTTEN_TOMATO = registerWithTab(ModItemIds.ROTTEN_TOMATO,
			RottenTomatoItem::new, basicItem().stacksTo(16));

	// Foodstuffs
	public static final Supplier<Item> FRIED_EGG = registerWithTab(ModItemIds.FRIED_EGG,
			Item::new, foodItem(FoodValues.FRIED_EGG));
	public static final Supplier<Item> MILK_BOTTLE = registerWithTab(ModItemIds.MILK_BOTTLE,
			properties -> new ConsumableItem(properties, false, true), drinkItem(FoodValues.ConsumableValues.MILK_BOTTLE));
	public static final Supplier<Item> HOT_COCOA = registerWithTab(ModItemIds.HOT_COCOA,
			properties -> new ConsumableItem(properties, false, true), drinkItem(FoodValues.ConsumableValues.HOT_COCOA));
	public static final Supplier<Item> APPLE_CIDER = registerWithTab(ModItemIds.APPLE_CIDER,
			properties -> new ConsumableItem(properties, true, false),
			drinkItem().food(FoodValues.APPLE_CIDER, FoodValues.ConsumableValues.APPLE_CIDER));
	public static final Supplier<Item> MELON_JUICE = registerWithTab(ModItemIds.MELON_JUICE,
			properties -> new ConsumableItem(properties, false, true), drinkItem(FoodValues.ConsumableValues.MELON_JUICE));
	public static final Supplier<Item> TOMATO_SAUCE = registerWithTab(ModItemIds.TOMATO_SAUCE,
			ConsumableItem::new, foodItem(FoodValues.TOMATO_SAUCE).craftRemainder(Items.BOWL));
	public static final Supplier<Item> WHEAT_DOUGH = registerWithTab(ModItemIds.WHEAT_DOUGH,
			Item::new, foodItem(FoodValues.WHEAT_DOUGH, FoodValues.ConsumableValues.HUNGER_INDUCING_FOOD));
	public static final Supplier<Item> RAW_PASTA = registerWithTab(ModItemIds.RAW_PASTA,
			Item::new, foodItem(FoodValues.RAW_PASTA, FoodValues.ConsumableValues.HUNGER_INDUCING_FOOD));
	public static final Supplier<Item> PUMPKIN_SLICE = registerWithTab(ModItemIds.PUMPKIN_SLICE,
			Item::new, foodItem(FoodValues.PUMPKIN_SLICE));
	public static final Supplier<Item> CABBAGE_LEAF = registerWithTab(ModItemIds.CABBAGE_LEAF,
			Item::new, foodItem(FoodValues.CABBAGE_LEAF, FoodValues.ConsumableValues.FAST_FOOD));
	public static final Supplier<Item> MINCED_BEEF = registerWithTab(ModItemIds.MINCED_BEEF,
			Item::new, foodItem(FoodValues.MINCED_BEEF, FoodValues.ConsumableValues.FAST_FOOD));
	public static final Supplier<Item> BEEF_PATTY = registerWithTab(ModItemIds.BEEF_PATTY,
			Item::new, foodItem(FoodValues.BEEF_PATTY, FoodValues.ConsumableValues.FAST_FOOD));
	public static final Supplier<Item> CHICKEN_CUTS = registerWithTab(ModItemIds.CHICKEN_CUTS,
			Item::new, foodItem(FoodValues.CHICKEN_CUTS, FoodValues.ConsumableValues.CHICKEN_CUTS));
	public static final Supplier<Item> COOKED_CHICKEN_CUTS = registerWithTab(ModItemIds.COOKED_CHICKEN_CUTS,
			Item::new, foodItem(FoodValues.COOKED_CHICKEN_CUTS, FoodValues.ConsumableValues.FAST_FOOD));
	public static final Supplier<Item> BACON = registerWithTab(ModItemIds.BACON,
			Item::new, foodItem(FoodValues.BACON, FoodValues.ConsumableValues.FAST_FOOD));
	public static final Supplier<Item> COOKED_BACON = registerWithTab(ModItemIds.COOKED_BACON,
			Item::new, foodItem(FoodValues.COOKED_BACON, FoodValues.ConsumableValues.FAST_FOOD));
	public static final Supplier<Item> COD_SLICE = registerWithTab(ModItemIds.COD_SLICE,
			Item::new, foodItem(FoodValues.COD_SLICE, FoodValues.ConsumableValues.FAST_FOOD));
	public static final Supplier<Item> COOKED_COD_SLICE = registerWithTab(ModItemIds.COOKED_COD_SLICE,
			Item::new, foodItem(FoodValues.COOKED_COD_SLICE, FoodValues.ConsumableValues.FAST_FOOD));
	public static final Supplier<Item> SALMON_SLICE = registerWithTab(ModItemIds.SALMON_SLICE,
			Item::new, foodItem(FoodValues.SALMON_SLICE, FoodValues.ConsumableValues.FAST_FOOD));
	public static final Supplier<Item> COOKED_SALMON_SLICE = registerWithTab(ModItemIds.COOKED_SALMON_SLICE,
			Item::new, foodItem(FoodValues.COOKED_SALMON_SLICE, FoodValues.ConsumableValues.FAST_FOOD));
	public static final Supplier<Item> MUTTON_CHOPS = registerWithTab(ModItemIds.MUTTON_CHOPS,
			Item::new, foodItem(FoodValues.MUTTON_CHOPS, FoodValues.ConsumableValues.FAST_FOOD));
	public static final Supplier<Item> COOKED_MUTTON_CHOPS = registerWithTab(ModItemIds.COOKED_MUTTON_CHOPS,
			Item::new, foodItem(FoodValues.COOKED_MUTTON_CHOPS, FoodValues.ConsumableValues.FAST_FOOD));
	public static final Supplier<Item> HAM = registerWithTab(ModItemIds.HAM,
			Item::new, foodItem(FoodValues.HAM));
	public static final Supplier<Item> SMOKED_HAM = registerWithTab(ModItemIds.SMOKED_HAM,
			Item::new, foodItem(FoodValues.SMOKED_HAM));
    public static final Supplier<Item> PIE_CRUST = registerWithTab(ModItemIds.PIE_CRUST,
            Item::new, foodItem(FoodValues.PIE_CRUST));

	// Sweets
	public static final Supplier<Item> APPLE_PIE = registerBlockWithTab(ModBlockItemIds.APPLE_PIE,
			PlaceableItem::new, ModBlocks.APPLE_PIE.get(), basicItem());
	public static final Supplier<Item> SWEET_BERRY_CHEESECAKE = registerBlockWithTab(ModBlockItemIds.SWEET_BERRY_CHEESECAKE,
			PlaceableItem::new, ModBlocks.SWEET_BERRY_CHEESECAKE.get(), basicItem());
	public static final Supplier<Item> CHOCOLATE_PIE = registerBlockWithTab(ModBlockItemIds.CHOCOLATE_PIE,
			PlaceableItem::new, ModBlocks.CHOCOLATE_PIE.get(), basicItem());
	public static final Supplier<Item> CAKE_SLICE = registerWithTab(ModItemIds.CAKE_SLICE,
			Item::new, foodItem(FoodValues.CAKE_SLICE, FoodValues.ConsumableValues.CAKE_SLICE));
	public static final Supplier<Item> APPLE_PIE_SLICE = registerWithTab(ModItemIds.APPLE_PIE_SLICE,
			Item::new, foodItem(FoodValues.PIE_SLICE, FoodValues.ConsumableValues.PIE_SLICE));
	public static final Supplier<Item> SWEET_BERRY_CHEESECAKE_SLICE = registerWithTab(ModItemIds.SWEET_BERRY_CHEESECAKE_SLICE,
			Item::new, foodItem(FoodValues.PIE_SLICE, FoodValues.ConsumableValues.PIE_SLICE));
	public static final Supplier<Item> CHOCOLATE_PIE_SLICE = registerWithTab(ModItemIds.CHOCOLATE_PIE_SLICE,
			Item::new, foodItem(FoodValues.PIE_SLICE, FoodValues.ConsumableValues.PIE_SLICE));
    public static final Supplier<Item> PUMPKIN_PIE_SLICE = registerWithTab(ModItemIds.PUMPKIN_PIE_SLICE,
			Item::new, foodItem(FoodValues.PIE_SLICE, FoodValues.ConsumableValues.PIE_SLICE));
	public static final Supplier<Item> SWEET_BERRY_COOKIE = registerWithTab(ModItemIds.SWEET_BERRY_COOKIE,
			Item::new, foodItem(FoodValues.COOKIES, FoodValues.ConsumableValues.FAST_FOOD));
    public static final Supplier<Item> HONEY_COOKIE = registerWithTab(ModItemIds.HONEY_COOKIE,
			Item::new, foodItem(FoodValues.COOKIES, FoodValues.ConsumableValues.FAST_FOOD));
	public static final Supplier<Item> MELON_POPSICLE = registerWithTab(ModItemIds.MELON_POPSICLE,
			ConsumableItem::new, foodItem(FoodValues.POPSICLE, FoodValues.ConsumableValues.MELON_POPSICLE));
	public static final Supplier<Item> GLOW_BERRY_CUSTARD = registerWithTab(ModItemIds.GLOW_BERRY_CUSTARD,
			properties -> new ConsumableItem(properties, true),
			foodItem(FoodValues.GLOW_BERRY_CUSTARD, FoodValues.ConsumableValues.GLOW_BERRY_CUSTARD).craftRemainder(Items.GLASS_BOTTLE).stacksTo(16));
	public static final Supplier<Item> FRUIT_SALAD = registerWithTab(ModItemIds.FRUIT_SALAD,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.FRUIT_SALAD, FoodValues.ConsumableValues.FRUIT_SALAD));

	// Basic Meals
	public static final Supplier<Item> MIXED_SALAD = registerWithTab(ModItemIds.MIXED_SALAD,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.MIXED_SALAD, FoodValues.ConsumableValues.MIXED_SALAD));
	public static final Supplier<Item> NETHER_SALAD = registerWithTab(ModItemIds.NETHER_SALAD,
            ConsumableItem::new,
			bowlFoodItem(FoodValues.NETHER_SALAD, FoodValues.ConsumableValues.NETHER_SALAD));
	public static final Supplier<Item> BARBECUE_STICK = registerWithTab(ModItemIds.BARBECUE_STICK,
			ConsumableItem::new, foodItem(FoodValues.BARBECUE_STICK));
	public static final Supplier<Item> EGG_SANDWICH = registerWithTab(ModItemIds.EGG_SANDWICH,
			ConsumableItem::new, foodItem(FoodValues.EGG_SANDWICH));
	public static final Supplier<Item> CHICKEN_SANDWICH = registerWithTab(ModItemIds.CHICKEN_SANDWICH,
			ConsumableItem::new, foodItem(FoodValues.CHICKEN_SANDWICH));
	public static final Supplier<Item> HAMBURGER = registerWithTab(ModItemIds.HAMBURGER,
			ConsumableItem::new, foodItem(FoodValues.HAMBURGER));
	public static final Supplier<Item> BACON_SANDWICH = registerWithTab(ModItemIds.BACON_SANDWICH,
			ConsumableItem::new, foodItem(FoodValues.BACON_SANDWICH));
	public static final Supplier<Item> MUTTON_WRAP = registerWithTab(ModItemIds.MUTTON_WRAP,
			ConsumableItem::new, foodItem(FoodValues.MUTTON_WRAP));
	public static final Supplier<Item> DUMPLINGS = registerWithTab(ModItemIds.DUMPLINGS,
			ConsumableItem::new, foodItem(FoodValues.DUMPLINGS));
	public static final Supplier<Item> STUFFED_POTATO = registerWithTab(ModItemIds.STUFFED_POTATO,
			ConsumableItem::new, foodItem(FoodValues.STUFFED_POTATO));
	public static final Supplier<Item> CABBAGE_ROLLS = registerWithTab(ModItemIds.CABBAGE_ROLLS,
			ConsumableItem::new, foodItem(FoodValues.CABBAGE_ROLLS));
	public static final Supplier<Item> SALMON_ROLL = registerWithTab(ModItemIds.SALMON_ROLL,
			ConsumableItem::new, foodItem(FoodValues.SALMON_ROLL));
	public static final Supplier<Item> COD_ROLL = registerWithTab(ModItemIds.COD_ROLL,
			ConsumableItem::new, foodItem(FoodValues.COD_ROLL));
	public static final Supplier<Item> KELP_ROLL = registerWithTab(ModItemIds.KELP_ROLL,
			ConsumableItem::new, foodItem(FoodValues.KELP_ROLL, FoodValues.ConsumableValues.KELP_ROLL));
	public static final Supplier<Item> KELP_ROLL_SLICE = registerWithTab(ModItemIds.KELP_ROLL_SLICE,
			ConsumableItem::new, foodItem(FoodValues.KELP_ROLL_SLICE, FoodValues.ConsumableValues.FAST_FOOD));

	// Soups and Stews
	public static final Supplier<Item> COOKED_RICE = registerWithTab(ModItemIds.COOKED_RICE,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.COOKED_RICE, FoodValues.ConsumableValues.NOURISHMENT_BRIEF_DURATION));
	public static final Supplier<Item> BONE_BROTH = registerWithTab(ModItemIds.BONE_BROTH,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.BONE_BROTH, FoodValues.ConsumableValues.BONE_BROTH));
	public static final Supplier<Item> BEEF_STEW = registerWithTab(ModItemIds.BEEF_STEW,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.BEEF_STEW, FoodValues.ConsumableValues.NOURISHMENT_MEDIUM_DURATION));
	public static final Supplier<Item> CHICKEN_SOUP = registerWithTab(ModItemIds.CHICKEN_SOUP,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.CHICKEN_SOUP, FoodValues.ConsumableValues.NOURISHMENT_LONG_DURATION));
	public static final Supplier<Item> VEGETABLE_SOUP = registerWithTab(ModItemIds.VEGETABLE_SOUP,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.VEGETABLE_SOUP, FoodValues.ConsumableValues.NOURISHMENT_MEDIUM_DURATION));
	public static final Supplier<Item> FISH_STEW = registerWithTab(ModItemIds.FISH_STEW,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.FISH_STEW, FoodValues.ConsumableValues.NOURISHMENT_MEDIUM_DURATION));
	public static final Supplier<Item> FRIED_RICE = registerWithTab(ModItemIds.FRIED_RICE,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.FRIED_RICE, FoodValues.ConsumableValues.NOURISHMENT_LONG_DURATION));
	public static final Supplier<Item> PUMPKIN_SOUP = registerWithTab(ModItemIds.PUMPKIN_SOUP,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.PUMPKIN_SOUP, FoodValues.ConsumableValues.NOURISHMENT_LONG_DURATION));
	public static final Supplier<Item> BAKED_COD_STEW = registerWithTab(ModItemIds.BAKED_COD_STEW,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.BAKED_COD_STEW, FoodValues.ConsumableValues.NOURISHMENT_LONG_DURATION));
	public static final Supplier<Item> NOODLE_SOUP = registerWithTab(ModItemIds.NOODLE_SOUP,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.NOODLE_SOUP, FoodValues.ConsumableValues.NOURISHMENT_LONG_DURATION));
    public static final Supplier<Item> ONION_SOUP = registerWithTab(ModItemIds.ONION_SOUP,
            properties -> new ConsumableItem(properties, true),
            // TODO: Double check consumable...
            bowlFoodItem(FoodValues.SHEPHERDS_PIE, FoodValues.ConsumableValues.NOURISHMENT_LONG_DURATION));

	// Plated Meals
	public static final Supplier<Item> BACON_AND_EGGS = registerWithTab(ModItemIds.BACON_AND_EGGS,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.BACON_AND_EGGS, FoodValues.ConsumableValues.NOURISHMENT_SHORT_DURATION));
	public static final Supplier<Item> PASTA_WITH_MEATBALLS = registerWithTab(ModItemIds.PASTA_WITH_MEATBALLS,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.PASTA_WITH_MEATBALLS, FoodValues.ConsumableValues.NOURISHMENT_MEDIUM_DURATION));
	public static final Supplier<Item> PASTA_WITH_MUTTON_CHOP = registerWithTab(ModItemIds.PASTA_WITH_MUTTON_CHOP,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.PASTA_WITH_MUTTON_CHOP, FoodValues.ConsumableValues.NOURISHMENT_MEDIUM_DURATION));
	public static final Supplier<Item> MUSHROOM_RICE = registerWithTab(ModItemIds.MUSHROOM_RICE,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.MUSHROOM_RICE, FoodValues.ConsumableValues.NOURISHMENT_MEDIUM_DURATION));
	public static final Supplier<Item> ROASTED_MUTTON_CHOPS = registerWithTab(ModItemIds.ROASTED_MUTTON_CHOPS,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.ROASTED_MUTTON_CHOPS, FoodValues.ConsumableValues.NOURISHMENT_LONG_DURATION));
	public static final Supplier<Item> VEGETABLE_NOODLES = registerWithTab(ModItemIds.VEGETABLE_NOODLES,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.VEGETABLE_NOODLES, FoodValues.ConsumableValues.NOURISHMENT_LONG_DURATION));
	public static final Supplier<Item> STEAK_AND_POTATOES = registerWithTab(ModItemIds.STEAK_AND_POTATOES,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.STEAK_AND_POTATOES, FoodValues.ConsumableValues.NOURISHMENT_MEDIUM_DURATION));
	public static final Supplier<Item> RATATOUILLE = registerWithTab(ModItemIds.RATATOUILLE,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.RATATOUILLE, FoodValues.ConsumableValues.NOURISHMENT_SHORT_DURATION));
	public static final Supplier<Item> SQUID_INK_PASTA = registerWithTab(ModItemIds.SQUID_INK_PASTA,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.SQUID_INK_PASTA, FoodValues.ConsumableValues.NOURISHMENT_LONG_DURATION));
	public static final Supplier<Item> GRILLED_SALMON = registerWithTab(ModItemIds.GRILLED_SALMON,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.GRILLED_SALMON, FoodValues.ConsumableValues.NOURISHMENT_LONG_DURATION));

	// Feasts
	public static final Supplier<Item> ROAST_CHICKEN_BLOCK = registerBlockWithTab(ModBlockItemIds.ROAST_CHICKEN_BLOCK,
			PlaceableItem::new, ModBlocks.ROAST_CHICKEN_BLOCK.get(), basicItem().stacksTo(1));
	public static final Supplier<Item> ROAST_CHICKEN = registerWithTab(ModItemIds.ROAST_CHICKEN,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.ROAST_CHICKEN, FoodValues.ConsumableValues.NOURISHMENT_LONG_DURATION));

	public static final Supplier<Item> STUFFED_PUMPKIN_BLOCK = registerBlockWithTab(ModBlockItemIds.STUFFED_PUMPKIN_BLOCK,
			PlaceableItem::new, ModBlocks.STUFFED_PUMPKIN_BLOCK.get(), basicItem().stacksTo(1));
	public static final Supplier<Item> STUFFED_PUMPKIN = registerWithTab(ModItemIds.STUFFED_PUMPKIN,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.STUFFED_PUMPKIN, FoodValues.ConsumableValues.NOURISHMENT_LONG_DURATION));

	public static final Supplier<Item> HONEY_GLAZED_HAM_BLOCK = registerBlockWithTab(ModBlockItemIds.HONEY_GLAZED_HAM_BLOCK,
			PlaceableItem::new, ModBlocks.HONEY_GLAZED_HAM_BLOCK.get(), basicItem().stacksTo(1));
	public static final Supplier<Item> HONEY_GLAZED_HAM = registerWithTab(ModItemIds.HONEY_GLAZED_HAM,
			properties -> new ConsumableItem(properties, true),
			bowlFoodItem(FoodValues.HONEY_GLAZED_HAM, FoodValues.ConsumableValues.NOURISHMENT_LONG_DURATION));

	public static final Supplier<Item> SHEPHERDS_PIE_BLOCK = registerBlockWithTab(ModBlockItemIds.SHEPHERDS_PIE_BLOCK,
			PlaceableItem::new, ModBlocks.SHEPHERDS_PIE_BLOCK.get(), basicItem().stacksTo(1));
	public static final Supplier<Item> SHEPHERDS_PIE = registerWithTab(ModItemIds.SHEPHERDS_PIE,
            properties -> new ConsumableItem(properties, true),
            bowlFoodItem(FoodValues.SHEPHERDS_PIE, FoodValues.ConsumableValues.NOURISHMENT_LONG_DURATION));

	public static final Supplier<Item> GLEAMING_SALAD_BLOCK = registerBlockWithTab(ModBlockItemIds.GLEAMING_SALAD_BLOCK,
			PlaceableItem::new, ModBlocks.GLEAMING_SALAD_BLOCK.get(), basicItem().stacksTo(1));
	public static final Supplier<Item> GLEAMING_SALAD = registerWithTab(ModItemIds.GLEAMING_SALAD,
            properties -> new ConsumableItem(properties, true),
            bowlFoodItem(FoodValues.GLEAMING_SALAD, FoodValues.ConsumableValues.NOURISHMENT_LONG_DURATION));

	public static final Supplier<Item> RICE_ROLL_MEDLEY_BLOCK = registerBlockWithTab(ModBlockItemIds.RICE_ROLL_MEDLEY_BLOCK,
			PlaceableItem::new, ModBlocks.RICE_ROLL_MEDLEY_BLOCK.get(), basicItem().stacksTo(1));

	// Pet Foods
	public static final Supplier<Item> DOG_FOOD = registerWithTab(ModItemIds.DOG_FOOD,
            DogFoodItem::new, bowlFoodItem(FoodValues.DOG_FOOD));
	public static final Supplier<Item> HORSE_FEED = registerWithTab(ModItemIds.HORSE_FEED,
			HorseFeedItem::new, basicItem().stacksTo(16));

	// Refabricated
	public static final Supplier<Item> STRAW_BED = registerBlockWithTab(ModBlockItemIds.STRAW_BED,
		BlockItem::new, ModBlocks.STRAW_BED.get(), basicItem().stacksTo(16).compostable(ContextIntProviders.COMPOSTABLE_MEDIUM_HIGH));

    // Hidden (Debug) Items
    public static final Supplier<Item> DEBUG_PUMPKIN_PIE = registerHidden(ModItemIds.DEBUG_PUMPKIN_PIE,
            properties -> new BlockItem(ModBlocks.PUMPKIN_PIE.get(), properties)
            {
                @Override
                public void appendHoverText(final ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                    super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
                    builder.accept(TextUtils.DEBUG_ITEM);
                }
            }, basicItem());

	public static void touch() {

	}
}
