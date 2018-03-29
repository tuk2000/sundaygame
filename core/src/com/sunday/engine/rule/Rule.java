package com.sunday.engine.rule;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.rule.condition.ClassCondition;
import com.sunday.engine.rule.condition.DataCondition;

public class Rule implements Data {
    protected Condition condition;
    protected Reaction reaction;

    public <T extends Data, S extends Signal> Rule(Condition<T, S> condition, Reaction<T, S> reaction) {
        this.condition = condition;
        this.reaction = reaction;
        condition.setReaction(reaction);
    }

    public <T extends Data, S extends Signal> Rule(T t, Class<S> signalClass, Reaction<T, S> reaction) {
        condition = new DataCondition(t, signalClass);
        this.reaction = reaction;
        condition.setReaction(reaction);
    }

    public <T extends Data, S extends Signal> Rule(Class<T> clazz, Class<S> signalClass, Reaction<T, S> reaction) {
        condition = new ClassCondition(clazz, signalClass);
        this.reaction = reaction;
        condition.setReaction(reaction);
    }

    protected void mountWith(SystemPort systemPort) {
        condition.connectWith(systemPort);
    }

    protected void dismountWith(SystemPort systemPort) {
        condition.disconnectWith(systemPort);
    }
}
