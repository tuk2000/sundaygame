package com.sunday.engine.rule;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.databank.SystemPort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Condition<T extends Data> {
    private T data;
    private List<Signal> signals = new ArrayList<>();
    private List<Tracer> tracers = new ArrayList<>();
    private Reaction reaction;
    private String mainInfo = "MainInfo:\nn/a";
    private String extraInfo = "ExtractInfo:\nn/a";

    protected abstract boolean isSatisfied();

    public void check() {
        if (isSatisfied() & reaction != null) {
            reaction.accept(data);
        }
    }

    public T getData() {
        return data;
    }

    protected void setData(T t) {
        data = t;
    }

    public List<Signal> getSignals() {
        return signals;
    }

    protected void setSignals(Signal... signals) {
        this.signals.clear();
        this.signals.addAll(Arrays.asList(signals));
    }

    protected List<Tracer> getTracers() {
        return tracers;
    }

    protected void setTracers(Tracer... tracers) {
        this.tracers.clear();
        this.tracers.addAll(Arrays.asList(tracers));
    }

    protected void generateMainInfo() {
        String names = "";
        for (Signal signal : signals) {
            names += signal.name();
        }
        mainInfo =
                "MainInfo:\n" +
                        "Source = [" + data + "]\n" +
                        "SourceClass = [" + data.getClass().getSimpleName() + "]\n" +
                        "Signals = [" + names + "]";
    }

    protected void setExtraInfo(String extraInfo) {
        this.extraInfo = "ExtractInfo:\n" + extraInfo;
    }

    protected abstract void bindWith(SystemPort systemPort);

    protected void unbindWith(SystemPort systemPort) {
        tracers.forEach(tracer -> {
            systemPort.removeConnection(data, tracer);
        });
    }

    public Reaction getReaction() {
        return reaction;
    }

    protected void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }

    public String getInfo() {
        return toString() + "\n" + mainInfo + "\n" + extraInfo;
    }

}
