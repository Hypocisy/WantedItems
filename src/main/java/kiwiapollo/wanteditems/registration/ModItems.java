package kiwiapollo.wanteditems.registration;

import kiwiapollo.wanteditems.WantedItems;
import kiwiapollo.wanteditems.luckybox.cobblemon.CobblemonLuckyBox;
import kiwiapollo.wanteditems.luckybox.mythsandlegends.MythsAndLegendsLuckyBox;
import kiwiapollo.wanteditems.luckyegg.GoldLuckyEgg;
import kiwiapollo.wanteditems.luckyegg.ShinyGoldLuckyEgg;
import kiwiapollo.wanteditems.luckyegg.ShinySilverLuckyEgg;
import kiwiapollo.wanteditems.luckyegg.SilverLuckyEgg;
import kiwiapollo.wanteditems.randomizer.EVRandomizer;
import kiwiapollo.wanteditems.randomizer.IVRandomizer;
import kiwiapollo.wanteditems.randomizer.LevelRandomizer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
	public static final DeferredRegister<Item> ITEM = DeferredRegister.create(Registries.ITEM, WantedItems.MOD_ID);
	public static final DeferredHolder<Item, GoldLuckyEgg> GOLD_LUCKY_EGG = ITEM.register("gold_lucky_egg", GoldLuckyEgg::new);
	public static final DeferredHolder<Item, ShinyGoldLuckyEgg> SHINY_GOLD_LUCKY_EGG = ITEM.register("shiny_gold_lucky_egg", ShinyGoldLuckyEgg::new);
	public static final DeferredHolder<Item, SilverLuckyEgg> SILVER_LUCKY_EGG = ITEM.register("silver_lucky_egg", SilverLuckyEgg::new);
	public static final DeferredHolder<Item, ShinySilverLuckyEgg> SHINY_SILVER_LUCKY_EGG = ITEM.register("shiny_silver_lucky_egg", ShinySilverLuckyEgg::new);
	public static final DeferredHolder<Item, EVRandomizer> EV_RANDOMIZER = ITEM.register("ev_randomizer", EVRandomizer::new);
	public static final DeferredHolder<Item, IVRandomizer> IV_RANDOMIZER = ITEM.register("iv_randomizer", IVRandomizer::new);
	public static final DeferredHolder<Item, LevelRandomizer> LEVEL_RANDOMIZER = ITEM.register("level_randomizer", LevelRandomizer::new);
	public static final DeferredHolder<Item, MythsAndLegendsLuckyBox> MYTHS_AND_LEGENDS_LUCKY_BOX = ITEM.register("myths_and_legends_lucky_box", MythsAndLegendsLuckyBox::new);
	public static final DeferredHolder<Item, CobblemonLuckyBox> COBBLEMON_LUCKY_BOX = ITEM.register("cobblemon_lucky_box", CobblemonLuckyBox::new);

	public static void register(IEventBus bus) {
		ITEM.register(bus);
	}
}
