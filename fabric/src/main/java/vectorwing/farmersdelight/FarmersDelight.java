package vectorwing.farmersdelight;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.api.ModInitializer;
import net.minecraft.world.inventory.RecipeBookType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vectorwing.farmersdelight.client.FarmersDelightClient;
import vectorwing.farmersdelight.common.CommonSetup;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.registry.*;
import vectorwing.farmersdelight.common.world.VillageStructures;
import vectorwing.farmersdelight.common.world.WildCropGeneration;


public class FarmersDelight implements ModInitializer
{
	public static final String MODID = "farmersdelight";
	public static final Logger LOGGER = LogManager.getLogger();
	public static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

	public static final RecipeBookType RECIPE_TYPE_COOKING = RecipeBookType.create("COOKING");

	@Override
	public void onInitialize() {

		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		modEventBus.addListener(CommonSetup::init);
		if (FMLEnvironment.dist.isClient()) {
			modEventBus.addListener(FarmersDelightClient::init);
		}

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Configuration.COMMON_CONFIG);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Configuration.CLIENT_CONFIG);

		ModSounds.SOUNDS.register();
		ModBlocks.BLOCKS.register();
		ModEffects.EFFECTS.register();
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
		ModBiomeModifiers.BIOME_MODIFIER_SERIALIZERS.register();
		ModLootFunctions.LOOT_FUNCTIONS.register();
		ModLootModifiers.LOOT_MODIFIERS.register();

		WildCropGeneration.load();
		MinecraftForge.EVENT_BUS.addListener(VillageStructures::addNewVillageBuilding);

		MinecraftForge.EVENT_BUS.register(this);
	}
}
