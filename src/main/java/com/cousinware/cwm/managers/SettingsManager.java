package com.cousinware.cwm.managers;


import com.cousinware.cwm.hack.Hack;
import com.cousinware.cwm.utils.Setting;

import java.util.ArrayList;



public class SettingsManager {

    private final ArrayList<Setting> settings;

    public SettingsManager(){
        this.settings = new ArrayList<>();

    }

    public void rSetting(Setting in){
        this.settings.add(in);
    }

    public ArrayList<Setting> getSettings(){
        return this.settings;
    }

    public ArrayList<Setting> getSettingsByMod(Hack hack){
        ArrayList<Setting> out = new ArrayList<>();
        for(Setting s : getSettings()){
            if(s.getParentMod().equals(hack)){
                out.add(s);
            }
        }
        if(out.isEmpty()){
            return null;
        }
        return out;
    }

    public Setting getSettingByDisplayName(String name){
        for(Setting set : getSettings()){
            if(set.getDisplayName().equalsIgnoreCase(name)){
                return set;
            }
        }
        //System.err.println("[Nord] Error Setting NOT found: '" + name +"'!");
        return null;
    }

    public Setting getSettingByID(String id){
        for(Setting s : getSettings()){
            if(s.getId().equalsIgnoreCase(id)){
                return s;
            }
        }
        System.err.println("[Nord] Error Setting NOT found: '" + id +"'!");
        return null;
    }

    public boolean isShown(Setting setting) {
        return setting.isShown();
    }

}