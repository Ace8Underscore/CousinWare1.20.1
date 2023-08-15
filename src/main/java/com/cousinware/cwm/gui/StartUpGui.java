/*package com.cousinware.cwm.gui;

import io.ace.nordclient.CousinWare;
import io.ace.nordclient.utilz.FontRenderUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

import java.io.IOException;
import java.util.List;

public class StartUpGui extends GuiScreen {
        public List<String> multilineMessage;
        public int textHeight;



        protected void keyTyped(char typedChar, int keyCode) throws IOException {
        }

        public void initGui() {
            this.buttonList.clear();
            this.buttonList.add(new GuiButton(0, this.width / 2 - 100, Math.min(this.height / 2 + this.textHeight / 2 + this.fontRenderer.FONT_HEIGHT, this.height + 30), I18n.format("Enjoy!", new Object[0])));
        }

        protected void actionPerformed(GuiButton button) throws IOException {
            if (button.id == 0) {
                this.mc.displayGuiScreen(null);
            }
        }

        public void drawScreen(int mouseX, int mouseY, float partialTicks) {
            this.drawDefaultBackground();
                    String s = "Welcome Back and Welcome to Cousinware " + CousinWare.VERSION;
                    String s1 = "Ive Been Developing this for when the goons return";
                    String s2 = "";
                    FontRenderUtil.drawCenteredString(s, this.width / 2, (this.height / 2 - this.textHeight / 2) - 50, 16777215);
                    FontRenderUtil.drawCenteredString(s1, this.width / 2, (this.height / 2 - this.textHeight / 2) - 40, 16777215);
                    FontRenderUtil.drawCenteredString(CousinWare.UPDATEMESSAGE, this.width / 2, (this.height / 2 - this.textHeight / 2) - 30, 16777215);

            super.drawScreen(mouseX, mouseY, partialTicks);
        }
    } */


