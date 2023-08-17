package com.cousinware.cwm.utils.gui.components;

import com.cousinware.cwm.command.commands.Bind;
import com.cousinware.cwm.hack.client.ClickGuiHack;
import com.cousinware.cwm.hack.client.ClickGuiHack3;
import com.cousinware.cwm.hack.client.Core;
import com.cousinware.cwm.utils.gui.Component;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

import java.awt.*;


public class Keybind extends Component
{
    private boolean hovered;
    private boolean binding;
    private Button parent;
    private int offset;
    private int x;
    private int y;

    public Keybind(final Button button, final int offset) {
        this.parent = button;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = offset;
    }

    @Override
    public void setOff(final int newOff) {
        this.offset = newOff;
    }

    @Override
    public void renderComponent(DrawContext context) {

        Color click = new Color(ClickGuiHack3.red.getValInt(), ClickGuiHack3.green.getValInt(), ClickGuiHack3.blue.getValInt(), 255);
        Color click2 = new Color(ClickGuiHack3.red.getValInt(), ClickGuiHack3.green.getValInt(), ClickGuiHack3.blue.getValInt(), 100);

        context.fill(this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getX() + this.parent.parent.getWidth(), this.parent.parent.getY() + this.offset + 17, this.hovered ? click2.darker().darker().getRGB() : click2.darker().getRGB());
        //context.drawBorder(this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getX() + this.parent.parent.getWidth(), this.parent.parent.getY() + this.offset + 1, new Color(29, 37, 48, ClickGuiHack.alpha.getValInt()).getRGB());
        //context.drawBorder(this.parent.parent.getX(), this.parent.parent.getY() + this.offset + 15, this.parent.parent.getX() + this.parent.parent.getWidth(), this.parent.parent.getY() + this.offset + 16, new Color(29, 37, 48, ClickGuiHack.alpha.getValInt()).getRGB());
        //context.drawBorder(this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getX() + 1, this.parent.parent.getY() + this.offset + 16, new Color(ClickGuiHack.red.getValInt(), ClickGuiHack.green.getValInt(), ClickGuiHack.blue.getValInt(), 255).getRGB());

        context.drawVerticalLine(this.parent.parent.getX(), this.parent.parent.getY() + this.offset - 1, this.parent.parent.getY() + this.offset + 17, click.brighter().getRGB());
        context.drawVerticalLine(this.parent.parent.getX() + this.parent.parent.getWidth(), this.parent.parent.getY() + this.offset - 1, this.parent.parent.getY() + this.offset + 17, click.brighter().getRGB());
        context.drawHorizontalLine(this.parent.parent.getX(), this.parent.parent.getX() + this.parent.parent.getWidth(), this.parent.parent.getY() + this.offset + 16, click.brighter().getRGB());
        //FontUtils.drawStringWithShadow(((ClickGuiModule) ModuleManager.getModuleByName("ClickGui")).customFont.getValue(),this.binding ? "Press a key..." : ("Key: " + ChatFormatting.GRAY + Keyboard.getKeyName(this.parent.mod.getBind())), this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset + 4, -1);
        if (!Core.customFont.getValBoolean()) context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, this.binding ? "Press a key..." : ("Key: " + Formatting.GRAY + Bind.getKeyName(this.parent.hack.getBind())), this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset + 4, -1);
        else context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer,this.binding ? "Press a key..." : ("Key: " + Formatting.GRAY + Bind.getKeyName(this.parent.hack.getBind())), this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset + 4, -1);
    }

    @Override
    public void updateComponent(final int mouseX, final int mouseY) {
        this.hovered = this.isMouseOnButton(mouseX, mouseY);
        this.y = this.parent.parent.getY() + this.offset;
        this.x = this.parent.parent.getX() - 10;
    }

    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButton(mouseX, mouseY) && button == 0 && this.parent.open) {
            this.binding = !this.binding;
        }
    }

    @Override
    public void keyTyped(final char typedChar, final int key) {
        if (this.binding) {
            if (key == 211 || key == GLFW.GLFW_KEY_BACKSPACE || key == GLFW.GLFW_KEY_ESCAPE) {
                this.parent.hack.setBind(0);
            }
            else {
                this.parent.hack.setBind(key);
            }
            this.binding = false;
        }
    }

    public boolean isMouseOnButton(final int x, final int y) {
        return x > this.x && x < this.x + 88 + 15 && y > this.y && y < this.y + 16;
    }
}