package com.sunday.engine.rule;

import com.sunday.engine.SubSystem;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;
import com.sunday.engine.common.Target;
import com.sunday.engine.common.signal.DataSignal;
import com.sunday.engine.databank.SystemPort;

import java.util.ArrayList;
import java.util.List;

public class RuleSystem extends SubSystem {
    private ClassContextConstructor classConditionConstructor = new ClassContextConstructor();
    private List<ContextConstructor> contextConstructors = new ArrayList<>();

    public RuleSystem(SystemPort systemPort) {
        super("RuleSystem", systemPort);
        initRuleSystem();
    }

    private void initRuleSystem() {
        systemPort.addConnection(Rule.class, new Target() {
            @Override
            public void notify(Data data, Signal signal) {
                Rule rule = (Rule) data;
                if (signal instanceof DataSignal) {
                    DataSignal dataSignal = (DataSignal) signal;
                    switch (dataSignal) {
                        case Add:
                            System.out.println("Rule added!");
                            systemPort.broadcast(rule, RuleSignal.Mounting);
                            if (classConditionConstructor.accept(rule.getCondition())) {
                                classConditionConstructor.construct((ClassCondition) rule.getCondition());
                            }
                            contextConstructors.forEach(contextConstructor -> {
                                if (contextConstructor.test(rule.getCondition())) {
                                    contextConstructor.accept(rule.getCondition());
                                }
                            });
                            System.out.println(rule.condition.getInfo());
                            break;
                        case Deletion:
                            System.out.println("Rule removed!");
                            System.out.println(rule.condition.getInfo());
                            systemPort.broadcast(rule, RuleSignal.Dismounting);
                            break;
                    }
                }
            }
        });
//        Rule<ClassContext<RuleContext>> ruleDataRule
//                = new Rule<>(new ClassCondition<>(Rule.class, DataSignal.class), new ClassReaction<RuleContext>() {
//            @Override
//            public void accept(RuleContext ruleContext) {
//                Rule<? extends Context> rule = ruleContext.getSystemData();
//                DataSignal dataSignal = (DataSignal) ruleContext.getSignal();
//                switch (dataSignal) {
//                    case Add:
//                        System.out.println("Rule added!");
//                        systemPort.broadcast(rule, RuleSignal.Mounting);
//                        if (classConditionConstructor.accept(rule.getCondition())) {
//                            classConditionConstructor.construct((ClassCondition) rule.getCondition());
//                        }
//                        contextConstructors.forEach(contextConstructor -> {
//                            if (contextConstructor.test(rule.getCondition())) {
//                                contextConstructor.accept(rule.getCondition());
//                            }
//                        });
//                        System.out.println(rule.condition.getInfo());
//                        break;
//                    case Deletion:
//                        System.out.println("Rule removed!");
//                        System.out.println(rule.condition.getInfo());
//                        systemPort.broadcast(rule, RuleSignal.Dismounting);
//                        break;
//                }
//            }
//        });
//        classConditionConstructor.construct((ClassCondition) ruleDataRule.getCondition());
//        systemPort.addDataInstance(ruleDataRule);
    }

    public void addContextConstructor(ContextConstructor contextConstructor) {
        contextConstructors.add(contextConstructor);
    }
}
