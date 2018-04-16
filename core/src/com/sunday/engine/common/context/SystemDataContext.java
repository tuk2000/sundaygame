package com.sunday.engine.common.context;

import com.sunday.engine.common.data.SystemData;
import com.sunday.engine.common.propertyholder.SystemRelated;

public abstract class SystemDataContext<SD extends SystemData> extends DataContext<SD> implements SystemRelated {
    public SystemDataContext(SD systemData) {
        super(systemData);
    }
}
