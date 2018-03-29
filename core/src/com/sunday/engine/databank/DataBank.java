package com.sunday.engine.databank;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.SubSystem;
import com.sunday.engine.common.Data;

public interface DataBank<T extends Data> extends Disposable {
    SystemPort getSystemPort(Class<? extends SubSystem> subSystemClass);

    Port<T> getPort(Object owner);

    void removePort(Port port);

}
