package com.sunday.game.engine.databank.synchronize;


public class SynchronizeTrigger {
    private SynchronizeCondition synchronizeCondition;
    private SynchronizeExecutor synchronizeExecutor;

    public SynchronizeCondition getSynchronizeCondition() {
        return synchronizeCondition;
    }

    public SynchronizeTrigger(SynchronizeCondition synchronizeCondition, SynchronizeExecutor synchronizeExecutor) {
        this.synchronizeExecutor = synchronizeExecutor;
        this.synchronizeCondition = synchronizeCondition;
    }

    public void check(SynchronizeEvent synchronizeEvent) {
        if (synchronizeCondition.isTriggered(synchronizeEvent)) {
            synchronizeExecutor.execute(synchronizeEvent);
        }
    }
}
