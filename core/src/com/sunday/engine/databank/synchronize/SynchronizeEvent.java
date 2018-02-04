package com.sunday.engine.databank.synchronize;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.DataSignal;

public class SynchronizeEvent<T extends Data> {
    public T source;
    public DataSignal dataSignal;

    public SynchronizeEvent(T t, DataSignal dataSignal) {
        source = t;
        this.dataSignal = dataSignal;
    }
}
