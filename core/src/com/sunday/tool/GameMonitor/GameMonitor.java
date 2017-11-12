package com.sunday.tool.GameMonitor;

import com.sunday.tool.ToolExtender;

import java.util.ArrayList;

public class GameMonitor extends ToolExtender<GameMonitorController> {
    private float gdxTime = 0.0f;
    private float internalTime = 0.0f;

    private class DataBundle{
        float time = 0.0f;
        long memoryUsage = 0L;
        int fps = 0;
        DataBundle(float time, long memoryUsage, int fps){
            this.time=time;
            this.memoryUsage=memoryUsage;
            this.fps=fps;
        }
    }
    private ArrayList<DataBundle> dataBuffer=new ArrayList<>();

    private void updateView() {
        if (getController() != null) {
            dataBuffer.forEach(e->{
                getController().newMemoryUsage(e.time, e.memoryUsage / 1024);
                getController().newFPS(e.time, e.fps);
            });
            dataBuffer.clear();
        }
    }

    //those should be called in Thread with GLContent
    public void updateData(float duration, long memoryUsage, int fps) {
        this.gdxTime += duration;
        if (gdxTime - internalTime > 1.0f) {
            internalTime = gdxTime;
            dataBuffer.add(new DataBundle(gdxTime,memoryUsage,fps));
            updateView();
        }
    }
}
