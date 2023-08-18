package com.cousinware.cwm.hack.render;

import com.cousinware.cwm.client.CwmClient;
import com.cousinware.cwm.event.MouseEvent;
import com.cousinware.cwm.event.RenderOverlayEvent;
import com.cousinware.cwm.hack.Hack;
import com.cousinware.cwm.hack.client.Core;
import com.cousinware.cwm.managers.HackManager;
import com.cousinware.cwm.utils.RainbowUtil;
import com.cousinware.cwm.utils.Setting;
import com.google.common.eventbus.Subscribe;
import net.minecraft.util.Formatting;

import java.awt.*;
import java.util.Comparator;

public class ArrayList extends Hack {

    /**
     * @author Ace________/Ace_#1233
     */

    int hackCount;



    Setting x;
    Setting y;
    Setting sideMode;
    Setting orderMode;
    Setting r;
    Setting g;
    Setting b;
    Color c;
    Setting rainbow;
    Setting staticc;
    Setting animation;
    Setting delaySetting;

    int displayX;
    int anima = 0;
    int delay = 0;

    public ArrayList() {
        super("ArrayList", Category.RENDER, 29700);
        this.drawn = true;
        CwmClient.settingsManager.rSetting(x = new Setting("x", this, 1, 0, 2000, false, "ArrayListX", true));
        CwmClient.settingsManager.rSetting(y = new Setting("y", this, 3, 0, 2000, false, "ArrayListY", true));

        java.util.ArrayList<String> sideModes = new java.util.ArrayList<>();
        sideModes.add("Left");
        sideModes.add("Right");
        CwmClient.settingsManager.rSetting(sideMode = new Setting("Side", this, "Left", sideModes, "ArrayListSideMode", true));

        java.util.ArrayList<String> orderModes = new java.util.ArrayList<>();
        orderModes.add("Up");
        orderModes.add("Down");
        CwmClient.settingsManager.rSetting(orderMode = new Setting("Order", this, "Up", orderModes, "ArrayListOrderMode", true));
        CwmClient.settingsManager.rSetting(staticc = new Setting("Static", this, true, "ArrayListStatic", true));
        CwmClient.settingsManager.rSetting(rainbow = new Setting("Rainbow", this, false, "ArrayListRainbow", !staticc.getValBoolean()));
        CwmClient.settingsManager.rSetting(r = new Setting("Red", this, 255, 0, 255, true, "ArrayListRed", !staticc.getValBoolean()));
        CwmClient.settingsManager.rSetting(g = new Setting("Green", this, 26, 0, 255, true, "ArrayListGreen", !staticc.getValBoolean()));
        CwmClient.settingsManager.rSetting(b = new Setting("Blue", this, 42, 0, 255, true, "ArrayListBlue", !staticc.getValBoolean()));
        CwmClient.settingsManager.rSetting(animation = new Setting("Animation", this, true, "ArrayListAnimation", true));
        CwmClient.settingsManager.rSetting(delaySetting = new Setting("AnimationDelay", this, 1, 1, 20, true, "ArrayListDelay", true));
    }

