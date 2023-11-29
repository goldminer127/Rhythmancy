package rhythmancy.tempo.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import rhythmancy.tempo.Rhythmancy;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class RhythmancyItems {
    public static final Item TRIANGLE = registerItem("triangle", new Item(new FabricItemSettings()));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(TRIANGLE);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Rhythmancy.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Rhythmancy.LOGGER.info("Registering Rhythmancy Items");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register((RhythmancyItems::addItemsToIngredientItemGroup));
    }
}
