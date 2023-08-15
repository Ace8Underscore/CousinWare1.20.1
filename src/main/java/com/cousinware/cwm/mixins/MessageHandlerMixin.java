package com.cousinware.cwm.mixins;

import com.cousinware.cwm.client.CwmClient;
import com.cousinware.cwm.command.Command;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.network.message.MessageHandler;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.time.Instant;

@Mixin(value = MessageHandler.class)

public abstract class MessageHandlerMixin {

    @Inject(at = @At("HEAD"), method = "onChatMessage")
    public void onChatMessage(SignedMessage message, GameProfile sender, MessageType.Parameters params, CallbackInfo ci) {
        //Command.sendClientSideMessage("Message just sent");
        //MinecraftClient.getInstance().player.sendMessage(Text.of("1"));
        //CwmClient.chatManager.doChat(message, info);
    }
}
