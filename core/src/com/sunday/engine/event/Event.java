package com.sunday.engine.event;


import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;

public class Event implements Data {
    private Data source;
    private Signal signal;

    public Event(Data source) {
        this.source = source;
    }

    protected void setSignal(Signal signal) {
        this.signal = signal;
    }

    public Data getSource() {
        return source;
    }

    public Signal getSignal() {
        return signal;
    }

    @Override
    public String toString() {
        return " from " + getSource() + " " + getSignal();
    }
}