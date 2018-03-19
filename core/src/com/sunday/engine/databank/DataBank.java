package com.sunday.engine.databank;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.common.Data;
import com.sunday.engine.common.SubSystem;

public interface DataBank<T extends Data> extends Disposable {
    SystemPort getSystemPort(Class<? extends SubSystem> subSystemClass);

    Port<T> getPort(Object owner);

    void removePort(Port port);

}
