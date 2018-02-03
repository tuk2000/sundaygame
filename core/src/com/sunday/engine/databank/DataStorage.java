package com.sunday.engine.databank;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.DataOperation;
import com.sunday.engine.databank.synchronize.SynchronizeManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStorage<T extends Data> {
    private Map<SynchronizePort, List<T>> portListMap = new HashMap<>();
    private Map<Class<T>, List<T>> dataClassInstancesMap = new HashMap<>();

    private SynchronizeManager synchronizeManager;

    public DataStorage(SynchronizeManager synchronizeManager) {
        this.synchronizeManager = synchronizeManager;
    }

    public List<T> getInstances(Class<T> clazz) {
        return dataClassInstancesMap.get(clazz);
    }

    public void addDataInstance(SynchronizePort synchronizePort, T t) {
        Class clazz = t.getClass();
        List<T> list;
        if (portListMap.containsKey(synchronizePort)) {
            list = portListMap.get(synchronizePort);
        } else {
            list = new ArrayList<>();
            portListMap.put(synchronizePort, list);
        }
        list.add(t);

        if (dataClassInstancesMap.keySet().contains(clazz)) {
            list = dataClassInstancesMap.get(clazz);
        } else {
            list = new ArrayList<>();
            dataClassInstancesMap.put(clazz, list);
        }
        list.add(t);

        synchronizeManager.synchronize(t, DataOperation.Add);
    }

    public void deleteDataInstance(SynchronizePort synchronizePort, T t) {
        Class clazz = t.getClass();
        List<T> list;
        if (portListMap.containsKey(synchronizePort)) {
            list = portListMap.get(synchronizePort);
            list.remove(t);
        }

        if (dataClassInstancesMap.keySet().contains(clazz)) {
            list = dataClassInstancesMap.get(clazz);
            list.remove(t);
        }
        synchronizeManager.synchronize(t, DataOperation.Deletion);
    }
}
