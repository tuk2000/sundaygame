package com.sunday.engine.contextbank;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.context.DataContext;

public interface ContextPredefining {
    <C extends DataContext> C getDataContext(Data data);
}
