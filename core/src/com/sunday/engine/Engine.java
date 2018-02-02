package com.sunday.engine;

import com.sunday.engine.databank.DataBank;
import com.sunday.engine.databank.DataBankImpl;
import com.sunday.engine.databank.port.HolderPort;
import com.sunday.engine.driver.DriverHub;
import com.sunday.engine.events.EventPoster;
import com.sunday.engine.rules.RuleSystem;
import com.sunday.engine.scenario.eventpocess.EventDispatcher;

public class Engine {
    private DataBank dataBank;
    private DriverHub driverHub;
    private EventPoster eventPoster;
    private EventDispatcher eventDispatcher;
    private RuleSystem ruleSystem;

    public Engine() {
        dataBank = new DataBankImpl();
        driverHub = new DriverHub();
        HolderPort driverHubPort = dataBank.getHolderPort(driverHub);
    }
}
