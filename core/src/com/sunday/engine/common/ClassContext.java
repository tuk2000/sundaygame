package com.sunday.engine.common;

public class ClassContext<D extends Data> implements Context {
    private Class<D> sensedClass;
    private D instance;
    private Signal signal;

    public ClassContext(Class<D> sensedClass) {
        this.sensedClass = sensedClass;
    }

    public Class<D> getSensedClass() {
        return sensedClass;
    }

    public D getInstance() {
        return instance;
    }

    public Signal getSignal() {
        return signal;
    }

    public void setSensed(D instance, Signal signal) {
        this.instance = instance;
        this.signal = signal;
    }
}
