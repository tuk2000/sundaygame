package com.sunday.game.World.View;

import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;
import java.util.Collections;

public abstract class View implements Disposable {
    private boolean isStatic = false;

    public View(boolean isStatic) {
        this.isStatic = isStatic;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void updateView() {
        if (isStatic) return;
        else updateLayers();
    }

    public abstract void updateLayers();

    private ArrayList<ViewLayer> viewLayers = new ArrayList<>();

    protected void addViewLayers(ViewLayer... viewLayers) {
        Collections.addAll(this.viewLayers, viewLayers);
    }

    public ArrayList<ViewLayer> getViewLayers() {
        return viewLayers;
    }

    @Override
    public void dispose() {
        viewLayers.forEach(viewLayer -> viewLayer.dispose());
    }
}
