package com.sunday.game.engine.databank;

import com.sunday.game.engine.common.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataClassStorage<T extends Data> {
    private Map<Class, ArrayList<T>> classListMap = new HashMap<>();
    private Map<Class, DataMonitor> classMonitorsMap = new HashMap<>();

    public void addDataInstance(Data data) {
        if (classListMap.containsKey(data.getClass())) {
            classListMap.get(data.getClass()).add((T) data);
        } else {
            Class c = data.getClass();
            ArrayList arrayList = new ArrayList();
            arrayList.add(data);
            classListMap.put(c, arrayList);
            classMonitorsMap.put(c, new DataMonitor<>());
        }
    }

    public void deleteDataInstance(Data data) {
        if (classListMap.containsKey(data.getClass())) {
            classListMap.get(data.getClass()).remove(data);
            if (classListMap.get(data.getClass()).size() == 0) {
                classMonitorsMap.remove(data.getClass());
            }
        }
    }


    public List<T> getInstances(Class<T> clazz) {
        if (classListMap.containsKey(clazz)) {
            return classListMap.get(clazz);
        } else {
            return new ArrayList<>();
        }
    }

    public DataMonitor<T> getMonitor(Class<T> clazz) {
        if (classMonitorsMap.containsKey(clazz)) {
            return classMonitorsMap.get(clazz);
        } else {
            return null;
        }
    }
}
