package com.cousinware.cwm.hack.render;

import com.cousinware.cwm.client.CwmClient;
import com.cousinware.cwm.event.RenderOverlayEvent;
import com.cousinware.cwm.hack.Hack;
import com.cousinware.cwm.utils.RainbowUtil;
import com.cousinware.cwm.utils.Setting;
import com.cousinware.cwm.hwid.UID;

import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;


public class Welcomer extends Hack {

    Setting mode;
    Setting r;
    Setting g;
    Setting b;
    Setting rainbow;

    public Welcomer() {
        super("Welcomer", Category.RENDER,3093151);
        ArrayList<String> modes = new ArrayList();
        modes.add("Name");
        modes.add("UID");
        CwmClient.settingsManager.rSetting(mode = new Setting("Mode", this, "UID", modes, "WelcomerModes", true));
        CwmClient.settingsManager.rSetting(rainbow = new Setting("Rainbow", this, false, "WelcomerRainbow", true));
        CwmClient.settingsManager.rSetting(r = new Setting("Red", this, 255, 0, 255, true, "WelcomerRed", !rainbow.getValBoolean()));
        CwmClient.settingsManager.rSetting(g = new Setting("Green", this, 26, 0, 255, true, "WelcomerGreen", !rainbow.getValBoolean()));
        CwmClient.settingsManager.rSetting(b = new Setting("Blue", this, 42, 0, 255, true, "WelcomerBlue", !rainbow.getValBoolean()));

    }

    @com.google.common.eventbus.Subscribe
    public void renderOverlayListener(RenderOverlayEvent event) {
        String timeMessage = "";
        long time = Calendar.getInstance().getTime().getHours();
        Color c = new Color(r.getValInt(), g.getValInt(), b.getValInt(), 255);
        if (time >= 0 && time <= 11) timeMessage = "Good Morning ";
        if (time > 11 && time <= 18) timeMessage = "Good Afternoon ";
        if (time > 18 && time < 24) timeMessage = "Good Night ";
        //else timeMessage = "Good Void";
        if (rainbow.getValBoolean()) RainbowUtil.settingRainbow(r, g, b);


        event.getMatrices().drawTextWithShadow(mc.textRenderer, timeMessage + (mode.getValString().equalsIgnoreCase("name") ? mc.player.getName().getString() : UID.getUID()),
                (event.getMatrices().getScaledWindowWidth() - mc.textRenderer.getWidth(timeMessage + (mode.getValString().equalsIgnoreCase("name") ? mc.player.getName().getString() : UID.getUID()))) / 2,
                1, c.getRGB());

    }

    public void onEnable() {
        CwmClient.EVENT_BUS.register(this);

    }

    public void onDisable() {
        CwmClient.EVENT_BUS.register(this);

    }

}