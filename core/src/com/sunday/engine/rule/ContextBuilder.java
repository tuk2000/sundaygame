package com.sunday.engine.rule;

import com.sunday.engine.common.*;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.databank.SystemPortSharing;

public class ContextBuilder implements SystemPortSharing {
    public static <D extends Data> DataContext<D> buildDataContext(D data) {
        return new DataContext<>(data);
    }

    public static <D extends Data> ClassContext<D> buildClassContext(Class<D> clazz) {
        return new ClassContext<>(clazz);
    }

    public static <M extends MetaData> MetaDataContext<M> buildMetaDataContext(Class<M> clazz) {
        return new MetaDataContext<>(clazz);
    }

    @Override
    public void connectWith(SystemPort systemPort) {

    }

    @Override
    public void disconnectWith(SystemPort systemPort) {

    }
}
