package com.sunday.engine.common;


public class SourceClass<T extends Data> implements Data {

    public Class<T> sensedSourceClass;

    public SourceClass(Class<T> sensedSourceClass) {
        this.sensedSourceClass = sensedSourceClass;
    }
}
