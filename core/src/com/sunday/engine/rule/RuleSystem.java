package com.sunday.engine.rule;

import com.sunday.engine.databank.SubSystem;
import com.sunday.engine.databank.ports.SystemPort;

public class RuleSystem extends SubSystem {
    public RuleSystem(SystemPort systemPort) {
        super("RuleSystem", systemPort);
    }

    private void applyRule(Rule rule) {

    }

    private void mount(Rule rule) {

    }

    private void disMount(Rule rule) {

    }

    public void addRule(Rule rule) {
        systemPort.addDataInstance(rule);
        mount(rule);
    }

    public void deleteRule(Rule rule) {
        systemPort.deleteDataInstance(rule);
        disMount(rule);
    }
}
