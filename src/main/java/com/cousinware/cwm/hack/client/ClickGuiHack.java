package com.cousinware.cwm.hack.client;

import com.cousinware.cwm.client.CwmClient;
import com.cousinware.cwm.command.Command;
import com.cousinware.cwm.gui.ClickGUI2;
import com.cousinware.cwm.gui.Frame;
import com.cousinware.cwm.hack.Hack;
import com.cousinware.cwm.utils.Setting;
import com.cousinware.cwm.utils.config.ConfigUtils;
import org.lwjgl.glfw.GLFW;

public class ClickGuiHack extends Hack {
    public ClickGuiHack INSTANCE;

    public static Setting red;
    public static Setting green;
    public static Setting blue;
    public static Setting alpha;
    public static Setting descriptions;
    public static Setting noise;
    public static Setting rainbow;
    public static Setting gradiant;
    public static Setting animationSpeed;

    public static boolean guiOpen = false;

    public ClickGuiHack() {
        super("ClickGUI", Category.CLIENT, "Opens the ClickGUI", 12126976);
        setBind(GLFW.GLFW_KEY_Y);
        INSTANCE = this;


        CwmClient.settingsManager.rSetting(red = new Setting("Red", this, 165, 0, 255, true, "ClickGuiHackRed", true));
        CwmClient.settingsManager.rSetting(green = new Setting("Green", this, 147, 0, 255, true, "ClickGuiHackGreen", true));
        CwmClient.settingsManager.rSetting(blue = new Setting("Blue", this, 44, 0, 255, true, "ClickGuiHackBlue", true));
        CwmClient.settingsManager.rSetting(alpha = new Setting("Alpha", this, 255, 0, 255, true, "ClickGuiHackAlpha", true));
        CwmClient.settingsManager.rSetting(gradiant = new Setting("Gradiant", this, 50, 0, 255, true, "ClickGuiHackGradiant", true));
        CwmClient.settingsManager.rSetting(descriptions = new Setting("Descriptions", this, true, "ClickGuiHackDescriptions", true));
        CwmClient.settingsManager.rSetting(noise = new Setting("Sound", this, true, "ClickGuiHackSound", true));
        CwmClient.settingsManager.rSetting(rainbow = new Setting("Rainbow", this, false, "ClickGuiHackRainbow", true));
        CwmClient.settingsManager.rSetting(animationSpeed = new Setting("AnimationSpeed", this, 5, 0, 10, true, "ClickGuiHackAnimationSpeed", true));

    }

    public static Setting customFont;
    int delay = 0;

    @Override
    public void onEnable() {
        guiOpen = true;
        try {
            if (CwmClient.fontRenderer.getFontName().equalsIgnoreCase("null")) {
                CwmClient.fontRenderer.setFontName("Arial");
                CwmClient.fontRenderer.setFontSize(18);
                CwmClient.configUtils.saveFont();
                CwmClient.configUtils.loadFont();
            }
        } catch (Exception ignored) {

//
        }
        for (Frame frame : ClickGUI2.frames) {
            //frame.frameScramble();
        }
        //disable();
    }

    public void onUpdate() {
        delay++;
        if (delay > 1) {
            mc.setScreenAndRender(CwmClient.clickGui2);
            delay = 0;
            disable();
        }
    }

}

