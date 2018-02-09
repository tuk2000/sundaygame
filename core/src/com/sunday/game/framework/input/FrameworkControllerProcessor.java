package com.sunday.game.framework.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

public class FrameworkControllerProcessor implements ControllerListener {
    @Override
    public void connected(Controller controller) {
        Gdx.app.log("Controller", "Controller " + controller.getName() + " connected");
    }

    @Override
    public void disconnected(Controller controller) {
        Gdx.app.log("Controller", " Controller " + controller.getName() + " disconnected");
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonCode) {
        Gdx.app.log("Controller", " Controller " + controller.getName() + " Button " + buttonCode + " buttonDown");
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonCode) {
        Gdx.app.log("Controller", " Controller " + controller.getName() + " Button " + buttonCode + " buttonUp");
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisCode, float value) {
        Gdx.app.log("Controller", " Controller " + controller.getName() + " Axis " + axisCode + " axisMoved " + value);
        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povCode, PovDirection value) {
        Gdx.app.log("Controller", " Controller " + controller.getName() + " Pov " + povCode + " povMoved " + value);
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
        Gdx.app.log("Controller", " Controller " + controller.getName() + " Slider " + sliderCode + " xSliderMoved " + value);
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {
        Gdx.app.log("Controller", " Controller " + controller.getName() + " Slider " + sliderCode + " ySliderMoved" + value);
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {
        Gdx.app.log("Controller", " Controller " + controller.getName() + " Accelerometer " + accelerometerCode + " accelerometerMoved " + value.toString());
        return false;
    }
}
