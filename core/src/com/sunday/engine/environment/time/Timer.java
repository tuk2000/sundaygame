package com.sunday.engine.environment.time;

import com.sunday.engine.common.propertyholder.Resettable;
import com.sunday.engine.environment.EnvironmentData;

public class Timer implements EnvironmentData, Resettable, TimeRelated {
    public boolean isRunning = false;
    public float period = 1.0f;
    public float frequency = 1;
    public long periodCount = 0;

    public float startTime = 0.0f;
    public float lastTriggeredTime = 0.0f;
    public float endTime = -1.0f;

    @Override
    public void reset() {
        isRunning = false;
        frequency = 1;
        period = 1.0f;
        periodCount = 0;
        startTime = 0.0f;
        lastTriggeredTime = 0.0f;
        endTime = -1.0f;
    }

    public void start(float time) {
        if (!isRunning) {
            startTime = time;
            isRunning = true;
        }
    }

    public void setPeriod(float period) {
        this.period = period;
        frequency = 1.0f / period;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
        period = 1.0f / frequency;
    }

    public boolean isTriggered(float time) {
        long presentPeriodCount = (long) ((time - startTime) / period);
        if (presentPeriodCount - periodCount >= 1) {
            lastTriggeredTime = time;
            periodCount = presentPeriodCount;
            return true;
        } else {
            return false;
        }
    }

    public void stop(float time) {
        isRunning = false;
        endTime = time;
    }

    public String getInfo() {
        String info = "isRunning :" + isRunning + "\n";
        info += "period :" + period + "\n";
        info += "frequency :" + frequency + "\n";
        info += "lastTriggeredTime :" + lastTriggeredTime + "\n";
        info += "startTime :" + startTime + "\n";
        info += "endTime :" + endTime + "\n";
        return info;
    }
}
