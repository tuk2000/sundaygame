package com.sunday.engine.environment.time;

import com.sunday.engine.SubSystem;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.context.DataContext;
import com.sunday.engine.contextbank.ContextBank;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.rule.Condition;
import com.sunday.engine.rule.DataProvider;

import java.util.HashMap;
import java.util.Map;

public class TimeSystem extends SubSystem implements DataProvider<TimerCondition<Timer>> {
    public float currentTime = 0.0f;
    private Map<Timer, TimerContext<Timer>> map = new HashMap<>();
    private TimerContext<Timer> animationTimerContext;

    public TimeSystem(SystemPort systemPort, ContextBank contextBank) {
        super("TimeSystem", systemPort);
        Timer animationTimer = TimerCondition.animationTimerCondition().getData();
        animationTimerContext = contextBank.getDataContext(animationTimer);
        map.put(animationTimer, animationTimerContext);
    }

    public void updateTime(float deltaTime) {
        currentTime += deltaTime;
        map.values().forEach(timerContext -> timerContext.evaluate(currentTime));
    }

    @Override
    public boolean isSuitedFor(Condition condition) {
        return condition instanceof TimerCondition;
    }

    @Override
    public Data requestData(TimerCondition<Timer> condition) {
        return condition.getData();
    }

    @Override
    public <D extends Data> void feedback(D data, DataContext<D> dataContext) {
        Timer timer = (Timer) data;
        TimerContext timerContext = (TimerContext) dataContext;
        if (!map.containsKey(timer)) {
            map.put(timer, timerContext);
            timer.start(currentTime);
        }
    }
}
