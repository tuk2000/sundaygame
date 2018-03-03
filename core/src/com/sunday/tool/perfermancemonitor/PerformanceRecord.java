package com.sunday.tool.perfermancemonitor;

public class PerformanceRecord {
    public float time = 0.0f;
    public long memoryUsage = 0L;
    public int fps = 0;

    public PerformanceRecord(float time, long memoryUsage, int fps) {
        this.time = time;
        this.memoryUsage = memoryUsage;
        this.fps = fps;
    }
}