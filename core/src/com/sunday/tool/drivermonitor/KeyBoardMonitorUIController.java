package com.sunday.tool.drivermonitor;

import com.sunday.tool.ToolExtenderUIController;

public interface KeyBoardMonitorUIController extends ToolExtenderUIController {
    void setKeyBoardSignal(String signal);

    void setKeyBoardKey(String key);

    void setKeyBoardStatus(String status);
}
