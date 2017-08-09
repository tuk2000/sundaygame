package com.sunday.game.Graphic;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.sunday.game.GameFramework.FocusedScreen;
import com.sunday.game.Player.Player;

public class TiledGameMap extends FocusedScreen {
//public class TiledGameMap implements Screen{
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private Texture img;
    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private Body box;
    Player player;

    private static final float TIMESTEP = 1 / 60f;
    private static final int VELOCITYITERATIONS = 8, POSITIONITERATIONS = 3;
    private Vector2 movement = new Vector2();

    @Override
    public void show(){
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        world = new World(new Vector2(0, -9.81f), true);
        box2DDebugRenderer = new Box2DDebugRenderer();
        //new player class test
        //player = new Player(new Sprite(new Texture("player_img/player1.png")));
        camera = new OrthographicCamera();
        camera.setToOrtho(false,w/5,h/5);
        camera.update();
        //It's same as the Texture Load
        //tiledMap = new TmxMapLoader().load("TileMap/MainGameMap.tmx");
        tiledMap = new TmxMapLoader().load("TileMap/test/thuloMap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        //we can change it with our own inputprocessor
        Gdx.input.setInputProcessor(getInputAdapter());

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
    public void render(float delta){
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
    public void resize(int width, int height) {
        camera.viewportWidth = width/5 ;
        camera.viewportWidth = height/5 ;
        camera.update();
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
        tiledMap.dispose();
    }

    @Override
    public InputAdapter getInputAdapter() {
        return null;
    }
}
