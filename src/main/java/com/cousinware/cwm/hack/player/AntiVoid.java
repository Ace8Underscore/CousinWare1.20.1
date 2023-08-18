package com.cousinware.cwm.hack.player;

import com.cousinware.cwm.client.CwmClient;
import com.cousinware.cwm.hack.Hack;
import com.cousinware.cwm.utils.Setting;

import java.util.ArrayList;

/**
 * @author Ace________/Ace_#1233
 */

public class AntiVoid extends Hack {

    Setting downMode;


    public AntiVoid() {
        super("AntiVoid", Category.PLAYER, 8263634);
        ArrayList<String> downModes = new ArrayList<>();
        downModes.add("LagBack");
        downModes.add("Strict");
        CwmClient.settingsManager.rSetting(downMode = new Setting("Mode", this, "lagBack", downModes, "AntiVoidDownMode", true));

    }

    @Override
    public void onUpdate() {
        Double yLevel = mc.player.getY();
        if (downMode.getValString().equalsIgnoreCase("lagBack")) {
        if (yLevel <=.5) {
            mc.player.jump();
            //Command.sendClientSideMessage("Attempting to get " + mc.player.getName() + " Out of the Void!");
             }
            }
        if (downMode.getValString().equalsIgnoreCase("strict")) {
            if (yLevel <=.9) {
                mc.player.jump();
                //Command.sendClientSideMessage("Attempting to get " + mc.player.getName() + " Out of the Void!");
            }
        }
        }

}
