package edu.example.adventure.blocks;

import com.mojang.serialization.MapCodec;
import edu.example.adventure.AdventureExample;
import edu.example.adventure.blockentity.RussianBlockEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
        return new RussianBlockEntity(pos,state);
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        BlockEntity be = world.getBlockEntity(pos);
        if(be instanceof RussianBlockEntity rbe){
            double number = Math.random();
            if(number < 0.8){
                ItemStack nuggets = Items.GOLD_NUGGET.getDefaultStack();
                nuggets.setCount(rbe.getPot());
                player.giveItemStack(nuggets);
                rbe.incrementPot();
            }else{
                world.createExplosion(null,pos.getX(),pos.getY(),pos.getZ(),3*rbe.getPot(),true, World.ExplosionSourceType.BLOCK);
            }


            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }
}
