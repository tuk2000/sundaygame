package com.sunday.game.engine.examples.hero;

import com.badlogic.gdx.math.Vector2;
import com.sunday.game.engine.common.enums.Action;
import com.sunday.game.engine.control.EventProcessor;
import com.sunday.game.engine.control.events.*;

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
        if (inputEvent.getSource() == InputSource.Keyboard) {
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
                case 'R':
                case 'r':
                    heroModel.movementState.action = Action.Running;
                    break;
                case 'J':
                case 'j':
                    heroModel.movementState.action = Action.Jumping;
                    break;
                case 'L':
                case 'l':
                    heroModel.outlook.dimension.add(16, 16);
                    break;
                case 'S':
                case 's':
                    heroModel.outlook.dimension.add(-16, -16);
                    break;
            }


//            switch (keyBoardEvent.getKey()) {
//                case Input.Keys.LEFT:
//                    position.add(-10, 0);
//                    break;
//                case Input.Keys.RIGHT:
//                    position.add(10, 0);
//                    break;
//                case Input.Keys.DOWN:
//                    position.add(0, -10);
//                    break;
//                case Input.Keys.UP:
//                    position.add(0, 10);
//                    break;
//                case Input.Keys.H:
//                    heroModel.movementState.action = Action.StandStill;
//                    break;
//                case Input.Keys.R:
//                    heroModel.movementState.action = Action.Running;
//                    break;
//                case Input.Keys.J:
//                    heroModel.movementState.action = Action.Jumping;
//                    break;
//                case Input.Keys.L:
//                    heroModel.outlook.dimension.add(16, 16);
//                    break;
//                case Input.Keys.S:
//                    heroModel.outlook.dimension.add(-16, -16);
//                    break;
//            }
        }
    }

    private void handleCollision(CollisionEvent collisionEvent) {

    }
}
