package com.sunday.engine.databank;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.common.Context;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.context.ClassContext;

import java.util.List;

public interface ContextBank<D extends Data, C extends Context> extends Disposable {
    void addClassContext(ClassContext<D, C> classContext);

    boolean hasClassContext(Class<D> clazz);

    ClassContext<D, C> getClassContext(Class<D> clazz);

    List<C> getContexts(Class<D> clazz);

    void addContext(Class<D> clazz, C context);

    void removeContext(C context);
}
