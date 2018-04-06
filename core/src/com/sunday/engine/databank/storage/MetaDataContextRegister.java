package com.sunday.engine.databank.storage;

import com.sunday.engine.common.MetaData;
import com.sunday.engine.common.MetaDataContext;
import com.sunday.engine.databank.register.AutoMappingRegister;

import java.util.function.Function;

public class MetaDataContextRegister<M extends MetaData, V extends MetaDataContext<M>> extends AutoMappingRegister<M, V> {
    public MetaDataContextRegister() {
        super(new Function<V, M>() {
            @Override
            public M apply(V v) {
                return v.getMetaData();
            }
        });
    }
}
