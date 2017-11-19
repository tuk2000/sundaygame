package com.sunday.game.engine.view;

import com.badlogic.gdx.utils.Disposable;
import com.sunday.game.engine.model.AbstractModel;

import java.util.ArrayList;
import java.util.Collections;

public abstract class AbstractView implements Disposable {

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

    public abstract void synchronizeWithRoleModel(AbstractModel abstractModel);
}
