package kiwiapollo.wanteditems.stateditor;

import com.cobblemon.mod.common.api.pokemon.stats.Stats;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public enum StatEditorItems {
	BOTTLE_CAP("bottle_cap", new BottleCap()),
	BOTTLE_CAP_ATK("bottle_cap_atk", new BottleCap(Stats.ATTACK)),
	BOTTLE_CAP_DEF("bottle_cap_def", new BottleCap(Stats.DEFENCE)),
	BOTTLE_CAP_HP("bottle_cap_hp", new BottleCap(Stats.HP)),
	BOTTLE_CAP_SPA("bottle_cap_spa", new BottleCap(Stats.SPECIAL_ATTACK)),
	BOTTLE_CAP_SPD("bottle_cap_spd", new BottleCap(Stats.SPECIAL_DEFENCE)),
	BOTTLE_CAP_SPE("bottle_cap_spe", new BottleCap(Stats.SPEED)),
	GOLD_BOTTLE_CAP("gold_bottle_cap", new GoldBottleCap()),
	COPPER_BOTTLE_CAP("copper_bottle_cap", new CopperBottleCap());

	private final ResourceLocation identifier;
	private final Item item;

	StatEditorItems(String path, Item item) {
		this.identifier = ResourceLocation.fromNamespaceAndPath("wanteditems", path);
		this.item = item;
	}

	public Item getItem() {
		return this.item;
	}

	public ResourceLocation getIdentifier() {
		return this.identifier;
	}
}
