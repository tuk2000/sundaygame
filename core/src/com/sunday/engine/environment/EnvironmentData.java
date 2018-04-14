package com.sunday.engine.environment;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.annotation.DataMark;
import com.sunday.engine.common.annotation.DataType;
import com.sunday.engine.common.signal.DataSignal;

@DataMark(type = DataType.System, signalClass = {DataSignal.class}, contextClass = EnvironmentDataContext.class)
public interface EnvironmentData extends Data, EnvironmentRelated {
}
