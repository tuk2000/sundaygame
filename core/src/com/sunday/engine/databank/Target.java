package com.sunday.engine.databank;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Signal;

public interface Target extends Data {
    void notify(Signal signal);
}
