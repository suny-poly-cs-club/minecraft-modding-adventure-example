package edu.example.adventure.blocks;

import edu.example.adventure.AdventureExample;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ThunderBlock extends Block {

    public static final BooleanProperty USED_PROPERTY = BooleanProperty.of("used");

    public static final Identifier ID = Identifier.of(AdventureExample.MOD_ID,"thunder_block");
    public static final RegistryKey<Block> BLOCK_KEY = AdventureExample.createBlockRegistryKey(ID);
    public static final RegistryKey<Item>  ITEM_KEY = AdventureExample.createItemRegistryKey(ID);

    public static final ThunderBlock ENTRY =
            new ThunderBlock(AbstractBlock.Settings.create().registryKey(BLOCK_KEY));

    public ThunderBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState()
                .with(USED_PROPERTY, false)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(USED_PROPERTY);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if(world instanceof ServerWorld serverWorld){
            if(!state.get(USED_PROPERTY)) {
                EntityType.LIGHTNING_BOLT.spawn(serverWorld, pos.up(), SpawnReason.EVENT);
                world.setBlockState(pos,state.with(USED_PROPERTY,true));
            }
        }
        return ActionResult.SUCCESS;
    }
}
