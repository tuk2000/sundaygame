package com.sunday.engine.environment.event;

import com.sunday.engine.SubSystem;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.environment.event.window.Window;
import com.sunday.engine.environment.event.window.WindowCondition;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;
import com.sunday.engine.rule.RuleSignal;

public class EventSystem extends SubSystem implements EventDispatcher {
    private Window window;
    private Rule windowConditionMountingRule = new Rule(Rule.class, RuleSignal.Mounting, new Reaction<Rule, RuleSignal>() {
        @Override
        public void accept(Rule rule, RuleSignal ruleSignal) {
            if (rule.getCondition() instanceof WindowCondition) {
                rule.getCondition().setData(window);
            }
        }
    });

    public EventSystem(SystemPort systemPort) {
        super("EventSystem", systemPort);
        window = new Window();
        systemPort.addDataInstance(window);
        systemPort.addDataInstance(windowConditionMountingRule);
    }

    @Override
    public void dispatch(Event event) {
        systemPort.addDataInstance(event);
        systemPort.broadcast(event.getSource(), event.getSignal());
        systemPort.deleteDataInstance(event);
    }

    public void addEventTransfer(EventTransfer eventTransfer) {
        systemPort.addDataInstance(eventTransfer);
        eventTransfer.setEventDispatcher(this);
    }

    public void deleteEventTransfer(EventTransfer eventTransfer) {
        systemPort.deleteDataInstance(eventTransfer);
        eventTransfer.useDummyDispatcher();
    }

    public Window getWindow() {
        return window;
    }
}
