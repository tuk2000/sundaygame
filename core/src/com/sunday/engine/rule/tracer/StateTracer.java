package com.sunday.engine.rule.tracer;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.rule.Rule;
import com.sunday.engine.rule.Tracer;

public class StateTracer extends Tracer {
    public StateTracer(Rule rule, Data data) {
        super(rule, data);
    }

    @Override
    public void notify(Signal signal) {

    }
}
