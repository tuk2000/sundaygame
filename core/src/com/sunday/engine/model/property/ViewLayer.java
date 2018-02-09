package com.sunday.engine.model.property;

import com.badlogic.gdx.utils.Disposable;

public interface ViewLayer extends Disposable {

    Class getComponentClass();

    void setVisible(boolean visible);

}
