package com.sunday.engine.databank.storage;

import com.sunday.engine.databank.Port;
import com.sunday.engine.databank.register.AutoMappingRegister;
import com.sunday.engine.databank.register.MappingRegister;

import java.util.function.Function;

public class PortRegister extends AutoMappingRegister<Object, Port> {

    public PortRegister() {
        super(new MappingRegister<Object, Port>(), new Function<Port, Object>() {
            @Override
            public Object apply(Port port) {
                return port.getOwner();
            }
        });
    }
}
