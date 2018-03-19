package com.sunday.engine.rule.condition;

import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.driver.gamepad.GamePad;
import com.sunday.engine.driver.gamepad.GamePadSignal;

import java.util.Arrays;
import java.util.List;

public class GamePadCondition extends DataCondition<GamePad> {
    private GamePadCondition() {

    }

    public static GamePadCondition buttonDown(int buttonCode) {
        GamePadCondition gamePadCondition = new GamePadCondition();
        gamePadCondition.setSignals(GamePadSignal.ButtonDown);
        gamePadCondition.predicates.add(gamePad ->
                gamePad.buttonCode == buttonCode
        );
        return gamePadCondition;
    }

    public static GamePadCondition buttonDown(int... buttonCodes) {
        GamePadCondition gamePadCondition = new GamePadCondition();
        gamePadCondition.setSignals(GamePadSignal.ButtonDown);
        List list = Arrays.asList(buttonCodes);
        gamePadCondition.predicates.add(gamePad ->
                list.contains(gamePad.buttonCode)
        );
        gamePadCondition.setAndOperation(false);
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
