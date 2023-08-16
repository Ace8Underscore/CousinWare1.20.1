package com.cousinware.cwm.utils.gui.components;

import com.cousinware.cwm.hack.client.ClickGuiHack;
import com.cousinware.cwm.hack.client.ClickGuiHack3;
import com.cousinware.cwm.hack.client.Core;
import com.cousinware.cwm.utils.Setting;
import com.cousinware.cwm.utils.gui.Component;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;


public class CheckBox extends Component
{
    private boolean hovered;
    private Setting op;
    private Button parent;
    private int offset;
    private int x;
    private int y;
    
    public CheckBox(final Setting option, final Button button, final int offset) {
        this.op = option;
        this.parent = button;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = offset;
    }
    
    @Override
    public void renderComponent(DrawContext context) {
        Color click = new Color(ClickGuiHack3.red.getValInt(), ClickGuiHack3.green.getValInt(), ClickGuiHack3.blue.getValInt(), 255);

        //context.fill(this.parent.parent.getX(), this.parent.parent.getY() + this.offset + 1, this.parent.parent.getX() + this.parent.parent.getWidth(), this.parent.parent.getY() + this.offset + 16, this.hovered ? (this.op.getValBoolean() ? click.getRGB() : click.darker().getRGB()) : (this.op.getValBoolean() ? click.getRGB() : click.getRGB()));
        context.fill(this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getX() + this.parent.parent.getWidth(), this.parent.parent.getY() + this.offset + 1, new Color(click.getRed(), click.getGreen(), click.getBlue(), 255).getRGB());
        //FontUtils.drawStringWithShadow(((ClickGuiModule) ModuleManager.getModuleByName("ClickGui")).customFont.getValInt(), this.op.getName(), this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset + 4, -1);
        context.fill(this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getX() + 1, this.parent.parent.getY() + this.offset + 16, new Color(ClickGuiHack.red.getValInt(), ClickGuiHack.green.getValInt(), ClickGuiHack.blue.getValInt(), 255).getRGB());

        if (!Core.customFont.getValBoolean()) context.drawCenteredTextWithShadow(MinecraftClient.getInstance().textRenderer,this.op.getDisplayName(), (int) (this.parent.parent.getX() + 40), this.parent.parent.getY() + this.offset + 4, -1);
        else context.drawCenteredTextWithShadow(MinecraftClient.getInstance().textRenderer,this.op.getDisplayName(), (int) (this.parent.parent.getX() + 40), this.parent.parent.getY() + this.offset + 4, -1);

        if (!Core.customFont.getValBoolean()) {
            if (this.op.getValBoolean())
                context.drawCenteredTextWithShadow(MinecraftClient.getInstance().textRenderer,this.op.getDisplayName(), (int) (this.parent.parent.getX() + 40), this.parent.parent.getY() + this.offset + 4, click.getRGB());
            else
                context.drawCenteredTextWithShadow(MinecraftClient.getInstance().textRenderer,this.op.getDisplayName(), (int) (this.parent.parent.getX() + 40), this.parent.parent.getY() + this.offset + 4, -1);
        } else {
            if (this.op.getValBoolean())
                context.drawCenteredTextWithShadow(MinecraftClient.getInstance().textRenderer,this.op.getDisplayName(), (int) (this.parent.parent.getX() + 40), this.parent.parent.getY() + this.offset + 4, click.getRGB());
            else
                context.drawCenteredTextWithShadow(MinecraftClient.getInstance().textRenderer,this.op.getDisplayName(), (int) (this.parent.parent.getX() + 40), this.parent.parent.getY() + this.offset + 4, -1);

        }
    }
    
    @Override
    public void setOff(final int newOff) {
        this.offset = newOff;
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
            this.op.setValBoolean(!this.op.getValBoolean());
        }
    }
    
    public boolean isMouseOnButton(final int x, final int y) {
        return x > this.x && x < this.x + 88 + 15 && y > this.y && y < this.y + 16;
    }
}