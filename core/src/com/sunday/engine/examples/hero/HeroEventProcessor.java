package com.sunday.engine.examples.hero;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.sunday.engine.common.DataSignal;
import com.sunday.engine.common.enums.Action;
import com.sunday.engine.event.Event;
import com.sunday.engine.event.EventProcessor;
import com.sunday.engine.event.driver.DriverEvent;
import com.sunday.engine.event.driver.KeyBoardEvent;
import com.sunday.engine.event.synchronize.CollisionEvent;

public class HeroEventProcessor implements EventProcessor {
    private HeroModel heroModel;

    public HeroEventProcessor(HeroModel heroModel) {
        this.heroModel = heroModel;
    }

    @Override
    public void processEvent(Event event) {

        if (event instanceof CollisionEvent) {
            handleCollision((CollisionEvent) event);
        } else if (event instanceof DriverEvent) {
            handleInput((DriverEvent) event);
        }
    }

    private void handleInput(DriverEvent driverEvent) {
        if (driverEvent instanceof KeyBoardEvent) {
            KeyBoardEvent keyBoardEvent = (KeyBoardEvent) driverEvent;
            Vector2 position = heroModel.movementState.position;

            switch ((keyBoardEvent.getCharacter())) {
                case '1':
                    position.add(-10, 0);
                    break;
                case '2':
                    position.add(10, 0);
                    break;
                case '3':
                    position.add(0, -10);
                    break;
                case '4':
                    position.add(0, 10);
                    break;
                case 'H':
                case 'h':
                    heroModel.movementState.action = Action.StandStill;
                    heroModel.port.broadcast(heroModel.movementState, DataSignal.Modification);
                    break;
                case 'L':
                case 'l':
                    heroModel.outlook.dimension.add(16, 16);
                    heroModel.port.broadcast(heroModel.outlook, DataSignal.Modification);
                    break;
                case 'S':
                case 's':
                    heroModel.outlook.dimension.add(-16, -16);
                    if (heroModel.outlook.dimension.x < 16 || heroModel.outlook.dimension.y < 32) {
                        heroModel.outlook.dimension.set(16, 32);
                    }
                    heroModel.port.broadcast(heroModel.outlook, DataSignal.Modification);
                    break;
            }

            Body body;
            Vector2 worldCenter;
            if (heroModel.physicReflection.bodyCreated) {
                body = heroModel.physicReflection.body;
                worldCenter = body.getWorldCenter();
            } else {
                return;
            }

            switch (keyBoardEvent.getCharacter()) {
                case 'R':
                case 'r':
                    heroModel.movementState.action = Action.Running;
                    body.applyLinearImpulse(1000, 0, worldCenter.x, worldCenter.y, true);
                    heroModel.port.broadcast(heroModel.movementState, DataSignal.Modification);
                    heroModel.port.broadcast(heroModel.physicReflection, DataSignal.Modification);
                    break;
                case 'J':
                case 'j':
                    heroModel.movementState.action = Action.Jumping;
                    body.applyLinearImpulse(0, 1000, worldCenter.x, worldCenter.y, true);
                    heroModel.port.broadcast(heroModel.movementState, DataSignal.Modification);
                    heroModel.port.broadcast(heroModel.physicReflection, DataSignal.Modification);
                    break;
                default:
                    body.setLinearVelocity(0, 0);
                    heroModel.port.broadcast(heroModel.physicReflection, DataSignal.Modification);
            }
        }
    }

    private void handleCollision(CollisionEvent collisionEvent) {

    }
}
