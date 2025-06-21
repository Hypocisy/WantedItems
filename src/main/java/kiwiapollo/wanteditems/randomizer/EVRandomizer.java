package kiwiapollo.wanteditems.randomizer;

import com.cobblemon.mod.common.CobblemonSounds;
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import com.cobblemon.mod.common.api.item.PokemonSelectingItem;
import com.cobblemon.mod.common.api.pokemon.stats.Stats;
import com.cobblemon.mod.common.battles.pokemon.BattlePokemon;
import com.cobblemon.mod.common.item.battle.BagItem;
import com.cobblemon.mod.common.pokemon.EVs;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class EVRandomizer extends Item implements PokemonSelectingItem {
	public EVRandomizer() {
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

	public @Nullable InteractionResultHolder<ItemStack> applyToPokemon(@NotNull ServerPlayer player, @NotNull ItemStack itemStack, @NotNull Pokemon pokemon) {
		EVs evs = this.createRandomEVs();
		pokemon.setEV(Stats.ATTACK, evs.getOrDefault(Stats.ATTACK));
		pokemon.setEV(Stats.DEFENCE, evs.getOrDefault(Stats.DEFENCE));
		pokemon.setEV(Stats.SPECIAL_ATTACK, evs.getOrDefault(Stats.SPECIAL_ATTACK));
		pokemon.setEV(Stats.SPECIAL_DEFENCE, evs.getOrDefault(Stats.SPECIAL_DEFENCE));
		pokemon.setEV(Stats.HP, evs.getOrDefault(Stats.HP));
		pokemon.setEV(Stats.SPEED, evs.getOrDefault(Stats.SPEED));
		if (!player.isCreative()) {
			itemStack.shrink(1);
		}

		player.makeSound(CobblemonSounds.MEDICINE_PILLS_USE);
		return InteractionResultHolder.success(itemStack);
	}

	private EVs createRandomEVs() {
		EVs evs = EVs.Companion.createEmpty();
		List<Stats> stats = List.of(Stats.ATTACK, Stats.DEFENCE, Stats.SPECIAL_ATTACK, Stats.SPECIAL_DEFENCE, Stats.HP, Stats.SPEED);
		int total = 0;

		for (Stats stat : stats) {
			if (total < 510) {
				int remaining = 510 - total;
				int value = (new Random()).nextInt(Math.min(252, remaining) + 1);
				evs.set(stat, value);
				total += value;
			} else {
				evs.set(stat, 0);
			}
		}

		return evs;
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
