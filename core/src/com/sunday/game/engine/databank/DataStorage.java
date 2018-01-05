package com.sunday.game.engine.databank;

import com.sunday.game.engine.common.Data;
import com.sunday.game.engine.common.DataOperation;
import com.sunday.game.engine.databank.port.DataHolder;
import com.sunday.game.engine.databank.synchronize.SynchronizeManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStorage<T extends Data> {
    private Map<DataHolder, List<T>> dataHolderListMap = new HashMap<>();
    private Map<Class<T>, List<T>> dataClassInstancesMap = new HashMap<>();

    private SynchronizeManager synchronizeManager;

    public DataStorage(SynchronizeManager synchronizeManager) {
        this.synchronizeManager = synchronizeManager;
    }

    public List<T> getInstances(Class<T> clazz) {
        return dataClassInstancesMap.get(clazz);
    }

    public void addDataInstance(DataHolder holder, T t) {
        Class clazz = t.getClass();
        List<T> list;
        if (dataHolderListMap.containsKey(holder)) {
            list = dataHolderListMap.get(holder);
        } else {
            list = new ArrayList<>();
            dataHolderListMap.put(holder, list);
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

    public void deleteDataInstance(DataHolder holder, T t) {
        Class clazz = t.getClass();
        List<T> list;
        if (dataHolderListMap.containsKey(holder)) {
            list = dataHolderListMap.get(holder);
            list.remove(t);
        }

        if (dataClassInstancesMap.keySet().contains(clazz)) {
            list = dataClassInstancesMap.get(clazz);
            list.remove(t);
        }
        synchronizeManager.synchronize(t, DataOperation.Deletion);
    }

    public DataHolder getDataHolder(T t) {
        for (DataHolder dataHolder : dataHolderListMap.keySet()) {
            if (dataHolderListMap.get(dataHolder).contains(t)) {
                return dataHolder;
            }
        }
        return null;
    }
}
