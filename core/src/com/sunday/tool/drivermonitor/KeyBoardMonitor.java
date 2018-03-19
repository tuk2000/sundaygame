package com.sunday.tool.drivermonitor;

import com.sunday.engine.common.Signal;
import com.sunday.engine.common.Target;
import com.sunday.engine.driver.keyboard.KeyBoard;
import com.sunday.engine.driver.keyboard.KeyBoardSignal;
import com.sunday.tool.ToolExtender;

import java.util.function.BiConsumer;

public class KeyBoardMonitor extends ToolExtender<KeyBoardMonitorUIController> implements Target {
    private KeyBoard keyBoard;
    private KeyBoardSignal keyBoardSignal = KeyBoardSignal.None;

    public KeyBoardMonitor() {

    }

    @Override
    public void notify(Signal signal) {
        if (signal instanceof KeyBoardSignal) {
            keyBoardSignal = (KeyBoardSignal) signal;
            flushBuffer();
        }
    }

    public void setKeyBoard(KeyBoard keyBoard) {
        if (this.keyBoard != keyBoard) {
            uiControllerBuffer.removeBuffer(this.keyBoard);
        }
        this.keyBoard = keyBoard;
        uiControllerBuffer.addBuffer(keyBoard,
                (BiConsumer<KeyBoardMonitorUIController, KeyBoard>) (keyBoardMonitorUIController, keyBoard1) -> {
                    keyBoardMonitorUIController.setKeyBoardSignal(keyBoardSignal.name());
                    keyBoardMonitorUIController.setKeyBoardKey(keyBoard1.character);
                    keyBoardMonitorUIController.setKeyBoardStatus(keyBoard1.character + "-" + keyBoardSignal.name());
                });
    }
}
