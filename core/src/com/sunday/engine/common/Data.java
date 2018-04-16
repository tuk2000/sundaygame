package com.sunday.engine.common;

import com.sunday.engine.common.annotation.DataMark;
import com.sunday.engine.common.annotation.DataType;

@DataMark(type = DataType.Unknown, signalClass = {Signal.class}, contextClass = Context.class)
public interface Data {
}
