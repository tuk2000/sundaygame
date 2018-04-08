package com.sunday.engine.environment.driver.gamepad;

import com.sunday.engine.environment.driver.DriverCondition;

import java.util.ArrayList;
import java.util.List;

public class GamePadCondition extends DriverCondition<GamePad> {

    public static GamePadCondition buttonDown(int buttonCode) {
        GamePadCondition gamePadCondition = new GamePadCondition();
        gamePadCondition.setSignals(GamePadSignal.ButtonDown);
        gamePadCondition.predicates.add(driverContext ->
                driverContext.getEnvironmentData().buttonCode == buttonCode
        );
        gamePadCondition.setExtraInfoEntry("Button", String.valueOf(buttonCode));
        return gamePadCondition;
    }

    public static GamePadCondition buttonDown(int... buttonCodes) {
        GamePadCondition gamePadCondition = new GamePadCondition();
        gamePadCondition.setSignals(GamePadSignal.ButtonDown);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < buttonCodes.length; i++) {
            list.add(buttonCodes[i]);
        }
        list.stream().forEach(buttonCode -> gamePadCondition.predicates.add(driverContext ->
                driverContext.getEnvironmentData().buttonCode == buttonCode));
        String extraInfo = "";
        for (int i = 0; i < list.size(); i++) {
            extraInfo += list.get(i) + " ";
        }
        gamePadCondition.setAndOperation(false);
        gamePadCondition.setExtraInfoEntry("Button", extraInfo);
        return gamePadCondition;
    }

    public static GamePadCondition pivotMoved() {
        GamePadCondition gamePadCondition = new GamePadCondition();
        gamePadCondition.setSignals(GamePadSignal.PovMove);
        return gamePadCondition;
    }
}
