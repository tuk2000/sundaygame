package com.sunday.engine.databank;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.DataSignal;
import com.sunday.engine.common.Signal;

import java.util.*;

class DataStorage<T extends Data> {
    private Map<Port, Map<Class<T>, List<T>>> portListMap = new HashMap<>();
    private Map<Class<T>, List<T>> dataClassInstancesMap = new HashMap<>();

    protected DataStorage() {

    }

    protected List<Class<T>> getDataClasses() {
        List<Class<T>> result = new ArrayList<>();
        Set<Class<T>> classes = dataClassInstancesMap.keySet();
        if (classes != null)
            result.addAll(classes);
        return result;
    }

    protected List<T> getInstances(Class<T> clazz) {
        List<T> result = new ArrayList<>();
        List<T> classInstances = dataClassInstancesMap.get(clazz);
        if (classInstances != null)
            result.addAll(classInstances);
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

        solve(t, DataSignal.Add);

    }

    protected void solve(T t, Signal signal) {
        getInstances((Class<T>) Connection.class).stream().filter(c -> {
            Connection connection = (Connection) c;
            boolean isSensor = connection.source instanceof ClassSensor;
            ClassSensor classSensor;
            boolean isSensorClass = false;
            if (isSensor) {
                classSensor = connection.source instanceof ClassSensor ? (ClassSensor) connection.source : null;
                isSensorClass = t.getClass().equals(classSensor.getSensedClass());
                if (isSensorClass) {
                    classSensor.setSensedInstance(t);
                }
            }
            return connection.source.equals(t) || isSensor & isSensorClass;
        })
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
        solve(t, DataSignal.Deletion);
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
                    solve(data, DataSignal.Deletion);
                });
                classListMap.clear();
            });
            portListMap.remove(port);
        }
    }
}
