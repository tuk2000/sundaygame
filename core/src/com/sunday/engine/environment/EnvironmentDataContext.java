package com.sunday.engine.environment;

import com.sunday.engine.common.context.DataContext;

public class EnvironmentDataContext<ED extends EnvironmentData> extends DataContext<ED> implements EnvironmentRelated {
    public EnvironmentDataContext(ED environmentRelatedData) {
        super(environmentRelatedData);
    }

}
