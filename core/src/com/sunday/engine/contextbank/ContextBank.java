package com.sunday.engine.contextbank;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.common.context.DataContext;

public interface ContextBank {

    <C extends DataContext> C getDataContext(Data data);

    <D extends Data, RC extends DataContext<D>> ClassContext<RC> getClassContext(Class<D> clazz);

}
