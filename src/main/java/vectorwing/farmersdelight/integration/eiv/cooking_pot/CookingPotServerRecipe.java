package vectorwing.farmersdelight.integration.eiv.cooking_pot;

import de.crafty.eiv.common.api.recipe.EivRecipeType;
import de.crafty.eiv.common.api.recipe.IEivServerRecipe;
import de.crafty.eiv.common.recipe.util.EivTagUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.FarmersDelight;

import java.util.List;

public class CookingPotServerRecipe implements IEivServerRecipe {

    public static final EivRecipeType<CookingPotServerRecipe> TYPE = EivRecipeType.register(
            FarmersDelight.res("cooking_pot"),
            () -> new CookingPotServerRecipe(null, ItemStack.EMPTY, ItemStack.EMPTY,0, 0)
    );

    private List<Ingredient> ingredients;
    private ItemStack result;
    private ItemStack container;
    private int cookTime;
    private float experience;

    public CookingPotServerRecipe(List<Ingredient> ingredients, ItemStack result, ItemStack container, float experience, int cookTime) {
        this.ingredients = ingredients;
        this.result = result;
        this.container = container;
        this.experience = experience;
        this.cookTime = cookTime;
    }

    public List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public ItemStack getResult() {
        return this.result;
    }

    public ItemStack getContainer() {
        return this.container;
    }

    public float getExperience() {
        return this.experience;
    }

    public int getCookTime() {
        return this.cookTime;
    }


    @Override
    public void writeToTag(CompoundTag tag) {

        tag.put("ingredients", EivTagUtil.writeList(this.ingredients, (origin, tag1) -> EivTagUtil.writeIngredient(origin)));
        tag.put("result", EivTagUtil.encodeItemStack(this.result));
        if (!this.container.isEmpty())
            tag.put("container", EivTagUtil.encodeItemStack(this.container));
        tag.put("cookingtime", IntTag.valueOf(this.cookTime));
        tag.put("experience", FloatTag.valueOf(this.experience));
    }

    @Override
    public void loadFromTag(CompoundTag tag) {

        this.ingredients = EivTagUtil.readList(tag, "ingredients", EivTagUtil::readIngredient);
        this.result = EivTagUtil.decodeItemStack(tag.getCompound("result").orElseGet(CompoundTag::new));
        this.container = EivTagUtil.decodeItemStack(tag.getCompound("container").orElseGet(CompoundTag::new));
        this.cookTime = tag.getIntOr("cookingtime", 0);
        this.experience = tag.getFloatOr("experience", 0);
    }

    @Override
    public EivRecipeType<? extends IEivServerRecipe> getRecipeType() {
        return TYPE;
    }
}