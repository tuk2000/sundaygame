package com.sunday.engine.rule;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Signal;

import java.util.*;
import java.util.function.Predicate;

public abstract class Condition<C extends Context> {
    protected Reaction<C> reaction;
    protected List<Predicate<? extends C>> predicates = new ArrayList<>();
    protected List<Boolean> result = new ArrayList<>();
    private boolean isAndOperation = true;
    private C context;
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

    protected void addPredicate(Predicate<? extends C> predicate) {
        predicates.add(predicate);
    }

    protected List<Signal> getSignals() {
        return signals;
    }

    protected void setSignals(Signal... signals) {
        this.signals.clear();
        this.signals.addAll(Arrays.asList(signals));
    }

    protected String getSignalNames() {
        StringBuilder names = new StringBuilder();
        for (Signal signal : getSignals()) {
            names.append(signal.name()).append(",");
        }
        names = new StringBuilder(names.substring(0, names.lastIndexOf(",")));
        return names.toString();
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
}
