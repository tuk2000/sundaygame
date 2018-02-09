package com.sunday.tool.perfermancemonitor;

import com.sunday.tool.ToolExtenderUIController;

public interface PerformanceMonitorUIController extends ToolExtenderUIController {
    void newMemoryUsage(float time, long memoryUsage);

    void newFPS(float time, int fps);
}
