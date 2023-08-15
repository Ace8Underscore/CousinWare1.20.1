package com.cousinware.cwm.gui;

import com.cousinware.cwm.gui.components.Button;
import com.cousinware.cwm.hack.Hack;
import com.cousinware.cwm.hack.client.ClickGuiHack;
import com.cousinware.cwm.hack.client.Core;
import com.cousinware.cwm.managers.HackManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;

public class Frame
{
    int mouseX;
    int mouseY;
    public ArrayList<Component> components;
    public Hack.Category category;
    private boolean open;
    private int width;
    private int y;
    private int x;
    private int originalY;
    public int originalX;
    private int distanceY;
    public int distanceX;
    public boolean directionX;
    private boolean directionY;
    public boolean isScramble;
    private int barHeight;
    private boolean isDragging;
    private static boolean doingStartAnimation = false;
    private static boolean setAnimationValues = false;
    public int dragX;
    public int dragY;
    private int height;
    static Random randomX;
    static Random randomY;
    private int animationX = 0;
    private int animationY = 0;
    private static Timer timer;
    //ClickGuiHack hack = ((ClickGuiHack) HackManager.getHackByName("ClickGuiModule"));
    public Frame(final Hack.Category cat) {
        this.components = new ArrayList<Component>();
        this.category = cat;
        this.width = 95;
        this.x = 5;
        this.y = 5;
        this.isScramble = false;
        this.originalY = 0;
        this.originalX = 0;
        this.distanceX = 0;
        this.distanceY = 0;
        this.animationX = 0;
        this.animationY = 0;
        this.barHeight = 16;
        this.dragX = 0;
        this.open = true;
        this.isDragging = false;
        int tY = this.barHeight;
        for (final Hack hack : HackManager.getHacksInCategory(cat)) {
            final Button hackButton = new Button(hack, this, tY);
            this.components.add(hackButton);
            tY += 16;
        }
        this.refresh();

    }

    public ArrayList<Component> getComponents() {
        return this.components;
    }

    public void setX(final int newX) {
        this.x = newX;
    }

    public void setY(final int newY) {
        this.y = newY;
    }

