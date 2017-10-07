package com.sunday.game.Graphic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.sunday.game.Enemies.Saw;
import com.sunday.game.GameFramework.FocusedScreen;
import com.sunday.game.GameFramework.GameFlowManager;
import com.sunday.game.GameFramework.GameStatus;
import com.sunday.game.Player.Player;
import net.dermetfan.gdx.graphics.g2d.AnimatedSprite;

/**
 * IMPORTANT: each tile is of 16px
 */
public class TiledGameMap extends FocusedScreen {
    private static final float TIMESTEP = 1 / 60f;
    private static final int VELOCITYITERATIONS = 8, POSITIONITERATIONS = 3;
    //public class TiledGameMap implements Screen{
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private Texture img;
    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private TiledMapRenderer tiledMapRenderer;
    private Body box;
    private Player player;
    private InputAdapter inputAdapter;
    private float speed = 1000;
    private Batch batch;
    private Vector2 movement = new Vector2();
    private float w;
    private float h;
    private float pWidth = 64, pHeight = 64;
    //to focus the camera inside the map
    private int levelPixelWidth;
    private int levelPixelHeight;
    //Enemies
    private Saw saw;
    private TextureRegion tr1, tr2, tr3, tr4, tr5, tr6, tr7, tr8;
    private int elapsedTime = 0;
    private Animation sawAnimation;
    private AnimatedSprite sawSprite;

    public TiledGameMap() {
        this.inputAdapter = new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.SPACE:
                        movement.y = 115f * speed;
                        break;
                    case Input.Keys.DOWN:
                        movement.y = -115f * speed;
                        break;
                    case Input.Keys.LEFT:
                        movement.x = -15 * speed;
                        break;
                    case Input.Keys.RIGHT:
                        movement.x = 15 * speed;
                        break;
                    case Input.Keys.P:
                        GameFlowManager.getInstance().setGameStatus(GameStatus.GamePause);
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
    public void show() {
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        //TODO gravity to be added as -9.81
        world = new World(new Vector2(0, -9.81f), true);
        box2DDebugRenderer = new Box2DDebugRenderer();
        //Enemy
        Texture sawTex = new Texture("Enemies/saw.png");
        //saw = new Saw(sawTex,152,32);
        tr1 = new TextureRegion(new Texture("Enemies/saw.png"));
        //tr1.setRegion();
        tr2 = new TextureRegion(new Texture("Enemies/saw2.png"));
        tr3 = new TextureRegion(new Texture("Enemies/saw3.png"));
        tr4 = new TextureRegion(new Texture("Enemies/saw4.png"));
        tr5 = new TextureRegion(new Texture("Enemies/saw5.png"));
        tr6 = new TextureRegion(new Texture("Enemies/saw6.png"));
        tr7 = new TextureRegion(new Texture("Enemies/saw7.png"));
        tr8 = new TextureRegion(new Texture("Enemies/saw8.png"));
        sawAnimation = new Animation(1 / 10f, tr1, tr2, tr3, tr4, tr5, tr6, tr7, tr8);
        sawAnimation.setPlayMode(Animation.PlayMode.LOOP);
        sawSprite = new AnimatedSprite(sawAnimation);
        sawSprite.setSize(48, 48);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();
        //It's same as the Texture Load
        //tiledMap = new TmxMapLoader().load("TileMap/MainGameMap.tmx");
        tiledMap = new TmxMapLoader().load("TileMap/sTest/sTest.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        //Focus the camera inside map
        MapProperties mapProperties = tiledMap.getProperties();
        int levelWidth = mapProperties.get("width", Integer.class);
        int levelHeight = mapProperties.get("height", Integer.class);
        int tilePixelWidth = mapProperties.get("tilewidth", Integer.class);
        int tilePixelHeight = mapProperties.get("tileheight", Integer.class);
        levelPixelWidth = levelWidth * tilePixelWidth;
        levelPixelHeight = levelHeight * tilePixelHeight;

        //Body Definition
        BodyDef bodyDef = new BodyDef();
        //Fixture Definition
        FixtureDef fixtureDef = new FixtureDef();
        //Box
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(64, 64);
        //Ball Shape
        PolygonShape playerBody = new PolygonShape();
        playerBody.setAsBox(10, 20);

        //We cann add fixture to a body like the properties below/
        fixtureDef.shape = playerBody;
        fixtureDef.density = 0.6f;
        fixtureDef.friction = 2.5f;
        fixtureDef.restitution = 0.5f;

        //uses all the properties and creates body
        //world.createBody(bodyDef).createFixture(fixtureDef);

        box = world.createBody(bodyDef);
        box.createFixture(fixtureDef);
        //world.createBody(bodyDef).createFixture(fixtureDef);
        playerBody.dispose();


        //Linking player with body
        //new player class test
        batch = new SpriteBatch();
        final Texture playerTxt = new Texture("player_img/player2.png");
        //player = new Player(playerTxt, box.getPosition().x, box.getPosition().y);
        player = new Player(playerTxt, box.getPosition().x / 2, box.getPosition().y / 2, pWidth, pHeight);
        //player.setPosition(bodyDef.position.x,bodyDef.position.y);
        //Body Definition
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);
        //Boden
        ChainShape chainShape = new ChainShape();
        chainShape.createChain(new Vector2[]{new Vector2(32, 32), new Vector2(688, 32)});

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

        //Fixture Definition
        //We cann add fixture to a body like the properties below
        fixtureDef.shape = chainShape1;
        fixtureDef.friction = .5f;
        fixtureDef.restitution = 0;

        //uses all the properties and creates body
        world.createBody(bodyDef).createFixture(fixtureDef);
        chainShape1.dispose();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //camera movement
        camera.position.x = Math.min(Math.max(player.getX(), w / 0.005f), levelPixelWidth - (w / 1.99f));
        camera.position.y = Math.min(Math.max(player.getY(), h / 2), levelPixelWidth - (h / 2.5f));
        camera.update();
        //camera.position.y = player.getY();
        //camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        box2DDebugRenderer.render(world, camera.combined);
        world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
        box.applyForceToCenter(movement, true);
        //elapsedTime += Gdx.graphics.getDeltaTime();
        batch.begin();
        player.setOrigin(pWidth / 2, pHeight / 2);
        player.setPosition(box.getPosition().x / 2, box.getPosition().y / 2);
        player.draw(batch);
        player.setRotation(box.getAngle() * 180 / (float) Math.PI);
        //player.update();
        batch.end();
        batch.begin();
        sawSprite.setPosition(150, 32);
        int num = 0;
        int posX = 150;
        while (num != 11) {
            sawSprite.draw(batch);
            sawSprite.setPosition(posX, 32);
            posX += 48;
            sawSprite.draw(batch);
            num++;
        }
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width / 1.12f;
        camera.viewportWidth = height / 1.12f;
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
        return this.inputAdapter;
    }
}
