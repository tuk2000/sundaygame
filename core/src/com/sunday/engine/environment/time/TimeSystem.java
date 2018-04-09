package com.sunday.engine.environment.time;

import com.sunday.engine.SubSystem;
import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.environment.EnvironmentDataContext;
import com.sunday.engine.rule.*;

public class TimeSystem extends SubSystem implements ContextConstructor<TimerCondition> {
    public float currentTime = 0.0f;

    public TimeSystem(SystemPort systemPort) {
        super("TimeSystem", systemPort);
        Rule<ClassContext<RuleContext>> timerConditionMountingRule
                = new Rule<>(new ClassCondition<>(Rule.class, RuleSignal.Mounting), new ClassReaction<RuleContext>() {
            @Override
            public void accept(RuleContext ruleContext) {
                Rule rule = ruleContext.getSystemData();
                if (!(rule.getCondition() instanceof TimerCondition)) return;
                RuleSignal ruleSignal = (RuleSignal) ruleContext.getSignal();
                EnvironmentDataContext<Timer> environmentDataContext = (EnvironmentDataContext<Timer>) rule.getContext();
                Timer timer = environmentDataContext.getEnvironmentData();
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
        systemPort.addDataInstance(timerConditionMountingRule);
    }

    public void updateTime(float deltaTime) {
        currentTime += deltaTime;
    }

    @Override
    public boolean accept(Condition condition) {
        return condition instanceof TimerCondition;
    }


    private EnvironmentDataContext<Timer> createTimerContext(TimerCondition timerCondition) {
        Timer timer = timerCondition.getTimer();
        return new EnvironmentDataContext<Timer>(timer) {
            @Override
            public void evaluate() {
                super.evaluate();
                if (timer.isTriggered(currentTime))
                    this.setSignal(TimerSignal.Triggered);
            }
        };
    }

    @Override
    public void construct(TimerCondition timerCondition) {
        EnvironmentDataContext<Timer> timerEnvironmentDataContext = createTimerContext(timerCondition);
        timerCondition.setEnvironmentContext(timerEnvironmentDataContext);
        timerCondition.getTimer().start(currentTime);
    }
}
