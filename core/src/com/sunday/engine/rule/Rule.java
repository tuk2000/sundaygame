package com.sunday.engine.rule;

import com.sunday.engine.common.Data;
import com.sunday.engine.databank.Connection;

public abstract class Rule implements Data {
    protected Condition condition;
    protected Reaction reaction;

    protected Rule(Condition condition, Reaction reaction) {
        this.condition = condition;
        this.reaction = reaction;
        condition.connect(reaction);
    }

    protected void setRuleHub(RuleHub ruleHub) {
        ruleHub.getSystemPort().addDataInstance(condition);
        ruleHub.getSystemPort().addDataInstance(reaction);
        condition.getTracers().forEach(((data, tracer) -> ruleHub.getSystemPort().addDataInstance(new Connection(data, tracer))));
    }

}
