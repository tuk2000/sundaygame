package com.sunday.tool.screenmonitor;

import com.sunday.tool.ToolExtenderUIController;

public interface ScreenMonitorUIController extends ToolExtenderUIController {
    void loadScreenClass(String screenClassName);

    void addScreenRecord(ScreenRecord screenRecord);

    void removeScreenRecord(ScreenRecord screenRecord);

}
