package com.sunday.engine.databank;

import com.sunday.engine.common.Data;

public class Connection implements Data {
    public Data source;
    public Target target;

    public Connection(Data source, Target target) {
        this.source = source;
        this.target = target;
    }
}
