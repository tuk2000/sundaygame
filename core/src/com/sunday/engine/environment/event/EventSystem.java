package com.sunday.engine.environment.event;

import com.sunday.engine.SubSystem;
import com.sunday.engine.common.ClassContext;
import com.sunday.engine.common.MetaDataContext;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.environment.event.window.Window;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;
import com.sunday.engine.rule.RuleSignal;

public class EventSystem extends SubSystem implements EventDispatcher {
    private Window window;
    private Rule windowConditionMountingRule = new Rule(Rule.class, RuleSignal.Mounting, new Reaction<ClassContext<Rule>>() {
        @Override
        public void accept(ClassContext<Rule> ruleClassContext) {
            Class sensedClass = ruleClassContext.getSensedClass();
            Rule rule = ruleClassContext.getInstance();
            if (sensedClass.equals(Window.class)) {
                ((MetaDataContext) rule.getContext()).setMetaData(window);
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
        systemPort.removeDataInstance(event);
    }

    public void addEventTransfer(EventTransfer eventTransfer) {
        systemPort.addDataInstance(eventTransfer);
        eventTransfer.setEventDispatcher(this);
    }

    public void deleteEventTransfer(EventTransfer eventTransfer) {
        systemPort.removeDataInstance(eventTransfer);
        eventTransfer.useDummyDispatcher();
    }

    public Window getWindow() {
        return window;
    }
}
