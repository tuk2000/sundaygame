package com.sunday.engine.databank;

import com.sunday.engine.common.*;
import com.sunday.engine.databank.storage.ClassContextRegister;
import com.sunday.engine.databank.storage.DataConetxtRegister;
import com.sunday.engine.databank.storage.MetaDataContextRegister;
import com.sunday.engine.databank.storage.SystemContextRegister;

public class ContextBankImpl<D extends Data, M extends MetaData, C extends CustomizedData> implements ContextBank<D, M, C> {
    private DataConetxtRegister<C, CustomizedDataContext<C>> customizedDataContextDataContextRegister = new DataConetxtRegister<>();
    private MetaDataContextRegister<M, MetaDataContext<M>> metaDataContextMetaDataContextRegister = new MetaDataContextRegister<>();
    private ClassContextRegister<D, ClassContext<D>> classContextClassContextRegister = new ClassContextRegister<>();
    private SystemContextRegister systemContextRegister = new SystemContextRegister();

    @Override
    public void addSystemContext(String name, Context context) {
        systemContextRegister.register(name, context);
    }

    @Override
    public void removeSystemContext(String name, Context context) {
        systemContextRegister.deregister(name, context);
    }

    @Override
    public Context getSystemContext(String name) {
        return systemContextRegister.getValue(name);
    }

    public void addContext(Context context) {
        if (context instanceof ClassContext) {
            classContextClassContextRegister.register((ClassContext) context);
        } else if (context instanceof CustomizedDataContext) {
            customizedDataContextDataContextRegister.register((CustomizedDataContext) context);
        } else if (context instanceof MetaDataContext) {
            metaDataContextMetaDataContextRegister.register((MetaDataContext) context);
        }
    }

    public void removeContext(Context context) {
        if (context instanceof ClassContext) {
            classContextClassContextRegister.deregister((ClassContext) context);
        } else if (context instanceof CustomizedDataContext) {
            customizedDataContextDataContextRegister.deregister((CustomizedDataContext) context);
        } else if (context instanceof MetaDataContext) {
            metaDataContextMetaDataContextRegister.deregister((MetaDataContext) context);
        }
    }

    @Override
    public CustomizedDataContext<C> getContext(C customizedData) {
        return customizedDataContextDataContextRegister.getValue(customizedData);
    }

    @Override
    public MetaDataContext<M> getContext(M metadata) {
        return metaDataContextMetaDataContextRegister.getValue(metadata);
    }

    @Override
    public ClassContext<D> getContext(Class<D> clazz) {
        ClassContext<D> classContext = classContextClassContextRegister.getValue(clazz);
        if (classContext == null) {
            classContextClassContextRegister.register(new ClassContext(clazz));
            classContext = classContextClassContextRegister.getValue(clazz);
        }
        return classContext;
    }

    @Override
    public void dispose() {

    }
}
