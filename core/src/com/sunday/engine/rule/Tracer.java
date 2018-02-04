package com.sunday.engine.rule;

import com.sunday.engine.common.Data;

public abstract class Tracer implements Data {
    protected Data tracedData;

    public Tracer(Data data) {
        tracedData = data;
    }
}
