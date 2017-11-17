package com.sunday.game.engine.view;

import com.badlogic.gdx.utils.Disposable;

public interface ViewLayer extends Disposable {

    public Class getComponentClass();

    public void setVisible(boolean visible);

}
