package com.sunday.engine.databank.port;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.DataOperation;
import com.sunday.engine.databank.DataStorage;
import com.sunday.engine.databank.synchronize.SynchronizeCondition;
import com.sunday.engine.databank.synchronize.SynchronizeExecutor;
import com.sunday.engine.databank.synchronize.SynchronizeManager;
import com.sunday.engine.databank.synchronize.SynchronizeTrigger;


public class HolderPortImpl implements HolderPort {

    private DataStorage dataStorage;
    private SynchronizeManager synchronizeManager;
    private DataHolder holder;


    public HolderPortImpl(DataHolder holder, DataStorage dataStorage, SynchronizeManager synchronizeManager) {
        this.holder = holder;
        this.dataStorage = dataStorage;
        this.synchronizeManager = synchronizeManager;
    }


    @Override
    public void synchronize(Data data, DataOperation dataOperation) {
        synchronizeManager.synchronize(data, dataOperation);
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

    @Override
    public void addDataInstance(Data data) {
        dataStorage.addDataInstance(holder, data);
    }

    @Override
    public void deleteDataInstance(Data data) {
        dataStorage.deleteDataInstance(holder, data);
    }
}
