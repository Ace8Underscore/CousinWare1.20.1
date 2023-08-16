package com.cousinware.cwm.event;

import net.minecraft.client.util.math.MatrixStack;

public class WorldRenderEvent extends EventCancellable {

	protected float partialTicks;
	protected MatrixStack matrices;
	
	public static class Pre extends WorldRenderEvent {

		public Pre(float partialTicks, MatrixStack matrices) {
			this.partialTicks = partialTicks;
			this.matrices = matrices;
		}
		
	}
	
	public static class Post extends WorldRenderEvent {

		public Post(float partialTicks, MatrixStack matrices) {
			this.partialTicks = partialTicks;
			this.matrices = matrices;
		}
		
	}

	public float getPartialTicks() {
		return partialTicks;
	}
	
	public MatrixStack getMatrices() {
		return matrices;
	}
}