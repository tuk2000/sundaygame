package com.sunday.engine.rule;

import com.sunday.engine.common.context.CustomizedDataContext;
import com.sunday.engine.common.context.DataContext;
import com.sunday.engine.common.data.CustomizedData;
import com.sunday.engine.common.register.AutoMappingRegister;
import com.sunday.engine.databank.ContextBank;

public class CustomizedDataContextConstructor<CD extends CustomizedData, CDC extends CustomizedDataCondition>
        implements DataContextConstructor<CDC> {
    private ContextBank contextBank;
    private AutoMappingRegister<CD, CustomizedDataContext<CD>> autoMappingRegister
            = new AutoMappingRegister<>(customizedDataContext -> customizedDataContext.getData());

    public CustomizedDataContextConstructor(ContextBank contextBank) {
        this.contextBank = contextBank;
    }

    @Override
    public boolean test(Condition condition) {
        return condition instanceof CustomizedDataCondition;
    }

    @Override
    public DataContext construct(CDC condition) {
        CD cd = (CD) condition.getData();
        CustomizedDataContext<CD> customizedDataContext;
        if (autoMappingRegister.hasKey(cd)) {
            customizedDataContext = autoMappingRegister.getValue(cd);
        } else {
            customizedDataContext = new CustomizedDataContext<>(cd);
            autoMappingRegister.register(customizedDataContext);
        }
        return customizedDataContext;
    }

}
