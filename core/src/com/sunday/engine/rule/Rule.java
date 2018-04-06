package com.sunday.engine.rule;

import com.sunday.engine.common.*;

public class Rule<C extends Context> implements Data {
    protected Condition<C> condition;
    protected Reaction<C> reaction;

    public <RC extends Context> Rule(Condition<RC> condition, Reaction<RC> reaction) {
        this.condition = (Condition<C>) condition;
        this.reaction = (Reaction<C>) reaction;
        condition.setReaction(reaction);
    }

    public <CD extends CustomizedData, S extends Signal> Rule(CD cd, S signal, Reaction<CustomizedDataContext<CD>> reaction) {
        this.reaction = (Reaction<C>) reaction;
        condition = new CustomizedDataCondition(cd, signal);
        condition.setReaction(reaction);
    }

    public <CD extends CustomizedData, S extends Signal> Rule(CD cd, Class<S> signalClass, Reaction<CustomizedDataContext<CD>> reaction) {
        this.reaction = (Reaction<C>) reaction;
        condition = new CustomizedDataCondition(cd, signalClass);
        condition.setReaction(reaction);
    }

    public <D extends Data, S extends Signal> Rule(Class<D> clazz, S signal, Reaction<ClassContext<D>> reaction) {
        this.reaction = (Reaction<C>) reaction;
        condition = new ClassCondition(clazz, signal);
        condition.setReaction(reaction);
    }

    public <D extends Data, S extends Signal> Rule(Class<D> clazz, Class<S> signalClass, Reaction<ClassContext<D>> reaction) {
        this.reaction = (Reaction<C>) reaction;
        condition = new ClassCondition(clazz, signalClass);
        condition.setReaction(reaction);
    }

    public Condition getCondition() {
        return condition;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public C getContext() {
        return condition.getContext();
    }
}
