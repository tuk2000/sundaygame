package com.sunday.engine.databank.ports;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.DataSignal;
import com.sunday.engine.databank.DataStorage;
import com.sunday.engine.databank.synchronize.SynchronizeCondition;
import com.sunday.engine.databank.synchronize.SynchronizeExecutor;
import com.sunday.engine.databank.synchronize.SynchronizeManager;
import com.sunday.engine.databank.synchronize.SynchronizeTrigger;

public class UserPortImpl<T extends Data> implements UserPort<T> {
    private DataStorage dataStorage;
    private SynchronizeManager synchronizeManager;

    public UserPortImpl(DataStorage dataStorage, SynchronizeManager synchronizeManager) {
        this.dataStorage = dataStorage;
        this.synchronizeManager = synchronizeManager;
    }

    @Override
    public void addDataInstance(T t) {
        dataStorage.addDataInstance(this, t);
    }

    @Override
    public void deleteDataInstance(T t) {
        dataStorage.deleteDataInstance(this, t);
    }

    @Override
    public void synchronize(Data data, DataSignal dataSignal) {
        synchronizeManager.synchronize(data, dataSignal);
    }

    @Override
    public void addDataSynchronize(SynchronizeCondition synchronizeCondition, SynchronizeExecutor synchronizeExecutor) {
        synchronizeManager.addTrigger(new SynchronizeTrigger(synchronizeCondition, synchronizeExecutor));
    }

    @Override
    public void deleteDataSynchronize(SynchronizeCondition synchronizeCondition) {
        SynchronizeTrigger trigger = synchronizeManager.search(synchronizeCondition);
        if (trigger != null) {
            synchronizeManager.removeTrigger(trigger);
        }
    }
}
