package com.cousinware.cwm.managers;

import com.cousinware.cwm.client.CwmClient;
import com.cousinware.cwm.event.KeyPressEvent;
import com.cousinware.cwm.hack.Hack;
import com.cousinware.eventlistener.Listener;
import com.google.common.eventbus.Subscribe;
import net.minecraft.client.MinecraftClient;


public class KeyboardManager{

    public KeyboardManager() {
        CwmClient.eventBus.addListener(this);
    }

    @Listener
    public static void keyPressListener(KeyPressEvent event) {
        if (event.getAction() == 1 && MinecraftClient.getInstance().currentScreen == null) {
            for (Hack hacks : HackManager.getHacks()) {
                if (hacks.getBind() == event.getKey()) {
                   hacks.toggle();
                }
            }
        }
    }
}
