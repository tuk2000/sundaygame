package com.sunday.engine.environment.time;

import com.sunday.engine.SubSystem;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.rule.Condition;
import com.sunday.engine.rule.ContextConstructor;

import java.util.HashMap;
import java.util.Map;

public class TimeSystem extends SubSystem implements ContextConstructor<TimerCondition> {
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
    public void construct(TimerCondition timerCondition) {
        Timer timer = timerCondition.getTimer();
        if (map.containsKey(timer)) {
            timerCondition.generateInfoWith(animationTimerContext);
            animationTimerContext.setEvaluateConnection(timerCondition, timerCondition.getReaction());
        } else {
            TimerContext<Timer> timerContext = new TimerContext<Timer>(timer);
            map.put(timer, timerContext);
            timerContext.setEvaluateConnection(timerCondition, timerCondition.getReaction());
            timerCondition.generateInfoWith(timerContext);
            timer.start(currentTime);
        }
    }
}
