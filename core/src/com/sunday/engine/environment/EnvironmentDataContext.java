package com.sunday.engine.environment;

import com.sunday.engine.common.context.DataContext;

public class EnvironmentDataContext<ED extends EnvironmentData> extends DataContext<ED> implements EnvironmentRelated {
    public EnvironmentDataContext(ED environmentRelatedData) {
        super(environmentRelatedData);
    }

    @Override
    public void evaluate() {
        System.out.println("Evaluating : " + getDataClass() + " - " + getData() + " " + getSignal());
        predicateConsumerMap.forEach((contextPredicate, contextConsumer) -> {
            if (contextPredicate.test(this)) {
                contextConsumer.accept(this);
            }
        });
    }
}
