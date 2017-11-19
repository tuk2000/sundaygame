package com.sunday.game.engine.scenario.render.independentrenders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.sunday.game.engine.scenario.render.IndependentRenderer;

import java.util.ArrayList;

public class TextureRenderer implements IndependentRenderer {
    private Batch batch;
    private ArrayList<Texture> textureBuffer;
    private ArrayList<Vector2> positionBuffer;
    private ArrayList<Vector2> sizeBuffer;

    public TextureRenderer(Batch batch) {
        textureBuffer = new ArrayList<>();
        positionBuffer = new ArrayList<>();
        sizeBuffer = new ArrayList<>();
        this.batch = batch;
    }

    public void renderLater(Texture texture, float x, float y, float w, float h) {
        textureBuffer.add(texture);
        positionBuffer.add(new Vector2(x, y));
        sizeBuffer.add(new Vector2(w, h));
    }

    @Override
    public void render(float delta) {
        Vector2 location = null;
        Vector2 size = null;
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
    public void dispose() {
        batch.dispose();
    }
}
