package com.sunday.engine.databank.ports;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.DataOperation;
import com.sunday.engine.databank.DataStorage;
import com.sunday.engine.databank.synchronize.SynchronizeCondition;
import com.sunday.engine.databank.synchronize.SynchronizeExecutor;
import com.sunday.engine.databank.synchronize.SynchronizeManager;
import com.sunday.engine.databank.synchronize.SynchronizeTrigger;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SystemPortImpl<T extends Data> implements SystemPort<T> {
    private DataStorage dataStorage;
    private SynchronizeManager synchronizeManager;

    public SystemPortImpl(DataStorage dataStorage, SynchronizeManager synchronizeManager) {
        this.dataStorage = dataStorage;
        this.synchronizeManager = synchronizeManager;
    }

    @Override
    public void addDataInstance(T t) {
        if (!containsDataInstance(t))
            dataStorage.addDataInstance(this, t);
    }

    @Override
    public boolean containsDataInstance(T t) {
        return dataStorage.getDataList(this).contains(t);
    }

    @Override
    public void deleteDataInstance(T t) {
        dataStorage.deleteDataInstance(this, t);
    }

    @Override
    public List<T> getDataList(Predicate<T> predicate) {
        return (List<T>) dataStorage.getDataList(this).stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public List<Class<T>> getDataClassList() {
        return dataStorage.getDataClassList(this);
    }

    @Override
    public void synchronize(T data, DataOperation dataOperation) {
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
