package com.sunday.game.Player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

public class MovePlayer extends Sprite {
   private World world;
   private Body body;
   private Box2DDebugRenderer debugRenderer;
   private float accumulator =0;

   private void somePhysics(float deltaTime){
       float frameTime = Math.min(deltaTime,0.25f);
       accumulator += frameTime;
       /*while (accumulator>= Constants.TIME_STEP){
           WorldManager.world.step(Constants.TIME_STEP , Constants.VELOCITY_ITERATIONS,
                   Constants.POSITION_ITERATIONS);
       }*/
   }
   public MovePlayer(String name,float x,float y){
       super(new Texture(name));
       setPosition(x-getWidth()/2,y-getHeight()/2);
   }
   void createBody(){
       BodyDef bodyDef = new BodyDef();
       //a static body is not affected by Gravity
       //a kinematic body is not affected by gravity but other forces
       //dynamic body is affected by gravity and other forces
       bodyDef.type = BodyDef.BodyType.DynamicBody;
       bodyDef.position.set(getX(),getY());
       body = world.createBody(bodyDef);

       PolygonShape shape = new PolygonShape();
       shape.setAsBox(getWidth()/2,getHeight()/2);
       //properties of Body
       FixtureDef fixtureDef = new FixtureDef();
       fixtureDef.shape = shape;
       fixtureDef.density = 1;

       Fixture fixture = body.createFixture(fixtureDef);
       shape.dispose();
       world.dispose();
   }

}
