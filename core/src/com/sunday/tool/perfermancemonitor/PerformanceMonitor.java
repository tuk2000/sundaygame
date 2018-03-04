package com.sunday.tool.perfermancemonitor;

import com.sunday.tool.ToolExtender;

import java.util.function.BiConsumer;

public class PerformanceMonitor extends ToolExtender<PerformanceMonitorUIController> {
    private float gdxTime = 0.0f;
    private float internalTime = 0.0f;


    public PerformanceMonitor() {
        uiControllerBuffer.addBuffer(PerformanceRecord.class, true, new BiConsumer<PerformanceMonitorUIController, PerformanceRecord>() {
            @Override
            public void accept(PerformanceMonitorUIController performanceMonitorUIController, PerformanceRecord performanceRecord) {
                performanceMonitorUIController.newPerformanceRecord(performanceRecord);
            }
        });
    }


    //those should be called in Thread with GLContent
    public void updateData(float duration, long memoryUsage, int fps) {
        this.gdxTime += duration;
        float timeEscaped = gdxTime - internalTime;
        if (timeEscaped > 0.95f) {
            internalTime = gdxTime;
            uiControllerBuffer.addInstance(new PerformanceRecord(gdxTime, memoryUsage, fps));
            flushBuffer();
        }
    }
}
