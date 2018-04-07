package com.sunday.engine.rule;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.common.context.CustomizedDataContext;
import com.sunday.engine.common.data.CustomizedData;
import com.sunday.engine.common.data.SystemRelatedData;

public class Rule<C extends Context> implements SystemRelatedData {
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

    public <D extends Data, S extends Signal, DC extends Context> Rule(Class<D> clazz, S signal, Reaction<ClassContext<D, DC>> reaction) {
        this.reaction = (Reaction<C>) reaction;
        condition = new ClassCondition(clazz, signal);
        condition.setReaction(reaction);
    }

    public <D extends Data, S extends Signal, DC extends Context> Rule(Class<D> clazz, Class<S> signalClass, Reaction<ClassContext<D, DC>> reaction) {
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
