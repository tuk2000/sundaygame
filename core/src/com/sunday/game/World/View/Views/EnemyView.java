package com.sunday.game.World.View.Views;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.sunday.game.World.View.EntityPhysicDefinition;
import com.sunday.game.World.View.Sprites.SawSprite;

public class EnemyView extends RoleView {
    private SawSprite sawSprite;

    public EnemyView() {
        sawSprite = new SawSprite();
        screenViewLayer.setViewComponent(sawSprite.getAnimationTexture());

        FixtureDef fixtureDef = new FixtureDef();
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(.5f, .5f);

        Shape circle = new CircleShape();
        circle.setRadius(.5f);

        fixtureDef.shape = circle;
        fixtureDef.friction = .75f;
        fixtureDef.restitution = .1f;
        fixtureDef.density = 15f;

        EntityPhysicDefinition entityPhysicDefinition = new EntityPhysicDefinition(fixtureDef, bodyDef, circle);
        physicViewLayer.setViewComponent(entityPhysicDefinition);
    }

    @Override
    public void updateLayers() {
        screenViewLayer.setViewComponent(sawSprite.getAnimationTexture());
    }
}
