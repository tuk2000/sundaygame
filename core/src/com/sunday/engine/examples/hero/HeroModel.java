package com.sunday.engine.examples.hero;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.sunday.engine.common.AnimationTimer;
import com.sunday.engine.common.DataSignal;
import com.sunday.engine.common.viewlayers.TextureViewLayer;
import com.sunday.engine.databank.Port;
import com.sunday.engine.model.AbstractModel;
import com.sunday.engine.rule.Condition;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.condition.DataCondition;
import com.sunday.engine.rule.rules.TriggerRule;

public class HeroModel extends AbstractModel {
    private HeroEventProcessor heroEventProcessor;
    private HeroAnimation heroAnimation = new HeroAnimation();
    private TextureViewLayer textureViewLayer = new TextureViewLayer(heroAnimation.getKeyFrame(movementState));
    private Condition movementWithAnimation = DataCondition.dataSignals(movementState, DataSignal.Modification);
    private Reaction animationUpdater = new Reaction() {
        @Override
        public void run() {

        }
//        @Override
//        public void execute(SynchronizeEvent<Outlook> synchronizeEvent) {
//            if (synchronizeEvent.dataSignal == DataSignal.Modification) {
//                textureViewLayer.updateTexture(heroAnimation.getKeyFrame(movementState));
//            }
//        }
    };

    public HeroModel() {

        outlook.shape = Shape.Type.Polygon;
        outlook.dimension.set(16.f, 20.f);
        outlook.viewLayers.add(textureViewLayer);
        movementState.position.set(32, 32);

        BodyDef bodyDef = physicReflection.bodyDef;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(movementState.position);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setRadius(1.0f);
        polygonShape.setAsBox(outlook.dimension.x, outlook.dimension.y);

        FixtureDef fixtureDef = physicReflection.fixtureDef;
        fixtureDef.shape = polygonShape;
        fixtureDef.friction = .1f;
        fixtureDef.restitution = 0.2f;
        fixtureDef.density = 0.1f;

    }

    @Override
    protected void initDataSynchronize(Port port) {
        port.addDataInstance(new TriggerRule(movementWithAnimation, animationUpdater));
        port.addDataInstance(new TriggerRule(AnimationTimer.getCondition(), animationUpdater));
    }

    @Override
    public void dispose() {

    }
}
