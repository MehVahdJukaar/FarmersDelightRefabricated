package vectorwing.farmersdelight.refabricated;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

import java.util.Set;
import java.util.function.Supplier;

public record CanItemPerformAbilityCondition(ItemAbility ability) implements LootItemCondition {
    public static final MapCodec<CanItemPerformAbilityCondition> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            ItemAbility.CODEC.fieldOf("ability").forGetter(CanItemPerformAbilityCondition::ability)
    ).apply(inst, CanItemPerformAbilityCondition::new));
    public static final Supplier<LootItemConditionType> TYPE = RegUtils.regLootCond("can_item_perform_ability", () -> new LootItemConditionType(CODEC));

    public static void touch() {

    }

    @Override
    public boolean test(LootContext context) {
        ItemStack stack = context.getParam(LootContextParams.TOOL);
        return ability.canPerformAction(stack);
    }

    @Override
    public Set<LootContextParam<?>> getReferencedContextParams() {
        return Set.of(LootContextParams.TOOL);
    }

    @Override
    public LootItemConditionType getType() {
        return TYPE.get();
    }
}
