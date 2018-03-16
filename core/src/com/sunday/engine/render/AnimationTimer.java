package com.sunday.engine.render;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.DataSignal;
import com.sunday.engine.common.SubSystem;
import com.sunday.engine.databank.DataBank;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.rule.Condition;
import com.sunday.engine.rule.condition.DataCondition;

public class AnimationTimer extends SubSystem implements Data {
    private static AnimationTimer instance;
    private static Condition condition;

    protected AnimationTimer(String name, SystemPort systemPort) {
        super(name, systemPort);
    }

    public static void initAnimationTimer(DataBank dataBank) {
        instance = new AnimationTimer("AnimationTimer", dataBank.getSystemPort(AnimationTimer.class));
        condition = new DataCondition(instance, DataSignal.Modification);
    }

    public static void synchronize() {
        instance.systemPort.broadcast(instance, DataSignal.Modification);
    }

    public static Condition getCondition() {
        return condition;
    }
}