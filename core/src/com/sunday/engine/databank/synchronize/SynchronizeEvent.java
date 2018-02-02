package com.sunday.engine.databank.synchronize;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.DataOperation;

public class SynchronizeEvent<T extends Data> {
    public T source;
    public DataOperation dataOperation;

    public SynchronizeEvent(T t, DataOperation dataOperation) {
        source = t;
        this.dataOperation = dataOperation;
    }
}
