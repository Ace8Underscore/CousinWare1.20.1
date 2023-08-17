package com.cousinware.cwm.utils.font;

import net.minecraft.client.MinecraftClient;

/**
 * @author Ace________/Ace_#1233
 */

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class FontRenderUtil {
    private static final MinecraftClient mc = MinecraftClient.getInstance();


  /*  public static float drawCenteredStringWithShadow(DrawContext context, String text, float x, float y, int color) {
        return mc.textRenderer.draw(text, x - mc.fontRenderer.getStringWidth(text) / 2, y, color);
    }

    public static float drawCenteredString(String text, float x, float y, int color) {
        return mc.fontRenderer.drawString(text, (int) x - mc.fontRenderer.getStringWidth(text) / 2, (int) y, color);
    } */

    public static float drawLeftStringWithShadow(DrawContext context, String text, int x, int y, int color) {
        return context.drawTextWithShadow(mc.textRenderer, text,(x - mc.textRenderer.getWidth(text)), y, color);
    }

   /* public static float drawLeftString(String text, float x, float y, int color) {
        return mc.fontRenderer.drawString(text, mc.fontRenderer.getStringWidth(text) - (int) x, (int) y, color);
    } */
}

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  /*  public static float drawCenteredStringWithShadowCustom(String text, float x, float y, int color) {
        return CousinWare.INSTANCE.fontRenderer.drawStringWithShadow(text, x - CousinWare.INSTANCE.fontRenderer.getStringWidth(text) / 2, y, color);
    }

    public static float drawCenteredStringCustom(String text, float x, float y, int color) {
        return CousinWare.INSTANCE.fontRenderer.drawString(text, (int) x - CousinWare.INSTANCE.fontRenderer.getStringWidth(text) / 2, (int) y, color);
    }

    public static float drawLeftStringWithShadowCustom(String text, float x, float y, int color) {
        return CousinWare.INSTANCE.fontRenderer.drawStringWithShadow(text, x - CousinWare.INSTANCE.fontRenderer.getStringWidth(text), y, color);
    }

    public static float drawLeftStringCustom(String text, float x, float y, int color) {
        return CousinWare.INSTANCE.fontRenderer.drawString(text, CousinWare.INSTANCE.fontRenderer.getStringWidth(text) - (int) x, (int) y, color);
    }

}
 */