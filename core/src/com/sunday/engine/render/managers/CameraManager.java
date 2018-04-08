package com.sunday.engine.render.managers;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.sunday.engine.databank.SystemPort;
import com.sunday.engine.databank.SystemPortSharing;
import com.sunday.engine.environment.driver.DriverContext;
import com.sunday.engine.environment.driver.gamepad.GamePad;
import com.sunday.engine.environment.driver.gamepad.GamePadCondition;
import com.sunday.engine.environment.driver.keyboard.KeyBoard;
import com.sunday.engine.environment.driver.keyboard.KeyBoardCondition;
import com.sunday.engine.rule.Reaction;
import com.sunday.engine.rule.Rule;

public class CameraManager implements SystemPortSharing {
    private OrthographicCamera camera;
    private float zoom;
    private Vector3 cameraPosition;
    private Vector2 translateVector;
    private float rotateSpeed;
    private float zoomFactor;

    public CameraManager(OrthographicCamera camera) {
        this.camera = camera;
        cameraPosition = new Vector3();
        translateVector = new Vector2();
        rotateSpeed = 0;
        zoomFactor = 1.0f;
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
    public void connectWith(SystemPort systemPort) {
        systemPort.addDataInstance(new Rule<>(KeyBoardCondition.keyPressed("Up", "Down", "Left", "Right"), new Reaction<DriverContext<KeyBoard>>() {
            @Override
            public void accept(DriverContext<KeyBoard> keyBoardDriverContext) {
                KeyBoard keyBoard = keyBoardDriverContext.getEnvironmentData();
                System.out.println("keyBoard---CameraManager---" + keyBoard.character);
                switch (keyBoard.keyCode) {
                    case Input.Keys.UP:
                        translateVector.set(0, 10);
                        break;
                    case Input.Keys.DOWN:
                        translateVector.set(0, -10);
                        break;
                    case Input.Keys.LEFT:
                        translateVector.set(-10, 0);
                        break;
                    case Input.Keys.RIGHT:
                        translateVector.set(10, 0);
                        break;
                }
            }
        }));
        systemPort.addDataInstance(new Rule<>(KeyBoardCondition.keyReleased("Up", "Down", "Left", "Right"), new Reaction<DriverContext<KeyBoard>>() {
            @Override
            public void accept(DriverContext<KeyBoard> keyBoardDriverContext) {
                KeyBoard keyBoard = keyBoardDriverContext.getEnvironmentData();
                System.out.println("keyBoard---CameraManager---" + keyBoard.character);
                switch (keyBoard.keyCode) {
                    case Input.Keys.UP:
                        translateVector.set(0, 0);
                        break;
                    case Input.Keys.DOWN:
                        translateVector.set(0, 0);
                        break;
                    case Input.Keys.LEFT:
                        translateVector.set(0, 0);
                        break;
                    case Input.Keys.RIGHT:
                        translateVector.set(0, 0);
                        break;
                }
            }
        }));
        systemPort.addDataInstance(new Rule<>(KeyBoardCondition.keyTyped("<", ">", ",", "."), new Reaction<DriverContext<KeyBoard>>() {
            @Override
            public void accept(DriverContext<KeyBoard> keyBoardDriverContext) {
                KeyBoard keyBoard = keyBoardDriverContext.getEnvironmentData();
                System.out.println("keyBoard---CameraManager---" + keyBoard.character);
                if (keyBoard.character.equals(",")) {
                    rotateSpeed = 1f;
                } else if (keyBoard.character.equals(".")) {
                    rotateSpeed = -1f;
                } else if (keyBoard.character.equals("<")) {
                    zoomFactor = 0.95f;
                } else if (keyBoard.character.equals(">")) {
                    zoomFactor = 1.05f;
                }
                camera.zoom *= zoomFactor;
                camera.rotate(rotateSpeed);
                camera.update();
                zoomFactor = 1.0f;
                rotateSpeed = 0;
            }
        }));

        systemPort.addDataInstance(new Rule<>(GamePadCondition.pivotMoved(), new Reaction<DriverContext<GamePad>>() {

            @Override
            public void accept(DriverContext<GamePad> gamePadDriverContext) {
                GamePad gamePad = gamePadDriverContext.getEnvironmentData();
                switch (gamePad.povDirection) {
                    case north:
                        camera.translate(0, 100);
                        break;
                    case south:
                        camera.translate(0, -100);
                        break;
                    case west:
                        camera.translate(-100, 0);
                        break;
                    case east:
                        camera.translate(100, 0);
                        break;
                }
            }
        }));
    }

    @Override
    public void disconnectWith(SystemPort systemPort) {

    }

    public void updateCamera() {
        camera.translate(translateVector);
        camera.rotate(rotateSpeed);
        camera.update();
    }
}
