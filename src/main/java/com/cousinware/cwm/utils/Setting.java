package com.cousinware.cwm.utils;


import com.cousinware.cwm.hack.Hack;

import java.awt.*;
import java.util.ArrayList;

public class Setting {
    //

    private final String displayName;
    private final String id;
    private final Hack parent;
    private final String mode;
    private final boolean shown;

    private String sval;
    private ArrayList<String> options;

    private boolean bval;

    private double dval;
    private double min;
    private double max;
    private boolean onlyint = false;

    private Color color;

    private String customVal;

    public Setting(String displayName, Hack parent, String sval, ArrayList<String> options, String id, boolean shown){
        this.displayName = displayName;
        this.parent = parent;
        this.sval = sval;
        this.options = options;
        this.mode = "Combo";
        this.id = id;
        this.shown = shown;
    }

    public Setting(String displayName, Hack parent, boolean bval, String id, boolean shown){
        this.displayName = displayName;
        this.parent = parent;
        this.bval = bval;
        this.mode = "Check";
        this.id = id;
        this.shown = shown;
    }

    public Setting(String displayName, Hack parent, final double dval, final double min, final double max, final boolean onlyint, String id, boolean shown){
        this.displayName = displayName;
        this.parent = parent;
        this.dval = dval;
        this.min = min;
        this.max = max;
        this.onlyint = onlyint;
        this.mode = "Slider";
        this.id = id;
        this.shown = shown;
    }

    public Setting(String displayName, Hack parent, Color color, String id, boolean shown){
        this.displayName = displayName;
        this.parent = parent;
        this.color = color;
        this.mode = "ColorPicker";
        this.id = id;
        this.shown = shown;
    }

    public Setting(String displayName, Hack parent, String customVal, String id, boolean shown){
        this.displayName = displayName;
        this.parent = parent;
        this.customVal = customVal;
        this.mode = "CustomString";
        this.id = id;
        this.shown = shown;
    }

    public String getDisplayName(){
        return displayName;
    }

    public String getId(){
        return id;
    }

    public Hack getParentMod(){
        return parent;
    }

    public String getValString(){
        return this.sval;
    }

    public void setValString(String in){
        this.sval = in;
    }

    public ArrayList<String> getOptions(){
        return this.options;
    }

    public boolean getValBoolean(){
        return this.bval;
    }

    public void setValBoolean(boolean in){
        this.bval = in;
    }

    public double getValDouble(){
        if(this.onlyint){
            this.dval = (int)dval;
        }
        return this.dval;
    }

    public boolean isShown() { return this.shown;}

    public int getValInt(){
        return (int)getValDouble();
    }

    public void setValDouble(double in){
        this.dval = in;
    }

    public double getMin(){
        return this.min;
    }

    public double getMax(){
        return this.max;
    }

    public boolean isCombo(){
        return this.mode.equalsIgnoreCase("Combo");
    }

    public boolean isCheck(){
        return this.mode.equalsIgnoreCase("Check");
    }

    public boolean isSlider(){
        return this.mode.equalsIgnoreCase("Slider");
    }

    public boolean isColorPicker(){
        return mode.equalsIgnoreCase("ColorPicker");
    }

    public boolean isCustomString(){
        return mode.equalsIgnoreCase("CustomString");
    }

    public boolean onlyInt(){
        return this.onlyint;
    }

    public Color getValColor(){
        return color;
    }

    public void setValColor(Color newColor){
        color = newColor;
    }

    public int getColorRed(){
        return color.getRed();
    }
    public int getColorGreen(){
        return color.getGreen();
    }
    public int getColorBlue(){
        return color.getBlue();
    }

    public int getColorRgb(){
        return color.getRGB();
    }

    public String getCustomVal(){
        return customVal;
    }

    public void setCustomVal(String newString){
        customVal = newString;
    }
}