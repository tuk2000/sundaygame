package com.sunday.engine.rule;

import com.sunday.engine.common.DataSignal;
import com.sunday.engine.common.SubSystem;
import com.sunday.engine.databank.ClassSensor;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.driver.keyboard.KeyBoard;
import com.sunday.engine.rule.condition.DataCondition;
import com.sunday.engine.rule.condition.KeyBoardCondition;

public class RuleSystem extends SubSystem implements RuleSolver, RuleHub {
    public RuleSystem(SystemPort systemPort) {
        super("RuleSystem", systemPort);
        initRuleSystem();
        KeyBoardCondition.setKeyBoard((KeyBoard) systemPort.searchInDataBank(KeyBoard.class).get(0));
    }

    private void initRuleSystem() {
        ClassSensor classSensor = ClassSensor.getClassSensor(Rule.class);
        Condition ruleAddCondition = DataCondition.classSignals(Rule.class, DataSignal.Add);
        Reaction ruleAddReaction = new Reaction() {
            @Override
            public void run() {
                System.out.println("New Rule added!");
                System.out.println(((Rule) (classSensor.getSensedInstance())).condition.getInfo());
            }
        };
        Condition ruleModificationCondition = DataCondition.classSignals(Rule.class, DataSignal.Modification);
        Reaction ruleModificationReaction = new Reaction() {
            @Override
            public void run() {
                System.out.println("Rule modified!");
                System.out.println(((Rule) (classSensor.getSensedInstance())).condition.getInfo());
            }
        };
        Condition ruleDeletionCondition = DataCondition.classSignals(Rule.class, DataSignal.Deletion);
        Reaction ruleDeletionReaction = new Reaction() {
            @Override
            public void run() {
                System.out.println("Rule removed!");
                System.out.println(((Rule) (classSensor.getSensedInstance())).condition.getInfo());
            }
        };
        mount(new Rule(ruleAddCondition, ruleAddReaction));
        mount(new Rule(ruleModificationCondition, ruleModificationReaction));
        mount(new Rule(ruleDeletionCondition, ruleDeletionReaction));
    }

    public void mount(Rule rule) {
        systemPort.addDataInstance(rule);
        rule.connect(this);
    }

    public void disMount(Rule rule) {
        systemPort.deleteDataInstance(rule);
        rule.disconnect(this);
    }

    @Override
    public SystemPort getSystemPort() {
        return systemPort;
    }
}
