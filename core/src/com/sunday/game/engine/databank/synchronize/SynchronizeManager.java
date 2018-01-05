package com.sunday.game.engine.databank.synchronize;

import com.sunday.game.engine.common.Data;
import com.sunday.game.engine.common.DataOperation;

import java.util.ArrayList;
import java.util.List;

public class SynchronizeManager {
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

    public void synchronize(Data data, DataOperation dataOperation) {
        SynchronizeEvent synchronizeEvent = new SynchronizeEvent(data, dataOperation);
        for (SynchronizeTrigger trigger : synchronizeTriggers) {
            trigger.check(synchronizeEvent);
        }
    }
}
