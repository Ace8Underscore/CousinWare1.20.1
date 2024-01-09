package com.cousinware.cwm;

import com.cousinware.cwm.client.CwmClient;
import com.cousinware.cwm.hwid.HWID;
import com.cousinware.cwm.hwid.Verification;
import com.google.common.eventbus.EventBus;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.impl.launch.FabricLauncher;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.crash.CrashReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.net.MalformedURLException;

public class Cwm implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");

    public static Cwm INSTANCE;

    public HWID hwid;

    public final String clientHWID  = String.valueOf(Runtime.getRuntime().availableProcessors() +
            ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize());


    public static Cwm getInstance() {
        return INSTANCE;
    }

    @Override
    public void onInitializeClient() {
//        try {
//            hwid = new HWID();
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }

//        runs basics hwid check with our website
//        if (HWID.isGoodHWID(clientHWID)) {
//            //Client can run as usual
//            System.out.println("PASSED HWID VERIFICATION 1");
//            CwmClient.Vpassed++;
//        } else {
//            CrashReport crashReport = new CrashReport("FAILED HWID CHECK ERROR CODE 19x00a", new Throwable());
//            MinecraftClient.getInstance().close();
//            //add code to delete lastest log for this issue to prevent reverse engineering
//            CwmClient.Vfailed++;
//        }


    }
}
