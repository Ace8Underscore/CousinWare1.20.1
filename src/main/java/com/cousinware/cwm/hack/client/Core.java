package com.cousinware.cwm.hack.client;


import com.cousinware.cwm.client.CwmClient;
import com.cousinware.cwm.hack.Hack;
import com.cousinware.cwm.utils.Setting;


public class Core extends Hack {

    public static Setting saturation;
    public static Setting brightness;
    public static Setting speed;
    public static Setting customFont;
    public static Setting antiAlias;
    public static Setting fractionalMetrics;
    public static Setting essentials;
    public static Setting capes;
    public static Setting snooper;

    int i = 0;
    int j = 0;
    boolean switchingSlot = false;
    int delaySlot = 0;

    public Core() {
        super("Core", Category.CLIENT, 10989199);

        CwmClient.settingsManager.rSetting(saturation = new Setting("Saturation", this, .8, 0, 1,false,  "ColorsSaturation", true));
        CwmClient.settingsManager.rSetting(brightness = new Setting("Brightness", this, .8, 0, 1,false,  "ColorsBrightness", true));
        CwmClient.settingsManager.rSetting(speed = new Setting("Speed", this, 1, 0, 20,true,  "ColorsSpeed", true));
        CwmClient.settingsManager.rSetting(customFont = new Setting("CustomFont(DONT TOGGLE WIP)", this, false, "CoreCustomFont", true));
        CwmClient.settingsManager.rSetting(antiAlias = new Setting("AntiAlias", this, true, "CoreAntiAlias", true));
        CwmClient.settingsManager.rSetting(fractionalMetrics = new Setting("FractionalMetrics", this, true, "CoreFractionalMetrics", true));
        CwmClient.settingsManager.rSetting(capes = new Setting("Capes", this, true, "CoreCapes", true));
        CwmClient.settingsManager.rSetting(essentials = new Setting("Essentials", this, false, "CoreEssentials", true));
        CwmClient.settingsManager.rSetting(snooper = new Setting("Snooper", this, true, "CoreSnooper", true));

    }




}

