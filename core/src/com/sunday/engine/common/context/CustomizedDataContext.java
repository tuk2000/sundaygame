package com.sunday.engine.common.context;

import com.sunday.engine.common.data.CustomizedData;
import com.sunday.engine.common.propertyholder.Customized;

public class CustomizedDataContext<CD extends CustomizedData> extends DataContext<CD> implements Customized {

    public CustomizedDataContext(CD customizedData) {
        super(customizedData);
    }

    @Override
    public void evaluate() {
        predicateConsumerMap.forEach((predicate, consumer) -> {
            if (predicate.test(this)) {
                consumer.accept(this);
            }
        });
    }

}