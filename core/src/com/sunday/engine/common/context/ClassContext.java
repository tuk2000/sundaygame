package com.sunday.engine.common.context;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.propertyholder.SystemRelated;
import com.sunday.engine.rule.Reaction;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ClassContext<RC extends Context> implements Context, Reaction<RC>, SystemRelated {
    private Class<? extends Data> sensedClass;
    private RC focusedContext;

    private Map<Predicate<ClassContext<RC>>, Consumer<ClassContext<RC>>> map = new HashMap<>();


    public ClassContext(Class<? extends Data> sensedClass) {
        this.sensedClass = sensedClass;
    }

    public Class getSensedClass() {
        return sensedClass;
    }

    public RC getFocusedContext() {
        return focusedContext;
    }

    public void setEvaluateConnection(Predicate<ClassContext<RC>> predicate, Consumer<ClassContext<RC>> consumer) {
        map.put(predicate, consumer);
    }

    @Override
    public void accept(RC context) {
        focusedContext = context;
        map.forEach((predicate, consumer) -> {
            if (predicate.test(this)) {
                consumer.accept(this);
            }
        });
    }
}
