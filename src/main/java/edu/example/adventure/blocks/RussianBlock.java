package edu.example.adventure.blocks;

import com.mojang.serialization.MapCodec;
import edu.example.adventure.AdventureExample;
import edu.example.adventure.blockentity.RussianBlockEntity;
import net.minecraft.block.*;
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
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
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

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        //generates the correct hitbox for the shape preventing incorrect block face culling
        return VoxelShapes.union(
                VoxelShapes.cuboid(0.3125, 0.4375, 0, 0.6875, 0.5625, 0.8125),
                VoxelShapes.cuboid(0.4375, 0.3125, 0, 0.5625, 0.375, 0.8125),
                VoxelShapes.cuboid(0.375, 0.375, 0, 0.625, 0.4375, 0.8125),
                VoxelShapes.cuboid(0.375, 0.5625, 0, 0.625, 0.625, 0.8125),
                VoxelShapes.cuboid(0.4375, 0.625, 0, 0.5625, 0.6875, 0.8125),
                VoxelShapes.cuboid(0.4375, 0, 0.75, 0.5625, 0.375, 0.875),
                VoxelShapes.cuboid(0.4375, 0, 0.734375, 0.5625, 0.0625, 0.859375),
                VoxelShapes.cuboid(0.4375, 0.1875, 0.5625, 0.5625, 0.25, 0.6875),
                VoxelShapes.cuboid(0.4375, 0.1875, 0.5, 0.5625, 0.3125, 0.5625)
        );
    }

}
