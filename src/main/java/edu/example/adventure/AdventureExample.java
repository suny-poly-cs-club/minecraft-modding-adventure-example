package edu.example.adventure;

import edu.example.adventure.blockentity.RussianBlockEntity;
import edu.example.adventure.blocks.RussianBlock;
import edu.example.adventure.blocks.ThunderBlock;
import edu.example.adventure.items.BoomStick;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdventureExample implements ModInitializer {
	public static final String MOD_ID = "adventure-example";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder().displayName(Text.of("Adventure Group")).build();
	public static final RegistryKey<ItemGroup> ITEM_GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP,Identifier.of(MOD_ID,"adventure_item_group"));

    public static final BlockEntityType<RussianBlockEntity> RUSSIAN_BLOCK_ENTITY_BLOCK = registerBlockEntity(
            RussianBlockEntity.ID,
            RussianBlockEntity::new,
            RussianBlock.ENTRY
    );

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

        registerBlocks();
		registerItems();
	}

	void registerItems(){
		Registry.register(Registries.ITEM_GROUP,ITEM_GROUP_KEY,ITEM_GROUP);

		BoomStick boomStickEntry = Registry.register(Registries.ITEM,BoomStick.ITEM_KEY, new BoomStick(new Item.Settings().registryKey(BoomStick.ITEM_KEY)));

        Registry.register(
                Registries.ITEM,
                ThunderBlock.ITEM_KEY,
                new BlockItem(
                        ThunderBlock.ENTRY,
                        new Item.Settings().registryKey(ThunderBlock.ITEM_KEY)
                ));

        Registry.register(
                Registries.ITEM,
                RussianBlock.ITEM_KEY,
                new BlockItem(
                        RussianBlock.ENTRY,
                        new Item.Settings().registryKey(RussianBlock.ITEM_KEY)
                ));

		ItemGroupEvents.modifyEntriesEvent(ITEM_GROUP_KEY).register(
				e -> {
                    e.add(boomStickEntry);
                    e.add(ThunderBlock.ENTRY);
                    e.add(RussianBlock.ENTRY);
                }
		);
	}

    void registerBlocks(){
        Registry.register(Registries.BLOCK, ThunderBlock.BLOCK_KEY, ThunderBlock.ENTRY);
        Registry.register(Registries.BLOCK, RussianBlock.BLOCK_KEY, RussianBlock.ENTRY);
    }

    public static RegistryKey<Item> createItemRegistryKey(Identifier id){
        return RegistryKey.of(RegistryKeys.ITEM, id);
    }

    public static RegistryKey<Block> createBlockRegistryKey(Identifier id){
        return RegistryKey.of(RegistryKeys.BLOCK, id);
    }

    /**Register a new block entity to minecraft
     * @param id The id of the block entity
     * @param entityFactory The constructor for the block entity (MyBlockEntity::new)
     * @param blocks A reference to the block object this block entity will be attaching to
     * @return The registered block entity type
     * @param <T> The type of the block entity class
     */
    public static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(
            Identifier id,
            FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
            Block... blocks
    ){
        return Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                id,
                FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build()
        );
    }

}