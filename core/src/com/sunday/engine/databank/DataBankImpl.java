package com.sunday.engine.databank;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.SubSystem;
import com.sunday.engine.databank.storage.PortRegister;
import com.sunday.engine.databank.storage.SystemPortRegister;

public class DataBankImpl<T extends Data> implements DataBank<T> {
    private PortRegister portRegister = new PortRegister();
    private SystemPortRegister systemPortRegister = new SystemPortRegister();
    private DataStorage dataStorage;

    public DataBankImpl() {
        dataStorage = new DataStorage();
    }

    @Override
    public SystemPort getSystemPort(Class<? extends SubSystem> subSystemClass) {
        SystemPort systemPort;
        if (systemPortRegister.hasKey(subSystemClass)) {
            systemPort = systemPortRegister.getValue(subSystemClass);
        } else {
            systemPort = new SystemPortImpl(subSystemClass, dataStorage);
            systemPortRegister.register(systemPort);
        }
        return systemPort;
    }

    @Override
    public Port<T> getPort(Object owner) {
        Port<T> port;
        if (portRegister.hasKey(owner)) {
            port = portRegister.getValue(owner);
        } else {
            port = new PortImpl<>(owner, dataStorage);
            portRegister.register(port);
        }
        return port;
    }

    @Override
    public void removePort(Port port) {
        if (port instanceof SystemPort) {
            systemPortRegister.deregister((SystemPort) port);
        } else {
            portRegister.deregister(port);
        }
        dataStorage.destroyPort(port);
    }
}