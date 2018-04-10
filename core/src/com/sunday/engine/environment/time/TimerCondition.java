package com.sunday.engine.environment.time;

import com.sunday.engine.environment.EnvironmentCondition;


public class TimerCondition<T extends Timer> extends EnvironmentCondition<T, TimerContext<T>> {
    private static TimerCondition<Timer> AnimationTimerCondition = null;
    private T timer;

    public TimerCondition(T timer) {
        this.timer = timer;
        signalCondition.setSignals(TimerSignal.Triggered);
        setExtraInfoEntry("Period", String.valueOf(timer.period));
    }

    public static TimerCondition<Timer> animationTimerCondition() {
        if (AnimationTimerCondition == null) {
            Timer animationTimer = new Timer();
            animationTimer.setPeriod(AnimationSetting.FrameDuration);
            AnimationTimerCondition = new TimerCondition<>(animationTimer);
        }
        return AnimationTimerCondition;
    }

    public T getTimer() {
        return timer;
    }

    @Override
    public boolean test(TimerContext<T> timerContext) {
        return signalCondition.test(timerContext);
    }
}
