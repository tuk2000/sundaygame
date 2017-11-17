package com.sunday.tool.gamemonitor;

import com.sunday.tool.ToolExtenderController;

public interface GameMonitorController extends ToolExtenderController {
    void newMemoryUsage(float time, long memoryUsage);

    void newFPS(float time, int fps);
}
