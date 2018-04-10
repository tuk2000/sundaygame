package com.sunday.engine.environment.time;

import com.sunday.engine.environment.EnvironmentCondition;


public class TimerCondition<T extends Timer> extends EnvironmentCondition<T, TimerContext<T>> {
    private static TimerCondition<Timer> AnimationTimerCondition = null;
    private T timer;

    public static TimerCondition<Timer> animationTimerCondition() {
        if (AnimationTimerCondition == null) {
            Timer animationTimer = new Timer();
            animationTimer.setPeriod(AnimationSetting.FrameDuration);
            AnimationTimerCondition = bind(animationTimer);
        }
        return AnimationTimerCondition;
    }

    public static TimerCondition<Timer> bind(Timer timer) {
        TimerCondition<Timer> timerCondition = new TimerCondition<>();
        timerCondition.timer = timer;
        timerCondition.signalCondition.setSignals(TimerSignal.Triggered);
        return timerCondition;
    }

    public T getTimer() {
        return timer;
    }

    @Override
    public boolean test(TimerContext<T> timerContext) {
        return signalCondition.test(timerContext);
    }
}
