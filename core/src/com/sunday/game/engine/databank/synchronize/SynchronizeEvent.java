package com.sunday.game.engine.databank.synchronize;

import com.sunday.game.engine.common.Data;
import com.sunday.game.engine.common.DataOperation;

public class SynchronizeEvent<T extends Data> {
    public T source;
    public DataOperation dataOperation;

    public SynchronizeEvent(T t, DataOperation dataOperation) {
        source = t;
        this.dataOperation = dataOperation;
    }
}
