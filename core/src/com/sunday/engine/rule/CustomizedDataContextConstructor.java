package com.sunday.engine.rule;

import com.sunday.engine.common.context.CustomizedDataContext;
import com.sunday.engine.common.data.CustomizedData;
import com.sunday.engine.databank.ContextBank;
import com.sunday.engine.common.register.AutoMappingRegister;

public class CustomizedDataContextConstructor<CD extends CustomizedData> implements ContextConstructor<CustomizedDataCondition<CD>> {
    private ContextBank contextBank;
    private AutoMappingRegister<CD, CustomizedDataContext<CD>> autoMappingRegister
            = new AutoMappingRegister<>(customizedDataContext -> customizedDataContext.getCustomizedData());

    public CustomizedDataContextConstructor(ContextBank contextBank) {
        this.contextBank = contextBank;
    }

    @Override
    public boolean test(Condition condition) {
        return condition instanceof CustomizedDataCondition;
    }

    @Override
    public void construct(CustomizedDataCondition<CD> condition) {
        CD cd = condition.getCustomizedData();
        CustomizedDataContext<CD> customizedDataContext;
        if (autoMappingRegister.hasKey(cd)) {
            customizedDataContext = autoMappingRegister.getValue(cd);
        } else {
            customizedDataContext = new CustomizedDataContext<>(cd);
            autoMappingRegister.register(customizedDataContext);
        }
        customizedDataContext.setEvaluateConnection(condition, condition.getReaction());
        condition.generateInfoWith(customizedDataContext);
        contextBank.addContext(cd.getClass(), customizedDataContext);
    }
}
