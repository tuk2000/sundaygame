package com.sunday.engine.rule;

import com.sunday.engine.common.MetaData;
import com.sunday.engine.common.MetaDataContext;

public abstract class MetaDataCondition<M extends MetaData> extends Condition<MetaDataContext<M>> {
    protected MetaDataCondition() {

    }

    @Override
    protected void generateMainInfo() {
        setMainInfo(
                "Source = [" + (getContext().isInitialed() ? getContext().getMetaData() : " n/a") + "]\n" +
                        "SourceClass = [" + getContext().getMetaDataClass().getSimpleName() + "]\n" +
                        "Signals = [" + getSignalNames() + "]"
        );
    }
}
