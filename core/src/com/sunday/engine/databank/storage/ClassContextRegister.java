package com.sunday.engine.databank.storage;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.databank.register.AutoMappingRegister;

import java.util.function.Function;

public class ClassContextRegister<K extends Data, C extends Context> extends AutoMappingRegister<Class<K>, ClassContext<K, C>> {
    public ClassContextRegister() {
        super(new Function<ClassContext<K, C>, Class<K>>() {
            @Override
            public Class<K> apply(ClassContext<K, C> kcClassContext) {
                return kcClassContext.getSensedClass();
            }
        });
    }
}
