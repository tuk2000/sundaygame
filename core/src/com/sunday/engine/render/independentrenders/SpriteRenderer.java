package com.sunday.engine.render.independentrenders;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.render.IndependentRenderer;

import java.util.ArrayList;

public class SpriteRenderer extends IndependentRenderer {
    private boolean ownBatch;
    private Batch batch;
    private Camera camera;
    private ArrayList<Sprite> spriteBuffer;
    private ArrayList<Vector2> positionBuffer;

    public SpriteRenderer(Batch batch, Camera camera) {
        spriteBuffer = new ArrayList<>();
        positionBuffer = new ArrayList<>();
        this.batch = batch;
        ownBatch = false;
        this.camera = camera;
    }

    public void renderLater(Sprite sprite) {
        spriteBuffer.add(sprite);
    }

    @Override
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    @Override
    protected void renderInternal(float delta) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        spriteBuffer.forEach(e -> {
            batch.draw(e, 0, 0);
        });
        spriteBuffer.clear();
        batch.end();
    }

    @Override
    public void connectWith(SystemPort systemPort) {

    }

    @Override
    public void disconnectWith(SystemPort systemPort) {

    }

    @Override
    public void dispose() {
        if (ownBatch)
            batch.dispose();
    }
}
