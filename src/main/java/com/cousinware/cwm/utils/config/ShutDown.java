package com.cousinware.cwm.utils.config;

import com.cousinware.cwm.client.CwmClient;

public class ShutDown extends Thread {

    public static void saveConfig() {
        CwmClient.configUtils.saveMods();
        CwmClient.configUtils.saveBinds();
        CwmClient.configUtils.saveDrawn();
        CwmClient.configUtils.savePrefix();
        CwmClient.configUtils.saveFriends();
        CwmClient.configUtils.saveSettingsList();
        CwmClient.configUtils.saveFont();
        //CwmClient.configUtils.saveHuds();
       // CwmClient.configUtils.saveHudPos();
        // CwmClient.configUtils.saveXray();

    }

    /**
     * @author Finz0
     **/

    @Override
    public void run() {
        saveConfig();
    }
}