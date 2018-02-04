package com.sunday.engine.databank;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.DataSignal;
import com.sunday.engine.databank.synchronize.SynchronizeManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStorage<T extends Data> {
    private Map<SynchronizePort, Map<Class<T>, List<T>>> portListMap = new HashMap<>();
    private Map<Class<T>, List<T>> dataClassInstancesMap = new HashMap<>();

    private SynchronizeManager synchronizeManager;

    public DataStorage(SynchronizeManager synchronizeManager) {
        this.synchronizeManager = synchronizeManager;
    }

    public List<Class<T>> getDataClasses() {
        List<Class<T>> result = new ArrayList<>();
        dataClassInstancesMap.keySet().forEach(clazz -> result.add(clazz));
        return result;
    }

    public List<T> getInstances(Class<T> clazz) {
        return dataClassInstancesMap.get(clazz);
    }

    public void addDataInstance(SynchronizePort synchronizePort, T t) {
        Class clazz = t.getClass();
        Map<Class<T>, List<T>> classListMap;
        addPort(synchronizePort);
        classListMap = portListMap.get(synchronizePort);

        if (classListMap.containsKey(clazz)) {
            classListMap.get(clazz).add(t);
        } else {
            List<T> list = new ArrayList<>();
            list.add(t);
            classListMap.put(clazz, list);
        }

        List<T> list;
        if (dataClassInstancesMap.keySet().contains(clazz)) {
            list = dataClassInstancesMap.get(clazz);
        } else {
            list = new ArrayList<>();
            dataClassInstancesMap.put(clazz, list);
        }
        list.add(t);

        synchronizeManager.synchronize(t, DataSignal.Add);
    }

    public void deleteDataInstance(SynchronizePort synchronizePort, T t) {
        Class clazz = t.getClass();
        List<T> list;
        Map<Class<T>, List<T>> classListMap;
        if (portListMap.containsKey(synchronizePort)) {
            classListMap = portListMap.get(synchronizePort);
            if (classListMap.containsKey(clazz)) {
                list = classListMap.get(clazz);
                list.remove(t);
                if (list.isEmpty()) {
                    classListMap.remove(clazz);
                }
            }
        }

        if (dataClassInstancesMap.keySet().contains(clazz)) {
            list = dataClassInstancesMap.get(clazz);
            list.remove(t);
            if (list.isEmpty()) {
                dataClassInstancesMap.remove(clazz);
            }
        }
        synchronizeManager.synchronize(t, DataSignal.Deletion);
    }

    public List<T> getDataList(SynchronizePort synchronizePort) {
        List<T> result = new ArrayList<>();
        portListMap.get(synchronizePort).values().forEach(list -> result.addAll(list));
        return result;
    }

    public <T extends Data> List<Class<T>> getDataClassList(SynchronizePort synchronizePort) {
        List<Class<T>> result = new ArrayList<>();
        portListMap.get(synchronizePort).keySet().forEach(e -> result.add((Class<T>) e));
        return result;
    }

    public void addPort(SynchronizePort synchronizePort) {
        Map<Class<T>, List<T>> classListMap;
        if (!portListMap.containsKey(synchronizePort)) {
            classListMap = new HashMap<>();
            portListMap.put(synchronizePort, classListMap);
        }
    }

    public void removePort(SynchronizePort synchronizePort) {
        Map<Class<T>, List<T>> classListMap;
        if (portListMap.containsKey(synchronizePort)) {
            classListMap = portListMap.get(synchronizePort);
            classListMap.keySet().forEach(clazz -> {
                classListMap.get(clazz).forEach(data -> {
                    dataClassInstancesMap.get(clazz).remove(data);
                    synchronizeManager.synchronize(data, DataSignal.Deletion);
                });
                classListMap.clear();
            });
            portListMap.remove(synchronizePort);
        }
    }
}
