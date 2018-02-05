package com.sunday.engine.rule;

import com.sunday.engine.databank.SubSystem;
import com.sunday.engine.databank.SystemPort;

public class RuleSystem extends SubSystem implements RuleSolver, RuleHub {
    public RuleSystem(SystemPort systemPort) {
        super("RuleSystem", systemPort);
    }

    public void applyRule(Rule rule) {

    }

    public void mount(Rule rule) {
        rule.setRuleHub(this);
    }

    public void disMount(Rule rule) {

    }

    public void addRule(Rule rule) {
        systemPort.addDataInstance(rule);

        mount(rule);
    }

    public void deleteRule(Rule rule) {
        systemPort.deleteDataInstance(rule);

        disMount(rule);
    }

    @Override
    public SystemPort getSystemPort() {
        return systemPort;
    }
}
