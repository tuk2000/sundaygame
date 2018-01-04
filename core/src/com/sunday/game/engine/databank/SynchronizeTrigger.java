package com.sunday.game.engine.databank;

public class SynchronizeTrigger {
    private SynchronizeCondition synchronizeCondition;
    private SynchronizeExecutor synchronizeExecutor;

    public SynchronizeCondition getSynchronizeCondition() {
        return synchronizeCondition;
    }

    protected SynchronizeTrigger(SynchronizeCondition synchronizeCondition, SynchronizeExecutor synchronizeExecutor) {
        this.synchronizeExecutor = synchronizeExecutor;
        this.synchronizeCondition = synchronizeCondition;
    }

    public void check() {
        if (synchronizeCondition.isTriggered()) {
            synchronizeExecutor.run();
        }
    }
}
