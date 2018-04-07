package com.sunday.engine.render.managers;


import com.badlogic.gdx.Input;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.databank.SystemPortSharing;
import com.sunday.engine.environment.EnvironmentDataContext;
import com.sunday.engine.environment.driver.gamepad.GamePad;
import com.sunday.engine.environment.driver.gamepad.GamePadCondition;
import com.sunday.engine.environment.driver.keyboard.KeyBoard;
import com.sunday.engine.environment.driver.keyboard.KeyBoardCondition;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;

public class RendererManager implements SystemPortSharing {
    public boolean DoRenderDebug = false;
    public boolean DoRenderMap = true;
    public boolean DoRenderSprite = false;
    public boolean DoRenderStage = false;
    public boolean DoRenderTexture = true;
    public boolean DoRenderWorld = true;

    @Override
    public void connectWith(SystemPort systemPort) {
        systemPort.addDataInstance(new Rule(KeyBoardCondition.keyPressed("F2", "F3", "F4", "F5", "F6", "F7"), new Reaction<EnvironmentDataContext<KeyBoard>>() {
            @Override
            public void accept(EnvironmentDataContext<KeyBoard> enviromentDataContext) {
                KeyBoard keyBoard = enviromentDataContext.getEnvironmentData();
                System.out.println("keyBoard---RenderManager---" + keyBoard.character);
                switch (keyBoard.keyCode) {
                    case Input.Keys.F2:
                        DoRenderMap = !DoRenderMap;
                        break;
                    case Input.Keys.F3:
                        DoRenderSprite = !DoRenderSprite;
                        break;
                    case Input.Keys.F4:
                        DoRenderStage = !DoRenderStage;
                        break;
                    case Input.Keys.F5:
                        DoRenderTexture = !DoRenderTexture;
                        break;
                    case Input.Keys.F6:
                        DoRenderWorld = !DoRenderWorld;
                        break;
                    case Input.Keys.F7:
                        DoRenderDebug = !DoRenderDebug;
                }
            }
        }));

        systemPort.addDataInstance(new Rule(GamePadCondition.buttonDown(0, 1, 2, 3, 9), new Reaction<EnvironmentDataContext<GamePad>>() {
            @Override
            public void accept(EnvironmentDataContext<GamePad> enviromentDataContext) {
                GamePad gamePad = enviromentDataContext.getEnvironmentData();
                System.out.println("gamePad---RenderManager---" + gamePad.buttonCode);
                switch (gamePad.buttonCode) {
                    case 0:
                        DoRenderMap = !DoRenderMap;
                        break;
                    case 1:
                        DoRenderSprite = !DoRenderSprite;
                        break;
                    case 2:
                        DoRenderStage = !DoRenderStage;
                        break;
                    case 3:
                        DoRenderTexture = !DoRenderTexture;
                        break;
                    case 9:
                        DoRenderWorld = !DoRenderWorld;
                        break;
                }
            }
        }));

    }

    @Override
    public void disconnectWith(SystemPort systemPort) {

    }
}
