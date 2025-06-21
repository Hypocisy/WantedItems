package kiwiapollo.wanteditems.stateditor;

import com.cobblemon.mod.common.CobblemonSounds;
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.api.item.PokemonSelectingItem;
import com.cobblemon.mod.common.api.pokemon.stats.Stats;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.cobblemon.mod.common.item.battle.BagItem;
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

public class CopperBottleCap extends Item implements PokemonSelectingItem {
	public CopperBottleCap() {
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
		if (this.isZeroEVs(pokemon)) {
			player.makeSound(SoundEvents.SHIELD_BLOCK);
			player.sendSystemMessage(Component.translatable("item.wanteditems.error.has_zero_stats", pokemon.getSpecies().getTranslatedName()).withStyle(ChatFormatting.RED));
			return InteractionResultHolder.pass(itemStack);
		} else {
			pokemon.setEV(Stats.ATTACK, 0);
			pokemon.setEV(Stats.DEFENCE, 0);
			pokemon.setEV(Stats.SPECIAL_ATTACK, 0);
			pokemon.setEV(Stats.SPECIAL_DEFENCE, 0);
			pokemon.setEV(Stats.HP, 0);
			pokemon.setEV(Stats.SPEED, 0);
			if (!player.isCreative()) {
				itemStack.shrink(1);
			}

			player.makeSound(CobblemonSounds.MEDICINE_PILLS_USE);
			return InteractionResultHolder.success(itemStack);
		}
	}

	private boolean isZeroEVs(Pokemon pokemon) {
		return pokemon.getEvs().get(Stats.ATTACK).equals(0) && pokemon.getEvs().get(Stats.DEFENCE).equals(0) && pokemon.getEvs().get(Stats.SPECIAL_ATTACK).equals(0) && pokemon.getEvs().get(Stats.SPECIAL_DEFENCE).equals(0) && pokemon.getEvs().get(Stats.HP).equals(0) && pokemon.getEvs().get(Stats.SPEED).equals(0);
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
