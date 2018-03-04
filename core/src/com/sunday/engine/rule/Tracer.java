package com.sunday.engine.rule;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Target;

public abstract class Tracer implements Target {
    protected Condition condition;
    protected Data tracedData;

    public Tracer(Condition condition, Data data) {
        this.condition = condition;
        this.tracedData = data;
    }
}
