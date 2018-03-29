package com.sunday.engine.rule.condition;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.render.AnimationTimer;
import com.sunday.engine.render.AnimationTimerSignal;
import com.sunday.engine.rule.Condition;


public class TimerCondition extends Condition<AnimationTimer, AnimationTimerSignal> {

    @Override
    public void connectWith(SystemPort systemPort) {

    }

    @Override
    public void disconnectWith(SystemPort systemPort) {

    }

    @Override
    public void notify(Data data, Signal signal) {

    }
}
