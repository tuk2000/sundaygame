package com.sunday.engine.databank.storage;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.common.context.DataContext;
import com.sunday.engine.common.register.AutoMappingRegister;

import java.util.function.Function;

public class ClassContextRegister extends AutoMappingRegister<Class<? extends Data>, ClassContext<? extends DataContext>> {
    public ClassContextRegister() {
        super(new Function<ClassContext<? extends DataContext>, Class<? extends Data>>() {
            @Override
            public Class<? extends Data> apply(ClassContext<? extends DataContext> classContext) {
                return classContext.getSensedClass();
            }
        });
    }
}
