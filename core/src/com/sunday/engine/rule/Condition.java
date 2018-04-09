package com.sunday.engine.rule;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Signal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class Condition<C extends Context> {
    private C context;
    protected Reaction<C> reaction;

    private boolean isAndOperation = true;
    protected List<Predicate<C>> predicates = new ArrayList<>();

    protected List<Predicate<C>> signalPredicates = new ArrayList<>();
    private List<Signal> signals = new ArrayList<>();

    private Map<String, String> mainInfo = new HashMap<>();
    private Map<String, String> extraInfo = new HashMap<>();

    protected C getContext() {
        return context;
    }

    protected void setContext(C context) {
        this.context = context;
    }

    protected void setReaction(Reaction<C> reaction) {
        this.reaction = reaction;
    }

    protected void setAndOperation(boolean isAndOperation) {
        this.isAndOperation = isAndOperation;
    }

    protected void addPredicate(Predicate<C> predicate) {
        predicates.add(predicate);
    }

    protected void addSignalPredicate(Predicate<C> predicate) {
        signalPredicates.add(predicate);
    }


    protected boolean isSatisfied() {
        List<Boolean> result = new ArrayList<>();
        predicates.forEach(predicate -> result.add(predicate.test(context)));
        boolean isSatisfied;
        if (isAndOperation) {
            result.add(true);
            isSatisfied = result.stream().reduce(((aBoolean, aBoolean2) -> aBoolean & aBoolean2)).get();
        } else {
            result.add(false);
            isSatisfied = result.stream().reduce(((aBoolean, aBoolean2) -> aBoolean || aBoolean2)).get();
        }
        List<Boolean> signalResult = new ArrayList<>();
        signalPredicates.forEach(predicate -> signalResult.add(predicate.test(context)));
        isSatisfied = isSatisfied & signalResult.stream().reduce(((aBoolean, aBoolean2) -> aBoolean || aBoolean2)).get();
        return isSatisfied;
    }

    protected List<Signal> getSignals() {
        return signals;
    }

    protected void setSignals(Signal... signals) {
        this.signals.clear();
        for (Signal signal : signals) {
            this.signals.add(signal);
        }
    }

    protected String getSignalNames() {
        return signals.stream().map(signal -> signal.name()).collect(Collectors.joining(","));
    }

    protected abstract void generateExtraInfo();

    protected abstract void generateMainInfo();

    protected void setMainInfoEntry(String key, String value) {
        mainInfo.put(key, value);
    }

    protected void setExtraInfoEntry(String key, String value) {
        extraInfo.put(key, value);
    }


    public String getInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MainInfo:\n");
        mainInfo.forEach((key, value) -> {
            stringBuilder.append(key);
            stringBuilder.append('=');
            stringBuilder.append('[');
            stringBuilder.append(value);
            stringBuilder.append(']');
            stringBuilder.append('\n');
        });
        stringBuilder.append("MainInfo:\n");
        extraInfo.forEach((key, value) -> {
            stringBuilder.append(key);
            stringBuilder.append('=');
            stringBuilder.append('[');
            stringBuilder.append(value);
            stringBuilder.append(']');
            stringBuilder.append('\n');
        });
        return stringBuilder.toString();
    }

    public abstract void check();
}
