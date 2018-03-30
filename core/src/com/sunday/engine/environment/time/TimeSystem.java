package com.sunday.engine.environment.time;

import com.sunday.engine.SubSystem;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;
import com.sunday.engine.rule.RuleSignal;

import java.util.List;

public class TimeSystem extends SubSystem {
    public float currentTime = 0.0f;

    private Rule timerConditionMountingRule = new Rule(Rule.class, RuleSignal.Mounting, new Reaction<Rule, RuleSignal>() {
        @Override
        public void accept(Rule rule, RuleSignal ruleSignal) {
            if (rule.getCondition() instanceof TimerCondition) {
                Timer timer = ((TimerCondition) rule.getCondition()).getData();
                if (!systemPort.containsDataInstance(timer)) {
                    systemPort.addDataInstance(timer);
                    switch (ruleSignal) {
                        case Mounting:
                            timer.start(currentTime);
                            break;
                        case Dismounting:
                            timer.stop(currentTime);
                    }
                }
            }
        }
    });

    public TimeSystem(SystemPort systemPort) {
        super("TimeSystem", systemPort);
        systemPort.addDataInstance(timerConditionMountingRule);
    }

    public void updateTime(float deltaTime) {
        currentTime += deltaTime;
        List<Timer> timers = systemPort.instancesOf(Timer.class);
        timers.forEach(timer -> {
            if (timer.isTriggered(currentTime)) {
                systemPort.broadcast(timer, TimerSignal.Triggered);
            }
        });
    }
}
