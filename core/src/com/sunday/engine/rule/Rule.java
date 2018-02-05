package com.sunday.engine.rule;

import com.sunday.engine.common.Data;

import java.util.List;

public abstract class Rule implements Data {
    protected Condition condition;
    protected Reaction reaction;
    protected List<Tracer> tracers;

    protected Rule(Condition condition, Reaction reaction) {
        this.condition = condition;
        this.reaction = reaction;
    }

    protected void setRuleHub(RuleHub ruleHub) {
        ruleHub.getSystemPort().addDataInstance(condition);
        ruleHub.getSystemPort().addDataInstance(reaction);
    }

    public void check() {
        if (condition.isSatisfied()) {
            reaction.run();
        }
    }
}
