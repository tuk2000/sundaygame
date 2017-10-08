package com.sunday.game.Graphic;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.*;
import com.sunday.game.GameFramework.Input.InputReceiver;

public class OurShapes implements InputReceiver{
    private static final float TIMESTEP = 1 / 60f;
    private static final int VELOCITYITERATIONS = 8 ;
    private static final int POSITIONITERATIONS = 3 ;
    private World world;
    private Body body;
    private FixtureDef fixtureDef;
    private BodyDef bodyDef;
    private Box2DDebugRenderer box2DDebugRenderer;
    private OrthographicCamera camera;

    public OurShapes(World world, Body body, Box2DDebugRenderer box2DDebugRenderer, OrthographicCamera camera) {
        this.world = world;
        this.body = body;
        this.box2DDebugRenderer = box2DDebugRenderer;
        this.camera = camera;
    }
    public void create(){
        render();
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
        //Box
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(450, 700);
        //Ball Shape
        PolygonShape playerBody = new PolygonShape();
        playerBody.setAsBox(10,20);

        //We cann add fixture to a body like the properties below/
        fixtureDef.shape = playerBody;
        fixtureDef.density = 0.6f;
        fixtureDef.friction = 2.5f;
        fixtureDef.restitution = 0.5f;

        //uses all the properties and creates body
        world.createBody(bodyDef).createFixture(fixtureDef);

//        this.body = world.createBody(bodyDef);
//        this.body.createFixture(fixtureDef);
        playerBody.dispose();

    }
    public void render(){
        //box2DDebugRenderer.render(this.world, this.camera.combined);
        //this.world.step(this.TIMESTEP, this.VELOCITYITERATIONS, this.POSITIONITERATIONS);
    }

    @Override
    public InputAdapter getInputAdapter() {
        return null;
    }
}
