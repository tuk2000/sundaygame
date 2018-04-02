package com.sunday.engine.common;

public class MetaDataContext<M extends MetaData> implements Context {
    private Class<M> metaDataClass;
    private M metaData;
    private Signal signal;
    private boolean initialed;

    public MetaDataContext(M metaData) {
        metaDataClass = (Class<M>) metaData.getClass();
        this.metaData = metaData;
        initialed = true;
    }

    public MetaDataContext(Class<M> clazz) {
        metaDataClass = clazz;
        initialed = false;
    }

    public M getMetaData() {
        return metaData;
    }

    public void setMetaData(M metaData) {
        this.metaData = metaData;
        initialed = true;
    }

    public Signal getSignal() {
        return signal;
    }

    public boolean isInitialed() {
        return initialed;
    }

    public Class<M> getMetaDataClass() {
        return metaDataClass;
    }
}
