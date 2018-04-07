package com.sunday.engine.databank;

import com.sunday.engine.common.Context;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.databank.storage.ClassContextRegister;
import com.sunday.engine.databank.storage.DataInstanceContextRegister;

import java.util.ArrayList;
import java.util.List;

public class ContextBankImpl<D extends Data, C extends Context> implements ContextBank<D, C> {
    private ClassContextRegister<D, C> classContextClassContextRegister = new ClassContextRegister<>();
    private DataInstanceContextRegister<D, C> dataInstanceContextRegister = new DataInstanceContextRegister<>();

    @Override
    public void addClassContext(ClassContext<D, C> classContext) {
        classContextClassContextRegister.register(classContext);
    }

    @Override
    public boolean hasClassContext(Class<D> clazz) {
        return classContextClassContextRegister.hasKey(clazz);
    }

    @Override
    public ClassContext<D, C> getClassContext(Class<D> clazz) {
        return classContextClassContextRegister.getValue(clazz);
    }

    @Override
    public List<C> getContexts(Class<D> clazz) {
        List<C> list = new ArrayList<>();
        list.addAll(dataInstanceContextRegister.getValues(clazz));
        return null;
    }

    @Override
    public void addContext(Class<D> clazz, C context) {
        dataInstanceContextRegister.register(clazz, context);
    }

    @Override
    public void removeContext(C context) {
        dataInstanceContextRegister.deregister(context);
    }

    @Override
    public void dispose() {

    }
}
