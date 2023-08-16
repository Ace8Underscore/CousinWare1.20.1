package com.cousinware.cwm.utils.gui.components;

import com.cousinware.cwm.client.CwmClient;
import com.cousinware.cwm.hack.client.ClickGuiHack3;
import com.cousinware.cwm.hack.Hack;
import com.cousinware.cwm.hack.client.Core;
import com.cousinware.cwm.utils.Setting;
import com.cousinware.cwm.utils.gui.Component;
import com.cousinware.cwm.utils.gui.Frame;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.sound.SoundEvents;

import java.awt.*;
import java.util.ArrayList;

public class Button extends Component
{
    public Hack hack;
    public Frame parent;
    public int offset;
    public boolean isHovered;
    private ArrayList<Component> subcomponents;
    public boolean open;
    private int height;

    public Button(final Hack hack, final Frame parent, final int offset) {
        this.hack = hack;
        this.parent = parent;
        this.offset = offset;
        this.subcomponents = new ArrayList<Component>();
        this.open = false;
        this.height = 16;
        int opY = offset + 16;
        if (CwmClient.settingsManager.getSettingsByMod(hack) != null && !CwmClient.settingsManager.getSettingsByMod(hack).isEmpty()) {
            for (final Setting s : CwmClient.settingsManager.getSettingsByMod(hack)) {
                    if (s.isCombo()) {
                        this.subcomponents.add(new ModeButton((Setting)s, this, hack, opY));
                        opY += 16;
                        continue;
                    }
                   /* case STRING: {
                        this.subcomponents.add(new StringButton((Setting.s)s, this, opY));
                        opY += 16;
                        continue;
                    } */
                    else if (s.isCheck()) {
                        this.subcomponents.add(new CheckBox((Setting)s, this, opY));
                        opY += 16;
                        continue;
                    }
                    else if (s.isSlider()) {
                        this.subcomponents.add(new DoubleSlider((Setting)s, this, opY));
                        opY += 16;
                        continue;
                    }
                  /*  case INT: {
                        this.subcomponents.add(new IntSlider((Setting)s, this, opY));
                        opY += 16;
                        continue;
                    } */
                }
            }
        this.subcomponents.add(new Keybind(this, opY));

    }


    @Override
    public void setOff(final int newOff) {
        this.offset = newOff;
        int opY = this.offset + 16;
        for (final Component comp : this.subcomponents) {
            comp.setOff(opY);
            opY += 16;
        }
    }

