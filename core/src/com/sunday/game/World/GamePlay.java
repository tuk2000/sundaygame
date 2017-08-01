package com.sunday.game.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class GamePlay implements Screen {
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private OrthographicCamera camera;
    ShapeRenderer shapeRenderer;

    @Override
    public void show() {
        world = new World(new Vector2(0,-9.81f),true);
        box2DDebugRenderer = new Box2DDebugRenderer();
        //The Camera variable when we divide width and height by for eg.  5 it will be 5:1
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(171/255f, 216/255f, 227/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Renders new world with camera combined
        box2DDebugRenderer.render(world,camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.ROYAL);
        shapeRenderer.circle(520,0,250);
        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.circle(520,0,150);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(520,0,50);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {

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
        world.dispose();
        box2DDebugRenderer.dispose();
    }
}
