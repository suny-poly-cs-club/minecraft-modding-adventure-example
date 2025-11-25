package edu.example.adventure.mixin;

import edu.example.adventure.blocks.ThunderBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FlintAndSteelItem.class)
public class FlintAndSteelItemMixin {

    @Inject(method = "useOnBlock(Lnet/minecraft/item/ItemUsageContext;)Lnet/minecraft/util/ActionResult;",
            at= @At("HEAD"), cancellable = true
    )
    void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir){
        BlockPos pos = context.getBlockPos();
        World world = context.getWorld();
        BlockState state = world.getBlockState(pos);
        if(state.getBlock() instanceof ThunderBlock){
            if(state.get(ThunderBlock.USED_PROPERTY)){
                world.setBlockState(pos,state.with(ThunderBlock.USED_PROPERTY,false));
                PlayerEntity player = context.getPlayer();
                if(player!=null){
                    context.getStack().damage(1,player);
                }
                cir.setReturnValue(ActionResult.SUCCESS);
            }
        }
    }

}
