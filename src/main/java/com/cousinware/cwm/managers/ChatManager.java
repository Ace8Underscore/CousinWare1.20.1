package com.cousinware.cwm.managers;

import com.cousinware.cwm.client.CwmClient;
import com.cousinware.cwm.command.Command;
import com.cousinware.cwm.event.PacketEvent;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.network.packet.s2c.play.ChatMessageS2CPacket;
import net.minecraft.util.Formatting;


public class ChatManager {

    public ChatManager() {
        CwmClient.EVENT_BUS.subscribe(stringListener);
    }

    @Subscribe
    private Listener<PacketEvent.Send> stringListener = new Listener<>(event -> {

        String message = "";

        //singleplayer
        if (event.getPacket() instanceof ChatMessageS2CPacket) message = (((ChatMessageS2CPacket) event.getPacket()).body().content().toString());

        //multiplayer servers
        if (event.getPacket() instanceof ChatMessageC2SPacket) message = (((ChatMessageC2SPacket) event.getPacket()).chatMessage());


        if (message.startsWith(Command.prefix)) {
                CwmClient.commandManager.callClientCommand(message.substring(1));
                event.setCanceled(true);

        }


    });


}


