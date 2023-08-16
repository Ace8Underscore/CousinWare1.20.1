package com.cousinware.cwm.hack;

import com.cousinware.cwm.client.CwmClient;
import com.cousinware.cwm.command.Command;
import com.cousinware.cwm.managers.HackManager;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Formatting;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_UNKNOWN;

public class Hack {
    public static final MinecraftClient mc = MinecraftClient.getInstance();

    public String name;
    public String description;
    public Category category;
    public int bind;
    public boolean enabled;
    public boolean drawn;
    public int color;
    public double anima;
    public boolean visableOnArray;

    public Hack(String hackName, Category hackCategory, int decimalColor) {
        name = hackName;
        description = " ";
        category = hackCategory;
        bind = GLFW_KEY_UNKNOWN;
        enabled = false;
        drawn = true;
        this.color = decimalColor;
        anima = 0;
        visableOnArray = false;
    }

    public Hack(String hackName, Category hackCategory, String hackDescription, int decimalColor) {
        name = hackName;
        description = hackDescription;
        category = hackCategory;
        bind = GLFW_KEY_UNKNOWN;
        enabled = false;
        drawn = true;
        this.color = decimalColor;

    }

    public int getBind(){
        return bind;
    }

    public void setBind(int b){
        bind = b;
    }

    public void onUpdate(){}
    public void onRender(){}

    public void doTick(){}
    protected void onEnable(){
    }

    protected void onDisable(){
    }
//

    public boolean isEnabled(){
        return enabled;
    }

    public boolean isDisabled(){ return !enabled; }

    public boolean isVisableOnArray() { return visableOnArray;}


    public void setEnabled(boolean e){
        enabled = e;
    }


    public void toggle(){
        if(isEnabled()) {
            disable();
        } else if (!isEnabled()){
            enable();
        }
    }

    public void enable() {
        //MinecraftForge.EVENT_BUS.register(this);
        visableOnArray = true;
        anima = 0;
        setEnabled(true);
        //if (HackManager.getHackByName("ToggleMsgs").isEnabled() && !this.name.equalsIgnoreCase("clickgui")) {
            Command.sendClientSideMessage("Enabled " + Formatting.GREEN + this.name);
       // }
        //MinecraftForge.EVENT_BUS.register(this);
        onEnable();
    }

    public void disable() {
        Command.sendClientSideMessage("Disabled " + Formatting.RED + this.name);

        setEnabled(false);
        onDisable();
    }
    public boolean isDrawn() {return drawn;}

    public Category getCategory(){
        return category;
    }

    public void setCategory(Category c){
        category = c;
    }

    public int getLength(String name) {
        return HackManager.getHackByName(name).getLength(name);
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getHudInfo(){
        return "";
    }






    public enum Category {
        COMBAT,
        PLAYER,
        MOVEMENT,
        MISC,
        RENDER,
        CLIENT,
        EXPLOIT
    }
}


