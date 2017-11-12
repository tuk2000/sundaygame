package com.sunday.tool.ObjectMonitor;

import com.sunday.tool.ToolExtenderController;

public interface ObjectMonitorController extends ToolExtenderController {
    void addMonitoredObject(MonitoredObject monitoredObject);
    void removeMonitoredObject(MonitoredObject monitoredObject);
}
