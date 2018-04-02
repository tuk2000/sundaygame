package com.sunday.engine.environment.driver;

import com.badlogic.gdx.controllers.Controller;
import com.sunday.engine.SubSystem;
import com.sunday.engine.common.ClassContext;
import com.sunday.engine.common.MetaDataContext;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.environment.driver.gamepad.GamePad;
import com.sunday.engine.environment.driver.gamepad.GamePadHub;
import com.sunday.engine.environment.driver.keyboard.KeyBoard;
import com.sunday.engine.environment.driver.mouse.Mouse;
import com.sunday.engine.rule.ClassCondition;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;
import com.sunday.engine.rule.RuleSignal;
import com.sunday.tool.ToolApplication;

import java.util.List;
import java.util.stream.Collectors;

public class DriverSystem extends SubSystem {
    private KeyBoard keyBoard = new KeyBoard();
    private DriverContext<KeyBoard> keyBoardDriverContext = new DriverContext<>(keyBoard);
    private Mouse mouse = new Mouse();
    private DriverContext<Mouse> mouseDriverContext = new DriverContext<>(mouse);
    private GamePadHub gamePadHub = new GamePadHub();

    private Rule driverRuleContextRule = new Rule(new ClassCondition(Rule.class, RuleSignal.Mounting), new Reaction<ClassContext<Rule>>() {
        @Override
        public void accept(ClassContext<Rule> ruleClassContext) {
            Class sensedClass = ruleClassContext.getSensedClass();
            Rule rule = ruleClassContext.getInstance();
            if (sensedClass.equals(KeyBoard.class)) {
                ((MetaDataContext<KeyBoard>) rule.getContext()).setMetaData(keyBoard);
            } else if (sensedClass.equals(KeyBoard.class)) {
                ((MetaDataContext<Mouse>) rule.getContext()).setMetaData(mouse);
            } else if (sensedClass.equals(GamePadHub.class)) {
                ((MetaDataContext<GamePadHub>) rule.getContext()).setMetaData(gamePadHub);
            }
        }
    });

    public DriverSystem(SystemPort systemPort) {
        super("DriverSystem", systemPort);
        addDriver(keyBoard);
        addDriver(mouse);
        addDriver(gamePadHub);
        systemPort.addDataInstance(driverRuleContextRule);
    }

    public void addDriver(Driver driver) {
        systemPort.addDataInstance(driver);
    }

    public void removeDriver(Driver driver) {
        systemPort.removeDataInstance(driver);
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
