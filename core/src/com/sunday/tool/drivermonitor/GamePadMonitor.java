package com.sunday.tool.drivermonitor;

import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.common.signal.DataSignal;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.databank.SystemPortSharing;
import com.sunday.engine.environment.driver.DriverContext;
import com.sunday.engine.environment.driver.gamepad.GamePad;
import com.sunday.engine.environment.driver.gamepad.GamePadSignal;
import com.sunday.engine.rule.ClassCondition;
import com.sunday.engine.rule.ClassReaction;
import com.sunday.engine.rule.Rule;
import com.sunday.tool.ToolExtender;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

public class GamePadMonitor extends ToolExtender<GamePadMonitorUIController> implements SystemPortSharing {
    private Set<GamePad> set = new HashSet<>();
    private Set<GamePad> waitingToBeAddedSet = new HashSet<>();

    private GamePadSignal currentGamePadSignal = GamePadSignal.None;
    private Rule<ClassContext<DriverContext<GamePad>>> gamePadDataMonitorRule
            = new Rule<>(new ClassCondition<>(GamePad.class, DataSignal.class), new ClassReaction<DriverContext<GamePad>>() {
        @Override
        public void accept(DriverContext<GamePad> gamePadDriverContext) {
            DataSignal dataSignal = (DataSignal) gamePadDriverContext.getSignal();
            GamePad gamePad = gamePadDriverContext.getData();
            System.out.println("GamePadMonitor---GamePad---" + dataSignal.name());
            switch (dataSignal) {
                case Add:
                    addGamePad(gamePad);
                    break;
                case Deletion:
                    removeGamePad(gamePad);
                    break;
            }
        }
    });


    private Rule<ClassContext<DriverContext<GamePad>>> gamePadStatusMonitorRule
            = new Rule<>(new ClassCondition<>(GamePad.class, GamePadSignal.class), new ClassReaction<DriverContext<GamePad>>() {
        @Override
        public void accept(DriverContext<GamePad> gamePadDriverContext) {
            currentGamePadSignal = (GamePadSignal) gamePadDriverContext.getSignal();
            GamePad gamePad = gamePadDriverContext.getData();
            System.out.println("GamePadMonitor---GamePad---" + currentGamePadSignal.name());
            if (!set.contains(gamePad)) {
                addGamePad(gamePad);
            }
            flushBuffer();
        }
    });

    private void addGamePadInternal(GamePad gamePad) {
        getUIController().addGamePad(gamePad.controller.getName());
        uiControllerBuffer.addBuffer(gamePad, new BiConsumer<GamePadMonitorUIController, GamePad>() {
            @Override
            public void accept(GamePadMonitorUIController gamePadMonitorUIController, GamePad gamePad) {
                String name = gamePad.controller.getName();
                gamePadMonitorUIController.setGamePadSignal(name, currentGamePadSignal.name());
                switch (currentGamePadSignal) {
                    case ButtonDown:
                    case ButtonUp:
                        gamePadMonitorUIController.setButtonCode(name, gamePad.buttonCode);
                        break;
                    case AxisMove:
                        gamePadMonitorUIController.setAxisInfo(name, gamePad.axisCode, gamePad.axisMoveValue);
                        break;
                    case PovMove:
                        gamePadMonitorUIController.setPovInfo(name, gamePad.povCode, gamePad.povDirection.name());
                        break;
                    case XSliderMove:
                        gamePadMonitorUIController.setXSliderInfo(name, gamePad.xSliderCode, gamePad.xSliderMoveValue);
                        break;
                    case YSliderMove:
                        gamePadMonitorUIController.setYSliderInfo(name, gamePad.ySliderCode, gamePad.ySliderMoveValue);
                        break;
                    case AccelerometerMove:
                        gamePadMonitorUIController.setAccelerometerInfo(name, gamePad.accelerometerCode, gamePad.accelerometerMoveValue.x,
                                gamePad.accelerometerMoveValue.y, gamePad.accelerometerMoveValue.z);
                        break;
                }
            }
        });
    }

    public void addGamePad(GamePad gamePad) {
        if (set.contains(gamePad)) {
            return;
        } else {
            set.add(gamePad);
            if (getUIController() == null) {
                waitingToBeAddedSet.add(gamePad);
                return;
            } else {
                waitingToBeAddedSet.forEach(this::addGamePadInternal);
                waitingToBeAddedSet.clear();
                addGamePadInternal(gamePad);
            }
        }
    }

    public void removeGamePad(GamePad gamePad) {
        if (getUIController() == null) {
            return;
        }
        if (!set.contains(gamePad)) {
            return;
        } else {
            uiControllerBuffer.removeBuffer(gamePad);
            getUIController().removeGamePad(gamePad.controller.getName());
        }
    }

    @Override
    public void connectWith(SystemPort systemPort) {
        systemPort.addDataInstance(gamePadDataMonitorRule);
        systemPort.addDataInstance(gamePadStatusMonitorRule);
    }

    @Override
    public void disconnectWith(SystemPort systemPort) {
        systemPort.removeDataInstance(gamePadDataMonitorRule);
        systemPort.removeDataInstance(gamePadStatusMonitorRule);
    }
}
