package com.sunday.engine.rule;

import com.sunday.engine.SubSystem;
import com.sunday.engine.common.ClassContext;
import com.sunday.engine.common.DataSignal;
import com.sunday.engine.databank.ContextBank;
import com.sunday.engine.databank.SystemContextUser;
import com.sunday.engine.databank.SystemPort;

public class RuleSystem extends SubSystem {
    private ContextBank contextBank;

    public RuleSystem(SystemPort systemPort, ContextBank contextBank) {
        super("RuleSystem", systemPort);
        initRuleSystem();
        this.contextBank = contextBank;
    }

    private void initRuleSystem() {

        Rule ruleDataRule = new Rule(Rule.class, DataSignal.class, new Reaction<ClassContext<Rule>>() {
            @Override
            public void accept(ClassContext<Rule> ruleClassContext) {
                Rule rule = ruleClassContext.getInstance();
                DataSignal dataSignal = (DataSignal) ruleClassContext.getSignal();
                switch (dataSignal) {
                    case Add:
                        //System.out.println("Rule added!");
                        systemPort.broadcast(rule, RuleSignal.Mounting);
                        if (rule.getCondition() instanceof SystemContextUser) {
                            ((SystemContextUser) rule.getCondition()).useSystemContext(contextBank);
                        }
                        System.out.println(rule.condition.getInfo());
                        break;
                    case Deletion:
                        //System.out.println("Rule removed!");
                        System.out.println(rule.condition.getInfo());
                        systemPort.broadcast(rule, RuleSignal.Dismounting);
                        break;
                }
            }
        });
        systemPort.addDataInstance(ruleDataRule);
    }
}
