package com.sunday.game.engine.common;

import com.sunday.game.engine.databank.DataBank;
import com.sunday.game.engine.databank.port.HolderPort;
import com.sunday.game.engine.databank.synchronize.SynchronizeCondition;

public class AnimationTimer implements Data {
    private static HolderPort<AnimationTimer> holderPort;
    private static AnimationTimer instance;
    private static SynchronizeCondition condition;

    public static void initAnimationTimer(DataBank dataBank) {
        instance = new AnimationTimer();
        holderPort = dataBank.getHolderPort(instance);
        condition = new SynchronizeCondition(instance, DataOperation.Modification);
    }

    public static void synchronize() {
        holderPort.synchronize(instance, DataOperation.Modification);
    }

    public static SynchronizeCondition getCondition() {
        return condition;
    }
}