package com.sunday.engine.databank;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.common.*;

public interface ContextBank<D extends Data, M extends MetaData, C extends CustomizedData> extends Disposable {
    void addSystemContext(String name, Context context);

    void removeSystemContext(String name, Context context);

    Context getSystemContext(String name);

    void addContext(Context context);

    void removeContext(Context context);

    CustomizedDataContext<C> getContext(C customizedData);

    MetaDataContext<M> getContext(M metadata);

    ClassContext<D> getContext(Class<D> clazz);

}
