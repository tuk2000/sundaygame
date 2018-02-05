package com.sunday.engine.rule;

import com.sunday.engine.common.Data;
import com.sunday.engine.databank.Target;

public abstract class Tracer implements Target {
    protected Rule rule;
    protected Data tracedData;

    public Tracer(Rule rule, Data data) {
        this.rule = rule;
        this.tracedData = data;
    }
}
