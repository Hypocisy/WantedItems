package kiwiapollo.wanteditems.luckybox;

import kiwiapollo.wanteditems.common.SimpleFactory;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemLuckyBox extends Item {
	private final SimpleFactory<Item> factory;

	public ItemLuckyBox(SimpleFactory<Item> factory) {
		super(new Item.Properties());
		this.factory = factory;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player user, InteractionHand usedHand) {
		if (level.isClientSide()) {
			return InteractionResultHolder.pass(user.getItemInHand(usedHand));
		} else {
			ItemStack random = (this.factory.create()).getDefaultInstance();
			if (!user.addItem(random)) {
				user.drop(random, true);
			}

			if (!user.isCreative()) {
				user.getItemInHand(usedHand).shrink(1);
			}

			user.makeSound(SoundEvents.EXPERIENCE_ORB_PICKUP);
			return InteractionResultHolder.success(user.getItemInHand(usedHand));
		}
	}

}
