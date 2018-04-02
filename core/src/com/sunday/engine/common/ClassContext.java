package com.sunday.engine.common;

public class ClassContext<D extends Data> implements Context, Target {
    public Class<D> sensedClass;
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

    @Override
    public void notify(Data data, Signal signal) {
        this.instance = (D) data;
        this.signal = signal;
    }
}
