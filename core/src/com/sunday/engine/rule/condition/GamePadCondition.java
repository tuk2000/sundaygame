package com.sunday.engine.rule.condition;

import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.driver.gamepad.GamePad;

public class GamePadCondition extends DataCondition<GamePad> {
    @Override
    protected boolean isSatisfied() {
        return false;
    }

    @Override
    protected void bindWith(SystemPort systemPort) {

    }
}
