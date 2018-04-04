package com.sunday.game.world;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sunday.game.framework.GameFramework;

public class RenderTest implements Screen {
    private Batch batch;
    private Texture texture_button;
    private Texture texture_saw;
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;
    private Viewport viewport;
    private float zoomIncrement = 1.05f;

    @Override
    public void show() {

        camera = new OrthographicCamera();
        camera.viewportWidth = 200;
        camera.viewportHeight = 200;
        camera.update();

        //  viewport = new FitViewport(2000, 2000, camera);
        //  viewport = new StretchViewport(2000, 2000, camera);
        viewport = new FillViewport(2000, 2000, camera);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.view);
        shapeRenderer.setAutoShapeType(true);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.projection);


        texture_button = GameFramework.Resource.getAsset("buttons/button.png");
        texture_saw = GameFramework.Resource.getAsset("saws/saw1.png");


    }

    @Override
    public void render(float delta) {
        if (camera.zoom > 1.5) {
            zoomIncrement = 0.95f;
        } else if (camera.zoom < 0.05) {
            zoomIncrement = 1.05f;
        }
        camera.zoom *= zoomIncrement;
        camera.rotate(zoomIncrement);
        camera.update();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(texture_button, -texture_button.getWidth() / 2, -texture_button.getHeight() / 2);
        batch.draw(texture_button, -texture_button.getWidth(), -texture_button.getHeight(), texture_button.getWidth(), texture_button.getHeight());
        batch.draw(texture_button, 0, 0, texture_button.getWidth(), texture_button.getHeight());
        batch.draw(texture_saw, -texture_saw.getWidth() / 2, -texture_saw.getHeight() / 2, texture_saw.getWidth(), texture_saw.getHeight());
        batch.end();
        shapeRenderer.begin();
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.line(0, 1, 0, -1);
        shapeRenderer.flush();
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.line(-1, 0, 1, 0);
        shapeRenderer.flush();
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(0, 0, 0.2f, 90);
        shapeRenderer.flush();
        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.circle(0, 0, 0.4f, 90);
        shapeRenderer.flush();
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.circle(0, 0, 0.6f, 90);
        shapeRenderer.flush();
        shapeRenderer.setColor(Color.CYAN);
        shapeRenderer.circle(0, 0, 0.8f, 90);
        shapeRenderer.flush();
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.circle(0, 0, 1.0f, 90);
        shapeRenderer.flush();
        shapeRenderer.setColor(Color.PURPLE);
        shapeRenderer.circle(0, 0, 1.2f, 90);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
    }
}
