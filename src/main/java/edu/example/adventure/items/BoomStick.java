package edu.example.adventure.items;

import edu.example.adventure.AdventureExample;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BoomStick extends Item {

    public static final Identifier ID = Identifier.of(AdventureExample.MOD_ID, "boom_stick");
    public static final RegistryKey<Item> ITEM_KEY = AdventureExample.createItemRegistryKey(ID);
    public BoomStick(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos position = context.getBlockPos();
        World world = context.getWorld();
        world.createExplosion(context.getPlayer(),position.getX(),position.getY(),position.getZ(),
                50, World.ExplosionSourceType.MOB);
        context.getStack().decrementUnlessCreative(1, context.getPlayer());

        PlayerEntity player = context.getPlayer();
        //make sure the player is not null
        if(player != null) {
            player.swingHand(context.getHand());
        }


        return super.useOnBlock(context);
    }
}