    @Override
    public void renderComponent(DrawContext context) {

        Color click2 = new Color(ClickGuiHack3.red.getValInt(), ClickGuiHack3.green.getValInt(), ClickGuiHack3.blue.getValInt(), 125);
        Color c = new Color(ClickGuiHack3.red.getValInt(), ClickGuiHack3.green.getValInt(), ClickGuiHack3.blue.getValInt(), 255);
        context.fill(this.parent.getX(), this.parent.getY() + this.offset + 1, this.parent.getX() + this.parent.getWidth(), this.parent.getY() + 16 + this.offset, this.isHovered ? (this.hack.isEnabled() ? click2.getRGB() : click2.darker().darker().getRGB()) : (this.hack.isEnabled() ? click2.getRGB() : click2.getRGB()));
        //context.drawHorizontalLine(this.parent.getX(), this.parent.getX() + this.parent.getWidth(), this.parent.getY() + this.offset + 1, c.getRGB());
        context.drawHorizontalLine(this.parent.getX(), this.parent.getX() + this.parent.getWidth(), this.parent.getY() + this.offset + 16, c.getRGB());
        context.drawVerticalLine(this.parent.getX(), this.parent.getY() + this.offset, this.parent.getY() + this.offset + 16, c.getRGB());
        context.drawVerticalLine(this.parent.getX() + this.parent.getWidth(), this.parent.getY() + this.offset, this.parent.getY() + this.offset + 16, c.getRGB());

        //context.fill(this.parent.getX(), this.parent.getY() + this.offset, this.parent.getX() + this.parent.getWidth(), this.parent.getY() + this.offset + 1, new Color(29, 37, 48, ClickGuiHack3.alpha.getValInt()).getRGB());
        //FontUtils.drawStringWithShadow(((ClickGuiModule) ModuleManager.getModuleByName("ClickGui")).customFont.getValInt(), this.mod.getName(), this.parent.getX() + 2, this.parent.getY() + this.offset + 2 + 2, -1);

        if (!Core.customFont.getValBoolean()) {
            if (this.hack.isEnabled())
                context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer, this.hack.getName(), this.parent.getX() + 2, this.parent.getY() + this.offset + 2 + 2, c.brighter().getRGB());
            else
                context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer,this.hack.getName(), this.parent.getX() + 2, this.parent.getY() + this.offset + 2 + 2, -1);
        } else {
            if (this.hack.isEnabled())
                context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer,this.hack.getName(), this.parent.getX() + 2, this.parent.getY() + this.offset + 2 + 2, c.brighter().getRGB());
            else
                context.drawTextWithShadow(MinecraftClient.getInstance().textRenderer,this.hack.getName(), this.parent.getX() + 2, this.parent.getY() + this.offset + 2 + 2, -1);

        }

        if (this.subcomponents.size() > 1) {
           /* context.getMatrices().push();
            RenderSystem.clearColor(1, 1, 1, 1);
            context.getMatrices().translate(0, 0, 4);
            RenderSystem.lineWidth(100);
            context.getMatrices().pop(); */
            //FontUtils.drawStringWithShadow(((ClickGuiModule) ModuleManager.getModuleByName("ClickGui")).customFont.getValInt(), this.open ? "-" : "+", this.parent.getX() + this.parent.getWidth() - 10, this.parent.getY() + this.offset + 2 + 2, -1);
            if (!Core.customFont.getValBoolean()) context.drawCenteredTextWithShadow(MinecraftClient.getInstance().textRenderer,this.open ? "v" : ">", this.parent.getX() + this.parent.getWidth() - 10, this.parent.getY() + this.offset + 2 + 2, -1);
            else CwmClient.fontRenderer.drawStringWithShadow(this.open ? "v" : ">", this.parent.getX() + this.parent.getWidth() - 10, this.parent.getY() + this.offset + 2 + 2, -1);
        }
        if (this.open && !this.subcomponents.isEmpty()) {
            for (final Component comp : this.subcomponents) {
                comp.renderComponent(context);
                //Gui.drawRect(this.parent.getX(), this.parent.getY() + this.offset + 1, this.parent.getX() + 1, this.parent.getY() + this.offset + 16, new Color(ClickGuiHack3.red.getValInt(), ClickGuiHack3.green.getValInt(), ClickGuiHack3.blue.getValInt(), ClickGuiHack3.alpha.getValInt()).getRGB());
            }
        }
    }

    @Override
    public int getHeight() {
        if (this.open) {
            return 16 * (this.subcomponents.size() + 1);
        }
        return 16;
    }

    @Override
    public void updateComponent(final int mouseX, final int mouseY) {
        this.isHovered = this.isMouseOnButton(mouseX, mouseY);
        if (!this.subcomponents.isEmpty()) {
            for (final Component comp : this.subcomponents) {
                comp.updateComponent(mouseX, mouseY);
            }
        }
        if (this.isHovered && ClickGuiHack3.descriptions.getValBoolean()) {
           // if (!Core.customFont.getValBoolean()) mc.fontRenderer.drawStringWithShadow(this.hack.getDescription(), mouseX + 12, mouseY + 4, -1);
           // else CousinWare.INSTANCE.fontRenderer.drawStringWithShadow(this.hack.getDescription(), mouseX + 12, mouseY + 4, -1);

        }
    }

    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        //Command.sendClientSideMessage(String.valueOf(button));
        if (this.isMouseOnButton(mouseX, mouseY) && button == 0) {
            this.hack.toggle();
            if (ClickGuiHack3.noise.getValBoolean()) {
                MinecraftClient.getInstance().player.playSound(SoundEvents.UI_BUTTON_CLICK.value(), 2f, 1f);
            }
        }
        if (this.isMouseOnButton(mouseX, mouseY) && button == 1) {
            this.open = !this.open;
            this.parent.refresh();
        }
        for (final Component comp : this.subcomponents) {
            comp.mouseClicked(mouseX, mouseY, button);
        }
    }

    @Override
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
        for (final Component comp : this.subcomponents) {
            comp.mouseReleased(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    public void keyTyped(final char typedChar, final int key) {
        for (final Component comp : this.subcomponents) {
            comp.keyTyped(typedChar, key);
        }
    }

    public boolean isMouseOnButton(final int x, final int y) {
        return x > this.parent.getX() && x < this.parent.getX() + this.parent.getWidth() && y > this.parent.getY() + this.offset && y < this.parent.getY() + 16 + this.offset;
    }
}