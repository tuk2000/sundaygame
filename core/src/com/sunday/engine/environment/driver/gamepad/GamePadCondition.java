package com.sunday.engine.environment.driver.gamepad;

import com.sunday.engine.common.MetaDataContext;
import com.sunday.engine.databank.ContextBank;
import com.sunday.engine.databank.SystemContextUser;
import com.sunday.engine.rule.MetaDataCondition;

import java.util.ArrayList;
import java.util.List;

public class GamePadCondition extends MetaDataCondition<GamePad> implements SystemContextUser {

    public static GamePadCondition buttonDown(int buttonCode) {
        GamePadCondition gamePadCondition = new GamePadCondition();
        gamePadCondition.setSignals(GamePadSignal.ButtonDown);
        gamePadCondition.predicates.add(metaDataContext ->
                metaDataContext.getMetaData().buttonCode == buttonCode
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
        list.stream().forEach(buttonCode -> gamePadCondition.predicates.add(metaDataContext ->
                metaDataContext.getMetaData().buttonCode == buttonCode));
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
    public void useSystemContext(ContextBank contextBank) {
        setContext((MetaDataContext<GamePad>) contextBank.getSystemContext("GamePad"));
        generateMainInfo();
    }
}
