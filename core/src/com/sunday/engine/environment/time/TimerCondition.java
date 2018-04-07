package com.sunday.engine.environment.time;

import com.sunday.engine.environment.EnvironmentCondition;
import com.sunday.engine.environment.EnvironmentDataContext;


public class TimerCondition extends EnvironmentCondition<Timer, EnvironmentDataContext<Timer>> implements TimeRelated {

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
        timerCondition.setContext(new EnvironmentDataContext(timer));
        timerCondition.setSignals(TimerSignal.Triggered);
        return timerCondition;
    }

}
