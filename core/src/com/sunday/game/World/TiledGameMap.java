package com.sunday.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.sunday.engine.common.AnimationSetting;
import com.sunday.engine.examples.enemy.SawSprite;
import com.sunday.engine.examples.hero.HeroSprite;
import com.sunday.game.framework.GameFramework;

/**
 * IMPORTANT: each tile is of 16px
 */
public class TiledGameMap implements Screen {

    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private TiledMapRenderer tiledMapRenderer;
    private TiledMap tiledMap;
    private OrthographicCamera camera;

    private Batch batch;
    private HeroSprite heroSprite;
    private SawSprite sawSprite;

    private float w;
    private float h;
    private float pWidth = 64, pHeight = 64;

    public TiledGameMap() {
        batch = new SpriteBatch();
        heroSprite = new HeroSprite();
        sawSprite = new SawSprite();
        world = new World(new Vector2(0, -9.81f), true);
        box2DDebugRenderer = new Box2DDebugRenderer();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputAdapter);
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();

        //load Map
        tiledMap = new TmxMapLoader().load("TileMap/sTest/sTest.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        //Focus the camera inside map
        //adjust viewport as large as the map
        MapProperties mapProperties = tiledMap.getProperties();
        int levelWidth = mapProperties.get("width", Integer.class);
        int levelHeight = mapProperties.get("height", Integer.class);
        int tilePixelWidth = mapProperties.get("tilewidth", Integer.class);
        int tilePixelHeight = mapProperties.get("tileheight", Integer.class);
        int levelPixelWidth = levelWidth * tilePixelWidth;
        int levelPixelHeight = levelHeight * tilePixelHeight;
        camera.viewportWidth = levelPixelWidth;
        camera.viewportHeight = levelPixelHeight;
        camera.update();

        //Linking heroSprite with body
        //new heroSprite class test
    }

    @Override
    public void render(float delta) {
        AnimationSetting.DeltaTime += Gdx.graphics.getDeltaTime();
        //camera movement
        camera.update();
        //camera.position.y = heroSprite.getY();
        //camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        box2DDebugRenderer.render(world, camera.combined);
        world.step(1 / 60f, 8, 3);

        batch.begin();
        heroSprite.draw(batch);
        int num = 0;
        int posX = 100;
        while (num != 5) {
            sawSprite.setPosition(posX, 30);
            posX += 120;
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

    private InputAdapter inputAdapter = new InputAdapter() {
        @Override
        public boolean keyDown(int keycode) {
            switch (keycode) {
                case Input.Keys.SPACE:
                    heroSprite.jump();
                    break;
                case Input.Keys.DOWN:
                    heroSprite.trunaround();
                    break;
                case Input.Keys.LEFT:
                    heroSprite.translateX(-20);
                    break;
                case Input.Keys.RIGHT:
                    heroSprite.translateX(20);
                    break;
                case Input.Keys.P:
                    GameFramework.GameFlow.setCurrentScreen("GamePause");
                    break;
            }
            return true;
        }
    };
}
