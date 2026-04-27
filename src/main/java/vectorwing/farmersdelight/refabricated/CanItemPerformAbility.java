package vectorwing.farmersdelight.refabricated;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

import javax.swing.plaf.PanelUI;
import java.util.Set;
import java.util.function.Supplier;

public record CanItemPerformAbility(ItemAbility ability) implements LootItemCondition {
    public static final MapCodec<CanItemPerformAbility> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            ItemAbility.CODEC.fieldOf("ability").forGetter(CanItemPerformAbility::ability)
    ).apply(inst, CanItemPerformAbility::new));
    public static final Supplier<LootItemConditionType> TYPE = RegUtils.regLootCond("can_item_perform_ability", () -> new LootItemConditionType(CODEC));

    public static void init() {

    }

	public static LootItemCondition.Builder canItemPerformAbility(ItemAbility ability) {
		return () -> new CanItemPerformAbility(ability);
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
