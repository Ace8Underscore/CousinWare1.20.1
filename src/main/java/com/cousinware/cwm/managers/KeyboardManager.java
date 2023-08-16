package com.cousinware.cwm.managers;

import com.cousinware.cwm.client.CwmClient;
import com.cousinware.cwm.command.Command;
import com.cousinware.cwm.event.KeyPressEvent;
import com.cousinware.cwm.event.PacketEvent;
import com.cousinware.cwm.hack.Hack;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class KeyboardManager {

    public KeyboardManager() {
        CwmClient.EVENT_BUS.subscribe(keyboardListener);
    }

    @Subscribe
    private Listener<KeyPressEvent> keyboardListener = new Listener<>(event -> {
        if (event.getAction() == 1 && MinecraftClient.getInstance().currentScreen == null) {
            for (Hack hacks : HackManager.getHacks()) {
                if (hacks.getBind() == event.getKey()) {
                   hacks.toggle();
                }
            }
        }
    });
}
