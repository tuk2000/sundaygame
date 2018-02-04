package com.sunday.engine;

import com.sunday.engine.databank.DataBank;
import com.sunday.engine.databank.DataBankImpl;
import com.sunday.engine.driver.DriverSystem;
import com.sunday.engine.events.EventSystem;
import com.sunday.engine.rules.RuleSystem;

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
    }
}
