package com.sunday.game.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.sunday.game.GameFramework.FocusedScreen;
import com.sunday.game.GameFramework.GameFlow.GameStatus;
import com.sunday.game.GameFramework.GameFramework;

public class GamePlay extends FocusedScreen {
    private static final float TIMESTEP = 1 / 60f;
    private static final int VELOCITYITERATIONS = 8, POSITIONITERATIONS = 3;
    private InputAdapter inputAdapter;
    private World world;
    private Box2DDebugRenderer box2DDebugRenderer;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    private float speed = 250;
    private Vector2 movement = new Vector2();
    private Body box;

    private SpriteBatch batch;
    private BitmapFont font;


    public GamePlay() {

        inputAdapter = new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                switch (keycode) {
                    case Input.Keys.SPACE:
                        movement.y = 1.5f * speed;
                        break;
                    case Input.Keys.LEFT:
                        movement.x = -speed;
                        break;
                    case Input.Keys.RIGHT:
                        movement.x = speed;
                        break;
                    case Input.Keys.P:
                        GameFramework.GameFlow.setGameStatus(GameStatus.GamePause);
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

        batch = new SpriteBatch();
        font = new BitmapFont();
        world = new World(new Vector2(0, -9.81f), true);
        box2DDebugRenderer = new Box2DDebugRenderer();
        //The Camera variable when we divide width and height by for eg.  5 it will be 5:1
        camera = new OrthographicCamera(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);

        //Shape Renderer
        shapeRenderer = new ShapeRenderer();
        //Fixture Definition
        FixtureDef fixtureDef = new FixtureDef();
        //Body Definition
        BodyDef bodyDef = new BodyDef();
        //Box
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(2.5f, 10);

        PolygonShape boxShape = new PolygonShape();
        boxShape.setAsBox(.5f, 1f);

        fixtureDef.shape = boxShape;
        fixtureDef.friction = .75f;
        fixtureDef.restitution = .1f;
        fixtureDef.density = 15f;

        box = world.createBody(bodyDef);
        box.createFixture(fixtureDef);

        boxShape.dispose();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0, 10);
        //Ball Shape
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(.5f);

        //We cann add fixture to a body like the properties below/
        fixtureDef.shape = circleShape;
        fixtureDef.density = 10.5f;
        fixtureDef.friction = .25f;
        fixtureDef.restitution = .75f;

        //uses all the properties and creates body
        //world.createBody(bodyDef).createFixture(fixtureDef);
        world.createBody(bodyDef).createFixture(fixtureDef);
        circleShape.dispose();

        //Body Definition
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);
        //Boden
        ChainShape chainShape = new ChainShape();
        chainShape.createChain(new Vector2[]{new Vector2(-10, 0), new Vector2(10, 0)});

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
    public void render(float delta) {
        Gdx.gl.glClearColor(171 / 255f, 216 / 255f, 227 / 255f, 1);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Renders new world with camera combined
        boden();
        box2DDebugRenderer.render(world, camera.combined);
        world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
        box.applyForceToCenter(movement, true);
        //Camera moves with the player
        camera.position.set(box.getPosition().x, box.getPosition().y / 2, 0);
        camera.update();//Wenn wir kein Update schreiben dann funktionert die Kamera gar nicht
        batch.begin();
        font.draw(batch, "Space for UP \n Left Arrow -> for Left\n Right Arrow <- for Right", 200, 200);
        batch.end();

    }

    public void boden() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.ROYAL);
        shapeRenderer.circle(520, 0, 250);
        shapeRenderer.setColor(Color.YELLOW);
        shapeRenderer.circle(520, 0, 150);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(520, 0, 50);
        shapeRenderer.end();

    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width / 25;
        camera.viewportWidth = height / 25;
        camera.update();
    }

    @Override
    public void dispose() {
        world.dispose();
        shapeRenderer.dispose();
        box2DDebugRenderer.dispose();

    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public InputAdapter getInputAdapter() {
        return inputAdapter;
    }
}
