package vectorwing.farmersdelight;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vectorwing.farmersdelight.common.CommonSetup;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.block.CuttingBoardBlock;
import vectorwing.farmersdelight.common.block.RichSoilBlock;
import vectorwing.farmersdelight.common.block.entity.CookingPotBlockEntity;
import vectorwing.farmersdelight.common.block.entity.CuttingBoardBlockEntity;
import vectorwing.farmersdelight.common.crafting.condition.VanillaCrateEnabledCondition;
import vectorwing.farmersdelight.common.crafting.ingredient.ItemAbilityIngredient;
import vectorwing.farmersdelight.common.event.CommonModBusEvents;
import vectorwing.farmersdelight.common.event.VillagerEvents;
import vectorwing.farmersdelight.common.item.DogFoodItem;
import vectorwing.farmersdelight.common.item.HorseFeedItem;
import vectorwing.farmersdelight.common.item.KnifeItem;
import vectorwing.farmersdelight.common.networking.ModNetworking;
import vectorwing.farmersdelight.common.registry.*;
import vectorwing.farmersdelight.common.world.VillageStructures;
import vectorwing.farmersdelight.integration.jei.JEIPlugin;
import vectorwing.farmersdelight.refabricated.CanItemPerformAbilityCondition;
import vectorwing.farmersdelight.refabricated.CompostableHelper;
import vectorwing.farmersdelight.refabricated.LootModificationEvents;

public class FarmersDelight implements ModInitializer
{
	public static final String MODID = "farmersdelight";
	public static final Logger LOGGER = LoggerFactory.getLogger(FarmersDelight.class);

	public static ResourceLocation res(String name) {
		return ResourceLocation.fromNamespaceAndPath(MODID, name);
	}

	@Override
	public void onInitialize() {
		Configuration.touch();

		ModSounds.touch();
		ModBlocks.touch();
		ModEffects.touch();
		ModParticleTypes.touch();
		ModItems.touch();
		ModDataComponents.touch();
		ModEntityTypes.touch();
		ModBlockEntityTypes.touch();
		ModMenuTypes.touch();
		ModRecipeTypes.touch();
		ModRecipeSerializers.touch();
		ModBiomeFeatures.touch();
		ModAdvancements.touch();
		ModPlacementModifiers.touch();
		ModLootFunctions.touch();
		ModCreativeTabs.touch();

		VillageStructures.init();
		CommonModBusEvents.init();
		VillagerEvents.init();

		CommonSetup.init();

		// new stuff
		VanillaCrateEnabledCondition.init();
		CanItemPerformAbilityCondition.init();
		LootModificationEvents.init();
		ModBiomeModifiers.init();
		CookingPotBlockEntity.init();
		CuttingBoardBlock.init();
		CuttingBoardBlockEntity.init();
		DogFoodItem.init();
		HorseFeedItem.init();
		KnifeItem.init();
		ModNetworking.init();
		RichSoilBlock.init();
		ItemAbilityIngredient.init();
		ModConsumeEffectTypes.touch();
		ModRecipeDisplays.touch();
		ModRecipeBookCategories.touch();

		CompostableHelper.apply();

		ServerPlayerEvents.JOIN.register(serverPlayer ->
				ServerPlayNetworking.send(serverPlayer, new ModNetworking.SendNaturalRegenerationValueMessage(serverPlayer.level().getGameRules().getBoolean(GameRules.RULE_NATURAL_REGENERATION))));


        if (FabricLoader.getInstance().isModLoaded("jei")) {
            JEIPlugin.syncRecipes();
        }
    }
}
