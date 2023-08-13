package com.cousinware.cwm.command;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public abstract class Command {

    /**
     * @author Ace________/Ace_#1233
     */

    public static final MinecraftClient mc = MinecraftClient.getInstance();
    public static String prefix = ".";

    public static void sendClientSideMessage(String message) {
        if (mc.world == null || mc.player == null) return;
        mc.player.sendMessage(Text.of(Formatting.DARK_RED + "[CousinWare]" + " " + Formatting.WHITE + message));
    }

    public static String getClientPrefix() {
        return prefix;
    }

    public static void setClientPrefix(String p) {
        prefix = p;
    }

    public abstract String[] getClientAlias();

    public abstract String getClientSyntax();

    public abstract void onClientCommand(String command, String[] args) throws Exception;

//
}