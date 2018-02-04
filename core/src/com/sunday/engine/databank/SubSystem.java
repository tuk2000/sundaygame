package com.sunday.engine.databank;

import com.sunday.engine.databank.ports.SystemPort;

public abstract class SubSystem {
    protected String name;
    protected SystemPort systemPort;

    protected SubSystem(String name, SystemPort systemPort) {
        this.name = name;
        this.systemPort = systemPort;
    }

    public String getName() {
        return name;
    }
}
