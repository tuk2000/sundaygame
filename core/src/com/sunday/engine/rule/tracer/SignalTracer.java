package com.sunday.engine.rule.tracer;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.rule.Tracer;

public class SignalTracer extends Tracer {
    private Signal signal;

    public SignalTracer(Data data, Signal signal) {
        super(data);
        this.signal = signal;
    }

    private Signal tracedSignal() {
        return signal;
    }

}
