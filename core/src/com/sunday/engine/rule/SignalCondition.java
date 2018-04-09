package com.sunday.engine.rule;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Signal;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SignalCondition<C extends Context> implements Predicate<C> {
    private List<Signal> signals = new ArrayList<>();
    private Function<C, Signal> signalFunction;

    public SignalCondition() {

    }

    public SignalCondition(Function<C, Signal> signalFunction) {
        this.signalFunction = signalFunction;
    }

    public List<Signal> getSignals() {
        return signals;
    }

    public String getSignalNames() {
        return signals.stream().map(signal -> signal.name()).collect(Collectors.joining(","));
    }

    public void setSignals(Signal... signals) {
        this.signals.clear();
        for (Signal signal : signals) {
            this.signals.add(signal);
        }
    }

    public void setSignalFunction(Function<C, Signal> signalFunction) {
        this.signalFunction = signalFunction;
    }

    @Override
    public boolean test(C c) {
        Signal signal = signalFunction.apply(c);
        return signals.contains(signal);
    }
}
