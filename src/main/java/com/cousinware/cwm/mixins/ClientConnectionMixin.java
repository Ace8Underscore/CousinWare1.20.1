package com.cousinware.cwm.mixins;

import com.cousinware.cwm.client.CwmClient;
import com.cousinware.cwm.command.Command;
import com.cousinware.cwm.event.PacketEvent;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.PacketCallbacks;
import net.minecraft.network.packet.Packet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {

    @Shadow private Channel channel;

    @Inject(method = "send(Lnet/minecraft/network/packet/Packet;Lnet/minecraft/network/PacketCallbacks;)V", at = @At("HEAD"), cancellable = true)
    private void onSendPacket(Packet<?> packet, PacketCallbacks callbacks, CallbackInfo ci) {
        //if (this.channel.isOpen() && packet != null) {
            PacketEvent.Send event = new PacketEvent.Send(packet);
            CwmClient.EVENT_BUS.post(event);

            if (event.isCanceled()) {
                ci.cancel();
            //}
        }
    }

    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    private void channelRead0(ChannelHandlerContext channelHandlerContext, Packet<?> packet, CallbackInfo callback) {
        PacketEvent.Receive event = new PacketEvent.Receive(packet);
        CwmClient.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            callback.cancel();
        }
    }

}