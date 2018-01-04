package com.sunday.game.engine.databank;

import com.sunday.game.engine.common.Data;

import java.util.List;

public class DataUserPortImpl<T extends Data> implements DataUserPort<T> {
    private DataClassStorage classStorage;
    private DataSynchronize dataSynchronize;

    public DataUserPortImpl(DataClassStorage classStorage, DataSynchronize dataSynchronize) {
        this.classStorage = classStorage;
        this.dataSynchronize = dataSynchronize;
    }

    @Override
    public List<T> getInstances(Class<T> clazz) {
        return classStorage.getInstances(clazz);
    }

    @Override
    public void requestSynchronize(T t) {
        dataSynchronize.synchronize();
    }

    @Override
    public void registerDataEventListener(Class<T> clazz,DataEventListener<T> dataEventListener) {
        DataMonitor<T> dataMonitor = classStorage.getMonitor(clazz);
        dataMonitor.registerListener(dataEventListener);
    }
}
