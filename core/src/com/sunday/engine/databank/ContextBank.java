package com.sunday.engine.databank;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.common.Context;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.common.context.DataContext;

import java.util.List;

public interface ContextBank extends Disposable {
    <RC extends DataContext> void addClassContext(ClassContext<RC> classContext);

    <D extends Data> boolean hasClassContext(Class<D> clazz);

    <D extends Data, C extends DataContext> ClassContext<C> getClassContext(Class<D> clazz);

    <D extends Data, C extends DataContext> List<C> getContexts(Class<D> clazz);

    <D extends Data, C extends DataContext> void addContext(Class<D> clazz, C context);

    <C extends DataContext> void removeContext(C context);
}
