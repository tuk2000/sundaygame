package com.sunday.engine.rule;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.annotation.DataMark;
import com.sunday.engine.common.annotation.DataType;
import com.sunday.engine.common.data.SystemData;
import com.sunday.engine.common.signal.DataSignal;

@DataMark(type = DataType.System, signalClass = {DataSignal.class, RuleSignal.class}, contextClass = RuleContext.class)
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
}
