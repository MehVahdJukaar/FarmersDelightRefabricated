package vectorwing.farmersdelight.integration.rrv.cutting;

import cc.cassian.rrv.api.TagUtil;
import cc.cassian.rrv.api.recipe.ReliableServerRecipe;
import cc.cassian.rrv.api.recipe.ReliableServerRecipeType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;

import java.util.ArrayList;
import java.util.List;

public class CuttingServerRecipe implements ReliableServerRecipe {
    public static final ReliableServerRecipeType<CuttingServerRecipe> TYPE = ReliableServerRecipeType.register(
            FarmersDelight.res("cutting"),
            () -> new CuttingServerRecipe(null, null, null, null)
    );

    private Ingredient ingredient;
    private List<ItemStack> results;
    private Ingredient tool;
    private List<ChanceResult> rollableResults;

    public CuttingServerRecipe(Ingredient input, List<ItemStack> results, Ingredient tool, List<ChanceResult> rollableResults) {
        this.ingredient = input;
        this.results = results;
        this.tool = tool;
        this.rollableResults = rollableResults;
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
        tag.put("ingredients", TagUtil.writeIngredient(this.ingredient));
        tag.put("result", TagUtil.writeList(this.results, (origin, tag1) -> TagUtil.encodeItemStackOnServer(origin)));
        tag.put("tool", TagUtil.writeIngredient(this.tool));
        tag.put("rollable_result", encodeRollableResult(this.rollableResults));
    }

    private static ListTag encodeRollableResult(List<ChanceResult> rollableResults) {
        ListTag listTag = new ListTag();
        for (ChanceResult rollableResult : rollableResults) {
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.put("item", TagUtil.encodeItemStackOnServer(rollableResult.stack()));
            compoundTag.putFloat("chance", rollableResult.chance());
            listTag.add(compoundTag);
        }
        return listTag;
    }

    private static List<ChanceResult> decodeRollableResult(Tag providedTag) {
        List<ChanceResult> list = new ArrayList<>();
        if (providedTag instanceof ListTag listTag) {
            for (Tag tag : listTag) {
                CompoundTag compoundTag = tag.asCompound().orElseGet(CompoundTag::new);
                var stack = TagUtil.decodeItemStackOnClient(compoundTag.getCompoundOrEmpty("item"));
                var chance = compoundTag.getFloatOr("chance", 1);
                ChanceResult chanceResult = new ChanceResult(stack, chance);
                list.add(chanceResult);
            }
        }
        return list;
    }

    @Override
    public void loadFromTag(CompoundTag tag) {
        this.results = TagUtil.readList(tag, "result", TagUtil::decodeItemStackOnClient);
        this.ingredient = TagUtil.readIngredient(tag.getCompound("ingredients").orElseGet(CompoundTag::new));
        this.tool = TagUtil.readIngredient(tag.getCompound("tool").orElseGet(CompoundTag::new));
        this.rollableResults = decodeRollableResult(tag.get("rollable_result"));
    }

    @Override
    public ReliableServerRecipeType<? extends ReliableServerRecipe> getRecipeType() {
        return TYPE;
    }
}