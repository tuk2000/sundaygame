package com.sunday.engine.rule;

public interface RuleSolver {
    void applyRule(Rule rule);

    void mount(Rule rule);

    void disMount(Rule rule);
}
