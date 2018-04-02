package com.sunday.engine.common;

public class DataContext<D extends Data> implements Context {
    private D data;
    private Signal signal;

    public DataContext(D data) {
        this.data = data;
    }

    public D getData() {
        return data;
    }

    public Signal getSignal() {
        return signal;
    }
}