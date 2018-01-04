package com.sunday.game.engine.databank;

import com.sunday.game.engine.common.Data;

public class DataHolderPortImpl implements DataHolderPort {

    private DataClassStorage dataClassStorage;
    private DataSynchronize dataSynchronize;

    public DataHolderPortImpl(DataClassStorage dataClassStorage, DataSynchronize dataSynchronize) {
        this.dataClassStorage = dataClassStorage;
        this.dataSynchronize = dataSynchronize;
    }

    @Override
    public void addDataSynchronize(SynchronizeCondition synchronizeCondition, SynchronizeExecutor synchronizeExecutor) {
        dataSynchronize.addTrigger(new SynchronizeTrigger(synchronizeCondition, synchronizeExecutor));
    }

    @Override
    public void deleteDataSynchronize(SynchronizeCondition synchronizeCondition) {
        SynchronizeTrigger trigger = dataSynchronize.search(synchronizeCondition);
        if (trigger != null) {
            dataSynchronize.removeTrigger(trigger);
        }
    }

    @Override
    public void addDataInstance(Data data) {
        dataClassStorage.addDataInstance(data);
    }

    @Override
    public void deleteDataInstance(Data data) {
        dataClassStorage.deleteDataInstance(data);
    }
}
