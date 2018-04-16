package com.sunday.engine.databank.storage;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.Target;
import com.sunday.engine.common.data.SourceClass;

public class ClassConnection<T extends Data> extends Connection<SourceClass<T>> {
    public ClassConnection(SourceClass<T> source, Target target) {
        super(source, target);
    }
}
