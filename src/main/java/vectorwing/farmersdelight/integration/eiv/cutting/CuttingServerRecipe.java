package vectorwing.farmersdelight.integration.eiv.cutting;

import de.crafty.eiv.common.api.recipe.EivRecipeType;
import de.crafty.eiv.common.api.recipe.IEivServerRecipe;
import de.crafty.eiv.common.recipe.util.EivTagUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;

import java.util.List;

public class CuttingServerRecipe implements IEivServerRecipe {
    public static final EivRecipeType<CuttingServerRecipe> TYPE = EivRecipeType.register(
            FarmersDelight.res("cutting"),
            () -> new CuttingServerRecipe(null, null, null)
    );

    private Ingredient ingredient;
    private List<ItemStack> results;
    private Ingredient tool;
    private List<ChanceResult> rollableResults;

    public CuttingServerRecipe(Ingredient input, List<ItemStack> results, Ingredient tool) {
        this.ingredient = input;
        this.results = results;
        this.tool = tool;
    }

    public List<ItemStack> getResults() {
        return this.results;
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    }

    public Ingredient getTool() {
        return this.tool;
    }

    public List<ChanceResult> getRollableResults() {
        return this.rollableResults;
    }

    @Override
    public void writeToTag(CompoundTag tag) {
        tag.put("ingredients", EivTagUtil.writeIngredient(this.ingredient));
        tag.put("result", EivTagUtil.writeList(this.results, (origin, tag1) -> EivTagUtil.encodeItemStack(origin)));
        tag.put("tool", EivTagUtil.writeIngredient(this.tool));
    }

    @Override
    public void loadFromTag(CompoundTag tag) {
        this.results = EivTagUtil.readList(tag, "result", EivTagUtil::decodeItemStack);
        this.ingredient = EivTagUtil.readIngredient(tag.getCompound("ingredients").orElseGet(CompoundTag::new));
        this.tool = EivTagUtil.readIngredient(tag.getCompound("tool").orElseGet(CompoundTag::new));
    }

    @Override
    public EivRecipeType<? extends IEivServerRecipe> getRecipeType() {
        return TYPE;
    }
}