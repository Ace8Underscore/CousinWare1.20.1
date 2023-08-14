package com.cousinware.cwm.mixins;

import com.cousinware.cwm.client.CwmClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.message.MessageHandler;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MessageHandler.class)

public abstract class MessageHandlerMixin {

    @Inject(at = @At("HEAD"), method = "onChatMessage")
    public void onChatMessage(final SignedMessage message, final MessageType.Parameters params, CallbackInfo info) {
        CwmClient.chatManager.doChat(message, info);
    }
}