    @Subscribe
    public void renderOverlayListener(RenderOverlayEvent event) {
        if (mc.world == null)
            return;
        hackCount = 0;

        //anima = 0;
        HackManager.getHacks()
                .stream()
                .filter(Hack::isVisableOnArray)
                .filter(Hack::isDrawn)
                .sorted(Comparator.comparing(hack -> mc.textRenderer.getWidth(hack.getName() + hack.getHudInfo()) * (-1)))
                .forEach(h -> {
                    if (animation.getValBoolean()) {
                            if (sideMode.getValString().equalsIgnoreCase("left")) {
                                if (h.anima < 50 && h.isEnabled()) h.anima += 1 / delaySetting.getValDouble();
                                if (h.anima != 0 && h.isDisabled()) h.anima -= 1 / delaySetting.getValDouble();
                                displayX = (int) (x.getValInt() - 50 + h.anima);
                            } else {
                                if (h.anima < 50 && h.isEnabled()) h.anima += 1 / delaySetting.getValDouble();
                                if (h.anima != 0 && h.isDisabled()) h.anima -= 1 / delaySetting.getValDouble();
                                displayX = (int) (x.getValInt() + 50 - h.anima);
                            }
                        }

                    if (h.isDisabled() && h.anima <= 0) h.visableOnArray = false;
                    if (!Core.customFont.getValBoolean()) {
                        if (orderMode.getValString().equalsIgnoreCase("up") && sideMode.getValString().equalsIgnoreCase("left")) {
                            if (rainbow.getValBoolean()) {
                                c = new Color(r.getValInt(), g.getValInt(), b.getValInt(), 255);
                                event.getMatrices().drawTextWithShadow(mc.textRenderer,h.getName() + Formatting.GRAY + " " + h.getHudInfo(), animation.getValBoolean() ? displayX : x.getValInt(), y.getValInt() + (hackCount * 10), RainbowUtil.getRainbow(hackCount * 150));
                                hackCount++;
                                RainbowUtil.settingRainbow(r, g, b);
                            } else {
                                c = new Color(r.getValInt(), g.getValInt(), b.getValInt(), 255);
                                event.getMatrices().drawTextWithShadow(mc.textRenderer,h.getName() + Formatting.GRAY + " " + h.getHudInfo(), animation.getValBoolean() ? displayX : x.getValInt(), y.getValInt() + (hackCount * 10), staticc.getValBoolean() ? h.color : staticc.getValBoolean() ? h.color : c.getRGB());
                                hackCount++;

                            }
                        }
                        if (orderMode.getValString().equalsIgnoreCase("down") && sideMode.getValString().equalsIgnoreCase("right")) {
                            if (rainbow.getValBoolean()) {
                                c = new Color(r.getValInt(), g.getValInt(), b.getValInt(), 255);
                                event.getMatrices().drawTextWithShadow(mc.textRenderer,h.getName() + Formatting.GRAY + " " + h.getHudInfo(), animation.getValBoolean() ? displayX : x.getValInt(), y.getValInt() - (hackCount * 10), RainbowUtil.getRainbow(hackCount * 150));
                                hackCount++;
                                RainbowUtil.settingRainbow(r, g, b);
                            } else {
                                c = new Color(r.getValInt(), g.getValInt(), b.getValInt(), 255);
                                event.getMatrices().drawTextWithShadow(mc.textRenderer,h.getName() + Formatting.GRAY + " " + h.getHudInfo(), animation.getValBoolean() ? displayX : x.getValInt(), y.getValInt() - (hackCount * 10), staticc.getValBoolean() ? h.color : c.getRGB());
                                hackCount++;

                            }
                        }

                        if (orderMode.getValString().equalsIgnoreCase("up") && sideMode.getValString().equalsIgnoreCase("right")) {
                            if (rainbow.getValBoolean()) {
                                c = new Color(r.getValInt(), g.getValInt(), b.getValInt(), 255);
                                event.getMatrices().drawTextWithShadow(mc.textRenderer,h.getName() + Formatting.GRAY + " " + h.getHudInfo(), animation.getValBoolean() ? displayX : x.getValInt(), y.getValInt() + (hackCount * 10), RainbowUtil.getRainbow(hackCount * 150));
                                hackCount++;
                                RainbowUtil.settingRainbow(r, g, b);
                            } else {
                                c = new Color(r.getValInt(), g.getValInt(), b.getValInt(), 255);
                                event.getMatrices().drawTextWithShadow(mc.textRenderer,h.getName() + Formatting.GRAY + " " + h.getHudInfo(), animation.getValBoolean() ? displayX : x.getValInt(), y.getValInt() + (hackCount * 10), staticc.getValBoolean() ? h.color : c.getRGB());
                                hackCount++;

                            }
                        }

                        if (orderMode.getValString().equalsIgnoreCase("down") && sideMode.getValString().equalsIgnoreCase("left")) {
                            if (rainbow.getValBoolean()) {
                                c = new Color(r.getValInt(), g.getValInt(), b.getValInt(), 255);
                                event.getMatrices().drawTextWithShadow(mc.textRenderer,h.getName() + Formatting.GRAY + " " + h.getHudInfo(), animation.getValBoolean() ? displayX : x.getValInt(), y.getValInt() + (hackCount * -10), RainbowUtil.getRainbow(hackCount * 150));
                                hackCount++;
                                RainbowUtil.settingRainbow(r, g, b);
                            } else {
                                c = new Color(r.getValInt(), g.getValInt(), b.getValInt(), 255);
                                event.getMatrices().drawTextWithShadow(mc.textRenderer,h.getName() + Formatting.GRAY + " " + h.getHudInfo(), animation.getValBoolean() ? displayX : x.getValInt(), y.getValInt() + (hackCount * -10), staticc.getValBoolean() ? h.color : c.getRGB());
                                hackCount++;

                            }
                        }
                    }
                    /* if (Core.customFont.getValBoolean()) {
                        if (orderMode.getValString().equalsIgnoreCase("up") && sideMode.getValString().equalsIgnoreCase("left")) {
                            if (rainbow.getValBoolean()) {
                                c = new Color(r.getValInt(), g.getValInt(), b.getValInt());
                                CwmClient.fontRenderer.drawStringWithShadow(h.getName() + Formatting.GRAY + " " + h.getHudInfo(), animation.getValBoolean() ? displayX : x.getValInt(), y.getValInt() + (hackCount * 10), RainbowUtil.getRainbow(hackCount * 150));
                                hackCount++;
                                RainbowUtil.settingRainbow(r, g, b);
                            } else {
                                c = new Color(r.getValInt(), g.getValInt(), b.getValInt());
                                CwmClient.fontRenderer.drawStringWithShadow(h.getName() + Formatting.GRAY + " " + h.getHudInfo(), animation.getValBoolean() ? displayX : x.getValInt(), y.getValInt() + (hackCount * 10), staticc.getValBoolean() ? h.color : c.getRGB());
                                hackCount++;

                            }
                        }
                        if (orderMode.getValString().equalsIgnoreCase("down") && sideMode.getValString().equalsIgnoreCase("right")) {
                            if (rainbow.getValBoolean()) {
                                c = new Color(r.getValInt(), g.getValInt(), b.getValInt());
                                FontRenderUtil.drawLeftStringWithShadowCustom(h.getName() + Formatting.GRAY + " " + h.getHudInfo(), animation.getValBoolean() ? displayX : x.getValInt(), y.getValInt() - (hackCount * 10), RainbowUtil.getRainbow(hackCount * 150));
                                hackCount++;
                                RainbowUtil.settingRainbow(r, g, b);
                            } else {
                                c = new Color(r.getValInt(), g.getValInt(), b.getValInt());
                                FontRenderUtil.drawLeftStringWithShadowCustom(h.getName() + Formatting.GRAY + " " + h.getHudInfo(), animation.getValBoolean() ? displayX : x.getValInt(), y.getValInt() - (hackCount * 10), staticc.getValBoolean() ? h.color : c.getRGB());
                                hackCount++;


                            }
                        }

                        if (orderMode.getValString().equalsIgnoreCase("up") && sideMode.getValString().equalsIgnoreCase("right")) {
                            if (rainbow.getValBoolean()) {
                                c = new Color(r.getValInt(), g.getValInt(), b.getValInt());
                                FontRenderUtil.drawLeftStringWithShadowCustom(h.getName() + Formatting.GRAY + " " + h.getHudInfo(), animation.getValBoolean() ? displayX : x.getValInt(), y.getValInt() + (hackCount * 10), RainbowUtil.getRainbow(hackCount * 150));
                                hackCount++;
                                if (anima < 200 && h.isEnabled()) anima++;
                                RainbowUtil.settingRainbow(r, g, b);
                            } else {
                                c = new Color(r.getValInt(), g.getValInt(), b.getValInt());
                                FontRenderUtil.drawLeftStringWithShadowCustom(h.getName() + Formatting.GRAY + " " + h.getHudInfo(), animation.getValBoolean() ? displayX : x.getValInt(), y.getValInt() + (hackCount * 10), staticc.getValBoolean() ? h.color : c.getRGB());
                                hackCount++;
                                if (anima < 200 && h.isEnabled()) anima++;

                            }
                        }

                        if (orderMode.getValString().equalsIgnoreCase("down") && sideMode.getValString().equalsIgnoreCase("left")) {
                            if (rainbow.getValBoolean()) {
                                c = new Color(r.getValInt(), g.getValInt(), b.getValInt());
                                CwmClient.fontRenderer.drawStringWithShadow(h.getName() + Formatting.GRAY + " " + h.getHudInfo(), animation.getValBoolean() ? displayX : x.getValInt(), y.getValInt() + (hackCount * -10), RainbowUtil.getRainbow(hackCount * 150));
                                hackCount++;
                                if (anima < 200 && h.isEnabled()) anima++;
                                RainbowUtil.settingRainbow(r, g, b);
                            } else {
                                c = new Color(r.getValInt(), g.getValInt(), b.getValInt());
                                CwmClient.fontRenderer.drawStringWithShadow(h.getName() + Formatting.GRAY + " " + h.getHudInfo(), animation.getValBoolean() ? displayX : x.getValInt(), y.getValInt() + (hackCount * -10), staticc.getValBoolean() ? h.color : c.getRGB());
                                hackCount++;
                                if (anima < 200 && h.isEnabled()) anima++;

                            }
                        }
                    } */

                });


    }

    public void onEnable() {


    }

    public void onDisable() {


    }

}