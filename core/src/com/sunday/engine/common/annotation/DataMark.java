package com.sunday.engine.common.annotation;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Signal;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface DataMark {
    DataType type() default DataType.Unknown;

    Class<? extends Signal>[] signalClass();

    Class<? extends Context> contextClass();
}
