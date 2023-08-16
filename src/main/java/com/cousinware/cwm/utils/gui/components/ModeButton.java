package com.cousinware.cwm.utils.gui.components;

import com.cousinware.cwm.hack.Hack;
import com.cousinware.cwm.hack.client.ClickGuiHack;
import com.cousinware.cwm.hack.client.Core;
import com.cousinware.cwm.utils.Setting;
import com.cousinware.cwm.utils.gui.Component;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Formatting;

import java.awt.*;


public class ModeButton extends Component
{
    private boolean hovered;
    private Button parent;
    private Setting set;
    private int offset;
    private int x;
    private int y;
    private Hack hack;
    private int modeIndex;
    
    public ModeButton(final Setting set, final Button button, final Hack hack, final int offset) {
        this.set = set;
        this.parent = button;
        this.hack = hack;
        this.x = button.parent.getX() + button.parent.getWidth();
        this.y = button.parent.getY() + button.offset;
        this.offset = offset;
        this.modeIndex = 0;
    }
    
    @Override
    public void setOff(final int newOff) {
        this.offset = newOff;
    }
    
    @Override
    public void renderComponent(DrawContext context) {
        context.fill(this.parent.parent.getX(), this.parent.parent.getY() + this.offset + 1, this.parent.parent.getX() + this.parent.parent.getWidth(), this.parent.parent.getY() + this.offset + 16, this.hovered ? new Color(29, 37, 48, ClickGuiHack.alpha.getValInt()).darker().darker().getRGB() : new Color(29, 37, 48, ClickGuiHack.alpha.getValInt()).getRGB());
        context.fill(this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getX() + this.parent.parent.getWidth(), this.parent.parent.getY() + this.offset + 1, new Color(29, 37, 48, ClickGuiHack.alpha.getValInt()).getRGB());
        context.fill(this.parent.parent.getX(), this.parent.parent.getY() + this.offset, this.parent.parent.getX() + 1, this.parent.parent.getY() + this.offset + 16, new Color(ClickGuiHack.red.getValInt(), ClickGuiHack.green.getValInt(), ClickGuiHack.blue.getValInt(), 255).getRGB());
        //FontUtils.drawStringWithShadow(((ClickGuiModule) ModuleManager.getModuleByName("ClickGui")).customFont.getValue(), this.set.getName() + " " + ChatFormatting.GRAY + this.set.getValue(), this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset + 4, -1);
        if (!Core.customFont.getValBoolean()) context.drawCenteredTextWithShadow(MinecraftClient.getInstance().textRenderer,this.set.getDisplayName() + " " + Formatting.GRAY + this.set.getValString(), this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset + 4, -1);
        else context.drawCenteredTextWithShadow(MinecraftClient.getInstance().textRenderer,this.set.getDisplayName() + " " + Formatting.GRAY + this.set.getValString(), this.parent.parent.getX() + 2, this.parent.parent.getY() + this.offset + 4, -1);
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
            final int maxIndex = this.set.getOptions().size() - 1;
            ++this.modeIndex;
            if (this.modeIndex > maxIndex) {
                this.modeIndex = 0;
            }
            this.set.setValString(this.set.getOptions().get(this.modeIndex));

        }
    }
    
    public boolean isMouseOnButton(final int x, final int y) {
        return x > this.x && x < this.x + 88 + 15 && y > this.y && y < this.y + 16;
    }
}