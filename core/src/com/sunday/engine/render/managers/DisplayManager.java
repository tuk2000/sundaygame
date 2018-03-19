package com.sunday.engine.render.managers;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.databank.SystemPortSharing;
import com.sunday.engine.driver.gamepad.GamePad;
import com.sunday.engine.event.window.Window;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;
import com.sunday.engine.rule.condition.GamePadCondition;
import com.sunday.engine.rule.condition.WindowCondition;

public class DisplayManager implements SystemPortSharing {
    private CameraManager cameraManager;
    private Viewport viewport;

    public DisplayManager(CameraManager cameraManager, Viewport viewport) {
        this.cameraManager = cameraManager;
        this.viewport = viewport;
    }

    @Override
    public void connectWith(SystemPort systemPort) {
        systemPort.addDataInstance(new Rule(WindowCondition.resized(), new Reaction<Window>() {
            @Override
            public void accept(Window window) {
                System.out.println("window---RenderManager---[" + window.width + "," + window.height + "]");
                cameraManager.recordCameraState();
                viewport.update(window.width, window.height);
                viewport.apply();
                cameraManager.recoverCameraState();
            }

        }));
        systemPort.addDataInstance(new Rule(GamePadCondition.buttonDown(5), new Reaction<GamePad>() {
            @Override
            public void accept(GamePad gamePad) {
                System.out.println("gamePad---RenderManager---" + gamePad.buttonCode);
                //
            }
        }));

    }

    @Override
    public void disconnectWith(SystemPort systemPort) {

    }
}
