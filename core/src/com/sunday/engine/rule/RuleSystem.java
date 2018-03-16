package com.sunday.engine.rule;

import com.sunday.engine.common.DataSignal;
import com.sunday.engine.common.SourceClass;
import com.sunday.engine.common.SubSystem;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.rule.condition.ClassCondition;

public class RuleSystem extends SubSystem {
    public RuleSystem(SystemPort systemPort) {
        super("RuleSystem", systemPort);
        initRuleSystem();
    }

    private void initRuleSystem() {

        Rule addRule = new Rule(new ClassCondition(Rule.class, DataSignal.Add), new Reaction<SourceClass<Rule>>() {
            @Override
            public void accept(SourceClass<Rule> ruleSourceClass) {
                Rule rule = ruleSourceClass.getSensedData();
                System.out.println("Rule added!");
                rule.mountWith(systemPort);
                System.out.println(rule.condition.getInfo());
            }
        });
        systemPort.addDataInstance(addRule);
        addRule.mountWith(systemPort);

        Rule deleteRule = new Rule(new ClassCondition(Rule.class, DataSignal.Deletion), new Reaction<SourceClass<Rule>>() {
            @Override
            public void accept(SourceClass<Rule> ruleSourceClass) {
                Rule rule = ruleSourceClass.getSensedData();
                System.out.println("Rule removed!");
                System.out.println(rule.condition.getInfo());
                rule.dismountWith(systemPort);
            }
        });
        systemPort.addDataInstance(deleteRule);
    }
}
