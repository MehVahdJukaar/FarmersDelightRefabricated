package vectorwing.farmersdelight.integration.rrv.cooking_pot;

import cc.cassian.rrv.api.TagUtil;
import cc.cassian.rrv.api.recipe.ReliableServerRecipe;
import cc.cassian.rrv.api.recipe.ReliableServerRecipeType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.FarmersDelight;

import java.util.List;

public class CookingPotServerRecipe implements ReliableServerRecipe {

    public static final ReliableServerRecipeType<CookingPotServerRecipe> TYPE = ReliableServerRecipeType.register(
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

        tag.put("ingredients", TagUtil.writeList(this.ingredients, (origin, tag1) -> TagUtil.writeIngredient(origin)));
        tag.put("result", TagUtil.encodeItemStackOnServer(this.result));
        if (!this.container.isEmpty())
            tag.put("container", TagUtil.encodeItemStackOnServer(this.container));
        tag.put("cookingtime", IntTag.valueOf(this.cookTime));
        tag.put("experience", FloatTag.valueOf(this.experience));
    }

    @Override
    public void loadFromTag(CompoundTag tag) {

        this.ingredients = TagUtil.readList(tag, "ingredients", TagUtil::readIngredient);
        this.result = TagUtil.decodeItemStackOnClient(tag.getCompound("result").orElseGet(CompoundTag::new));
        this.container = TagUtil.decodeItemStackOnClient(tag.getCompound("container").orElseGet(CompoundTag::new));
        this.cookTime = tag.getIntOr("cookingtime", 0);
        this.experience = tag.getFloatOr("experience", 0);
    }

    @Override
    public ReliableServerRecipeType<? extends ReliableServerRecipe> getRecipeType() {
        return TYPE;
    }
}