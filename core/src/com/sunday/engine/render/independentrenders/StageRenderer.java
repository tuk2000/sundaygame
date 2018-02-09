package com.sunday.engine.render.independentrenders;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sunday.engine.render.IndependentRenderer;

public class StageRenderer implements IndependentRenderer {
    private Stage stage;

    public StageRenderer() {
        stage = new Stage();
        stage.setDebugAll(true);
    }

    public StageRenderer(Batch batch, Viewport viewport) {
        stage = new Stage(viewport, batch);
        stage.setDebugAll(true);
    }

    @Override
    public void render(float delta) {
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
