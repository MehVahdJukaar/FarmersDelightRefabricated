package vectorwing.farmersdelight.refabricated.datamap;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.common.datamap.MushroomColony;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class RefabricatedDataMapType<R, T> {
	Map<Holder<Block>, T> map = new HashMap<>();
	private final ResourceLocation id;
	private final ResourceKey<Registry<R>> registry;
	private final Codec<T> codec;
	private final Codec<T> networkCodec;
	private final boolean mandatorySync;

	public RefabricatedDataMapType(ResourceLocation id, ResourceKey<Registry<R>> registry,
								   Codec<T> codec, Codec<T> networkCodec, boolean mandatorySync) {
		this.id = id;
		this.registry = registry;
		this.codec = codec;
		this.networkCodec = networkCodec;
		this.mandatorySync = mandatorySync;
	}

	public T getData(Holder<R> blockReference) {
		return map.get(blockReference);
	}

	public ResourceLocation id() {
		return id;
	}

	public ResourceKey<Registry<R>> registry() {
		return registry;
	}

	public Codec<T> codec() {
		return codec;
	}

	public Codec<Map<Holder<Block>, T>> mapCodec() {
		return Codec.unboundedMap(BuiltInRegistries.BLOCK.holderByNameCodec(), codec).fieldOf("values").codec();
	}

	public Codec<T> networkCodec() {
		return networkCodec;
	}

	public boolean mandatorySync() {
		return mandatorySync;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (RefabricatedDataMapType) obj;
		return Objects.equals(this.id, that.id) &&
			Objects.equals(this.registry, that.registry) &&
			Objects.equals(this.codec, that.codec) &&
			Objects.equals(this.networkCodec, that.networkCodec) &&
			this.mandatorySync == that.mandatorySync;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, registry, codec, networkCodec, mandatorySync);
	}

	@Override
	public String toString() {
		return "RefabricatedDataMapType[" +
			"id=" + id + ", " +
			"registry=" + registry + ", " +
			"codec=" + codec + ", " +
			"networkCodec=" + networkCodec + ", " +
			"mandatorySync=" + mandatorySync + ']';
	}

}
