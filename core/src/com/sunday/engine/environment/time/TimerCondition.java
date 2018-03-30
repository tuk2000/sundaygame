package com.sunday.engine.environment.time;

import com.sunday.engine.rule.DataCondition;


public class TimerCondition extends DataCondition<Timer, TimerSignal> {

    private static TimerCondition AnimationTimerCondition = null;

    public static TimerCondition animationTimerCondition() {
        if (AnimationTimerCondition == null) {
            Timer animationTimer = new Timer();
            animationTimer.setPeriod(AnimationSetting.FrameDuration);
            AnimationTimerCondition = bind(animationTimer);
        }
        return AnimationTimerCondition;
    }

    public static TimerCondition bind(Timer timer) {
        TimerCondition timerCondition = new TimerCondition();
        timerCondition.setData(timer);
        timerCondition.setSignals(TimerSignal.Triggered);
        return timerCondition;
    }

}
