/*
 * This file is part of the BleachHack distribution (https://github.com/BleachDev/BleachHack/).
 * Copyright (c) 2021 Bleach and contributors.
 *
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package com.cousinware.cwm.event;

import me.zero.alpine.event.CancellableEvent;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

import net.minecraft.util.Identifier;

public class RenderOverlayEvent extends CancellableEvent {

	private DrawContext context;
	
	public RenderOverlayEvent(DrawContext context, float tickDelta) {
		this.context = context;

	}

	public DrawContext getMatrices() {
		return context;
	}


}