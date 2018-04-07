package com.sunday.engine.databank.storage;

import com.sunday.engine.common.data.SourceClass;
import com.sunday.engine.databank.register.AutoGroupRegister;


public class SourceClassConnectionRegister extends AutoGroupRegister<SourceClass, Connection<SourceClass>> {
    public SourceClassConnectionRegister() {
        super(v -> v.source);
    }
}
