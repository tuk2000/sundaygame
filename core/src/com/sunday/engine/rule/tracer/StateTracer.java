package com.sunday.engine.rule.tracer;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.rule.Condition;
import com.sunday.engine.rule.Tracer;

public class StateTracer extends Tracer {


    public StateTracer(Condition condition, Data data) {
        super(condition, data);
    }

    @Override
    public void notify(Signal signal) {

    }
}
