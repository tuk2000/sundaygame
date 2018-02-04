package com.sunday.engine.rule;

import com.sunday.engine.databank.SubSystem;
import com.sunday.engine.databank.ports.SystemPort;

public class RuleSystem extends SubSystem {
    public RuleSystem(SystemPort systemPort) {
        super("RuleSystem", systemPort);
    }

    public void addRule(Rule rule) {
    }

    public void deleteRule(Rule rule) {
    }
}
