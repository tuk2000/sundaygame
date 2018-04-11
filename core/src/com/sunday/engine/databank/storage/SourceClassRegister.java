package com.sunday.engine.databank.storage;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.data.SourceClass;
import com.sunday.engine.common.register.AutoMappingRegister;

public class SourceClassRegister<T extends Data> extends AutoMappingRegister<Class<T>, SourceClass<T>> {
    public SourceClassRegister() {
        super(tSourceClass -> tSourceClass.sensedSourceClass);
    }

    public SourceClass<T> getSourceClass(Class<T> clazz) {
        if (hasKey(clazz)) {
            return getValue(clazz);
        } else {
            SourceClass<T> sourceClass = new SourceClass<>(clazz);
            register(sourceClass);
            return sourceClass;
        }
    }
}
