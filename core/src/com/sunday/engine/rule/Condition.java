package com.sunday.engine.rule;

import com.sunday.engine.common.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class Condition<C extends Context> implements Predicate<C> {
    private C context;
    protected Reaction<C> reaction;

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

    protected abstract void generateExtraInfo();

    protected abstract void generateMainInfo();

    protected void setMainInfoEntry(String key, String value) {
        mainInfo.put(key, value);
    }

    protected void setExtraInfoEntry(String key, String value) {
        extraInfo.put(key, value);
    }

    private String buildInfoString(Map<String, String> mainInfo) {
        StringBuilder stringBuilder = new StringBuilder();
        mainInfo.forEach((key, value) -> {
            stringBuilder.append(key);
            stringBuilder.append('=');
            stringBuilder.append('[');
            stringBuilder.append(value);
            stringBuilder.append(']');
            stringBuilder.append('\n');
        });
        return stringBuilder.toString();
    }

    public String getInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MainInfo:\n");
        stringBuilder.append(buildInfoString(mainInfo));
        stringBuilder.append("ExtraInfo:\n");
        stringBuilder.append(buildInfoString(mainInfo));
        return stringBuilder.toString();
    }
}
