package kiwiapollo.wanteditems.swapper;

import com.cobblemon.mod.common.CobblemonSounds;
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.api.item.PokemonSelectingItem;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.cobblemon.mod.common.item.battle.BagItem;
import com.cobblemon.mod.common.pokemon.Gender;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GenderSwapper extends Item implements PokemonSelectingItem {
	public GenderSwapper() {
		super(new Item.Properties());
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
		ItemStack itemStack = player.getItemInHand(usedHand);
		return level.isClientSide() ? InteractionResultHolder.pass(itemStack) : this.use((ServerPlayer) player, itemStack);
	}

	public @Nullable BagItem getBagItem() {
		return null;
	}

	public @NotNull InteractionResultHolder<ItemStack> use(@NotNull ServerPlayer player, @NotNull ItemStack itemStack) {
		return DefaultImpls.use(this, player, itemStack);
	}

	@Nullable
	@Override
	public InteractionResultHolder<ItemStack> applyToPokemon(@NotNull ServerPlayer player, @NotNull ItemStack itemStack, @NotNull Pokemon pokemon) {
		switch (pokemon.getGender()) {
			case GENDERLESS:
				player.makeSound(SoundEvents.SHIELD_BLOCK);
				player.sendSystemMessage(Component.translatable("item.wanteditems.error.is_genderless", pokemon.getSpecies().getTranslatedName()).withStyle(ChatFormatting.RED));
				return InteractionResultHolder.pass(itemStack);
			case FEMALE:
				this.setMale(pokemon);
				break;
			case MALE:
				this.setFemale(pokemon);
		}

		if (!player.isCreative()) {
			itemStack.shrink(1);
		}

		player.makeSound(CobblemonSounds.MEDICINE_PILLS_USE);
		return InteractionResultHolder.success(itemStack);
	}

	private void setMale(Pokemon pokemon) {
		pokemon.setGender(Gender.MALE);
	}

	private void setFemale(Pokemon pokemon) {
		pokemon.setGender(Gender.FEMALE);
	}

	@Override
	public void applyToBattlePokemon(@NotNull ServerPlayer serverPlayer, @NotNull ItemStack itemStack, @NotNull BattlePokemon battlePokemon) {

	}

	public boolean canUseOnPokemon(@NotNull Pokemon pokemon) {
		return true;
	}

	public boolean canUseOnBattlePokemon(@NotNull BattlePokemon battlePokemon) {
		return false;
	}

	@NotNull
	@Override
	public InteractionResultHolder<ItemStack> interactWithSpecificBattle(@NotNull ServerPlayer serverPlayer, @NotNull ItemStack itemStack, @NotNull BattlePokemon battlePokemon) {
		return DefaultImpls.interactWithSpecificBattle(this, serverPlayer, itemStack, battlePokemon);
	}

	@NotNull
	@Override
	public InteractionResultHolder<ItemStack> interactGeneral(@NotNull ServerPlayer serverPlayer, @NotNull ItemStack itemStack) {
		return DefaultImpls.interactGeneral(this, serverPlayer, itemStack);
	}

	@NotNull
	@Override
	public InteractionResultHolder<ItemStack> interactGeneralBattle(@NotNull ServerPlayer serverPlayer, @NotNull ItemStack itemStack, @NotNull BattleActor battleActor) {
		return DefaultImpls.interactGeneralBattle(this, serverPlayer, itemStack, battleActor);
	}
}
