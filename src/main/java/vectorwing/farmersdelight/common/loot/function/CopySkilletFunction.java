package vectorwing.farmersdelight.common.loot.function;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.block.entity.SkilletBlockEntity;

import java.util.List;
import java.util.Optional;

public class CopySkilletFunction extends LootItemConditionalFunction
{
	public static final Identifier ID = Identifier.fromNamespaceAndPath(FarmersDelight.MODID, "copy_skillet");
	public static final MapCodec<CopySkilletFunction> CODEC = RecordCodecBuilder.mapCodec(
			p_298131_ -> commonFields(p_298131_).apply(p_298131_, CopySkilletFunction::new)
	);

	private CopySkilletFunction(Optional<Holder<LootItemCondition>> conditions) {
		super(conditions);
	}

	public static Builder<?> builder() {
		return simpleBuilder(CopySkilletFunction::new);
	}

	@Override
	protected ItemStack run(ItemStack stack, LootContext context) {
		BlockEntity tile = context.getOptionalParameter(LootContextParams.BLOCK_ENTITY);
		if (tile instanceof SkilletBlockEntity blockEntity) {
			stack = blockEntity.getSkilletAsItem();
		}
		return stack;
	}

    @Override
    public MapCodec<? extends LootItemConditionalFunction> codec() {
        return CODEC;
    }
}
