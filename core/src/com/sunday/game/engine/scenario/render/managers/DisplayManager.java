package com.sunday.game.engine.scenario.render.managers;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.sunday.game.engine.control.EventProcessor;
import com.sunday.game.engine.control.events.Event;
import com.sunday.game.engine.control.events.WindowEvent;
import com.sunday.game.engine.control.events.WindowResizedEvent;

public class DisplayManager implements EventProcessor {
    private float aspectRatio;//height/width
    private int displayWidth; // pixels
    private int displayHeight; // pixels
    private CameraManager cameraManager;
    private Viewport viewport;

    public DisplayManager(CameraManager cameraManager, Viewport viewport) {
        this.cameraManager = cameraManager;
        this.viewport = viewport;
    }

    @Override
    public void processEvent(Event event) {
        if (event instanceof WindowEvent) {
            WindowEvent windowEvent = (WindowEvent) event;
            switch (windowEvent.getType()) {
                case Resized:
                    WindowResizedEvent windowResizedEvent = (WindowResizedEvent) event;
                    cameraManager.recordCameraState();
                    displayWidth = windowResizedEvent.getWidth();
                    displayHeight = windowResizedEvent.getHeight();
                    viewport.update(displayWidth, displayHeight);
                    viewport.apply();
                    cameraManager.recoverCameraState();
                    break;
                case Hide:
                case Show:
                case Closed:
                case Maximum:
                case Minimum:
            }
        }
    }
}
