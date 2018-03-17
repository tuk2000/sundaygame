package com.sunday.engine.render.managers;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.sunday.engine.driver.gamepad.GamePad;
import com.sunday.engine.driver.gamepad.GamePadSignal;
import com.sunday.engine.event.Event;
import com.sunday.engine.event.EventProcessor;
import com.sunday.engine.event.driver.GamePadEvent;
import com.sunday.engine.event.window.WindowEvent;
import com.sunday.engine.event.window.WindowSignal;

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
            WindowSignal windowSignal = (WindowSignal) event.getSignal();
            switch (windowSignal) {
                case Resized:
                    cameraManager.recordCameraState();
                    displayWidth = windowEvent.getWidth();
                    displayHeight = windowEvent.getHeight();
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
        } else if (event instanceof GamePadEvent) {
            GamePad gamePad = (GamePad) event.getSource();
            GamePadSignal gamePadSignal = (GamePadSignal) event.getSignal();
            switch (gamePadSignal) {
                case ButtonDown:
                    if (gamePad.buttonCode == 5) {
                        //
                    }
                    break;
            }
        }
    }
}
