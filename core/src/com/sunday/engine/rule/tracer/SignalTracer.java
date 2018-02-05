package com.sunday.engine.rule.tracer;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.rule.Rule;
import com.sunday.engine.rule.Tracer;

public class SignalTracer extends Tracer {
    private Signal signal;

    public SignalTracer(Rule rule, Data data, Signal signal) {
        super(rule, data);
        this.signal = signal;
    }

    private Signal tracedSignal() {
        return signal;
    }

    @Override
    public void notify(Signal signal) {
        if (this.signal.equals(signal)) {
            rule.check();
        }
    }
}
