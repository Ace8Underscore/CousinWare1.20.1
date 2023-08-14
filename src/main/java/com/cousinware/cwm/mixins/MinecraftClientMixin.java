package com.cousinware.cwm.mixins;


import com.cousinware.cwm.command.Command;
import com.cousinware.cwm.managers.HackManager;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MinecraftClient.class)
public abstract class MinecraftClientMixin {

    @Inject(at = @At("TAIL"), method = "tick")
    private void onTick(CallbackInfo info) {
        if (MinecraftClient.getInstance().world == null) return;
        HackManager.onUpdate();
    }
}
