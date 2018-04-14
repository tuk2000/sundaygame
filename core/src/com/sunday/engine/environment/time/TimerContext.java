package com.sunday.engine.environment.time;

import com.sunday.engine.environment.EnvironmentDataContext;

public class TimerContext<T extends Timer> extends EnvironmentDataContext<T> {
    public TimerContext(T timer) {
        super(timer);
    }

    public void evaluate(float currentTime) {
        setSignal(TimerSignal.None);
        if (getData().isTriggered(currentTime)) {
            setSignal(TimerSignal.Triggered);
        }
//        System.out.println(getData().getInfo());
        evaluate();
    }
}
