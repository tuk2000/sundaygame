package com.sunday.game.World;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.sunday.game.GameFramework.InputReceiver;

public class GameTest implements Screen, InputReceiver {
    private int PPM = 50; // 1 meter = PPM pixels
    private InputAdapter inputAdapter = new InputAdapter() {
        @Override
        public boolean keyDown(int keycode) {
            if (keycode == Input.Keys.SPACE) {
                //testBody.applyTorque(2.0f, true);
                testBody.applyLinearImpulse(new Vector2(-0.1f,0),testBody.getLocalCenter(),true);
                //force.add(-0.1f, 0);
                System.out.println(force.toString());
            }
            return false;
        }
    };
    private World world;
    private OrthographicCamera camera;
    private Box2DDebugRenderer box2DDebugRenderer;

    private Body testBody;
    private BodyDef testBodyDef;
    private FixtureDef testBodyFixtureDef;

    private Vector2 force;

    public GameTest() {
        world = new World(new Vector2(0, 0f), false);
        box2DDebugRenderer = new Box2DDebugRenderer(true, true, false, true, true, true);
        camera = new OrthographicCamera(Gdx.graphics.getWidth() / PPM, Gdx.graphics.getHeight() / PPM);

        testBodyDef = new BodyDef();
        testBodyDef.position.set(Gdx.graphics.getWidth() / 4 / PPM, Gdx.graphics.getHeight() / 4 / PPM);
        testBodyDef.type = BodyDef.BodyType.DynamicBody;

        Shape testBodyShape;
        testBodyShape = new CircleShape();
        testBodyShape.setRadius(.5f);

        testBodyFixtureDef = new FixtureDef();
        testBodyFixtureDef.shape = testBodyShape;
        testBodyFixtureDef.density = 1.0f;
        testBodyFixtureDef.friction = 0.2f;
        testBodyFixtureDef.restitution = 1.0f;


        testBody = world.createBody(testBodyDef);
        testBody.createFixture(testBodyFixtureDef);

        testBodyShape.dispose();

        testBodyDef.type = BodyDef.BodyType.StaticBody;
        testBodyDef.position.set(0, 10 / PPM);

        testBodyShape = new EdgeShape();
        ((EdgeShape) testBodyShape).set(new Vector2(0, 0), new Vector2(10, 0));

        testBodyFixtureDef.shape = testBodyShape;
        testBodyFixtureDef.restitution = 0.2f;

        world.createBody(testBodyDef).createFixture(testBodyFixtureDef);


        force = new Vector2();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

       // camera.setToOrtho(false, (testBody.getLocalCenter()).x*PPM,(testBody.getLocalCenter()).y*PPM);
        box2DDebugRenderer.render(world, camera.combined);

        world.step(0.16f, 8, 3);
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

    }

    @Override
    public InputAdapter getInputAdapter() {
        return inputAdapter;
    }
}
