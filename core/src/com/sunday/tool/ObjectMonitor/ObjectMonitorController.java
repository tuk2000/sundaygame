package com.sunday.tool.ObjectMonitor;

import com.sunday.tool.ToolExtenderController;

public interface ObjectMonitorController extends ToolExtenderController {
    void addMonitoredObject(Object object);
    void removeMonitoredObject(Object object);
}
