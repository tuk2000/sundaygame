package com.sunday.tool.drivermonitor;

import com.badlogic.gdx.Input;
import com.sunday.engine.common.Signal;
import com.sunday.engine.databank.Target;
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
        uiControllerBuffer.addBuffer(keyBoard, new BiConsumer<KeyBoardMonitorUIController, KeyBoard>() {
            @Override
            public void accept(KeyBoardMonitorUIController keyBoardMonitorUIController, KeyBoard keyBoard) {
                keyBoardMonitorUIController.setKeyBoardSignal(keyBoard.keyBoardSignal.name());
                switch (keyBoard.keyBoardSignal) {
                    case Pressed:
                    case Released:
                        keyBoardMonitorUIController.setKeyBoardKey(Input.Keys.toString(keyBoard.key) + '[' + keyBoard.key + ']');
                        keyBoardMonitorUIController.setKeyBoardStatus(Input.Keys.toString(keyBoard.key) + '[' + keyBoard.key + ']' + "-" + keyBoard.keyBoardSignal.name());
                        break;
                    case Typed:
                        keyBoardMonitorUIController.setKeyBoardKey(String.valueOf(keyBoard.character));
                        keyBoardMonitorUIController.setKeyBoardStatus(String.valueOf(keyBoard.character) + "-" + keyBoard.keyBoardSignal.name());
                        break;
                    case None:
                }
            }
        });
    }
}
