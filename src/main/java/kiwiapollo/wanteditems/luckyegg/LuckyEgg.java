
package kiwiapollo.wanteditems.luckyegg;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.pokemon.Pokemon;
import kiwiapollo.wanteditems.common.SimpleFactory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class LuckyEgg extends Item {
	private final SimpleFactory<Pokemon> factory;

	public LuckyEgg(SimpleFactory<Pokemon> factory) {
		super(new Item.Properties());
		this.factory = factory;
	}

	@Override
	public @NotNull InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
		if (world.isClientSide()) {
			return InteractionResultHolder.pass(user.getItemInHand(hand));
		} else {
			ServerPlayer player = world.getServer().getPlayerList().getPlayer(user.getUUID());
			Cobblemon.INSTANCE.getStorage().getParty(player).add(this.factory.create());
			if (!user.isCreative()) {
				user.getItemInHand(hand).shrink(1);
			}

			user.makeSound(SoundEvents.EXPERIENCE_ORB_PICKUP);
			return InteractionResultHolder.success(user.getItemInHand(hand));
		}
	}
}
