package com.sunday.engine;

import com.sunday.engine.databank.SystemPort;

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
