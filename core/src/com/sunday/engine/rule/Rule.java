package com.sunday.engine.rule;

import com.sunday.engine.common.*;
import com.sunday.engine.databank.SystemPort;

public class Rule<C extends Context> implements Data {
    protected C context;
    protected Condition<C> condition;
    protected Reaction<C> reaction;

    public <RC extends Context> Rule(Condition<RC> condition, Reaction<RC> reaction) {
        this.condition = (Condition<C>) condition;
        this.reaction = (Reaction<C>) reaction;
        condition.setReaction(reaction);
    }

    public <D extends Data, S extends Signal> Rule(D d, S signal, Reaction<DataContext<D>> reaction) {
        this.reaction = (Reaction<C>) reaction;
        condition = new DataCondition(d, signal);
        condition.setReaction(reaction);
    }

    public <D extends Data, S extends Signal> Rule(Class<D> clazz, S signal, Reaction<ClassContext<D>> reaction) {
        this.reaction = (Reaction<C>) reaction;
        condition = new ClassCondition(clazz, signal);
        condition.setReaction(reaction);
    }

    public <D extends Data, S extends Signal> Rule(D t, Class<S> signalClass, Reaction<DataContext<D>> reaction) {
        this.reaction = (Reaction<C>) reaction;
        condition = new DataCondition(t, signalClass);
        condition.setReaction(reaction);
    }

    public <D extends Data, S extends Signal> Rule(Class<D> clazz, Class<S> signalClass, Reaction<ClassContext<D>> reaction) {
        this.reaction = (Reaction<C>) reaction;
        condition = new ClassCondition(clazz, signalClass);
        condition.setReaction(reaction);
    }

    public C getContext() {
        return context;
    }

    public void setContext(C context) {
        this.context = context;
        condition.setContext(context);
    }

    public Condition getCondition() {
        return condition;
    }

    public Reaction getReaction() {
        return reaction;
    }

    protected void mountWith(SystemPort systemPort) {
        systemPort.broadcast(this, RuleSignal.Mounting);
        condition.connectWith(systemPort);
    }

    protected void dismountWith(SystemPort<Data> systemPort) {
        condition.disconnectWith(systemPort);
    }
}
