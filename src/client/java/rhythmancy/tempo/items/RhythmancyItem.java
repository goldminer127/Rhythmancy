package rhythmancy.tempo.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import rhythmancy.tempo.RhythmancyClient;

public class RhythmancyItem {
    public static final Item TRIANGLE = registerItem("triangle", new Item(new FabricItemSettings()));
    public static final Item GUIItem = registerItem("guiitem", new GuiItem(new FabricItemSettings()));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(TRIANGLE);
    }

    private static void addItemsToFunctionalItemGroup(FabricItemGroupEntries entries) {
        entries.add(GUIItem);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(RhythmancyClient.MOD_ID, name), item);
    }
    public static void registerModItems() {
        RhythmancyClient.LOGGER.info("Registering Rhythmancy Items");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register((RhythmancyItem::addItemsToIngredientItemGroup));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register((RhythmancyItem::addItemsToFunctionalItemGroup));
    }
}