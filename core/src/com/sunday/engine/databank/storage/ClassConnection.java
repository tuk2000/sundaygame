package com.sunday.engine.databank.storage;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.SourceClass;
import com.sunday.engine.common.Target;

public class ClassConnection<T extends Data> extends Connection<SourceClass<T>> {
    public ClassConnection(SourceClass<T> source, Target target) {
        super(source, target);
    }
}
