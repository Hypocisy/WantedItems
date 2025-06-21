package kiwiapollo.wanteditems.datagen;

import com.github.d0ctorleon.mythsandlegends.items.Items;
import kiwiapollo.wanteditems.WantedItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ItemTagProvider extends ItemTagsProvider {

		public static final TagKey<Item> MYTHS_AND_LEGENDS_ITEMS = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("wanteditems", "myths_and_legends_items"));;

		public ItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagsProvider.TagLookup<Block>> blockTagProvider, ExistingFileHelper existingFileHelper) {
			super(output, lookupProvider, blockTagProvider, WantedItems.MOD_ID, existingFileHelper);
		}

		@Override
		protected void addTags(HolderLookup.Provider provider) {
			var mythicalItemsTag = tag(MYTHS_AND_LEGENDS_ITEMS);
			for (var itemSupplier : Items.ITEM_REGISTRY_SUPPLIERS) {
				mythicalItemsTag.add(itemSupplier.get());
			}
		}
	}