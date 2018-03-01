package com.sunday.tool.drivermonitor;

import com.badlogic.gdx.Input;
import com.sunday.engine.common.Signal;
import com.sunday.engine.databank.Target;
import com.sunday.engine.driver.keyboard.KeyBoard;
import com.sunday.tool.ToolExtender;

public class KeyBoardMonitor extends ToolExtender<KeyBoardMonitorUIController> implements Target {
    private KeyBoard keyBoard;

    @Override
    public void notify(Signal signal) {
        getController().setKeyBoardSignal(signal.name());
        switch (keyBoard.keyBoardSignal) {
            case Pressed:
            case Released:
                getController().setKeyBoardKey(Input.Keys.toString(keyBoard.key) + '[' + keyBoard.key + ']');
                getController().setKeyBoardStatus(Input.Keys.toString(keyBoard.key) + '[' + keyBoard.key + ']' + "-" + signal.name());
                break;
            case Typed:
                getController().setKeyBoardKey(String.valueOf(keyBoard.character));
                getController().setKeyBoardStatus(String.valueOf(keyBoard.character) + "-" + signal.name());
                break;
            case None:
        }
    }

    public void setKeyBoard(KeyBoard keyBoard) {
        this.keyBoard = keyBoard;
    }
}
