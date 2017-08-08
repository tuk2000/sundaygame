package com.sunday.game.Graphic;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class TiledGameMap extends ApplicationAdapter implements InputProcessor {
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private Texture img;
    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private Body box;

    private static final float TIMESTEP = 1 / 60f;
    private static final int VELOCITYITERATIONS = 8, POSITIONITERATIONS = 3;
    private Vector2 movement = new Vector2();

    @Override
    public void create(){
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        world = new World(new Vector2(0, -9.81f), true);
        box2DDebugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false,w/5,h/5);
        camera.update();
        //It's same as the Texture Load
        //tiledMap = new TmxMapLoader().load("TileMap/MainGameMap.tmx");
        tiledMap = new TmxMapLoader().load("TileMap/test/thuloMap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        //we can change it with our own inputprocessor
        Gdx.input.setInputProcessor(this);

        //Fixture Definition
        FixtureDef fixtureDef = new FixtureDef();
        //Body Definition
        BodyDef bodyDef = new BodyDef();
        //Box
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(16f, 50f);

        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(5f, 10f);

        fixtureDef.shape = boxShape;
        fixtureDef.friction = .75f;
        fixtureDef.restitution = .75f;
        fixtureDef.density = 15f;

        box = world.createBody(bodyDef);
        box.createFixture(fixtureDef);

        boxShape.dispose();
        //Body Definition
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);
        //Boden
        ChainShape chainShape = new ChainShape();
        chainShape.createChain(new Vector2[]{new Vector2(-1000, 16), new Vector2(1000, 16)});
        //chainShape.createChain(new Vector2[]{new Vector2(-1000, 150), new Vector2(1000, 150)});

        //Fixture Definition
        //We cann add fixture to a body like the properties below
        fixtureDef.shape = chainShape;
        fixtureDef.friction = .5f;
        fixtureDef.restitution = 0;

        //uses all the properties and creates body
        world.createBody(bodyDef).createFixture(fixtureDef);
        chainShape.dispose();

    }

    @Override
    public void render(){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA,GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        box2DDebugRenderer.render(world, camera.combined);
        world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
        box.applyForceToCenter(movement, true);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
//        if(keycode == Input.Keys.LEFT)
//            camera.translate(-32,0);
//        if(keycode == Input.Keys.RIGHT)
//            camera.translate(32,0);
//        if(keycode == Input.Keys.UP)
//            camera.translate(0,-32);
//        if(keycode == Input.Keys.DOWN)
//            camera.translate(0,32);
//        if(keycode == Input.Keys.NUM_1)
//            tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
//        if(keycode == Input.Keys.NUM_2)
//            tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width/5 ;
        camera.viewportWidth = height/5 ;
        camera.update();
    }
    @Override
    public void dispose() {
        super.dispose();
        world.dispose();
        box2DDebugRenderer.dispose();
        tiledMap.dispose();
    }
}
