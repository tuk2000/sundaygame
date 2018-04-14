package com.sunday.engine.contextbank;

import com.sunday.engine.environment.driver.DriverContext;
import com.sunday.engine.environment.driver.gamepad.GamePad;
import com.sunday.engine.environment.driver.keyboard.KeyBoard;
import com.sunday.engine.environment.driver.keyboard.KeyBoardSignal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ContextBankImplTest {

    @Test
    void getDataContext() {
        ContextBank contextBank = new ContextBankImpl();
        KeyBoard keyBoard = new KeyBoard();
        DriverContext<KeyBoard> keyBoardDriverContext = contextBank.getDataContext(keyBoard);
        DriverContext driverContext = contextBank.getDataContext(keyBoard);

        Assertions.assertEquals(driverContext, keyBoardDriverContext);

        KeyBoard keyBoard1 = new KeyBoard();
        driverContext = contextBank.getDataContext(keyBoard1);
        Assertions.assertNotEquals(driverContext, keyBoardDriverContext);
    }

    @Test
    void getClassContext() {
        ContextBank contextBank = new ContextBankImpl();
        KeyBoard keyBoard = new KeyBoard();
        DriverContext<KeyBoard> keyBoardDriverContext = contextBank.getDataContext(keyBoard);

        Assertions.assertNotNull(contextBank.getClassContext(KeyBoard.class));
        Assertions.assertEquals(contextBank.getClassContext(KeyBoard.class).getFocusedContext(), keyBoardDriverContext);

        Assertions.assertNotNull(contextBank.getClassContext(GamePad.class));

        keyBoardDriverContext.setSignal(KeyBoardSignal.Pressed);
        keyBoardDriverContext.evaluate();
        Assertions.assertEquals(contextBank.getClassContext(KeyBoard.class).getFocusedContext(), keyBoardDriverContext);
        Assertions.assertEquals(contextBank.getClassContext(KeyBoard.class).getFocusedContext().getSignal(), KeyBoardSignal.Pressed);

        KeyBoard keyBoard1 = new KeyBoard();
        DriverContext<KeyBoard> keyBoardDriverContext1 = contextBank.getDataContext(keyBoard1);
        Assertions.assertNotEquals(contextBank.getClassContext(KeyBoard.class).getFocusedContext(), keyBoardDriverContext);
        Assertions.assertEquals(contextBank.getClassContext(KeyBoard.class).getFocusedContext(), keyBoardDriverContext1);

    }
}