package com.sunday.engine.examples.hero;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.sunday.engine.databank.Port;
import com.sunday.engine.environment.driver.keyboard.KeyBoard;
import com.sunday.engine.environment.driver.keyboard.KeyBoardCondition;
import com.sunday.engine.environment.driver.keyboard.KeyBoardSignal;
import com.sunday.engine.environment.time.Timer;
import com.sunday.engine.environment.time.TimerCondition;
import com.sunday.engine.environment.time.TimerSignal;
import com.sunday.engine.model.AbstractModel;
import com.sunday.engine.model.property.MovementSignal;
import com.sunday.engine.model.property.Outlook;
import com.sunday.engine.model.property.OutlookSignal;
import com.sunday.engine.model.property.viewlayers.TextureViewLayer;
import com.sunday.engine.model.state.Action;
import com.sunday.engine.model.state.Direction;
import com.sunday.engine.rule.DataCondition;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;

public class HeroModel extends AbstractModel {
    private HeroAnimation heroAnimation = new HeroAnimation();
    private TextureViewLayer textureViewLayer = new TextureViewLayer(heroAnimation.getKeyFrame(movement));

    public HeroModel() {

        outlook.dimension.set(16.f, 20.f);
        outlook.viewLayers.add(textureViewLayer);
        movement.position.set(32, 32);

        BodyDef bodyDef = physicReflection.bodyDef;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(movement.position);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(outlook.dimension.x, outlook.dimension.y);

        FixtureDef fixtureDef = physicReflection.fixtureDef;
        fixtureDef.shape = polygonShape;
        fixtureDef.friction = 1;
        fixtureDef.restitution = 0;
        fixtureDef.density = 2f;
    }

    @Override
    protected void disconnectWithInternal(Port port) {

    }

    @Override
    protected void connectWithInternal(Port port) {

        port.addDataInstance(new Rule(new DataCondition(outlook, OutlookSignal.class), new Reaction<Outlook, OutlookSignal>() {
            @Override
            public void accept(Outlook outlook, OutlookSignal outlookSignal) {
                textureViewLayer.updateTexture(heroAnimation.getKeyFrame(movement));
            }
        }));

        port.addDataInstance(new Rule(TimerCondition.animationTimerCondition(), new Reaction<Timer, TimerSignal>() {
            @Override
            public void accept(Timer timer, TimerSignal timerSignal) {
                heroAnimation.setStateTime(timer.lastTriggeredTime);
                textureViewLayer.updateTexture(heroAnimation.getKeyFrame(movement));
            }
        }));

        port.addDataInstance(new Rule(KeyBoardCondition.keyTyped("1"), new Reaction<KeyBoard, KeyBoardSignal>() {

            @Override
            public void accept(KeyBoard keyBoard, KeyBoardSignal keyBoardSignal) {
                System.out.println("keyTyped('1')");
                movement.position.add(-10, 0);
                port.broadcast(movement, MovementSignal.ReLocated);
            }
        }));
        port.addDataInstance(new Rule(KeyBoardCondition.keyTyped("2"), new Reaction<KeyBoard, KeyBoardSignal>() {

            @Override
            public void accept(KeyBoard keyBoard, KeyBoardSignal keyBoardSignal) {
                System.out.println("keyTyped('2')");
                movement.position.add(10, 0);
                port.broadcast(movement, MovementSignal.ReLocated);
            }
        }));
        port.addDataInstance(new Rule(KeyBoardCondition.keyPressed("3"), new Reaction<KeyBoard, KeyBoardSignal>() {

            @Override
            public void accept(KeyBoard keyBoard, KeyBoardSignal keyBoardSignal) {
                System.out.println("keyTyped('3')");
                movement.direction = movement.direction == Direction.Left ? Direction.Right : Direction.Left;
                movement.action = Action.Fighting;
                port.broadcast(movement, MovementSignal.ReDirection);
            }
        }));
    }

    @Override
    public void dispose() {

    }
}
