package com.cousinware.cwm.hack.movement;

import com.cousinware.cwm.event.PacketEvent;
import com.cousinware.cwm.hack.Hack;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInputC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdatePlayerAbilitiesC2SPacket;

public class FastSwim extends Hack {

    public FastSwim() {
        super("FastSwim", Category.MOVEMENT, 4264952);
    }
    int delay = 0;

    public void onUpdate() {
        if (mc.player.isTouchingWater()) {
            delay++;
            mc.player.setSprinting(true);
            if (delay > 20) {
                //mc.player.setSwimming(true);
            }
        }else {
            delay = 0;
        }

    }


}
