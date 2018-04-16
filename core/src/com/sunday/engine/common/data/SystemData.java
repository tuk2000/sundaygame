package com.sunday.engine.common.data;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.annotation.DataMark;
import com.sunday.engine.common.annotation.DataType;
import com.sunday.engine.common.context.SystemDataContext;
import com.sunday.engine.common.propertyholder.SystemRelated;
import com.sunday.engine.common.signal.DataSignal;

@DataMark(type = DataType.System, signalClass = {DataSignal.class}, contextClass = SystemDataContext.class)
public interface SystemData extends Data, SystemRelated {
}
