package com.sunday.engine.common;

import com.sunday.engine.databank.DataBank;
import com.sunday.engine.databank.ports.UserPort;
import com.sunday.engine.databank.synchronize.SynchronizeCondition;

public class AnimationTimer implements Data {
    private static UserPort<AnimationTimer> userPort;
    private static AnimationTimer instance;
    private static SynchronizeCondition condition;

    public static void initAnimationTimer(DataBank dataBank) {
        instance = new AnimationTimer();
        userPort = dataBank.getUserPort(instance);
        condition = new SynchronizeCondition(instance, DataSignal.Modification);
    }

    public static void synchronize() {
        userPort.synchronize(instance, DataSignal.Modification);
    }

    public static SynchronizeCondition getCondition() {
        return condition;
    }
}