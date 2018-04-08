package com.sunday.engine.databank;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.common.Context;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.context.ClassContext;

import java.util.List;

public interface ContextBank extends Disposable {
    <RC extends Context> void addClassContext(ClassContext<RC> classContext);

    <D extends Data> boolean hasClassContext(Class<D> clazz);

    <D extends Data, C extends Context> ClassContext<C> getClassContext(Class<D> clazz);

    <D extends Data, C extends Context> List<C> getContexts(Class<D> clazz);

    <D extends Data, C extends Context> void addContext(Class<D> clazz, C context);

    <C extends Context> void removeContext(C context);
}
