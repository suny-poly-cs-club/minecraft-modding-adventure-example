package edu.example.adventure.blocks;

import edu.example.adventure.AdventureExample;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ThunderBlock extends Block {

    public static final Identifier ID = Identifier.of(AdventureExample.MOD_ID,"thunder_block");
    public static final RegistryKey<Block> BLOCK_KEY = AdventureExample.createBlockRegistryKey(ID);
    public static final RegistryKey<Item>  ITEM_KEY = AdventureExample.createItemRegistryKey(ID);

    public static final ThunderBlock ENTRY =
            new ThunderBlock(AbstractBlock.Settings.create().registryKey(BLOCK_KEY));

    public ThunderBlock(Settings settings) {
        super(settings);
    }
}
