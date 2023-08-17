package com.cousinware.cwm.hwid;

import com.cousinware.cwm.Cwm;
import com.cousinware.cwm.client.CwmClient;
import net.minecraft.client.MinecraftClient;

public class Verification {

    public Verification() throws ClassNotFoundException {
        Class classs = Class.forName("com.cousinware.cwm.hwid.HWID");
        if (classs == null) {
            MinecraftClient.getInstance().close();
            CwmClient.Vfailed++;
        }
    }
}
