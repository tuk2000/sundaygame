package com.sunday.engine.common;

public class Connection implements Data {
    public Data source;
    public Target target;

    public Connection(Data source, Target target) {
        this.source = source;
        this.target = target;
    }
}
