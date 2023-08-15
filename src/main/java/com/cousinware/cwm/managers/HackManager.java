package com.cousinware.cwm.managers;

import com.cousinware.cwm.hack.client.ClickGuiHack;
import com.cousinware.cwm.hack.client.Core;
import com.cousinware.cwm.hack.exploit.Exploit;
import com.cousinware.cwm.hack.Hack;
import com.cousinware.cwm.hack.exploit.OutgoingPackets;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author Ace________/Ace_#1233
 */

public class HackManager {
    public static ArrayList<Hack> hacks;
    private static final String allHackNames = "Hacks: ";
    private static String officialAllHackNames;

    public static void addHack(Hack h){
        hacks.add(h);
    }

    public static ArrayList<Hack> getHacks() {
        return hacks;
    }

    public static Hack getHackByName(String name){
        return getHacks().stream().filter(hm->hm.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public static void onUpdate() {
        hacks.stream().filter(Hack::isEnabled).forEach(Hack::onUpdate);
    }
    public static void doTick() {
        hacks.stream().filter(Hack::isEnabled).forEach(Hack::doTick);
    }


    public static String getAllHackList() {
        HackManager.getHacks()
                .forEach(hack -> {
                    officialAllHackNames = allHackNames + " " + hack.name;

                });

        return officialAllHackNames;
    }

    public static ArrayList<Hack> getHacksInCategory(Hack.Category c){
        return (ArrayList<Hack>) getHacks().stream().filter(h -> h.getCategory().equals(c)).collect(Collectors.toList());
    }

   /* public static void onWorldRender(RenderWorldLastEvent event) {
        Minecraft.getMinecraft().profiler.startSection("nordClient");

        Minecraft.getMinecraft().profiler.startSection("setup");
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        GlStateManager.disableDepth();

        GlStateManager.glLineWidth(1f);
        Vec3d renderPos = NordTessellator.getInterpolatedPos(Minecraft.getMinecraft().player, event.getPartialTicks());

        RenderEvent e = new RenderEvent(NordTessellator.INSTANCE, renderPos, event.getPartialTicks());
        e.resetTranslation();
        Minecraft.getMinecraft().profiler.endSection();

        hacks.stream().filter(Hack::isEnabled).forEach(module -> {
            Minecraft.getMinecraft().profiler.startSection(module.getName());
            module.onWorldRender(e);
            Minecraft.getMinecraft().profiler.endSection();
        });

        Minecraft.getMinecraft().profiler.startSection("release");
        GlStateManager.glLineWidth(1f);
        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.enableCull();
        NordTessellator.releaseGL();
        Minecraft.getMinecraft().profiler.endSection();
        Minecraft.getMinecraft().profiler.endSection();
    } */


    public static void onBind(int key) {
        if (key == 0 || key == -1) return;
        hacks.forEach(hack -> {
            if(hack.getBind() == key){
                hack.toggle();
            }
        });
    }

    public HackManager() {
        hacks = new ArrayList<>();
        hacks.add(new Exploit());
        hacks.add(new OutgoingPackets());
        hacks.add(new ClickGuiHack());
        hacks.add(new Core());
    }
}
