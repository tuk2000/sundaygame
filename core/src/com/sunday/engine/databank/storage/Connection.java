package com.sunday.engine.databank.storage;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Target;

public class Connection<T extends Data> {
    public T source;
    public Target target;

    public Connection(T source, Target target) {
        this.source = source;
        this.target = target;
    }
}
