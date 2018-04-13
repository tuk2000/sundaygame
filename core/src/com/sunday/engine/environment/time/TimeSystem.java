package com.sunday.engine.environment.time;

import com.sunday.engine.SubSystem;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.rule.Condition;
import com.sunday.engine.rule.DataContextConstructor;

import java.util.HashMap;
import java.util.Map;

public class TimeSystem extends SubSystem implements DataContextConstructor<TimerCondition> {
    public float currentTime = 0.0f;
    private Map<Timer, TimerContext<Timer>> map = new HashMap<>();
    private TimerContext<Timer> animationTimerContext;

    public TimeSystem(SystemPort systemPort) {
        super("TimeSystem", systemPort);
        Timer animationTimer = TimerCondition.animationTimerCondition().getTimer();
        animationTimerContext = new TimerContext<Timer>(animationTimer);
        map.put(animationTimer, animationTimerContext);
    }

    public void updateTime(float deltaTime) {
        currentTime += deltaTime;
        map.values().forEach(timerContext -> timerContext.evaluate(currentTime));
    }

    @Override
    public boolean test(Condition condition) {
        return condition instanceof TimerCondition;
    }

    @Override
    public TimerContext construct(TimerCondition timerCondition) {
        Timer timer = timerCondition.getTimer();
        TimerContext timerContext;
        if (map.containsKey(timer)) {
            timerContext = animationTimerContext;
        } else {
            timerContext = new TimerContext(timer);
            map.put(timer, timerContext);
            timer.start(currentTime);
        }
        return timerContext;
    }
}
