package com.sunday.engine.environment.time;

import com.sunday.engine.SubSystem;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.environment.EnvironmentDataContext;
import com.sunday.engine.rule.Condition;
import com.sunday.engine.rule.ContextConstructor;

import java.util.ArrayList;
import java.util.List;

public class TimeSystem extends SubSystem implements ContextConstructor<TimerCondition> {
    public float currentTime = 0.0f;
    private List<EnvironmentDataContext<Timer>> timerEnvironmentDataContexts = new ArrayList<>();

    public TimeSystem(SystemPort systemPort) {
        super("TimeSystem", systemPort);
    }

    public void updateTime(float deltaTime) {
        currentTime += deltaTime;
        timerEnvironmentDataContexts.forEach(EnvironmentDataContext::evaluate);
    }

    @Override
    public boolean test(Condition condition) {
        return condition instanceof TimerCondition;
    }

    @Override
    public void accept(TimerCondition timerCondition) {
        Timer timer = timerCondition.getTimer();
        EnvironmentDataContext<Timer> timerEnvironmentDataContext = new EnvironmentDataContext<>(timer);
        timerEnvironmentDataContexts.add(timerEnvironmentDataContext);
        timerEnvironmentDataContext.setEvaluateConnection(timerCondition, timerCondition.getReaction());
        timerCondition.setEnvironmentContext(timerEnvironmentDataContext);
        timer.start(currentTime);
    }
}
