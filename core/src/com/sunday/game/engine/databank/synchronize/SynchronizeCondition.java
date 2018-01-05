package com.sunday.game.engine.databank.synchronize;

import com.sunday.game.engine.common.Data;
import com.sunday.game.engine.common.DataOperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SynchronizeCondition<T extends Data> {
    private Class<T> clazz;
    private T source = null;
    private List<DataOperation> dataOperationList = new ArrayList<>();

    public SynchronizeCondition(Class<T> clazz, DataOperation... dataOperations) {
        this.clazz = clazz;
        source = null;
        dataOperationList.addAll(Arrays.asList(dataOperations));
    }

    public SynchronizeCondition(T t, DataOperation... dataOperations) {
        clazz = (Class<T>) t.getClass();
        source = t;
        dataOperationList.addAll(Arrays.asList(dataOperations));
    }

    boolean isTriggered(SynchronizeEvent<T> synchronizeEvent) {
        if (synchronizeEvent.source.equals(source) || synchronizeEvent.source.getClass().equals(clazz)) {
            if (dataOperationList.contains(synchronizeEvent.dataOperation))
                return true;
        }
        return false;
    }
}
