package com.cousinware.cwm.event;


public class KeyPressEvent extends EventCancellable {

    public int key;

    public int scanCode;

    public int action;

    public int modifiers;

    public KeyPressEvent(int key, int scanCode, int action, int modifiers) {
        this.key = key;
        this.scanCode = scanCode;
        this.action = action;
        this.modifiers = modifiers;
    }

    public int getKey() {
        return this.key;
    }

    public int getScanCode() {
        return this.scanCode;
    }

    public int getAction() {
        return this.action;
    }

    public int getModifiers() {
        return this.modifiers;
    }

}
