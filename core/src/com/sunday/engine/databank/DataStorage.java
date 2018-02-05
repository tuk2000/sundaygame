package com.sunday.engine.databank;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.DataSignal;
import com.sunday.engine.common.Signal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DataStorage<T extends Data> {
    private Map<Port, Map<Class<T>, List<T>>> portListMap = new HashMap<>();
    private Map<Class<T>, List<T>> dataClassInstancesMap = new HashMap<>();

    protected DataStorage() {

    }

    protected List<Class<T>> getDataClasses() {
        List<Class<T>> result = new ArrayList<>();
        result.addAll( dataClassInstancesMap.keySet());
        return result;
    }

    protected List<T> getInstances(Class<T> clazz) {
        List<T> result = new ArrayList<>();
        result.addAll(dataClassInstancesMap.get(clazz));
        return result;
    }

    protected void addDataInstance(Port port, T t) {
        Class clazz = t.getClass();
        Map<Class<T>, List<T>> classListMap;
        addPort(port);
        classListMap = portListMap.get(port);

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

        if (!(t instanceof Connection)) {
            solve(t, DataSignal.Add);
        }

    }

    protected void solve(T t, Signal signal) {
        getInstances((Class<T>) Connection.class).stream().filter(c -> ((Connection) c).source.equals(t))
                .map(c -> ((Connection) c).target)
                .forEach(target -> target.notify(signal));
    }

    protected void deleteDataInstance(Port port, T t) {
        Class clazz = t.getClass();
        List<T> list;
        Map<Class<T>, List<T>> classListMap;
        if (portListMap.containsKey(port)) {
            classListMap = portListMap.get(port);
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
        if (!(t instanceof Connection)) {
            solve(t, DataSignal.Deletion);
        }
    }

    protected List<T> getDataList(Port port) {
        List<T> result = new ArrayList<>();
        portListMap.get(port).values().forEach(list -> result.addAll(list));
        return result;
    }

    protected <T extends Data> List<Class<T>> getDataClassList(Port port) {
        List<Class<T>> result = new ArrayList<>();
        portListMap.get(port).keySet().forEach(e -> result.add((Class<T>) e));
        return result;
    }

    protected void addPort(Port port) {
        Map<Class<T>, List<T>> classListMap;
        if (!portListMap.containsKey(port)) {
            classListMap = new HashMap<>();
            portListMap.put(port, classListMap);
        }
    }

    protected void removePort(Port port) {
        Map<Class<T>, List<T>> classListMap;
        if (portListMap.containsKey(port)) {
            classListMap = portListMap.get(port);
            classListMap.keySet().forEach(clazz -> {
                classListMap.get(clazz).forEach(data -> {
                    dataClassInstancesMap.get(clazz).remove(data);
                    if (!(data instanceof Connection)) {
                        solve(data, DataSignal.Deletion);
                    }
                });
                classListMap.clear();
            });
            portListMap.remove(port);
        }
    }
}
