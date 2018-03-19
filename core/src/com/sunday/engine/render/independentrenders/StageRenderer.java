package com.sunday.engine.render.independentrenders;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.render.IndependentRenderer;

public class StageRenderer extends IndependentRenderer {
    private Stage stage;

    public StageRenderer() {
        stage = new Stage();
        stage.setDebugAll(true);
    }

    public StageRenderer(Batch batch, Viewport viewport) {
        stage = new Stage(new ScreenViewport(), new SpriteBatch());
        stage.setDebugAll(true);
    }

    @Override
    public void setCamera(Camera camera) {

    }

    @Override
    protected void renderInternal(float delta) {
        stage.draw();
    }

    @Override
    public void connectWith(SystemPort systemPort) {

    }

    @Override
    public void disconnectWith(SystemPort systemPort) {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
