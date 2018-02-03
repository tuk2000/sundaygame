package com.sunday.engine.databank;

import com.sunday.engine.databank.ports.SystemPort;

public abstract class SubSystem {
    protected String name;
    protected SystemPort systemPort;

    protected SubSystem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void connectToDataBank(DataBank dataBank) {
        systemPort = dataBank.getSystemPort(this);
    }
}
