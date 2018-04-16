package com.sunday.engine.common.context;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class DataContext<D extends Data> implements Context {
    protected D data;
    protected Class dataClass;
    protected Signal signal;
    protected Map<Predicate, Consumer> predicateConsumerMap = new HashMap<>();

    protected DataContext(D data) {
        this.data = data;
        dataClass = data.getClass();
    }

    public D getData() {
        return data;
    }

    public Class<D> getDataClass() {
        return dataClass;
    }

    public Signal getSignal() {
        return signal;
    }

    public void setSignal(Signal signal) {
        this.signal = signal;
    }

    public void setPredicateConsumer(Predicate predicate, Consumer consumer) {
        predicateConsumerMap.put(predicate, consumer);
    }

    public abstract void evaluate();

}
