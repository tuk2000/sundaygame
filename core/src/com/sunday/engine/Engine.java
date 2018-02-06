package com.sunday.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controllers;
import com.sunday.engine.databank.DataBank;
import com.sunday.engine.databank.DataBankImpl;
import com.sunday.engine.driver.DriverSystem;
import com.sunday.engine.event.EventSystem;
import com.sunday.engine.event.driver.DriverEventTransfer;
import com.sunday.engine.event.synchronize.CollisionEventTransfer;
import com.sunday.engine.rule.RuleSystem;

public class Engine {
    private DataBank dataBank;
    private DriverSystem driverSystem;
    private EventSystem eventSystem;
    private RuleSystem ruleSystem;

    public Engine() {
        dataBank = new DataBankImpl();
        driverSystem = new DriverSystem(dataBank.getSystemPort(DriverSystem.class));
        eventSystem = new EventSystem(dataBank.getSystemPort(EventSystem.class));
        ruleSystem = new RuleSystem(dataBank.getSystemPort(RuleSystem.class));

        DriverEventTransfer driverEventTransfer = new DriverEventTransfer(driverSystem);
        Gdx.input.setInputProcessor(driverEventTransfer);
        Controllers.addListener(driverEventTransfer);

        CollisionEventTransfer collisionEventTransfer = new CollisionEventTransfer();
        eventSystem.addEventTransfer(driverEventTransfer);
        eventSystem.addEventTransfer(collisionEventTransfer);




    }
}
