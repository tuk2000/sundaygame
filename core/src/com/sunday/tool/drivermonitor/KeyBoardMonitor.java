package com.sunday.tool.drivermonitor;

import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.common.signal.DataSignal;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.databank.SystemPortSharing;
import com.sunday.engine.environment.driver.DriverContext;
import com.sunday.engine.environment.driver.keyboard.KeyBoard;
import com.sunday.engine.environment.driver.keyboard.KeyBoardSignal;
import com.sunday.engine.rule.ClassCondition;
import com.sunday.engine.rule.ClassReaction;
import com.sunday.engine.rule.Rule;
import com.sunday.tool.ToolExtender;

import java.util.function.BiConsumer;

public class KeyBoardMonitor extends ToolExtender<KeyBoardMonitorUIController> implements SystemPortSharing {
    private KeyBoard currentKeyBoard;
    private KeyBoardSignal currentKeyBoardSignal = KeyBoardSignal.None;
    private Rule<ClassContext<DriverContext<KeyBoard>>> keyBoardDataMonitorRule
            = new Rule<>(new ClassCondition<>(KeyBoard.class, DataSignal.class), new ClassReaction<DriverContext<KeyBoard>>() {
        @Override
        public void accept(DriverContext<KeyBoard> keyBoardDriverContext) {
            KeyBoard keyBoard = keyBoardDriverContext.getEnvironmentData();
            currentKeyBoardSignal = (KeyBoardSignal) keyBoardDriverContext.getSignal();
            DataSignal dataSignal = (DataSignal) keyBoardDriverContext.getSignal();
            switch (dataSignal) {
                case Add:
                    setCurrentKeyBoard(keyBoard);
                    break;
            }
        }
    });
    private Rule<ClassContext<DriverContext<KeyBoard>>> keyBoardStatusMonitorRule = new Rule<>(new ClassCondition<>(KeyBoard.class, KeyBoardSignal.class), new ClassReaction<DriverContext<KeyBoard>>() {
        @Override
        public void accept(DriverContext<KeyBoard> keyBoardDriverContext) {
            KeyBoard keyBoard = keyBoardDriverContext.getEnvironmentData();
            currentKeyBoardSignal = (KeyBoardSignal) keyBoardDriverContext.getSignal();
            if (currentKeyBoard != keyBoard) {
                setCurrentKeyBoard(keyBoard);
            }
            flushBuffer();
        }
    });

    public KeyBoardMonitor() {

    }

    public void setCurrentKeyBoard(KeyBoard currentKeyBoard) {
        if (this.currentKeyBoard != currentKeyBoard) {
            uiControllerBuffer.removeBuffer(this.currentKeyBoard);
        }
        this.currentKeyBoard = currentKeyBoard;
        uiControllerBuffer.addBuffer(currentKeyBoard,
                (BiConsumer<KeyBoardMonitorUIController, KeyBoard>) (keyBoardMonitorUIController, keyBoard1) -> {
                    keyBoardMonitorUIController.setKeyBoardSignal(currentKeyBoardSignal.name());
                    keyBoardMonitorUIController.setKeyBoardKey(keyBoard1.character);
                    keyBoardMonitorUIController.setKeyBoardStatus(keyBoard1.character + "-" + currentKeyBoardSignal.name());
                });
    }

    @Override
    public void connectWith(SystemPort systemPort) {
        systemPort.addDataInstance(keyBoardDataMonitorRule);
        systemPort.addDataInstance(keyBoardStatusMonitorRule);
    }

    @Override
    public void disconnectWith(SystemPort systemPort) {
        systemPort.removeDataInstance(keyBoardDataMonitorRule);
        systemPort.removeDataInstance(keyBoardStatusMonitorRule);
    }
}
