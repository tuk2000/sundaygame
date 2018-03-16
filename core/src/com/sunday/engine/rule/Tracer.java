package com.sunday.engine.rule;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.common.Target;

public class Tracer implements Target {
    private Condition condition;
    private Data tracedData;
    private Signal tracedSignal;
    private Signal lastSignal = null;

    public Tracer(Condition condition, Data data, Signal tracedSignal) {
        this.condition = condition;
        this.tracedData = data;
        this.tracedSignal = tracedSignal;
    }

    public Condition getCondition() {
        return condition;
    }

    public Data getTracedData() {
        return tracedData;
    }

    private Signal getTracedSignal() {
        return tracedSignal;
    }

    @Override
    public void notify(Signal signal) {
        lastSignal = signal;
        if (tracedSignal.equals(signal)) {
            condition.check();
        }
    }
}
