package com.sunday.engine.environment.time;

import com.sunday.engine.SubSystem;
import com.sunday.engine.common.ClassContext;
import com.sunday.engine.common.MetaDataContext;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;
import com.sunday.engine.rule.RuleSignal;

import java.util.List;

public class TimeSystem extends SubSystem {
    public float currentTime = 0.0f;

    private Rule timerConditionMountingRule = new Rule(Rule.class, RuleSignal.Mounting, new Reaction<ClassContext<Rule>>() {
        @Override
        public void accept(ClassContext<Rule> ruleClassContext) {
            Class sensedClass = ruleClassContext.getSensedClass();
            if (!sensedClass.equals(Timer.class)) return;
            Rule rule = ruleClassContext.getInstance();
            RuleSignal ruleSignal = (RuleSignal) ruleClassContext.getSignal();
            MetaDataContext<Timer> metaDataContext = (MetaDataContext<Timer>) rule.getContext();
            Timer timer = metaDataContext.getMetaData();
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
