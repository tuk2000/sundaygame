package com.sunday.game.engine.databank;

import java.util.ArrayList;
import java.util.List;

public class DataSynchronize {
    private List<SynchronizeTrigger> synchronizeTriggers = new ArrayList<>();

    public void addTrigger(SynchronizeTrigger synchronizeTrigger) {
        synchronizeTriggers.add(synchronizeTrigger);
    }

    public SynchronizeTrigger search(SynchronizeCondition synchronizeCondition) {
        for (SynchronizeTrigger trigger : synchronizeTriggers) {
            if (trigger.getSynchronizeCondition().equals(synchronizeCondition)) {
                return trigger;
            }
        }
        return null;
    }

    public void removeTrigger(SynchronizeTrigger synchronizeTrigger) {
        for (SynchronizeTrigger trigger : synchronizeTriggers) {
            if (trigger.equals(synchronizeTrigger)) {
                synchronizeTriggers.remove(synchronizeTrigger);
                break;
            }
        }
    }

    public void synchronize() {
        synchronizeTriggers.forEach(SynchronizeTrigger::check);
    }
}
