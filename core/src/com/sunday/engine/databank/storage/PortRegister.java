package com.sunday.engine.databank.storage;

import com.sunday.engine.databank.Port;
import com.sunday.engine.common.register.AutoMappingRegister;

public class PortRegister extends AutoMappingRegister<Object, Port> {
    public PortRegister() {
        super(port -> port.getOwner());
    }

}
