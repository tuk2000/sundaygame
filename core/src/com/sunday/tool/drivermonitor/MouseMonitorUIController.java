package com.sunday.tool.drivermonitor;

import com.sunday.tool.ToolExtenderUIController;

public interface MouseMonitorUIController extends ToolExtenderUIController {
    void setMouseSignal(String signal);

    void setMouseLocation(int screenX, int screenY);

    void setMouseKey(String key);
}
