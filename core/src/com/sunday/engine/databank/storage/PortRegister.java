package com.sunday.engine.databank.storage;

import com.sunday.engine.common.register.AutoMappingRegister;
import com.sunday.engine.databank.Port;

public class PortRegister extends AutoMappingRegister<Object, Port> {
    public PortRegister() {
        super(port -> port.getOwner());
    }

}
