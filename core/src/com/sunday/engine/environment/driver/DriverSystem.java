package com.sunday.engine.environment.driver;

import com.badlogic.gdx.controllers.Controller;
import com.sunday.engine.SubSystem;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.environment.driver.gamepad.GamePad;
import com.sunday.engine.environment.driver.gamepad.GamePadCondition;
import com.sunday.engine.environment.driver.gamepad.GamePadHub;
import com.sunday.engine.environment.driver.keyboard.KeyBoard;
import com.sunday.engine.environment.driver.keyboard.KeyBoardCondition;
import com.sunday.engine.environment.driver.mouse.Mouse;
import com.sunday.engine.environment.driver.mouse.MouseCondition;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;
import com.sunday.engine.rule.RuleSignal;
import com.sunday.tool.ToolApplication;

import java.util.List;
import java.util.stream.Collectors;

public class DriverSystem extends SubSystem {
    private KeyBoard keyBoard = new KeyBoard();
    private Mouse mouse = new Mouse();
    private GamePadHub gamePadHub = new GamePadHub();

    private Rule keyBoardConditionMountingRule = new Rule(Rule.class, RuleSignal.Mounting, new Reaction<Rule, RuleSignal>() {
        @Override
        public void accept(Rule rule, RuleSignal ruleSignal) {
            if (rule.getCondition() instanceof KeyBoardCondition) {
                rule.getCondition().setData(keyBoard);
            }
        }
    });

    private Rule mouseConditionMountingRule = new Rule(Rule.class, RuleSignal.Mounting, new Reaction<Rule, RuleSignal>() {
        @Override
        public void accept(Rule rule, RuleSignal ruleSignal) {
            if (rule.getCondition() instanceof MouseCondition) {
                rule.getCondition().setData(mouse);
            }
        }
    });

    private Rule gamePadConditionMountingRule = new Rule(Rule.class, RuleSignal.Mounting, new Reaction<Rule, RuleSignal>() {
        @Override
        public void accept(Rule rule, RuleSignal ruleSignal) {
            if (rule.getCondition() instanceof GamePadCondition) {
                rule.getCondition().setData(gamePadHub);
            }
        }
    });

    public DriverSystem(SystemPort systemPort) {
        super("DriverSystem", systemPort);
        addDriver(keyBoard);
        addDriver(mouse);
        addDriver(gamePadHub);
        systemPort.addDataInstance(keyBoardConditionMountingRule);
        systemPort.addDataInstance(mouseConditionMountingRule);
        systemPort.addDataInstance(gamePadConditionMountingRule);
    }

    public void addDriver(Driver driver) {
        systemPort.addDataInstance(driver);
    }

    public void removeDriver(Driver driver) {
        systemPort.deleteDataInstance(driver);
    }

    public KeyBoard getDefaultKeyBoard() {
        return keyBoard;
    }

    public Mouse getDefaultMouse() {
        return mouse;
    }

    public GamePadHub getGamePadHub() {
        return gamePadHub;
    }

    public GamePad getMatchGamePad(Controller controller) {
        List<GamePad> list = systemPort.instancesOf(GamePad.class);
        list = list.stream().filter(gamePad -> gamePad.controller.equals(controller)).collect(Collectors.toList());
        return list.get(0);
    }

    public void connectToDriverMonitor() {
        ToolApplication.keyBoardMonitor.connectWith(systemPort);
        ToolApplication.mouseMonitor.connectWith(systemPort);
        ToolApplication.gamePadMonitor.connectWith(systemPort);
    }
}
