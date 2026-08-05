package vectorwing.farmersdelight.refabricated;

import com.mojang.datafixers.util.Either;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.entries.UniformContainerBase;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

record LootTableHolder(ResourceKey<LootTable> key) implements Holder<LootTable> {
	static Registry<LootTable> lootRegistry;

	static UniformContainerBase.Builder<?> lootTableReference(ResourceKey<LootTable> key) {
		return NestedLootTable.lootTableReference(new LootTableHolder(key));
	}

	@Override
	public LootTable value() {
		return lootRegistry.getValue(key);
	}

	@Override
	public boolean isBound() {
		return lootRegistry != null && lootRegistry.get(key).isPresent();
	}

	@Override
	public boolean areComponentsBound() {
		return true;
	}

	@Override
	public boolean is(Identifier key) {
		return key.equals(this.key.identifier());
	}

	@Override
	public boolean is(ResourceKey key) {
		return key.equals(this.key);
	}

	@Override
	public boolean is(TagKey<LootTable> tag) {
		return false;
	}

	@Override
	public boolean is(Holder<LootTable> holder) {
		return this.value().equals(holder.value());
	}

	@Override
	public Stream<TagKey<LootTable>> tags() {
		return Stream.empty();
	}

	@Override
	public DataComponentMap components() {
		return null;
	}

	@Override
	public Either<ResourceKey<LootTable>, LootTable> unwrap() {
		if (isBound()) {
			return Either.right(value());
		} else {
			return Either.left(key);
		}
	}

	@Override
	public Optional<ResourceKey<LootTable>> unwrapKey() {
		return Optional.of(key);
	}

	@Override
	public Kind kind() {
		return Kind.REFERENCE;
	}

	@Override
	public boolean canSerializeIn(HolderOwner registry) {
		return false;
	}

	@Override
	public boolean is(Predicate predicate) {
		return false;
	}
}
