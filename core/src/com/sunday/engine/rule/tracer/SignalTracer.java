package com.sunday.engine.rule.tracer;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.rule.Condition;
import com.sunday.engine.rule.Tracer;

public class SignalTracer extends Tracer {
    private Signal signal;
    private Signal lastSignal=null;

    public SignalTracer(Condition condition, Data data, Signal signal) {
        super(condition, data);
        this.signal = signal;
    }

    private Signal tracedSignal() {
        return signal;
    }

    public boolean isTracedSignal() {
        return signal.equals(lastSignal);
    }

    @Override
    public void notify(Signal signal) {
        lastSignal=signal;
        if (isTracedSignal()) {
            condition.check();
        }
    }
}
