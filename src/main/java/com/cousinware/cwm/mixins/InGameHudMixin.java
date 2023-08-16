package com.cousinware.cwm.mixins;

import com.cousinware.cwm.client.CwmClient;
import com.cousinware.cwm.command.Command;
import com.cousinware.cwm.event.RenderOverlayEvent;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void renderOverlay(DrawContext context, float tickDelta, CallbackInfo ci) {
            RenderOverlayEvent event = new RenderOverlayEvent(context, tickDelta);
            CwmClient.EVENT_BUS.post(event);

            if (ci.isCancellable()) {
                //ci.cancel();
        }
    }

}
