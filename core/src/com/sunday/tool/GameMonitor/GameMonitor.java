package com.sunday.tool.GameMonitor;

import com.sunday.tool.ToolExtender;

public class GameMonitor extends ToolExtender<GameMonitorController> {
    private float gdxTime = 0.0f;
    private float internalTime = 0.0f;
    private long memoryUsage = 0L;
    private int fps = 0;

    private void updateView() {
        if (gdxTime - internalTime > 1.0f) {
            internalTime = gdxTime;
            if (getController() == null) return;
            getController().newMemoryUsage(internalTime, memoryUsage / 1024);
            getController().newFPS(internalTime, fps);
        }
    }

    //those should be called in Thread with GLContent
    public void updateData(float duration, long memoryUsage, int fps) {
        this.gdxTime += duration;
        this.memoryUsage = memoryUsage;
        this.fps = fps;
        updateView();
    }
}
