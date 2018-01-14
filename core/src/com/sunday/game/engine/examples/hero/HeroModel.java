package com.sunday.game.engine.examples.hero;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.sunday.game.engine.common.AnimationTimer;
import com.sunday.game.engine.common.DataOperation;
import com.sunday.game.engine.common.Outlook;
import com.sunday.game.engine.common.viewlayers.TextureViewLayer;
import com.sunday.game.engine.control.EventProcessor;
import com.sunday.game.engine.databank.port.HolderPort;
import com.sunday.game.engine.databank.synchronize.SynchronizeCondition;
import com.sunday.game.engine.databank.synchronize.SynchronizeEvent;
import com.sunday.game.engine.databank.synchronize.SynchronizeExecutor;
import com.sunday.game.engine.model.AbstractModel;

public class HeroModel extends AbstractModel {
    private HeroEventProcessor heroEventProcessor;
    private HeroAnimation heroAnimation = new HeroAnimation();
    private TextureViewLayer textureViewLayer = new TextureViewLayer(heroAnimation.getKeyFrame(movementState));
    private SynchronizeCondition movementWithAnimation = new SynchronizeCondition(movementState, DataOperation.Modification);
    private SynchronizeExecutor<Outlook> animationUpdater = new SynchronizeExecutor<Outlook>() {
        @Override
        public void execute(SynchronizeEvent<Outlook> synchronizeEvent) {
            if (synchronizeEvent.dataOperation == DataOperation.Modification) {
                textureViewLayer.updateTexture(heroAnimation.getKeyFrame(movementState));
            }
        }
    };

    public HeroModel() {
        heroEventProcessor = new HeroEventProcessor(this);
        outlook.shape = Shape.Type.Polygon;
        outlook.dimension.set(16.f, 32.f);
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
    public EventProcessor getEventProcessor() {
        return heroEventProcessor;
    }

    @Override
    protected void initDataSynchronize(HolderPort holderPort) {
        holderPort.addDataSynchronize(movementWithAnimation, animationUpdater);
        holderPort.addDataSynchronize(AnimationTimer.getCondition(), animationUpdater);
    }

    @Override
    public void dispose() {

    }
}
