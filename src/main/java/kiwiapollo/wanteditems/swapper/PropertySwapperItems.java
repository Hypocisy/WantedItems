package kiwiapollo.wanteditems.swapper;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public enum PropertySwapperItems {
	GENDER_SWAPPER("gender_swapper", new GenderSwapper()),
	SHINY_SWAPPER("shiny_swapper", new ShinySwapper());

	private final ResourceLocation identifier;
	private final Item item;

	PropertySwapperItems(String path, Item item) {
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
