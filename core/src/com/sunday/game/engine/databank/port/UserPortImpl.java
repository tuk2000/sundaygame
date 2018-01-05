package com.sunday.game.engine.databank.port;

import com.sunday.game.engine.common.Data;
import com.sunday.game.engine.common.DataOperation;
import com.sunday.game.engine.databank.DataStorage;
import com.sunday.game.engine.databank.synchronize.SynchronizeCondition;
import com.sunday.game.engine.databank.synchronize.SynchronizeExecutor;
import com.sunday.game.engine.databank.synchronize.SynchronizeManager;
import com.sunday.game.engine.databank.synchronize.SynchronizeTrigger;

import java.util.List;

public class UserPortImpl<T extends Data> implements UserPort<T> {
    private DataStorage dataStorage;
    private SynchronizeManager synchronizeManager;
    private DataUser<T> dataUser;

    public UserPortImpl(DataUser<T> dataUser, DataStorage dataStorage, SynchronizeManager synchronizeManager) {
        this.dataUser = dataUser;
        this.dataStorage = dataStorage;
        this.synchronizeManager = synchronizeManager;
    }

    @Override
    public List<T> getInstances(Class<T> clazz) {
        return dataStorage.getInstances(clazz);
    }

    @Override
    public DataHolder getDataHolder(T t) {
        return dataStorage.getDataHolder(t);
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
}
