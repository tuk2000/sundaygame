package com.sunday.game.engine.examples.hero;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.sunday.game.engine.common.EntityPhysicDefinition;
import com.sunday.game.engine.model.AbstractModel;
import com.sunday.game.engine.view.RoleAbstractView;

public class HeroView extends RoleAbstractView {

    private HeroAnimation heroAnimation;

    public HeroView() {
        heroAnimation = new HeroAnimation();

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
    public void synchronizeWithRoleModel(AbstractModel abstractModel) {
        screenViewLayer.setViewComponent(heroAnimation.getKeyFrame(abstractModel.roleMovementStatus));
    }
}