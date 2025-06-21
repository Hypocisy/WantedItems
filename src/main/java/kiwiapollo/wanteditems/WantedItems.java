package kiwiapollo.wanteditems;

import kiwiapollo.wanteditems.registration.ModItems;
import kiwiapollo.wanteditems.registration.ModTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(WantedItems.MOD_ID)
public class WantedItems {
	public static final String MOD_ID = "wanteditems";

	public WantedItems(IEventBus modEventBus, ModContainer modContainer) {
		// Register the commonSetup method for modloading

		// Register the Deferred Register to the mod event bus so blocks get registered
		// Register the Deferred Register to the mod event bus so items get registered
		ModItems.register(modEventBus);
		// Register the Deferred Register to the mod event bus so tabs get registered
		ModTabs.register(modEventBus);

		// Register ourselves for server and other game events we are interested in.
		// Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
		// Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.

	}
}
