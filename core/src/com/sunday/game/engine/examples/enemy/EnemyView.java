package com.sunday.game.engine.examples.enemy;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.sunday.game.engine.common.EntityPhysicDefinition;
import com.sunday.game.engine.model.AbstractModel;
import com.sunday.game.engine.view.RoleAbstractView;

public class EnemyView extends RoleAbstractView {
    private SawAnimation sawAnimation;

    public EnemyView() {
        sawAnimation = new SawAnimation();
        screenViewLayer.setViewComponent(sawAnimation.getKeyFrame());
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
    public void synchronizeWithRoleModel(AbstractModel abstractModel) {
        screenViewLayer.setViewComponent(sawAnimation.getKeyFrame());
    }
}