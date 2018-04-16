package com.sunday.engine.rule;

import com.sunday.engine.common.context.SystemDataContext;

public class RuleContext extends SystemDataContext<Rule> {
    public RuleContext(Rule rule) {
        super(rule);
    }

    @Override
    public void evaluate() {

    }
}
