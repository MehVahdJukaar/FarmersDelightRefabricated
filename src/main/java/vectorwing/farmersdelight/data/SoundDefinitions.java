package vectorwing.farmersdelight.data;

import net.fabricmc.fabric.api.client.datagen.v1.builder.SoundTypeBuilder;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricSoundsProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class SoundDefinitions extends FabricSoundsProvider
{
	protected SoundDefinitions(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(HolderLookup.Provider registryLookup, SoundExporter exporter) {
		this.generateNewSoundWithSubtitle(exporter, ModSounds.BLOCK_COOKING_POT_BOIL, "block/cooking_pot/boil_water", 2);
		this.generateNewSoundCustomSubtitle(exporter, ModSounds.BLOCK_COOKING_POT_BOIL_SOUP, "block/cooking_pot/boil_soup", 3, "subtitles.farmersdelight.block.cooking_pot.boil");
		this.generateNewSoundWithSubtitle(exporter, ModSounds.BLOCK_CUTTING_BOARD_KNIFE, "block/cutting_board/knife", 2);
		this.generateNewSoundWithSubtitle(exporter, ModSounds.BLOCK_SKILLET_ADD_FOOD, "block/skillet/add_food", 2);
		this.generateNewSound(exporter, ModSounds.ITEM_SKILLET_ATTACK_STRONG, "block/skillet/attack_strong", 1, false);
		this.generateNewSound(exporter, ModSounds.ITEM_SKILLET_ATTACK_WEAK, "block/skillet/attack_weak", 1, false);
		this.generateNewSoundWithSubtitle(exporter, ModSounds.BLOCK_SKILLET_SIZZLE, "block/skillet/sizzle", 3);

		this.generateExistingSoundWithSubtitle(exporter, ModSounds.BLOCK_CUTTING_BOARD_CARVE, SoundEvents.WOOD_PLACE);
		this.generateExistingSoundWithSubtitle(exporter, ModSounds.BLOCK_CUTTING_BOARD_PLACE, SoundEvents.WOOD_PLACE);
		this.generateExistingSoundWithSubtitle(exporter, ModSounds.BLOCK_CUTTING_BOARD_REMOVE, SoundEvents.WOOD_HIT);
		this.generateExistingSoundWithSubtitle(exporter, ModSounds.BLOCK_STOVE_CRACKLE, SoundEvents.CAMPFIRE_CRACKLE);
		this.generateExistingSoundWithSubtitle(exporter, ModSounds.BLOCK_ROPE_FENCE_GATE_CLOSE, SoundEvents.LEAD_TIED);
		this.generateExistingSoundWithSubtitle(exporter, ModSounds.BLOCK_ROPE_FENCE_GATE_OPEN, SoundEvents.LEAD_BREAK);
		this.generateExistingSoundWithSubtitle(exporter, ModSounds.BLOCK_CABINET_CLOSE, SoundEvents.BARREL_CLOSE);
		this.generateExistingSoundWithSubtitle(exporter, ModSounds.BLOCK_CABINET_OPEN, SoundEvents.BARREL_OPEN);
		this.generateExistingSoundWithSubtitle(exporter, ModSounds.BLOCK_TOMATOES_PICK_TOMATOES, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES);
		this.generateExistingSoundWithSubtitle(exporter, ModSounds.BLOCK_FOOD_TAKE_PORTION, SoundEvents.ARMOR_EQUIP_GENERIC.value());
		this.generateExistingSoundWithSubtitle(exporter, ModSounds.BLOCK_FOOD_SLICE, SoundEvents.WOOL_BREAK);
		this.generateExistingSoundWithSubtitle(exporter, ModSounds.ENTITY_ROTTEN_TOMATO_THROW, SoundEvents.SNOWBALL_THROW);
		this.generateExistingSoundWithSubtitle(exporter, ModSounds.ENTITY_ROTTEN_TOMATO_HIT, SoundEvents.SLIME_ATTACK);

	}

	public void generateNewSoundWithSubtitle(SoundExporter exporter, Supplier<SoundEvent> event, String baseSoundDirectory, int numberOfSounds) {
		generateNewSound(exporter, event, baseSoundDirectory, numberOfSounds, true);
	}

	public void generateNewSound(SoundExporter exporter, Supplier<SoundEvent> event, String baseSoundDirectory, int numberOfSounds, boolean subtitle) {
		String formattedSub = subtitle ? TextUtils.subtitleKey(event.get().location().getPath()) : null;
		this.generateNewSoundCustomSubtitle(exporter, event, baseSoundDirectory, numberOfSounds, formattedSub);
	}

	public void generateNewSoundCustomSubtitle(SoundExporter exporter, Supplier<SoundEvent> event, String baseSoundDirectory, int numberOfSounds, @Nullable String subtitle) {
		SoundTypeBuilder definition = SoundTypeBuilder.of();
		if (subtitle != null) {
			definition.subtitle(subtitle);
		}
		for (int i = 1; i <= numberOfSounds; i++) {
			definition.sound(SoundTypeBuilder.RegistrationBuilder.ofFile(Identifier.fromNamespaceAndPath(FarmersDelight.MODID, baseSoundDirectory + (numberOfSounds > 1 ? i : ""))));
		}
		exporter.add(event.get(), definition);
	}

	public void generateExistingSoundWithSubtitle(SoundExporter exporter, Supplier<SoundEvent> event, SoundEvent referencedSound) {
		this.generateExistingSound(exporter, event, referencedSound, true);
	}

	public void generateExistingSound(SoundExporter exporter, Supplier<SoundEvent> event, SoundEvent referencedSound, boolean subtitle) {
		SoundTypeBuilder definition = SoundTypeBuilder.of();;
		if (subtitle) {
			definition.subtitle(TextUtils.subtitleKey(event.get().location().getPath()));
		}
		exporter.add(event.get(), definition
				.sound(SoundTypeBuilder.RegistrationBuilder.ofEvent(referencedSound)));
	}

	@Override
	public String getName() {
		return "";
	}
}
