package kiwiapollo.wanteditems.registration;

import kiwiapollo.wanteditems.WantedItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTabs {
	private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WantedItems.MOD_ID);

	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ITEM_GROUP = CREATIVE_MODE_TABS.register("item_group", () -> CreativeModeTab.builder()
			.title(Component.translatable("item_group.wanteditems.title")) //The language key for the title of your CreativeModeTab
			.withTabsBefore(CreativeModeTabs.COMBAT)
			.icon(() -> ModItems.SHINY_GOLD_LUCKY_EGG.get().getDefaultInstance())
			.displayItems((parameters, output) -> output.acceptAll(ModItems.ITEM.getEntries().stream().map(DeferredHolder::get).map(Item::getDefaultInstance).toList())).build());

	public static void register(IEventBus bus) {
		CREATIVE_MODE_TABS.register(bus);
	}
}
