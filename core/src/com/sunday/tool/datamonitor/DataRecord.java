package com.sunday.tool.datamonitor;

import com.sunday.engine.common.Data;

public class DataRecord {
    public String itemName;
    public String systemName;
    public String className;
    public String instanceName;
    public Data data;

    public DataRecord(Data data, String systemName) {
        this.data = data;
        className = data.getClass().getName();
        this.systemName = systemName;
        instanceName = data.toString();
    }

    public DataRecord() {

    }
}
