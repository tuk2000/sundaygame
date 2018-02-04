package com.sunday.engine.examples.hero;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.sunday.engine.common.AnimationTimer;
import com.sunday.engine.common.DataSignal;
import com.sunday.engine.common.Outlook;
import com.sunday.engine.common.viewlayers.TextureViewLayer;
import com.sunday.engine.databank.ports.UserPort;
import com.sunday.engine.databank.synchronize.SynchronizeCondition;
import com.sunday.engine.databank.synchronize.SynchronizeEvent;
import com.sunday.engine.databank.synchronize.SynchronizeExecutor;
import com.sunday.engine.model.AbstractModel;

public class HeroModel extends AbstractModel {
    private HeroEventProcessor heroEventProcessor;
    private HeroAnimation heroAnimation = new HeroAnimation();
    private TextureViewLayer textureViewLayer = new TextureViewLayer(heroAnimation.getKeyFrame(movementState));
    private SynchronizeCondition movementWithAnimation = new SynchronizeCondition(movementState, DataSignal.Modification);
    private SynchronizeExecutor<Outlook> animationUpdater = new SynchronizeExecutor<Outlook>() {
        @Override
        public void execute(SynchronizeEvent<Outlook> synchronizeEvent) {
            if (synchronizeEvent.dataSignal == DataSignal.Modification) {
                textureViewLayer.updateTexture(heroAnimation.getKeyFrame(movementState));
            }
        }
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
    protected void initDataSynchronize(UserPort userPort) {
        userPort.addDataSynchronize(movementWithAnimation, animationUpdater);
        userPort.addDataSynchronize(AnimationTimer.getCondition(), animationUpdater);
    }

    @Override
    public void dispose() {

    }
}
