package com.sunday.tool.drivermonitor;

import com.badlogic.gdx.Input;
import com.sunday.engine.common.Signal;
import com.sunday.engine.common.Target;
import com.sunday.engine.driver.keyboard.KeyBoard;
import com.sunday.tool.ToolExtender;

import java.util.function.BiConsumer;

public class KeyBoardMonitor extends ToolExtender<KeyBoardMonitorUIController> implements Target {
    private KeyBoard keyBoard;

    public KeyBoardMonitor() {

    }

    @Override
    public void notify(Signal signal) {
        flushBuffer();
    }

    public void setKeyBoard(KeyBoard keyBoard) {
        if (this.keyBoard != null) {
            uiControllerBuffer.removeBuffer(this.keyBoard);
        }
        this.keyBoard = keyBoard;
        uiControllerBuffer.addBuffer(keyBoard,
                (BiConsumer<KeyBoardMonitorUIController, KeyBoard>) (keyBoardMonitorUIController, keyBoard1) -> {
                    keyBoardMonitorUIController.setKeyBoardSignal(keyBoard1.keyBoardSignal.name());
                    switch (keyBoard1.keyBoardSignal) {
                        case Pressed:
                        case Released:
                            keyBoardMonitorUIController.setKeyBoardKey(Input.Keys.toString(keyBoard1.keyCode) + '[' + keyBoard1.keyCode + ']');
                            keyBoardMonitorUIController.setKeyBoardStatus(Input.Keys.toString(keyBoard1.keyCode) + '[' + keyBoard1.keyCode + ']' + "-" + keyBoard1.keyBoardSignal.name());
                            break;
                        case Typed:
                            keyBoardMonitorUIController.setKeyBoardKey(String.valueOf(keyBoard1.character));
                            keyBoardMonitorUIController.setKeyBoardStatus(String.valueOf(keyBoard1.character) + "-" + keyBoard1.keyBoardSignal.name());
                            break;
                        case None:
                    }
                });
    }
}
