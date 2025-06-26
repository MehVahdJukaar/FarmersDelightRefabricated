// TODO: Reimport when REI updates.
//package vectorwing.farmersdelight.integration.rei;
//
//import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
//import me.shedaniel.rei.api.common.plugins.REICommonPlugin;
//import me.shedaniel.rei.api.common.registry.display.ServerDisplayRegistry;
//import me.shedaniel.rei.api.common.util.EntryIngredients;
//import me.shedaniel.rei.plugin.common.displays.DefaultInformationDisplay;
//import net.minecraft.network.chat.Component;
//import net.minecraft.world.item.Items;
//import vectorwing.farmersdelight.FarmersDelight;
//import vectorwing.farmersdelight.common.crafting.CookingPotRecipe;
//import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
//import vectorwing.farmersdelight.common.registry.ModItems;
//import vectorwing.farmersdelight.common.registry.ModRecipeTypes;
//import vectorwing.farmersdelight.common.utility.TextUtils;
//import vectorwing.farmersdelight.integration.rei.client.display.ClientsidedCookingPotDisplay;
//import vectorwing.farmersdelight.integration.rei.display.CookingPotDisplay;
//import vectorwing.farmersdelight.integration.rei.display.CuttingDisplay;
//import vectorwing.farmersdelight.integration.rei.display.DecompositionDisplay;
//
//import java.util.List;
//
//public class CommonREIPlugin implements REICommonPlugin {
//    @Override
//    public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
//        registry.register(FarmersDelight.res("default/cooking"), CookingPotDisplay.SERIALIZER);
//        registry.register(FarmersDelight.res("default/cutting"), CuttingDisplay.SERIALIZER);
//        registry.register(FarmersDelight.res("default/decomposition"), DecompositionDisplay.SERIALIZER);
//    }
//
//    @Override
//    public void registerDisplays(ServerDisplayRegistry registry) {
//        registry.beginRecipeFiller(CookingPotRecipe.class)
//                .filterType(ModRecipeTypes.COOKING.get())
//                .fill(CookingPotDisplay::new);
//        registry.beginRecipeFiller(CuttingBoardRecipe.class)
//                .filterType(ModRecipeTypes.CUTTING.get())
//                .fill(CuttingDisplay::new);
//        registry.add(new DecompositionDisplay());
//
//        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.of(ModItems.STRAW.get()), Component.translatable("item.farmersdelight.straw")).lines(TextUtils.getTranslation("jei.info.straw")));
//        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.of(ModItems.HAM.get()), Component.translatable("item.farmersdelight.ham")).lines(TextUtils.getTranslation("jei.info.ham")));
//        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(ModItems.FLINT_KNIFE.get(), ModItems.IRON_KNIFE.get(), ModItems.IRON_KNIFE.get(), ModItems.DIAMOND_KNIFE.get(), ModItems.NETHERITE_KNIFE.get(), ModItems.GOLDEN_KNIFE.get())), Component.translatable("tag.item.farmersdelight.tools.knives")).lines(TextUtils.getTranslation("jei.info.knife")));
//
//        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(ModItems.WILD_CABBAGES.get(), ModItems.CABBAGE.get(), ModItems.CABBAGE_LEAF.get())), Component.translatable("item.farmersdelight.cabbage")).lines(TextUtils.getTranslation("jei.info.wild_cabbages")));
//        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(ModItems.WILD_BEETROOTS.get(), Items.BEETROOT)), Component.translatable("item.minecraft.beetroot")).lines(TextUtils.getTranslation("jei.info.wild_beetroots")));
//        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(ModItems.WILD_CARROTS.get(), Items.CARROT)), Component.translatable("item.minecraft.carrot")).lines(TextUtils.getTranslation("jei.info.wild_carrots")));
//        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(ModItems.WILD_ONIONS.get(), ModItems.ONION.get())), Component.translatable("item.farmersdelight.onion")).lines(TextUtils.getTranslation("jei.info.wild_onions")));
//        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(ModItems.WILD_POTATOES.get(), Items.POTATO)), Component.translatable("item.minecraft.potato")).lines(TextUtils.getTranslation("jei.info.wild_potatoes")));
//        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(ModItems.WILD_TOMATOES.get(), ModItems.TOMATO.get())), Component.translatable("item.farmersdelight.tomato")).lines(TextUtils.getTranslation("jei.info.wild_tomatoes")));
//        registry.add(DefaultInformationDisplay.createFromEntries(EntryIngredients.ofItems(List.of(ModItems.WILD_RICE.get(), ModItems.RICE_PANICLE.get())), Component.translatable("item.farmersdelight.rice")).lines(TextUtils.getTranslation("jei.info.wild_rice")));
//    }
//}
