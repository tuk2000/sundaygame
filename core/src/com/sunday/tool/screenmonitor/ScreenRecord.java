package com.sunday.tool.screenmonitor;

import javafx.beans.property.SimpleStringProperty;

public class ScreenRecord {

    private SimpleStringProperty className;
    private SimpleStringProperty instanceName;

    public ScreenRecord(String className, String instanceName) {
        this.className = new SimpleStringProperty(className);
        this.instanceName = new SimpleStringProperty(instanceName);
    }

    public String getClassName() {
        return className.get();
    }

    public String getInstanceName() {
        return instanceName.get();
    }
}
