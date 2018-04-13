package com.sunday.engine.databank;

import com.sunday.engine.common.Data;
import com.sunday.engine.common.context.ClassContext;
import com.sunday.engine.common.context.DataContext;
import com.sunday.engine.databank.storage.ClassContextRegister;
import com.sunday.engine.databank.storage.DataInstanceContextRegister;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ContextBankImpl implements ContextBank {
    private ClassContextRegister classContextClassContextRegister = new ClassContextRegister();
    private DataInstanceContextRegister dataInstanceContextRegister = new DataInstanceContextRegister();

    @Override
    public <RC extends DataContext> void addClassContext(ClassContext<RC> classContext) {
        classContextClassContextRegister.register(classContext);
    }

    @Override
    public <D extends Data> boolean hasClassContext(Class<D> clazz) {
        return classContextClassContextRegister.hasKey(clazz);
    }

    @Override
    public <D extends Data, C extends DataContext> ClassContext<C> getClassContext(Class<D> clazz) {
        return (ClassContext<C>) classContextClassContextRegister.getValue(clazz);
    }

    @Override
    public <D extends Data, C extends DataContext> List<C> getContexts(Class<D> clazz) {
        List<C> list = new ArrayList<>();
        list.addAll((Collection<? extends C>) dataInstanceContextRegister.getValues(clazz));
        return list;
    }

    @Override
    public <D extends Data, C extends DataContext> void addContext(Class<D> clazz, C context) {
        dataInstanceContextRegister.register(clazz, context);
    }

    @Override
    public <C extends DataContext> void removeContext(C context) {
        dataInstanceContextRegister.deregister(context);
    }

    @Override
    public void dispose() {

    }
}
