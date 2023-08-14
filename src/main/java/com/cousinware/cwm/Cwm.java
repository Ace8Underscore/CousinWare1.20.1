package com.cousinware.cwm;

import com.google.common.eventbus.EventBus;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.impl.launch.FabricLauncher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cwm implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");

    public static Cwm INSTANCE;
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("Cousinware Modern Initiated");
    }

    public static Cwm getInstance() {
        return INSTANCE;
    }
}
