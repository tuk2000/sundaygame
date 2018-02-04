package com.sunday.engine.databank.synchronize;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.DataSignal;
import com.sunday.engine.common.Signal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SynchronizeCondition<T extends Data> {
    private Class<T> clazz;
    private T source = null;
    private List<DataSignal> dataSignalList = new ArrayList<>();
    private List<Signal> signals = new ArrayList<>();

    public SynchronizeCondition(Class<T> clazz, DataSignal... dataSignals) {
        this.clazz = clazz;
        source = null;
        dataSignalList.addAll(Arrays.asList(dataSignals));
    }

    public SynchronizeCondition(T t, DataSignal... dataSignals) {
        clazz = (Class<T>) t.getClass();
        source = t;
        dataSignalList.addAll(Arrays.asList(dataSignals));
    }

    public SynchronizeCondition(T t, Signal signal) {
        clazz = (Class<T>) t.getClass();
        source = t;

    }

    boolean isTriggered(SynchronizeEvent<T> synchronizeEvent) {
        if (synchronizeEvent.source.equals(source) || synchronizeEvent.source.getClass().equals(clazz)) {
            if (dataSignalList.contains(synchronizeEvent.dataSignal))
                return true;
        }
        return false;
    }
}
