package com.sunday.engine.databank.storage;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.databank.register.AutoMappingRegister;

import java.util.function.Function;

public class ClassContextRegister extends AutoMappingRegister<Class<? extends Data>, ClassContext<? extends Context>> {
    public ClassContextRegister() {
        super(new Function<ClassContext<? extends Context>, Class<? extends Data>>() {
            @Override
            public Class<? extends Data> apply(ClassContext<? extends Context> classContext) {
                return classContext.getSensedClass();
            }
        });
    }
}
