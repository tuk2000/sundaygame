package com.sunday.tool.drivermonitor;


import javafx.beans.property.SimpleStringProperty;

public class GamePadStatus {
    public SimpleStringProperty name;
    public SimpleStringProperty signal;
    public SimpleStringProperty buttonInfo;
    public SimpleStringProperty axisInfo;
    public SimpleStringProperty povInfo;
    public SimpleStringProperty xSliderInfo;
    public SimpleStringProperty ySliderInfo;
    public SimpleStringProperty accelerometerInfo;

    public GamePadStatus(String name) {
        this.name = new SimpleStringProperty(name);
        signal = new SimpleStringProperty();
        buttonInfo = new SimpleStringProperty();
        axisInfo = new SimpleStringProperty();
        povInfo = new SimpleStringProperty();
        xSliderInfo = new SimpleStringProperty();
        ySliderInfo = new SimpleStringProperty();
        accelerometerInfo = new SimpleStringProperty();
    }

    public String getName() {
        return name.get();
    }

    public String getSignal() {
        return signal.get();
    }

    public String getButtonInfo() {
        return buttonInfo.get();
    }

    public String getAxisInfo() {
        return axisInfo.get();
    }

    public String getPovInfo() {
        return povInfo.get();
    }

    public String getxSliderInfo() {
        return xSliderInfo.get();
    }

    public String getySliderInfo() {
        return ySliderInfo.get();
    }

    public String getAccelerometerInfo() {
        return accelerometerInfo.get();
    }
}
