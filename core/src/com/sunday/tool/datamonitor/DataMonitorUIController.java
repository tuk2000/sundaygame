package com.sunday.tool.datamonitor;

import com.sunday.tool.ToolExtenderUIController;

public interface DataMonitorUIController extends ToolExtenderUIController {
    void addMonitoredObject(MonitoredData monitoredObject);

    void removeMonitoredObject(MonitoredData monitoredObject);
}
