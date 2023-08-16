package com.cousinware.cwm.event;

public class MouseEvent {

    long window;
    int button;
    int action;
    int mods;

    public MouseEvent(long window, int button, int action, int mods) {
        this.window = window;
        this.button = button;
        this.action = action;
        this.mods = mods;
    }

    public int getButton() {
        return this.button;
    }

    public int getAction() {
        return this.action;
    }
}
