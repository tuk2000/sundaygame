package com.sunday.game.World.View.Views;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.sunday.game.World.View.EntityPhysicDefinition;
import com.sunday.game.World.View.Sprites.HeroSprite;

public class HeroView extends RoleView {

    private HeroSprite heroSprite;

    public HeroView() {
        heroSprite = new HeroSprite();
        screenViewLayer.setViewComponent(heroSprite.getAnimationTexture());

        FixtureDef fixtureDef = new FixtureDef();
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(.5f, .5f);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setRadius(.5f);
        polygonShape.setAsBox(.5f, 1f);

        fixtureDef.shape = polygonShape;
        fixtureDef.friction = .75f;
        fixtureDef.restitution = .1f;
        fixtureDef.density = 15f;

        EntityPhysicDefinition entityPhysicDefinition = new EntityPhysicDefinition(fixtureDef, bodyDef, polygonShape);
        physicViewLayer.setViewComponent(entityPhysicDefinition);
    }

    @Override
    public void updateLayers() {
        screenViewLayer.setViewComponent(heroSprite.getAnimationTexture());
    }
}
