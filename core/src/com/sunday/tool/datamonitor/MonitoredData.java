package com.sunday.tool.datamonitor;

import javafx.beans.property.SimpleStringProperty;

public class MonitoredData {

    private SimpleStringProperty className;
    private SimpleStringProperty objectShortName;

    public MonitoredData(String className, String objectShortName) {
        this.className = new SimpleStringProperty(className);
        this.objectShortName = new SimpleStringProperty(objectShortName);
    }

    public String getClassName() {
        return className.get();
    }

    public String getObjectName() {
        return objectShortName.get();
    }
}
