package com.cousinware.cwm.client;

import com.cousinware.cwm.managers.*;
import me.zero.alpine.EventBus;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.impl.launch.FabricLauncher;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class CwmClient implements ClientModInitializer {

    public static final EventBus EVENT_BUS = new me.zero.alpine.EventManager();

    public static HackManager hackManager;
    public static ChatManager chatManager;

    public static CommandManager commandManager;
    public static SettingsManager settingsManager;
    public FriendManager friendManager;


    @Override
    public void onInitializeClient() {
        settingsManager = new SettingsManager();
        friendManager = new FriendManager();
        chatManager = new ChatManager();
        hackManager = new HackManager();
        commandManager = new CommandManager();

    }




}
