package com.sunday.tool.drivermonitor;

import com.sunday.engine.common.DataSignal;
import com.sunday.engine.common.MetaDataContext;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.databank.SystemPortSharing;
import com.sunday.engine.environment.driver.gamepad.GamePad;
import com.sunday.engine.environment.driver.gamepad.GamePadSignal;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;
import com.sunday.tool.ToolExtender;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

public class GamePadMonitor extends ToolExtender<GamePadMonitorUIController> implements SystemPortSharing {
    private Set<GamePad> set = new HashSet<>();
    private Set<GamePad> waitingToBeAddedSet = new HashSet<>();

    private GamePadSignal currentGamePadSignal = GamePadSignal.None;
    private Rule gamePadDataMonitorRule = new Rule(GamePad.class, DataSignal.class, new Reaction<MetaDataContext<GamePad>>() {
        @Override
        public void accept(MetaDataContext<GamePad> gamePadMetaDataContext) {
            DataSignal dataSignal = (DataSignal) gamePadMetaDataContext.getSignal();
            GamePad gamePad = gamePadMetaDataContext.getMetaData();
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
    private Rule gamePadStatusMonitorRule = new Rule(GamePad.class, GamePadSignal.class, new Reaction<MetaDataContext<GamePad>>() {
        @Override
        public void accept(MetaDataContext<GamePad> gamePadMetaDataContext) {
            currentGamePadSignal = (GamePadSignal) gamePadMetaDataContext.getSignal();
            GamePad gamePad = gamePadMetaDataContext.getMetaData();
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
