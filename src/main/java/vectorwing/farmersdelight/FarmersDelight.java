package vectorwing.farmersdelight;

import io.github.fabricators_of_create.porting_lib.config.ConfigRegistry;
import io.github.fabricators_of_create.porting_lib.config.ModConfig;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vectorwing.farmersdelight.common.CommonSetup;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.block.RichSoilBlock;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;
import vectorwing.farmersdelight.common.block.entity.CuttingBoardBlockEntity;
import vectorwing.farmersdelight.common.crafting.ingredient.ItemAbilityIngredient;
import vectorwing.farmersdelight.common.event.CommonEvents;
import vectorwing.farmersdelight.common.event.VillagerEvents;
import vectorwing.farmersdelight.common.item.DogFoodItem;
import vectorwing.farmersdelight.common.item.HorseFeedItem;
import vectorwing.farmersdelight.common.item.KnifeItem;
import vectorwing.farmersdelight.common.networking.ModNetworking;
import vectorwing.farmersdelight.common.registry.*;
import vectorwing.farmersdelight.common.world.VillageStructures;

public class FarmersDelight implements ModInitializer
{
	public static final String MODID = "farmersdelight";
	public static final Logger LOGGER = LogManager.getLogger();

	public static ResourceLocation res(String name) {
		return ResourceLocation.fromNamespaceAndPath(MODID, name);
	}

	@Override
	public void onInitialize() {
		ConfigRegistry.registerConfig(MODID, ModConfig.Type.COMMON, Configuration.COMMON_CONFIG);
		ConfigRegistry.registerConfig(MODID, ModConfig.Type.CLIENT, Configuration.CLIENT_CONFIG);

		ModSounds.SOUNDS.register();
		ModBlocks.BLOCKS.register();
		ModEffects.register();
		ModParticleTypes.PARTICLE_TYPES.register();
		ModItems.ITEMS.register();
		ModDataComponents.DATA_COMPONENTS.register();
		ModDataComponents.ENCHANTMENT_EFFECT_COMPONENTS.register();
		ModEntityTypes.ENTITIES.register();
		ModBlockEntityTypes.TILES.register();
		ModMenuTypes.MENU_TYPES.register();
		ModRecipeTypes.RECIPE_TYPES.register();
		ModRecipeSerializers.RECIPE_SERIALIZERS.register();
		ModBiomeFeatures.FEATURES.register();
		ModCreativeTabs.CREATIVE_TABS.register();
		ModPlacementModifiers.PLACEMENT_MODIFIERS.register();
		ModLootFunctions.LOOT_FUNCTIONS.register();
		ModLootModifiers.LOOT_MODIFIERS.register();
		ModConditionCodecs.register();
		ItemAbilityIngredient.register();
		ModAdvancements.TRIGGERS.register();

		VillageStructures.init();
		CommonEvents.init();
		VillagerEvents.init();

		CommonSetup.registerDispenserBehaviors();

		// new stuff
		ModBiomeModifiers.init();
		CookingPotBlockEntity.init();
		CuttingBoardBlockEntity.init();
		DogFoodItem.init();
		HorseFeedItem.init();
		KnifeItem.init();
		ModNetworking.init();
		RichSoilBlock.init();
		CompostableHelper.init();
	}
}