    public void setDrag(final boolean drag) {
        this.isDragging = drag;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(final boolean open) {
        this.open = open;
    }

    public void renderFrame(DrawContext context, final TextRenderer textRenderer, final int mouseX, final int mouseY) {
        Color c = new Color(29, 37, 48, 255);
        Color click = new Color(ClickGuiHack.red.getValInt(), ClickGuiHack.green.getValInt(), ClickGuiHack.blue.getValInt(), 255);
        this.mouseX = mouseX;
        this.mouseY = mouseY;

       /* if (doingStartAnimation) {
            if (setAnimationValues) {
                this.x += this.animationX;
                this.y += this.animationY;
                setAnimationValues = false;
            }

            if (timer.getPassedTimeMs() >= 500) {
                //this.x -= animationX;
                //this.y -= animationY;
                Minecraft.getMinecraft().player.sendChatMessage("Animation Stopped");
                doingStartAnimation = false;
            } else {
                if (!(this.animationX < 5)) {
                    this.animationX = this.animationX - (this.animationX / 120);
                }
                if (!(this.animationY < 5)) {
                    this.animationY = this.animationY - (this.animationY / 120);
                }
                Minecraft.getMinecraft().player.sendChatMessage("Animation Working X:" + this.x + " Y:" + this.y);
                this.x -= animationX;
                this.y -= animationY;
            } */


        context.drawBorder(this.x, this.y, this.x + this.width, this.y + this.barHeight, c.getRGB());
        //final Color outline = new Color(10, 10, 10, 200);
        //Gui.drawRect(this.x - 2, this.y - 2, this.x + this.width + 2, this.y, outline.getRGB());
        //Gui.drawRect(this.x - 2, this.y, this.x, this.y + this.height + 2, outline.getRGB());
        //Gui.drawRect(this.x, this.y + this.height, this.x + this.width + 2, this.y + this.height + 2, outline.getRGB());
        //Gui.drawRect(this.x + this.width, this.y, this.x + this.width + 2, this.y + this.height, outline.getRGB());
        context.drawBorder(this.x, this.y + 17, this.x + 80 + 15,  this.y + 15, click.getRGB() );
        MinecraftClient mc = MinecraftClient.getInstance();

        //FontUtils.drawStringWithShadow(((ClickGuiModule) ModuleManager.getModuleByName("ClickGui")).customFont.getValue(), this.category.name(), this.x + 2, this.y + 3, -1);
        if (!Core.customFont.getValBoolean()) context.drawCenteredTextWithShadow(MinecraftClient.getInstance().textRenderer, this.category.name(), (int) (this.x + 47.5), this.y + 3, -1);
        else context.drawCenteredTextWithShadow(MinecraftClient.getInstance().textRenderer, this.category.name(), (int) (this.x + 47.5), this.y + 3, -1);
        if (this.open && !this.components.isEmpty()) {
            for (final Component component : this.components) {
                component.renderComponent(context);
            }
        }
    }

    public void refresh() {
        int off = this.barHeight;
        for (final Component comp : this.components) {
            comp.setOff(off);
            off += comp.getHeight();
        }
        this.height = off;
    }

    public void scrollGuiUp() {
        this.y += 10;
    }

    public void scrollGuiDown() {
        this.y -= 10;
    }

    public void frameScramble() {
        this.originalPos();
        this.setRandom();
        this.isScramble = true;

    }

    public void originalPos() {
        this.originalX = this.x;
        this.originalY = this.y;
    }

    public void setRandom() {
        //ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        this.getRandomDirectionX();
        this.getRandomDirectionY();
        int randomNumX = 0;
        int randomNumY = 0;
        if (this.directionX) {
          // randomNumX = ThreadLocalRandom.current().nextInt(sr.getScaledWidth(), sr.getScaledWidth() + 100);
        } else {
           // randomNumX = ThreadLocalRandom.current().nextInt(-100, 0);
        }
        if (this.directionY) {
           // randomNumY = ThreadLocalRandom.current().nextInt(sr.getScaledHeight(),sr.getScaledHeight() + 150);

        } else {
            randomNumY = ThreadLocalRandom.current().nextInt(-150, 0);
        }

        this.x = randomNumX;
        this.y = randomNumY;
    }

    public void getRandomDirectionX() {
        this.directionX = ThreadLocalRandom.current().nextBoolean();
    }

    public void getRandomDirectionY() {
        this.directionY = ThreadLocalRandom.current().nextBoolean();
    }

    public void glideToOrgin() {
        if (this.directionX) {
            this.distanceX = this.x - this.originalX;
            if (this.distanceX != 0) {
                this.x--;
            }
        } else {
            if (this.x < 0) this.distanceX = this.originalX + (this.x * -1);
            else this.distanceX = this.originalX - this.x;
            if (this.distanceX != 0) {
                this.x++;
            }
        }
        if (this.directionY) {
            this.distanceY = this.y - this.originalY;
            if (this.distanceY != 0) {
                this.y--;
            }
        } else {
                if (this.y < 0) this.distanceY = this.originalY + (this.y * -1);
                else this.distanceY = this.y - this.originalY;
                if (this.distanceY != 0) {
                    this.y++;
                }
            }
        if (this.distanceX == 0 && this.distanceY == 0) this.isScramble = false;

    }


    public void renderOpenGuiAnimation() {
        doingStartAnimation = true;
        setAnimationValues = true;
        //ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
       // int randomNumX = ThreadLocalRandom.current().nextInt(sr.getScaledWidth(), sr.getScaledWidth() + 100);
       // int randomNumY = ThreadLocalRandom.current().nextInt(sr.getScaledHeight(), sr.getScaledHeight() + 100);
        //this.animationX = randomNumX;
       // this.animationY = randomNumY;
        //MinecraftClient.getInstance().player.sendChatMessage(String.valueOf(randomNumX));
        timer = new Timer();
        //timer.setMs(0);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public void updatePosition(final int mouseX, final int mouseY) {
        if (this.isDragging) {
            this.setX(mouseX - this.dragX);
            this.setY(mouseY - this.dragY);
        }
    }

    public boolean isWithinHeader(final int x, final int y) {
        return x >= this.x && x <= this.x + this.width + 15 && y >= this.y && y <= this.y + this.barHeight;
    }
}