package com.cousinware.cwm.utils.gui;

import com.cousinware.cwm.client.CwmClient;
import com.cousinware.cwm.event.MouseEvent;
import com.cousinware.cwm.hack.Hack;
import com.cousinware.cwm.hack.client.ClickGuiHack;
import com.cousinware.cwm.hack.client.Core;
import com.cousinware.cwm.utils.gui.components.Button;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.ArrayList;

public class ClickGUI3 extends Screen
{
    public static ArrayList<Frame> frames;
    public static int color;
    int mouseX;
    int mouseY;


    public ClickGUI3() {
        super(Text.of("cousinware2"));
        CwmClient.EVENT_BUS.subscribe(mouseListener);

        ClickGUI3.frames = new ArrayList<Frame>();
        int frameX = 5;
        for (final Hack.Category category : Hack.Category.values()) {
            final Frame frame = new Frame(category);
            frame.setX(frameX);
            ClickGUI3.frames.add(frame);
            frameX += frame.getWidth() + 10;
        }

    }

    @Subscribe
    private Listener<MouseEvent> mouseListener = new Listener<>(event -> {
        if (MinecraftClient.getInstance().currentScreen == CwmClient.clickGUI3 && event.getAction() == 1) {
            // Might bug out in the future. Possible itll turn on and off a bunch of times
            mouseClicked(mouseX, mouseY, event.getButton());
        }
        if (MinecraftClient.getInstance().currentScreen == CwmClient.clickGUI3 && event.getAction() == 0) {
            for (final Frame frame : ClickGUI3.frames) {
                if (frame.isWithinHeader(mouseX, mouseY)) {
                    frame.setDrag(false);
                    frame.dragX = mouseX - frame.getX();
                    frame.dragY = mouseY - frame.getY();

                }

                for (Component component : frame.getComponents()) {
                        component.mouseReleased(mouseX, mouseY, event.getButton());
                }
            }


            }
    });


    public void init() {

    }
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        context.fill(0, 0, context.getScaledWindowWidth(), context.getScaledWindowHeight(), new Color(ClickGuiHack.red.getValInt(), ClickGuiHack.green.getValInt(),ClickGuiHack.blue.getValInt(), ClickGuiHack.gradiant.getValInt()).getRGB());
        ClickGUI3.color = new Color(ClickGuiHack.red.getValInt(), ClickGuiHack.green.getValInt(),ClickGuiHack.blue.getValInt(), ClickGuiHack.alpha.getValInt()).getRGB();
        for (final Frame frame : ClickGUI3.frames) {
            frame.renderFrame(context, this.textRenderer, mouseX, mouseY);
            frame.updatePosition(mouseX, mouseY);
            /*if (ClickGuiHack.ran)
                //frame.renderOpenGuiAnimation(); */
            for (final Component comp : frame.getComponents()) {
                comp.updateComponent(mouseX, mouseY);

            }
            for (int i = 0; i < 15 + ClickGuiHack.animationSpeed.getValInt(); i++) {
                if (frame.isScramble) {
                    frame.glideToOrgin();
                    //mc.player.sendChatMessage(frame.category + " is now gliding back to original spot");
                }
            }
            if (frame.category.equals(Hack.Category.MISC)) {
                //System.out.println("misc x:" + frame.getX() + "misc direction:" + frame.directionX + "misc distance:" + frame.distanceX + "misc original:" + frame.originalX);
            }
        }
        //scrolling up and down on GUI

        //Command.sendClientSideMessage(String.valueOf(ClickGuiHack.guiOpen));

        //SCROLLING MECXH
       /* if (ClickGuiHack.guiOpen) {
            int dWheel = Mouse.getDWheel();
                if (dWheel > 0) {
                    for (Frame frame : frames) {
                        frame.scrollGuiUp();
                    }
                }
                if (dWheel < 0) {
                    for (Frame frame : frames) {
                        frame.scrollGuiDown();
                    }
                }
            dWheel = 0;

        } */
        for (final Frame frame : ClickGUI3.frames) {
            for (final Component component : frame.getComponents()) {
                if (component instanceof Button) {
                    boolean isHovered = ((Button) component).isMouseOnButton(mouseX, mouseY);
                    if (isHovered && ClickGuiHack.descriptions.getValBoolean()) {
                        if (!Core.customFont.getValBoolean()) context.drawCenteredTextWithShadow(MinecraftClient.getInstance().textRenderer, ((Button) component).hack.getDescription(), mouseX + 12, mouseY + 4, -1);
                        else CwmClient.fontRenderer.drawStringWithShadow(((Button) component).hack.getDescription(), mouseX + 12, mouseY + 4, -1);

                    }
                }
            }
        }

    }




    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) {
        for (final Frame frame : ClickGUI3.frames) {
            if (frame.isWithinHeader(mouseX, mouseY) && mouseButton == 0) {
                frame.setDrag(true);
                frame.dragX = mouseX - frame.getX();
                frame.dragY = mouseY - frame.getY();
            }
            if (frame.isWithinHeader(mouseX, mouseY) && mouseButton == 1) {
                frame.setOpen(!frame.isOpen());
            }
            if (frame.isOpen() && !frame.getComponents().isEmpty()) {
                for (final Component component : frame.getComponents()) {
                    component.mouseClicked(mouseX, mouseY, mouseButton);
                }
            }

        }
    }

    protected void keyTyped(final char typedChar, final int keyCode) {
        for (final Frame frame : ClickGUI3.frames) {
            if (frame.isOpen() && keyCode != 1 && !frame.getComponents().isEmpty()) {
                for (final Component component : frame.getComponents()) {
                    component.keyTyped(typedChar, keyCode);
                }
            }
        }
        if (keyCode == 1) {
            //MinecraftClient.getInstance().setScreen(null);
            ClickGuiHack.guiOpen = false;
        }
    }

    protected void mouseReleased(final int mouseX, final int mouseY, final int state) {
        for (final Frame frame : ClickGUI3.frames) {
            frame.setDrag(false);
        }
        for (final Frame frame : ClickGUI3.frames) {
            if (frame.isOpen() && !frame.getComponents().isEmpty()) {
                for (final Component component : frame.getComponents()) {
                    component.mouseReleased(mouseX, mouseY, state);
                }
            }
        }
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    static {
        ClickGUI3.color = -1;
    }
}