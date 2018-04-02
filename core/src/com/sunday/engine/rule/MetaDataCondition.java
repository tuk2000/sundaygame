package com.sunday.engine.rule;

import com.sunday.engine.common.MetaData;
import com.sunday.engine.common.MetaDataContext;
import com.sunday.engine.databank.SystemPort;

public class MetaDataCondition<M extends MetaData> extends Condition<MetaDataContext<M>> {

    @Override
    protected void generateMainInfo() {
        setMainInfo(
                "Source = [" + (context.isInitialed() ? context.getMetaData() : " n/a") + "]\n" +
                        "SourceClass = [" + context.getMetaDataClass().getSimpleName() + "]\n" +
                        "Signals = [" + getSignalNames() + "]"
        );
    }

    @Override
    public void connectWith(SystemPort systemPort) {

    }

    @Override
    public void disconnectWith(SystemPort systemPort) {

    }
}
