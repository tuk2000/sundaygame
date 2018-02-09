package com.sunday.engine.render.independentrenders;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.sunday.engine.render.IndependentRenderer;

import java.util.ArrayList;

public class SpriteRenderer implements IndependentRenderer {
    private Batch batch;
    private ArrayList<Sprite> spriteBuffer;
    private ArrayList<Vector2> positionBuffer;

    public SpriteRenderer(Batch batch) {
        spriteBuffer = new ArrayList<>();
        positionBuffer = new ArrayList<>();
        this.batch = batch;
    }

    public void renderLater(Sprite sprite) {
        spriteBuffer.add(sprite);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        spriteBuffer.forEach(e -> {
            batch.draw(e, 0, 0);
        });
        spriteBuffer.clear();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
