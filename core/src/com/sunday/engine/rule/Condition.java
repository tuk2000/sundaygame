package com.sunday.engine.rule;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Signal;
import com.sunday.engine.databank.SystemPortSharing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public abstract class Condition<C extends Context> implements SystemPortSharing {
    protected C context;
    protected Reaction<C> reaction;
    protected List<Predicate<C>> predicates = new ArrayList<>();
    protected List<Boolean> result = new ArrayList<>();
    protected boolean isAndOperation = true;
    private List<Signal> signals = new ArrayList<>();
    private String mainInfo = "MainInfo:\nn/a";
    private String extraInfo = "ExtractInfo:\nn/a";

    protected void setContext(C context) {
        this.context = context;
    }

    protected void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }

    protected void setAndOperation(boolean isAndOperation) {
        this.isAndOperation = isAndOperation;
    }

    protected void addPredicate(Predicate<C> predicate) {
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
        String names = "";
        for (Signal signal : getSignals()) {
            names += signal.name() + ",";
        }
        names = names.substring(0, names.lastIndexOf(","));
        return names;
    }

    protected abstract void generateMainInfo();

    protected void setMainInfo(String mainInfo) {
        this.mainInfo = "MainInfo:\n" + mainInfo;
    }

    protected void setExtraInfo(String extraInfo) {
        this.extraInfo = "ExtractInfo:\n" + extraInfo;
    }


    public String getInfo() {
        return toString() + "\n" + mainInfo + "\n" + extraInfo;
    }
}
