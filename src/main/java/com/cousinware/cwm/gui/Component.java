package com.cousinware.cwm.gui;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class Component
{

    protected MinecraftClient mc = MinecraftClient.getInstance();
    public void renderComponent(DrawContext context) {
    }
    
    public void updateComponent(final int mouseX, final int mouseY) {
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
    }
    
    public int getParentHeight() {
        return 0;
    }
    
    public void keyTyped(final char typedChar, final int key) {
    }
    
    public void setOff(final int newOff) {
    }
    
    public int getHeight() {
        return 0;
    }
}