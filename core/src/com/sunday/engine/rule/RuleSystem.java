package com.sunday.engine.rule;

import com.sunday.engine.SubSystem;
import com.sunday.engine.common.ClassContext;
import com.sunday.engine.common.DataSignal;
import com.sunday.engine.databank.SystemPort;

public class RuleSystem extends SubSystem {
    public RuleSystem(SystemPort systemPort) {
        super("RuleSystem", systemPort);
        initRuleSystem();
    }

    private void initRuleSystem() {

        Rule ruleDataRule = new Rule(Rule.class, DataSignal.class, new Reaction<ClassContext<Rule>>() {
            @Override
            public void accept(ClassContext<Rule> ruleClassContext) {
                Rule rule = ruleClassContext.getInstance();
                DataSignal dataSignal = (DataSignal) ruleClassContext.getSignal();
                switch (dataSignal) {
                    case Add:
                        System.out.println("Rule added!");
                        rule.mountWith(systemPort);
                        System.out.println(rule.condition.getInfo());
                        break;
                    case Deletion:
                        //System.out.println("Rule removed!");
                        System.out.println(rule.condition.getInfo());
                        //rule.dismountWith(systemPort);
                        break;
                }
            }
        });
        systemPort.addDataInstance(ruleDataRule);
        ruleDataRule.mountWith(systemPort);
    }
}
