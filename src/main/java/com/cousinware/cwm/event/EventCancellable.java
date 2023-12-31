package com.cousinware.cwm.event;

/**
 * Author Seth
 * 4/6/2019 @ 1:27 AM.
 * Fixed by Ace for 1.19+
 */

public class EventCancellable extends EventStageable {

    private boolean canceled;

    public EventCancellable() {
    }

    public EventCancellable(EventStage stage) {
        super(stage);
    }

    public EventCancellable(EventStage stage, boolean canceled) {
        super(stage);
        this.canceled = canceled;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
}