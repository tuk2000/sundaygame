package com.sunday.engine.rule;

import com.sunday.engine.common.Connection;
import com.sunday.engine.common.Data;

public class Rule implements Data {
    protected Condition condition;
    protected Reaction reaction;

    public Rule(Condition condition, Reaction reaction) {
        this.condition = condition;
        this.reaction = reaction;
        reaction.bind(condition);
        condition.bind(reaction);
    }

    public void connect(RuleHub ruleHub) {
        ruleHub.getSystemPort().addDataInstance(condition);
        ruleHub.getSystemPort().addDataInstance(reaction);
        condition.getTracers().forEach(((data, tracer) -> ruleHub.getSystemPort().addDataInstance(new Connection(data, tracer))));
    }

    public void disconnect(RuleHub ruleHub) {
        ruleHub.getSystemPort().deleteDataInstance(condition);
        ruleHub.getSystemPort().deleteDataInstance(reaction);
        ruleHub.getSystemPort().getDataList(data -> data instanceof Connection)
                .stream().filter(c -> condition.getTracers().containsValue(((Connection) c).target))
                .forEach(connection -> ruleHub.getSystemPort().deleteDataInstance((Data) connection));
    }
}
