package com.sunday.engine.render.managers;


import com.badlogic.gdx.Input;
import com.sunday.engine.driver.gamepad.GamePad;
import com.sunday.engine.driver.keyboard.KeyBoard;
import com.sunday.engine.event.Event;
import com.sunday.engine.event.EventProcessor;
import com.sunday.engine.event.driver.GamePadEvent;
import com.sunday.engine.event.driver.KeyBoardEvent;

public class RendererManager implements EventProcessor {
    public boolean DoRenderMap = true;
    public boolean DoRenderSprite = false;
    public boolean DoRenderStage = false;
    public boolean DoRenderTexture = true;
    public boolean DoRenderWorld = true;

    @Override
    public void processEvent(Event event) {
        if (event instanceof KeyBoardEvent) {
            KeyBoard keyBoard = (KeyBoard) event.getSource();
            switch (keyBoard.keyBoardSignal) {
                case Pressed:
                    switch (keyBoard.key) {
                        case Input.Keys.F6:
                            DoRenderMap = !DoRenderMap;
                            break;
                        case Input.Keys.F7:
                            DoRenderSprite = !DoRenderSprite;
                            break;
                        case Input.Keys.F8:
                            DoRenderStage = !DoRenderStage;
                            break;
                        case Input.Keys.F9:
                            DoRenderTexture = !DoRenderTexture;
                            break;
                        case Input.Keys.F10:
                            DoRenderWorld = !DoRenderWorld;
                            break;
                    }
            }
        } else if (event instanceof GamePadEvent) {
            GamePad gamePad = (GamePad) event.getSource();
            switch (gamePad.gamePadSignal) {
                case ButtonDown:
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
                    break;
            }
        }
    }
}
