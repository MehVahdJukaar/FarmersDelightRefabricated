package vectorwing.farmersdelight.refabricated;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public record RefabricatedDataMapType<R, T>(ResourceLocation id, ResourceKey<Registry<R>> registry,
											Codec<T> codec, Codec<T> networkCodec, boolean mandatorySync) {
	public T getData(Holder.Reference<R> blockReference) {
		return null; //FIXME
	}
}
