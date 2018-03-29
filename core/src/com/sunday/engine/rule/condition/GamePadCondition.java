package com.sunday.engine.rule.condition;

import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.driver.gamepad.GamePad;
import com.sunday.engine.driver.gamepad.GamePadSignal;

import java.util.ArrayList;
import java.util.List;

public class GamePadCondition extends DataCondition<GamePad, GamePadSignal> {
    public GamePadCondition() {

    }

    public static GamePadCondition buttonDown(int buttonCode) {
        GamePadCondition gamePadCondition = new GamePadCondition();
        gamePadCondition.setSignals(GamePadSignal.ButtonDown);
        gamePadCondition.predicates.add(gamePad ->
                gamePad.buttonCode == buttonCode
        );
        gamePadCondition.setExtraInfo("Button=[" + buttonCode + "]");
        return gamePadCondition;
    }

    public static GamePadCondition buttonDown(int... buttonCodes) {
        GamePadCondition gamePadCondition = new GamePadCondition();
        gamePadCondition.setSignals(GamePadSignal.ButtonDown);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < buttonCodes.length; i++) {
            list.add(buttonCodes[i]);
        }
        list.stream().forEach(buttonCode -> gamePadCondition.predicates.add(gamePad -> gamePad.buttonCode == buttonCode));
        String extraInfo = "Button=[";
        for (int i = 0; i < list.size(); i++) {
            extraInfo += list.get(i) + " ";
        }
        gamePadCondition.setAndOperation(false);
        gamePadCondition.setExtraInfo(extraInfo + "]");
        return gamePadCondition;
    }

    public static GamePadCondition pivotMoved() {
        GamePadCondition gamePadCondition = new GamePadCondition();
        gamePadCondition.setSignals(GamePadSignal.PovMove);
        return gamePadCondition;
    }

    @Override
    public void connectWith(SystemPort systemPort) {
        if (systemPort.searchInDataBank(GamePad.class).size() == 0) return;
        setData((GamePad) systemPort.searchInDataBank(GamePad.class).get(0));
        super.connectWith(systemPort);
    }
}
