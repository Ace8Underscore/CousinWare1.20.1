package com.cousinware.cwm.client;

import com.cousinware.cwm.gui.ClickGUI2;
import com.cousinware.cwm.managers.*;
import com.cousinware.cwm.utils.config.ConfigUtils;
import com.cousinware.cwm.utils.config.ShutDown;
import com.cousinware.cwm.utils.font.CFontRenderer;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;
import me.zero.alpine.listener.Subscriber;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.text.Text;

import java.awt.*;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class CwmClient implements ClientModInitializer {

    public static final EventBus EVENT_BUS = EventManager.builder()
            .setName("cousinware") // Descriptive name for the bus
            .build();

    public static HackManager hackManager;
    public static ChatManager chatManager;

    public static CommandManager commandManager;
    public static SettingsManager settingsManager;
    public FriendManager friendManager;
    public static ClickGUI2 clickGui2;
    public static CFontRenderer fontRenderer;
    public static ConfigUtils configUtils;


    @Override
    public void onInitializeClient() {
        settingsManager = new SettingsManager();
        friendManager = new FriendManager();
        chatManager = new ChatManager();
        hackManager = new HackManager();
        commandManager = new CommandManager();
        fontRenderer = new CFontRenderer(new Font("Verdana", Font.PLAIN, 17), true, false);
        clickGui2 = new ClickGUI2();
        configUtils = new ConfigUtils();

        Runtime.getRuntime().addShutdownHook(new ShutDown());



    }




}
