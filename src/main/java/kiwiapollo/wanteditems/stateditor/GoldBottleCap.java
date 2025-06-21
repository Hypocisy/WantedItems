package kiwiapollo.wanteditems.stateditor;

import com.cobblemon.mod.common.CobblemonSounds;
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.api.item.PokemonSelectingItem;
import com.cobblemon.mod.common.api.pokemon.stats.Stats;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.cobblemon.mod.common.item.battle.BagItem;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.*;
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

public class GoldBottleCap extends Item implements PokemonSelectingItem {
	public GoldBottleCap() {
		super(new Item.Properties());
	}

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
		if (this.isPerfectIVs(pokemon)) {
			player.makeSound(SoundEvents.SHIELD_BLOCK);
			player.sendSystemMessage(Component.translatable("item.wanteditems.error.has_maximum_stats", pokemon.getSpecies().getTranslatedName()).withStyle(ChatFormatting.RED));
			return InteractionResultHolder.pass(itemStack);
		} else {
			pokemon.setIV(Stats.ATTACK, 31);
			pokemon.setIV(Stats.DEFENCE, 31);
			pokemon.setIV(Stats.SPECIAL_ATTACK, 31);
			pokemon.setIV(Stats.SPECIAL_DEFENCE, 31);
			pokemon.setIV(Stats.HP, 31);
			pokemon.setIV(Stats.SPEED, 31);
			if (!player.isCreative()) {
				itemStack.shrink(1);
			}

			player.makeSound(CobblemonSounds.MEDICINE_PILLS_USE);
			return InteractionResultHolder.success(itemStack);
		}
	}

	private boolean isPerfectIVs(Pokemon pokemon) {
		return pokemon.getIvs().get(Stats.ATTACK).equals(31) && pokemon.getIvs().get(Stats.DEFENCE).equals(31) && pokemon.getIvs().get(Stats.SPECIAL_ATTACK).equals(31) && pokemon.getIvs().get(Stats.SPECIAL_DEFENCE).equals(31) && pokemon.getIvs().get(Stats.HP).equals(31) && pokemon.getIvs().get(Stats.SPEED).equals(31);
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
