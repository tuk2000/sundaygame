package com.sunday.engine.environment.driver.gamepad;

import com.sunday.engine.environment.driver.DriverCondition;
import com.sunday.engine.environment.driver.DriverContext;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GamePadCondition extends DriverCondition<GamePad> {
    protected GamePadCondition(Predicate<DriverContext<GamePad>> driverContextPredicate) {
        super(driverContextPredicate);
    }

    public static GamePadCondition anyGamePadSignal() {
        GamePadCondition gamePadCondition = new GamePadCondition(driverContext -> true);
        gamePadCondition.signalCondition.setSignals(GamePadSignal.class);
        return gamePadCondition;
    }

    public static GamePadCondition buttonDown(int buttonCode) {
        GamePadCondition gamePadCondition = new GamePadCondition(driverContext -> driverContext.getData().buttonCode == buttonCode);
        gamePadCondition.signalCondition.setSignals(GamePadSignal.ButtonDown);
        gamePadCondition.setExtraInfoEntry("Button", String.valueOf(buttonCode));
        return gamePadCondition;
    }

    public static GamePadCondition buttonDown(int... buttonCodes) {
        List<Integer> list = new ArrayList<>();

        for (int buttonCode : buttonCodes) {
            list.add(buttonCode);
        }

        Predicate<DriverContext<GamePad>> predicate = context -> {
            GamePad gamePad = context.getData();
            return list.contains(gamePad.buttonCode);
        };

        String extraInfo = list.stream().map(integer -> String.valueOf(integer)).collect(Collectors.joining(","));

        GamePadCondition gamePadCondition = new GamePadCondition(predicate);
        gamePadCondition.signalCondition.setSignals(GamePadSignal.ButtonDown);
        gamePadCondition.setExtraInfoEntry("Button", extraInfo);
        return gamePadCondition;
    }

    public static GamePadCondition pivotMoved() {
        GamePadCondition gamePadCondition = new GamePadCondition(context -> true);
        gamePadCondition.signalCondition.setSignals(GamePadSignal.PovMove);
        return gamePadCondition;
    }

    @Override
    protected void generateExtraInfo(DriverContext<GamePad> gamePadDriverContext) {
        super.generateExtraInfo(gamePadDriverContext);
        setExtraInfoEntry("ConditionType", "MouseCondition");
    }
}
