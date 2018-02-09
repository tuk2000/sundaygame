package com.sunday.engine.databank;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;

public class PortImpl<T extends Data> implements Port<T> {
    private DataStorage dataStorage;

    public PortImpl(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    @Override
    public void addDataInstance(T t) {
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
    public void broadcast(T t, Signal signal) {
        dataStorage.solve(t, signal);
    }
}
