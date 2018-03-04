package com.sunday.engine.databank.storage;

import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.databank.register.AutoMappingRegister;
import com.sunday.engine.databank.register.MappingRegister;

import java.util.function.Function;

public class SystemPortRegister extends AutoMappingRegister<Class, SystemPort> {
    public SystemPortRegister() {
        super(new MappingRegister<Class, SystemPort>(), new Function<SystemPort, Class>() {
            @Override
            public Class apply(SystemPort systemPort) {
                return (Class) systemPort.getOwner();
            }
        });
    }
}
