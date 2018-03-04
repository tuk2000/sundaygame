package com.sunday.engine.databank.storage;

import com.sunday.engine.common.Data;
import com.sunday.engine.databank.ClassSensor;
import com.sunday.engine.databank.register.AutoGroupRegister;
import com.sunday.engine.databank.register.GroupRegister;

import java.util.function.Function;

public class ClassSensorRegister<K extends Data, V extends ClassSensor> extends AutoGroupRegister<Class<K>, V> {

    public ClassSensorRegister() {
        super(new GroupRegister<Class<K>, V>(), new Function<V, Class<K>>() {
            @Override
            public Class<K> apply(V v) {
                return v.getSensedClass();
            }
        });
    }
}
