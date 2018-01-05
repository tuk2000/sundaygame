package com.sunday.game.engine.examples.hero;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.sunday.game.engine.common.DataOperation;
import com.sunday.game.engine.common.enums.Action;
import com.sunday.game.engine.control.EventProcessor;
import com.sunday.game.engine.control.events.CollisionEvent;
import com.sunday.game.engine.control.events.Event;
import com.sunday.game.engine.control.events.InputEvent;
import com.sunday.game.engine.control.events.KeyBoardEvent;

public class HeroEventProcessor implements EventProcessor {
    private HeroModel heroModel;

    public HeroEventProcessor(HeroModel heroModel) {
        this.heroModel = heroModel;
    }

    @Override
    public void processEvent(Event event) {
        switch (event.getEventType()) {
            case Collision:
                handleCollision((CollisionEvent) event);
                break;
            case Input:
                handleInput((InputEvent) event);
                break;
        }
    }

    private void handleInput(InputEvent inputEvent) {
        if (inputEvent instanceof KeyBoardEvent) {
            KeyBoardEvent keyBoardEvent = (KeyBoardEvent) inputEvent;
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
                    heroModel.holderPort.synchronize(heroModel.movementState, DataOperation.Modification);
                    break;
                case 'L':
                case 'l':
                    heroModel.outlook.dimension.add(16, 16);
                    heroModel.holderPort.synchronize(heroModel.outlook, DataOperation.Modification);
                    break;
                case 'S':
                case 's':
                    heroModel.outlook.dimension.add(-16, -16);
                    if (heroModel.outlook.dimension.x < 16 || heroModel.outlook.dimension.y < 32) {
                        heroModel.outlook.dimension.set(16, 32);
                    }
                    heroModel.holderPort.synchronize(heroModel.outlook, DataOperation.Modification);
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
                    heroModel.holderPort.synchronize(heroModel.physicReflection, DataOperation.Modification);
                    break;
                case 'J':
                case 'j':
                    heroModel.movementState.action = Action.Jumping;
                    body.applyLinearImpulse(0, 1000, worldCenter.x, worldCenter.y, true);
                    heroModel.holderPort.synchronize(heroModel.physicReflection, DataOperation.Modification);
                    break;
                default:
                    body.setLinearVelocity(0, 0);
                    heroModel.holderPort.synchronize(heroModel.physicReflection, DataOperation.Modification);
            }
        }
    }

    private void handleCollision(CollisionEvent collisionEvent) {

    }
}
