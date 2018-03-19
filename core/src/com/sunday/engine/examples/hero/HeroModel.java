package com.sunday.engine.examples.hero;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.sunday.engine.common.DataSignal;
import com.sunday.engine.databank.Port;
import com.sunday.engine.model.AbstractModel;
import com.sunday.engine.model.property.Outlook;
import com.sunday.engine.model.property.viewlayers.TextureViewLayer;
import com.sunday.engine.model.state.Action;
import com.sunday.engine.model.state.Direction;
import com.sunday.engine.render.AnimationTimer;
import com.sunday.engine.rule.Rule;
import com.sunday.engine.rule.condition.DataCondition;
import com.sunday.engine.rule.condition.KeyBoardCondition;

public class HeroModel extends AbstractModel {
    private HeroAnimation heroAnimation = new HeroAnimation();
    private TextureViewLayer textureViewLayer = new TextureViewLayer(heroAnimation.getKeyFrame(movement));

    public HeroModel() {

        outlook.shape = Shape.Type.Polygon;
        outlook.dimension.set(16.f, 20.f);
        outlook.viewLayers.add(textureViewLayer);
        movement.position.set(32, 32);

        BodyDef bodyDef = physicReflection.bodyDef;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(movement.position);

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
    protected void disconnectWithInternal(Port port) {

    }

    @Override
    protected void connectWithInternal(Port port) {

        port.addDataInstance(new Rule(new DataCondition<Outlook>(outlook, DataSignal.Modification), outlook1 -> {
            textureViewLayer.updateTexture(heroAnimation.getKeyFrame(movement));
        }));

        port.addDataInstance(new Rule(AnimationTimer.getCondition(), animationTimer -> {
            textureViewLayer.updateTexture(heroAnimation.getKeyFrame(movement));
        }));

        port.addDataInstance(new Rule(KeyBoardCondition.keyTyped("1"), keyBoard -> {
            System.out.println("keyTyped('1')");
            movement.position.add(-10, 0);
            port.broadcast(movement, DataSignal.Modification);
        }));

        port.addDataInstance(new Rule(KeyBoardCondition.keyTyped("2"), keyBoard -> {
            System.out.println("keyTyped('2')");
            movement.position.add(10, 0);
            port.broadcast(movement, DataSignal.Modification);
        }));

        port.addDataInstance(new Rule(KeyBoardCondition.keyPressed("3"), keyBoard -> {
            System.out.println("keyTyped('3')");
            movement.direction = movement.direction == Direction.Left ? Direction.Right : Direction.Left;
            movement.action = Action.Fighting;
            port.broadcast(movement, DataSignal.Modification);
        }));

    }

    @Override
    public void dispose() {

    }
}
