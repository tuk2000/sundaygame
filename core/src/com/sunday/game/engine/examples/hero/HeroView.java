package com.sunday.game.engine.examples.hero;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.sunday.game.engine.common.PhysicDefinition;
import com.sunday.game.engine.model.AbstractModel;
import com.sunday.game.engine.view.RoleAbstractView;

public class HeroView extends RoleAbstractView {

    private HeroAnimation heroAnimation;

    public HeroView() {
        heroAnimation = new HeroAnimation();

        FixtureDef fixtureDef = new FixtureDef();
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0, 32);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setRadius(10.0f);
        polygonShape.setAsBox(16f, 16f);

        fixtureDef.shape = polygonShape;
        fixtureDef.friction = .5f;
        fixtureDef.restitution = .1f;
        fixtureDef.density = 15f;

        PhysicDefinition physicDefinition = new PhysicDefinition(fixtureDef, bodyDef);
        physicViewLayer.setViewComponent(physicDefinition);
    }

    @Override
    public void synchronizeWithRoleModel(AbstractModel abstractModel) {
        screenViewLayer.setViewComponent(heroAnimation.getKeyFrame(abstractModel.movementState));
        abstractModel.physicDefinition = physicViewLayer.getViewComponent();
    }
}
