package com.sunday.engine.rule;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.common.Target;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.databank.SystemPortSharing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Condition<T extends Data, S extends Signal> implements SystemPortSharing, Target {
    private T data;
    private List<S> signals = new ArrayList<>();
    private Reaction reaction;
    private String mainInfo = "MainInfo:\nn/a";
    private String extraInfo = "ExtractInfo:\nn/a";

    public T getData() {
        return data;
    }

    protected void setData(T t) {
        data = t;
    }

    public List<S> getSignals() {
        return signals;
    }

    protected void setSignals(S... signals) {
        this.signals.clear();
        this.signals.addAll(Arrays.asList(signals));
    }

    protected void generateMainInfo() {
        String names = "";
        for (Signal signal : signals) {
            names += signal.name() + " ";
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

    public abstract void connectWith(SystemPort systemPort);

    public void disconnectWith(SystemPort systemPort) {
        signals.forEach(tracer -> {
            systemPort.removeConnection(data, this);
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
