package com.sunday.engine.databank.storage;

import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.common.register.AutoMappingRegister;


public class SystemPortRegister extends AutoMappingRegister<Class, SystemPort> {
    public SystemPortRegister() {
        super(systemPort -> (Class) systemPort.getOwner());
    }
}
