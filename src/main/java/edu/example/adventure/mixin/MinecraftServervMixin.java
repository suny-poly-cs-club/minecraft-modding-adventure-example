package edu.example.adventure.mixin;

import edu.example.adventure.AdventureExample;
import net.minecraft.network.QueryableServer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerTask;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.world.ChunkErrorHandler;
import net.minecraft.util.thread.ReentrantThreadExecutor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServervMixin extends ReentrantThreadExecutor<ServerTask> implements QueryableServer, ChunkErrorHandler, CommandOutput {

    public MinecraftServervMixin(String string) {
        super(string);
    }

    @Shadow
    private boolean pvpEnabled;

    @Inject(method = "loadWorld()V", at = @At("HEAD"))
    void loadWorld(CallbackInfo ci){
        AdventureExample.LOGGER.info("MIXIN TIME !!!!!!  "+pvpEnabled);
    }
}
