package com.sunday.tool.screenloader;

import com.sunday.tool.ToolExtenderUIController;

public interface ScreenMonitorUIController extends ToolExtenderUIController {
    void loadScreenClass(String screenClassName);

    void addScreenRecord(ScreenRecord screenRecord);

    void removeScreenRecord(ScreenRecord screenRecord);

}
