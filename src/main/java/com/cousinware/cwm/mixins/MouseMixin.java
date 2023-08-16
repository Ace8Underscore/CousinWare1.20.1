package com.cousinware.cwm.mixins;

import com.cousinware.cwm.client.CwmClient;
import com.cousinware.cwm.event.MouseEvent;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin {

    @Inject(at = @At("HEAD"), method = "onMouseButton")
    private void onMouseButton(long window, int button, int action, int mods, CallbackInfo ci) {

        MouseEvent event = new MouseEvent(window, button, action, mods);
        CwmClient.EVENT_BUS.post(event);

    }
}
