package com.sunday.engine.rule;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.data.SystemData;

public class Rule<C extends Context> implements SystemData {
    protected Condition<C> condition;
    protected Reaction<C> reaction;

    public Rule(Condition<C> condition, Reaction<C> reaction) {
        this.condition = condition;
        this.reaction = reaction;
        condition.setReaction(reaction);
    }

    public Condition<C> getCondition() {
        return condition;
    }

    public Reaction<C> getReaction() {
        return reaction;
    }

    public C getContext() {
        return condition.getContext();
    }
}
