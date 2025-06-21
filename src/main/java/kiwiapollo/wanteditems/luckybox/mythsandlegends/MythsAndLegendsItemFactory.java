package kiwiapollo.wanteditems.luckybox.mythsandlegends;

import kiwiapollo.wanteditems.common.SimpleFactory;
import kiwiapollo.wanteditems.datagen.ItemTagProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MythsAndLegendsItemFactory implements SimpleFactory<Item> {
	public MythsAndLegendsItemFactory() {
	}

	public Item create() {
		List<Item> random = new ArrayList<>(this.getItems());
		random.removeAll(this.getForbiddenItems());
		Collections.shuffle(random);
		return random.getFirst();
	}

	private List<Item> getItems() {
		List<Item> items = new ArrayList<>();
		BuiltInRegistries.ITEM.getTag(ItemTagProvider.MYTHS_AND_LEGENDS_ITEMS).ifPresent((itemEntryList) ->
				itemEntryList.forEach(itemHolder -> items.add(itemHolder.value())));
		return items;
	}

	private List<Item> getForbiddenItems() {
		return List.of();
	}
}
