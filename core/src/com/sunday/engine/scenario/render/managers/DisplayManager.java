package com.sunday.engine.scenario.render.managers;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.sunday.engine.events.Event;
import com.sunday.engine.events.EventProcessor;
import com.sunday.engine.events.driver.GamePadEvent;
import com.sunday.engine.events.window.WindowEvent;

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
        }else if(event instanceof GamePadEvent){
            GamePadEvent gamePadEvent=(GamePadEvent)event;
            switch (gamePadEvent.getOperation()){
                case ButtonDown:
                   if(gamePadEvent.getButton()==5){
                       //
                   }
                   break;
            }
        }
    }
}
