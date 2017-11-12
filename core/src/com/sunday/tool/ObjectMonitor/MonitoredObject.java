package com.sunday.tool.ObjectMonitor;

import javafx.beans.property.SimpleStringProperty;

public class MonitoredObject {

    private SimpleStringProperty className;
    private SimpleStringProperty objectShortName;

    public MonitoredObject(String className, String objectShortName) {
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
