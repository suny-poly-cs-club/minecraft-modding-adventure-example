package edu.example.adventure.blocks;

import com.mojang.serialization.MapCodec;
import edu.example.adventure.AdventureExample;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class RussianBlock extends BlockWithEntity {

    public static final Identifier ID = Identifier.of(AdventureExample.MOD_ID,"russian_block");
    public static final RegistryKey<Block> BLOCK_KEY = AdventureExample.createBlockRegistryKey(ID);
    public static final RegistryKey<Item>  ITEM_KEY = AdventureExample.createItemRegistryKey(ID);

    public static final RussianBlock ENTRY =
            new RussianBlock(AbstractBlock.Settings.create().registryKey(BLOCK_KEY));
    protected RussianBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(RussianBlock::new);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return null;
    }
}
