package com.sunday.game.engine.scenario.render.managers;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.sunday.game.engine.control.EventProcessor;
import com.sunday.game.engine.control.events.Event;
import com.sunday.game.engine.control.events.KeyBoardEvent;

public class CameraManager implements EventProcessor {
    private OrthographicCamera camera;

    public CameraManager(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    public void processEvent(Event event) {
        if (event instanceof KeyBoardEvent) {
            KeyBoardEvent keyBoardEvent = (KeyBoardEvent) event;
            switch (keyBoardEvent.getKey()) {
                case Input.Keys.UP:
                    camera.translate(0, -10);
                    break;
                case Input.Keys.DOWN:
                    camera.translate(0, 10);
                    break;
                case Input.Keys.LEFT:
                    camera.translate(10, 0);
                    break;
                case Input.Keys.RIGHT:
                    camera.translate(-10, 0);
                    break;
                case Input.Keys.F2:
                    camera.zoom += 0.2f;
                    break;
                case Input.Keys.F3:
                    camera.zoom -= 0.2f;
                    break;
                case Input.Keys.F4:
                    camera.rotate(0.2f);
                    break;
                case Input.Keys.F5:
                    camera.rotate(-0.2f);
                    break;
            }
            camera.update();
        }
    }
}
