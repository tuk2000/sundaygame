package com.sunday.tool.drivermonitor;

import com.sunday.engine.common.DataSignal;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.databank.SystemPortSharing;
import com.sunday.engine.environment.driver.keyboard.KeyBoard;
import com.sunday.engine.environment.driver.keyboard.KeyBoardSignal;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;
import com.sunday.tool.ToolExtender;

import java.util.function.BiConsumer;

public class KeyBoardMonitor extends ToolExtender<KeyBoardMonitorUIController> implements SystemPortSharing {
    private KeyBoard currentKeyBoard;
    private KeyBoardSignal currentKeyBoardSignal = KeyBoardSignal.None;
    private Rule keyBoardDataMonitorRule = new Rule(KeyBoard.class, DataSignal.class, new Reaction<KeyBoard, DataSignal>() {
        @Override
        public void accept(KeyBoard keyBoard, DataSignal dataSignal) {
            switch (dataSignal) {
                case Add:
                    setCurrentKeyBoard(keyBoard);
                    break;
            }
        }
    });
    private Rule keyBoardStatusMonitorRule = new Rule(KeyBoard.class, KeyBoardSignal.class, new Reaction<KeyBoard, KeyBoardSignal>() {
        @Override
        public void accept(KeyBoard keyBoard, KeyBoardSignal keyBoardSignal) {
            if (currentKeyBoard != keyBoard) {
                setCurrentKeyBoard(keyBoard);
            }
            currentKeyBoardSignal = keyBoardSignal;
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
        systemPort.deleteDataInstance(keyBoardDataMonitorRule);
        systemPort.deleteDataInstance(keyBoardStatusMonitorRule);
    }
}
