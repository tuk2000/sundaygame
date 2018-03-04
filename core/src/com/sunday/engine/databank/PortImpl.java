package com.sunday.engine.databank;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.tool.ToolApplication;

public class PortImpl<T extends Data> implements Port<T> {
    protected DataStorage dataStorage;
    protected Object owner;

    public PortImpl(Object owner, DataStorage dataStorage) {
        this.owner = owner;
        this.dataStorage = dataStorage;
    }

    @Override
    public Object getOwner() {
        return owner;
    }

    @Override
    public void addDataInstance(T t) {
        if (owner instanceof Class) {
            ToolApplication.dataMonitor.newData(t, ((Class) owner).getSimpleName());
        } else {
            ToolApplication.dataMonitor.newData(t, owner.getClass().getSimpleName());
        }
        dataStorage.addDataInstance(this, t);
    }

    @Override
    public boolean containsDataInstance(T t) {
        return dataStorage.getDataList(this).contains(t);
    }

    @Override
    public void deleteDataInstance(T t) {
        if (owner instanceof Class) {
            ToolApplication.dataMonitor.deleteData(t);
        } else {
            ToolApplication.dataMonitor.deleteData(t);
        }
        dataStorage.deleteDataInstance(this, t);
    }

    @Override
    public void broadcast(T t, Signal signal) {
        dataStorage.solve(t, signal);
    }

}
