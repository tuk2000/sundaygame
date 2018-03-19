package com.sunday.engine.render;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.Disposable;
import com.sunday.engine.databank.SystemPortSharing;

public abstract class IndependentRenderer implements Disposable, SystemPortSharing {
    private boolean isWorking = true;

    final public void render(float delta) {
        if (isWorking) {
            renderInternal(delta);
        }
    }

    public abstract void setCamera(Camera camera);

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        this.isWorking = working;
    }

    protected abstract void renderInternal(float delta);
}
