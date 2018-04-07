package com.sunday.engine.environment.driver;

import com.badlogic.gdx.controllers.Controller;
import com.sunday.engine.SubSystem;
import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.databank.ContextBank;
import com.sunday.engine.databank.SystemContextBuilder;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.environment.driver.gamepad.GamePad;
import com.sunday.engine.environment.driver.gamepad.GamePadHub;
import com.sunday.engine.environment.driver.keyboard.KeyBoard;
import com.sunday.engine.environment.driver.mouse.Mouse;
import com.sunday.tool.ToolApplication;

import java.util.List;
import java.util.stream.Collectors;

public class DriverSystem extends SubSystem implements SystemContextBuilder {
    private KeyBoard keyBoard = new KeyBoard();
    private Mouse mouse = new Mouse();
    private GamePadHub gamePadHub = new GamePadHub();

    public DriverSystem(SystemPort systemPort) {
        super("DriverSystem", systemPort);
        addDriver(keyBoard);
        addDriver(mouse);
        addDriver(gamePadHub);
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

    @Override
    public void buildSystemContext(ContextBank contextBank) {
        contextBank.addClassContext(new ClassContext(KeyBoard.class));
        contextBank.addClassContext(new ClassContext(Mouse.class));
        contextBank.addClassContext(new ClassContext(GamePad.class));
        contextBank.addClassContext(new ClassContext(GamePadHub.class));
    }
}
