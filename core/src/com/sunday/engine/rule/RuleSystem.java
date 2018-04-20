package com.sunday.engine.rule;

import com.sunday.engine.SubSystem;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.common.context.DataContext;
import com.sunday.engine.common.signal.DataSignal;
import com.sunday.engine.contextbank.ContextBank;
import com.sunday.engine.databank.SystemPort;

import java.util.ArrayList;
import java.util.List;

public class RuleSystem extends SubSystem {

    private ContextBank contextBank;
    private List<DataProvider> dataProviders = new ArrayList<>();

    public RuleSystem(SystemPort systemPort, ContextBank contextBank) {
        super("RuleSystem", systemPort);
        initRuleSystem();
        this.contextBank = contextBank;
    }

    private void initRuleSystem() {
        systemPort.addConnection(Rule.class, (data, signal) -> {
            Rule rule = (Rule) data;
            if (signal instanceof DataSignal) {
                DataSignal dataSignal = (DataSignal) signal;
                switch (dataSignal) {
                    case Add:
                        System.out.println("Rule added!");
                        mountRule(rule);
                        systemPort.broadcast(rule, RuleSignal.Mounting);
                        System.out.println(rule.condition.getInfo());
                        break;
                    case Deletion:
                        System.out.println("Rule removed!");
                        systemPort.broadcast(rule, RuleSignal.Dismounting);
                        dismountRule(rule);
                }
            }
        });
    }

    public void addDataProvider(DataProvider dataProvider) {
        dataProviders.add(dataProvider);
    }

    @SuppressWarnings("unchecked")
    private void mountRule(Rule rule) {
        if (rule.condition instanceof ClassCondition) {
            ClassCondition classCondition = (ClassCondition) rule.condition;
            ClassContext classContext = contextBank.getClassContext(classCondition.getSensedClass());
            classContext.setPredicateConsumer(rule.condition, rule.reaction);
            rule.condition.generateInfoWith(classContext);
        } else if (rule.condition instanceof DataCondition) {
            Data data = null;
            for (DataProvider dataProvider : dataProviders) {
                if (dataProvider.isSuitedFor(rule.condition)) {
                    data = dataProvider.requestData((DataCondition) rule.condition);
                    if (data != null) {
                        DataContext dataContext = contextBank.getDataContext(data);
                        dataProvider.feedback(data, dataContext);
                        dataContext.setPredicateConsumer(rule.condition, rule.reaction);
                        rule.condition.generateInfoWith(dataContext);
                    }
                    break;
                }
            }
            try {
                if (data == null) {
                    if (rule.condition instanceof PreAssignedDataCondition) {
                        PreAssignedDataCondition preAssignedDataCondition = (PreAssignedDataCondition) rule.condition;
                        data = preAssignedDataCondition.getData();
                        DataContext dataContext = contextBank.getDataContext(data);
                        dataContext.setPredicateConsumer(rule.condition, rule.reaction);
                        rule.condition.generateInfoWith(dataContext);
                    }
                    if (data == null) {
                        throw new Exception("Unable to initial DataContext for Rule :  " + rule.toString() + " condition class : " + rule.condition.getClass().getName());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void dismountRule(Rule rule) {

    }
}
