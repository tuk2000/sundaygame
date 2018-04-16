package com.sunday.engine.common.data;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.annotation.DataMark;
import com.sunday.engine.common.annotation.DataType;
import com.sunday.engine.common.context.CustomizedDataContext;
import com.sunday.engine.common.propertyholder.Customized;
import com.sunday.engine.common.signal.DataSignal;

@DataMark(type = DataType.Customized, signalClass = {DataSignal.class}, contextClass = CustomizedDataContext.class)
public interface CustomizedData extends Data, Customized {
}
