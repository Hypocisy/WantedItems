
package kiwiapollo.wanteditems.luckybox.cobblemon;

import com.cobblemon.mod.common.CobblemonItems;
import kiwiapollo.wanteditems.common.SimpleFactory;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CobblemonItemFactory implements SimpleFactory<Item> {
	public CobblemonItemFactory() {
	}

	public Item create() {
		List<Item> random = new ArrayList<>(CobblemonItems.INSTANCE.all());
		random.removeAll(this.getForbiddenItems());
		Collections.shuffle(random);
		return random.getFirst();
	}

	private List<Item> getForbiddenItems() {
		return List.of(CobblemonItems.POKEMON_MODEL);
	}
}
