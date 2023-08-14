package com.cousinware.cwm.event;


import net.minecraft.network.Packet;

public class PacketEvent extends EventCancellable {

    /**
     * @author zeroeightsix/kami
     * Fixed by Ace for 1.19+
     */

    private final Packet packet;

    public PacketEvent(Packet packet) {
        this.packet = packet;
    }

    public Packet getPacket() {
        return packet;
    }

    public static class Receive extends PacketEvent {
        public Receive(Packet packet) {
            super(packet);
        }
    }

    public static class Send extends PacketEvent {
        public Send(Packet packet) {
            super(packet);
        }
    }
}