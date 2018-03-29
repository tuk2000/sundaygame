package com.sunday.tool.drivermonitor;

import com.sunday.tool.ToolExtenderUIController;

public interface GamePadMonitorUIController extends ToolExtenderUIController {
    void addGamePad(String name);

    void removeGamePad(String name);

    void setGamePadSignal(String name, String signal);

    void setButtonCode(String name, int buttonCode);

    void setAxisInfo(String name, int axisCode, float axisMoveValue);

    void setPovInfo(String name, int povCode, String povDirection);

    void setXSliderInfo(String name, int xSliderCode, boolean xSliderMoveValue);

    void setYSliderInfo(String name, int ySliderCode, boolean ySliderMoveValue);

    void setAccelerometerInfo(String name, int accelerometerCode, float x, float y, float z);
}
