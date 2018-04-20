package com.sunday.engine.examples.hero;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.sunday.engine.common.context.CustomizedDataContext;
import com.sunday.engine.databank.Port;
import com.sunday.engine.environment.driver.DriverContext;
import com.sunday.engine.environment.driver.keyboard.KeyBoard;
import com.sunday.engine.environment.driver.keyboard.KeyBoardCondition;
import com.sunday.engine.environment.time.Timer;
import com.sunday.engine.environment.time.TimerCondition;
import com.sunday.engine.environment.time.TimerContext;
import com.sunday.engine.model.AbstractModel;
import com.sunday.engine.model.property.MovementSignal;
import com.sunday.engine.model.property.Outlook;
import com.sunday.engine.model.property.OutlookSignal;
import com.sunday.engine.model.property.viewlayers.TextureViewLayer;
import com.sunday.engine.model.state.Action;
import com.sunday.engine.model.state.Direction;
import com.sunday.engine.rule.CustomizedDataCondition;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;

public class HeroModel extends AbstractModel {
    private HeroAnimation heroAnimation = new HeroAnimation();
    private TextureViewLayer<com.badlogic.gdx.graphics.Texture> textureViewLayer = new TextureViewLayer<>(heroAnimation.getKeyFrame(movement));

    public HeroModel() {

        outlook.dimension.set(16.f, 20.f);
        outlook.viewLayers.add(textureViewLayer);
        movement.position.set(32, 32);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(movement.position);

        physicDefinition.setBodyDef(bodyDef);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(outlook.dimension.x, outlook.dimension.y);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.friction = 1;
        fixtureDef.restitution = 0;
        fixtureDef.density = 2f;

        physicDefinition.addFixtureDef(fixtureDef);
    }

    @Override
    protected void disconnectWithInternal(Port port) {

    }

    @Override
    protected void connectWithInternal(Port port) {

        port.addDataInstance(new Rule<>(new CustomizedDataCondition<Outlook, CustomizedDataContext<Outlook>>(outlook, OutlookSignal.class), new Reaction<CustomizedDataContext<Outlook>>() {
            @Override
            public void accept(CustomizedDataContext<Outlook> outlookCustomizedDataContext) {
                textureViewLayer.updateTexture(heroAnimation.getKeyFrame(movement));
            }
        }));

        port.addDataInstance(new Rule<>(TimerCondition.animationTimerCondition(), new Reaction<TimerContext<Timer>>() {
            @Override
            public void accept(TimerContext<Timer> timerContext) {
                Timer timer = timerContext.getData();
                heroAnimation.setStateTime(timer.lastTriggeredTime);
                textureViewLayer.updateTexture(heroAnimation.getKeyFrame(movement));
            }
        }));

        port.addDataInstance(new Rule<>(KeyBoardCondition.keyTyped("1"), new Reaction<DriverContext<KeyBoard>>() {

            @Override
            public void accept(DriverContext<KeyBoard> keyBoardDriverContext) {
                System.out.println("keyTyped('1')");
                movement.position.add(-10, 0);
                port.broadcast(movement, MovementSignal.ReLocated);
            }
        }));
        port.addDataInstance(new Rule<>(KeyBoardCondition.keyTyped("2"), new Reaction<DriverContext<KeyBoard>>() {

            @Override
            public void accept(DriverContext<KeyBoard> keyBoardDriverContext) {
                System.out.println("keyTyped('2')");
                movement.position.add(10, 0);
                port.broadcast(movement, MovementSignal.ReLocated);
            }
        }));
        port.addDataInstance(new Rule<>(KeyBoardCondition.keyPressed("3"), new Reaction<DriverContext<KeyBoard>>() {

            @Override
            public void accept(DriverContext<KeyBoard> keyBoardDriverContext) {
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
