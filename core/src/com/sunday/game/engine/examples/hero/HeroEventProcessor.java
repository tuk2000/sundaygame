package com.sunday.game.engine.examples.hero;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
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
                    break;
                case 'L':
                case 'l':
                    heroModel.outlook.dimension.add(16, 16);
                    heroModel.outlook.sizeChanged = true;
                    break;
                case 'S':
                case 's':
                    heroModel.outlook.dimension.add(-16, -16);
                    heroModel.outlook.sizeChanged = true;
                    break;
            }

            Body body;
            Vector2 worldCenter;
            if (heroModel.physicDefinition.hasPhysicReflection()) {
                body = heroModel.physicDefinition.getPhysicReflection().body;
                worldCenter = body.getWorldCenter();
            } else {
                return;
            }

            switch (keyBoardEvent.getCharacter()) {
                case 'R':
                case 'r':
                    heroModel.movementState.action = Action.Running;
                    body.applyLinearImpulse(100, 0, worldCenter.x, worldCenter.y, true);
                    break;
                case 'J':
                case 'j':
                    heroModel.movementState.action = Action.Jumping;
                    body.applyLinearImpulse(0, 100, worldCenter.x, worldCenter.y, true);
                    break;
                default:
                    body.setLinearVelocity(0, 0);
            }
        }
    }

    private void handleCollision(CollisionEvent collisionEvent) {

    }
}
