package com.cousinware.cwm.managers;

import com.cousinware.cwm.client.CwmClient;
import com.cousinware.cwm.command.Command;
import com.cousinware.cwm.event.MouseEvent;
import com.cousinware.cwm.event.PacketEvent;
import com.cousinware.eventlistener.Listener;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.network.packet.s2c.play.ChatMessageS2CPacket;
import net.minecraft.util.Formatting;


public class ChatManager {

    public ChatManager() {
        CwmClient.eventBus.addListener(this);
    }


    @Listener
    public static void packetListener(PacketEvent.Send event) {

        String message = "";

        //singleplayer
        if (event.getPacket() instanceof ChatMessageS2CPacket) message = (((ChatMessageS2CPacket) event.getPacket()).body().content().toString());

        //multiplayer servers
        if (event.getPacket() instanceof ChatMessageC2SPacket) message = (((ChatMessageC2SPacket) event.getPacket()).chatMessage());


        if (message.startsWith(Command.prefix)) {
                CwmClient.commandManager.callClientCommand(message.substring(1));
                event.setCanceled(true);

        }


    }


}


