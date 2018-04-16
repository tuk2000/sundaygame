package com.sunday.engine.databank;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.tool.ToolApplication;

public class PortImpl implements Port {
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
    public void addDataInstance(Data data) {
        if (owner instanceof Class) {
            ToolApplication.dataMonitor.newData(data, ((Class) owner).getSimpleName());
        } else {
            ToolApplication.dataMonitor.newData(data, owner.getClass().getSimpleName());
        }
        dataStorage.addDataInstance(this, data);
    }

    @Override
    public boolean containsDataInstance(Data data) {
        return dataStorage.getDataList(this).contains(data);
    }

    @Override
    public void removeDataInstance(Data data) {
        if (owner instanceof Class) {
            ToolApplication.dataMonitor.deleteData(data);
        } else {
            ToolApplication.dataMonitor.deleteData(data);
        }
        dataStorage.removeDataInstance(this, data);
    }

    @Override
    public void broadcast(Data data, Signal signal) {
        dataStorage.solve(data, signal);
    }

}
