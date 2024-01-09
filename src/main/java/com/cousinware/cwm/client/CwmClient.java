package com.cousinware.cwm.client;

import com.cousinware.cwm.gui.ClickGUI2;
import com.cousinware.cwm.managers.*;
import com.cousinware.cwm.utils.config.ConfigUtils;
import com.cousinware.cwm.utils.config.ShutDown;
import com.cousinware.cwm.utils.font.CFontRenderer;
import com.cousinware.cwm.utils.gui.ClickGUI3;
import com.cousinware.eventlistener.EventBus;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.crash.CrashReport;

import java.awt.*;
import java.lang.management.ManagementFactory;

@net.fabricmc.api.Environment(EnvType.CLIENT)
public class CwmClient implements ModInitializer {

    // public static final EventBus EVENT_BUS = EventManager.builder()
    //.setName("cousinware")// Descriptive name for the bus
    //.build();

    public final String clientHWID  = String.valueOf(Runtime.getRuntime().availableProcessors() +
            ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize());

   // public static final EventBus EVENT_BUS = new EventBus();
    public static EventBus eventBus;
    public static KeyboardManager keyboardManager;
    public static HackManager hackManager;
    public static ChatManager chatManager;

    public static CommandManager commandManager;
    public static SettingsManager settingsManager;
    public FriendManager friendManager;
    public static ClickGUI2 clickGui2;
    public static ClickGUI3 clickGUI3;
    public static CFontRenderer fontRenderer;
    public static ConfigUtils configUtils;
    public static int Vfailed = 0;
    public static int Vpassed = 0;


    @Override
    public void onInitialize() {
        eventBus = new EventBus();
        keyboardManager = new KeyboardManager();
        commandManager = new CommandManager();
        try {
            settingsManager = new SettingsManager();
        } catch (ClassNotFoundException e) {
            MinecraftClient.printCrashReport(new CrashReport("FAILED HWID 3 ERROR CODE 19a88x0", new Throwable()));
            Vfailed++;
        }
        friendManager = new FriendManager();
        chatManager = new ChatManager();
        hackManager = new HackManager();
        fontRenderer = new CFontRenderer(new Font("Verdana", Font.PLAIN, 17), true, false);
        clickGui2 = new ClickGUI2();
        clickGUI3 = new ClickGUI3();
        configUtils = new ConfigUtils();

        Runtime.getRuntime().addShutdownHook(new ShutDown());

        MinecraftClient.getModStatus().description();
        CrashReport crashReport = new CrashReport("Gay", new Throwable("Gay"));
       // MinecraftClient.printCrashReport(crashReport);


    }


}
