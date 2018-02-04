package com.sunday.engine.rule;

import com.sunday.engine.common.Data;

public abstract class Rule implements Data {
    protected Condition condition;
    protected Reaction reaction;
    protected RuleHub ruleHub;

    protected Rule(Condition condition, Reaction reaction) {
        this.condition = condition;
        this.reaction = reaction;
    }

    protected void setRuleHub(RuleHub ruleHub) {
        this.ruleHub = ruleHub;
        ruleHub.getSystemPort().addDataInstance(condition);
        ruleHub.getSystemPort().addDataInstance(reaction);
    }
}
