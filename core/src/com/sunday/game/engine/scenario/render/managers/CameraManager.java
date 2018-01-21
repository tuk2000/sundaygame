package com.sunday.game.engine.scenario.render.managers;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.sunday.game.engine.events.EventProcessor;
import com.sunday.game.engine.events.Event;
import com.sunday.game.engine.events.driver.GamePadEvent;
import com.sunday.game.engine.events.driver.KeyBoardEvent;

public class CameraManager implements EventProcessor {
    private OrthographicCamera camera;
    private float zoom;
    private Vector3 cameraPosition;

    public CameraManager(OrthographicCamera camera) {
        this.camera = camera;
        cameraPosition = new Vector3();
        recordCameraState();
    }

    public void recordCameraState() {
        zoom = camera.zoom;
        cameraPosition.set(camera.position);
    }

    public void recoverCameraState() {
        camera.zoom = zoom;
        camera.position.set(cameraPosition);
        camera.update();
    }

    @Override
    public void processEvent(Event event) {
        if (event instanceof KeyBoardEvent) {
            KeyBoardEvent keyBoardEvent = (KeyBoardEvent) event;
            switch (keyBoardEvent.getKey()) {
                case Input.Keys.UP:
                    camera.translate(0, 10);
                    break;
                case Input.Keys.DOWN:
                    camera.translate(0, -10);
                    break;
                case Input.Keys.LEFT:
                    camera.translate(-10, 0);
                    break;
                case Input.Keys.RIGHT:
                    camera.translate(10, 0);
                    break;
                case Input.Keys.F2:
                    camera.zoom += 0.1f;
                    break;
                case Input.Keys.F3:
                    camera.zoom -= 0.1f;
                    break;
                case Input.Keys.F4:
                    camera.rotate(0.2f);
                    break;
                case Input.Keys.F5:
                    camera.rotate(-0.2f);
                    break;
            }
            camera.update();
        } else if (event instanceof GamePadEvent) {
            GamePadEvent gamePadEvent = (GamePadEvent) event;
            switch (gamePadEvent.getOperation()) {
                case ButtonDown:
                    switch (gamePadEvent.getButton()) {
                        case 4:
                            camera.zoom += 0.1f;
                            break;
                        case 5:
                            camera.zoom -= 0.1f;
                            break;
                        case 6:
                            camera.rotate(0.2f);
                            break;
                        case 7:
                            camera.rotate(-0.2f);
                            break;
                    }
                    break;
                case PovMove:
                    switch (gamePadEvent.getPovDirection()) {
                        case north:
                            camera.translate(0, 10);
                            break;
                        case south:
                            camera.translate(0, -10);
                            break;
                        case west:
                            camera.translate(-10, 0);
                            break;
                        case east:
                            camera.translate(10, 0);
                            break;
                    }
            }
        }
    }
}
