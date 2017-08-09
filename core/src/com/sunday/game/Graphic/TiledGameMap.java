package com.sunday.game.Graphic;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.sunday.game.GameFramework.FocusedScreen;
import com.sunday.game.GameFramework.GameFlowManager;
import com.sunday.game.GameFramework.GameStatus;
import com.sunday.game.Player.Player;

/**
 * IMPORTANT: each tile is of 16px
 */
public class TiledGameMap extends FocusedScreen {
//public class TiledGameMap implements Screen{
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private Texture img;
    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private Body box;
    private Player player;
    private  InputAdapter inputAdapter ;
    private float speed = 550;

    private static final float TIMESTEP = 1 / 60f;
    private static final int VELOCITYITERATIONS = 8, POSITIONITERATIONS = 3;
    private Vector2 movement = new Vector2();
    private float w;
    private float h;
    //to focus the camera inside the map
    private int levelPixelWidth;
    private int levelPixelHeight;

    @Override
    public void show(){
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        world = new World(new Vector2(0, -9.81f), true);
        box2DDebugRenderer = new Box2DDebugRenderer();
        //new player class test
        //player = new Player(new Sprite(new Texture("player_img/player1.png")));
        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.update();
        //It's same as the Texture Load
        //tiledMap = new TmxMapLoader().load("TileMap/MainGameMap.tmx");
        tiledMap = new TmxMapLoader().load("TileMap/sTest/sTest.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        //Focus the camera inside map
        MapProperties mapProperties = tiledMap.getProperties();
        int levelWidth = mapProperties.get("width",Integer.class);
        int levelHeight = mapProperties.get("height",Integer.class);
        int tilePixelWidth = mapProperties.get("tilewidth",Integer.class);
        int tilePixelHeight = mapProperties.get("tileheight",Integer.class);
        levelPixelWidth = levelWidth * tilePixelWidth;
        levelPixelHeight = levelHeight * tilePixelHeight;

        //Fixture Definition
        FixtureDef fixtureDef = new FixtureDef();
        //Body Definition
        BodyDef bodyDef = new BodyDef();
        //Box
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(64f, 64f);

        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(16f, 20f);

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
        chainShape.createChain(new Vector2[]{new Vector2(32, 32), new Vector2(688, 32)});
        //chainShape.createChain(new Vector2[]{new Vector2(-1000, 150), new Vector2(1000, 150)});

        //Fixture Definition
        //We cann add fixture to a body like the properties below
        fixtureDef.shape = chainShape;
        fixtureDef.friction = .5f;
        fixtureDef.restitution = 0;

        //uses all the properties and creates body
        world.createBody(bodyDef).createFixture(fixtureDef);
        chainShape.dispose();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);
        ChainShape chainShape1 = new ChainShape();
        chainShape.createChain(new Vector2[]{new Vector2(32, 32), new Vector2(32, 1000)});
        //chainShape.createChain(new Vector2[]{new Vector2(-1000, 150), new Vector2(1000, 150)});

        //Fixture Definition
        //We cann add fixture to a body like the properties below
        fixtureDef.shape = chainShape1;
        fixtureDef.friction = .5f;
        fixtureDef.restitution = 0;

        //uses all the properties and creates body
        world.createBody(bodyDef).createFixture(fixtureDef);
        chainShape1.dispose();

        //input
        inputAdapter= new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.SPACE:
                        movement.y = 50f * speed;
                        break;
                    case Input.Keys.LEFT:
                        movement.x = -speed;
                        break;
                    case Input.Keys.RIGHT:
                        movement.x = speed;
                        break;
                    case Input.Keys.P:
                        GameFlowManager.setGameStatus(GameStatus.GamePause);
                        break;
                }
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                switch (keycode) {
                    case Input.Keys.SPACE:
                    case Input.Keys.LEFT:
                        movement.y = 0;
                        break;
                    case Input.Keys.RIGHT:
                        movement.x = 0;

                }
                return true;
            }
        };
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA,GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //camera movement
        camera.position.x = Math.min(Math.max(box.getPosition().x,w/0.005f),levelPixelWidth -(w/1.99f));
        camera.position.y = Math.min(Math.max(box.getPosition().y,h/2),levelPixelWidth -(h/2.5f));
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        box2DDebugRenderer.render(world, camera.combined);
        world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
        box.applyForceToCenter(movement, true);

    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width/1.12f ;
        camera.viewportWidth = height/1.12f ;
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
        return inputAdapter;
    }
}
