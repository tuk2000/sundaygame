package com.sunday.engine.common.data;


import com.sunday.engine.common.Data;

public class SourceClass<T extends Data> implements Data {

    public Class<T> sensedSourceClass;

    public SourceClass(Class<T> sensedSourceClass) {
        this.sensedSourceClass = sensedSourceClass;
    }
}
