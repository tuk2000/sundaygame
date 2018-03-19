package com.sunday.engine.render.independentrenders;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.render.IndependentRenderer;

import java.util.ArrayList;

public class TextureRenderer extends IndependentRenderer {
    private boolean ownBatch;
    private Batch batch;
    private Camera camera;
    private ArrayList<Texture> textureBuffer;
    private ArrayList<Vector2> positionBuffer;
    private ArrayList<Vector2> sizeBuffer;

    public TextureRenderer(Batch batch, Camera camera) {
        ownBatch = false;
        textureBuffer = new ArrayList<>();
        positionBuffer = new ArrayList<>();
        sizeBuffer = new ArrayList<>();
        this.batch = batch;
        this.camera = camera;
        batch.setProjectionMatrix(camera.projection);
    }

    public void renderLater(Texture texture, float x, float y, float w, float h) {
        textureBuffer.add(texture);
        positionBuffer.add(new Vector2(x, y));
        sizeBuffer.add(new Vector2(w, h));
    }

    @Override
    public void setCamera(Camera camera) {
        if (camera.equals(this.camera)) return;
        this.camera = camera;
    }

    @Override
    protected void renderInternal(float delta) {
        batch.setProjectionMatrix(camera.combined);
        Vector2 location;
        Vector2 size;
        batch.begin();
        for (int i = 0; i < textureBuffer.size(); i++) {
            location = positionBuffer.get(i);
            size = sizeBuffer.get(i);
            batch.draw(textureBuffer.get(i), location.x, location.y, size.x, size.y);
        }
        textureBuffer.clear();
        positionBuffer.clear();
        sizeBuffer.clear();
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
