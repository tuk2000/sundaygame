package com.sunday.engine.databank;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.SubSystem;

public interface DataBank extends Disposable {
    <S extends SubSystem> SystemPort getSystemPort(Class<S> subSystemClass);

    Port getPort(Object owner);

    void removePort(Port port);

}
