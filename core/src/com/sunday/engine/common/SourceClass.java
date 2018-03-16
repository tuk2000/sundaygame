package com.sunday.engine.common;


public class SourceClass<T extends Data> implements Data {

    public Class<T> sensedSourceClass;
    public T sensedData = null;

    public SourceClass(Class<T> sensedSourceClass) {
        this.sensedSourceClass = sensedSourceClass;
    }

    public T getSensedData() {
        return sensedData;
    }

    public void setSensedData(T t) {
        sensedData = t;
    }
}
