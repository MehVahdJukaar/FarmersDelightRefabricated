package vectorwing.farmersdelight;

import com.chocohead.mm.api.ClassTinkerers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.fabricators_of_create.porting_lib.config.ConfigRegistry;
import io.github.fabricators_of_create.porting_lib.config.ConfigType;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.RecipeBookType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vectorwing.farmersdelight.common.CommonSetup;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.block.CuttingBoardBlock;
import vectorwing.farmersdelight.common.block.RichSoilBlock;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;
import vectorwing.farmersdelight.common.block.entity.CuttingBoardBlockEntity;
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
	public static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

	// Moved initializer to RecipeBookTypeMixin.
	public static final RecipeBookType RECIPE_TYPE_COOKING = ClassTinkerers.getEnum(RecipeBookType.class, FarmersDelightASM.COOKING_RECIPE_BOOK_TYPE);

	public static ResourceLocation res(String name) {
		return new ResourceLocation(MODID, name);
	}

	@SuppressWarnings("UnstableApiUsage")
	@Override
	public void onInitialize() {
		ConfigRegistry.registerConfig(MODID, ConfigType.COMMON, Configuration.COMMON_CONFIG);
		ConfigRegistry.registerConfig(MODID, ConfigType.CLIENT, Configuration.CLIENT_CONFIG);
		ModSounds.init();
		ModBlocks.BLOCKS.register();
		ModEffects.init();
		ModParticleTypes.PARTICLE_TYPES.register();
		ModItems.ITEMS.register();
		ModEntityTypes.ENTITIES.register();
		ModEnchantments.ENCHANTMENTS.register();
		ModBlockEntityTypes.TILES.register();
		ModMenuTypes.MENU_TYPES.register();
		ModRecipeTypes.RECIPE_TYPES.register();
		ModRecipeSerializers.RECIPE_SERIALIZERS.register();
		ModBiomeFeatures.FEATURES.register();
		ModCreativeTabs.CREATIVE_TABS.register();
		ModPlacementModifiers.PLACEMENT_MODIFIERS.register();
		ModLootFunctions.LOOT_FUNCTIONS.register();
		ModLootModifiers.LOOT_MODIFIERS.register();

		VillageStructures.init();
		CommonEvents.init();
		VillagerEvents.init();


		CommonSetup.init();

		// new stuff
		ModBiomeModifiers.init();
		CookingPotBlockEntity.init();
		CuttingBoardBlock.init();
		CuttingBoardBlockEntity.init();
		DogFoodItem.init();
		HorseFeedItem.init();
		KnifeItem.init();
		ModNetworking.init();
		RichSoilBlock.init();
	}
}
