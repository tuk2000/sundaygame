package com.sunday.engine.render.managers;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.databank.SystemPortSharing;
import com.sunday.engine.environment.driver.gamepad.GamePad;
import com.sunday.engine.environment.driver.gamepad.GamePadCondition;
import com.sunday.engine.environment.driver.gamepad.GamePadSignal;
import com.sunday.engine.environment.event.window.Window;
import com.sunday.engine.environment.event.window.WindowCondition;
import com.sunday.engine.environment.event.window.WindowSignal;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;

public class DisplayManager implements SystemPortSharing {
    private CameraManager cameraManager;
    private Viewport viewport;

    public DisplayManager(CameraManager cameraManager, Viewport viewport) {
        this.cameraManager = cameraManager;
        this.viewport = viewport;
    }

    @Override
    public void connectWith(SystemPort systemPort) {
        systemPort.addDataInstance(new Rule(WindowCondition.resized(), new Reaction<Window, WindowSignal>() {
            @Override
            public void accept(Window window, WindowSignal windowSignal) {
                System.out.println("window---RenderManager---[" + window.width + "," + window.height + "]");
                cameraManager.recordCameraState();
                viewport.update(window.width, window.height);
                viewport.apply();
                cameraManager.recoverCameraState();
            }
        }));
        systemPort.addDataInstance(new Rule(GamePadCondition.buttonDown(5), new Reaction<GamePad, GamePadSignal>() {
            @Override
            public void accept(GamePad gamePad, GamePadSignal gamePadSignal) {
                System.out.println("gamePad---RenderManager---" + gamePad.buttonCode);
                //
            }
        }));

    }

    @Override
    public void disconnectWith(SystemPort systemPort) {

    }
}
