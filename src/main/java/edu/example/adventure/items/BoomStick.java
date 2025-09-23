package edu.example.adventure.items;

import edu.example.adventure.AdventureExample;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class BoomStick extends Item {

    public static final Identifier ID = Identifier.of(AdventureExample.MOD_ID, "boom_stick");
    public static final RegistryKey<Item> ITEM_KEY = AdventureExample.createItemRegistryKey(ID);
    public BoomStick(Settings settings) {
        super(settings);
    }
}
