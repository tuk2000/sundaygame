package com.sunday.game.PhysicalEmulation;

import com.badlogic.gdx.math.Vector2;

public interface EntityInterface extends TimeSlotReciver{

    Vector2 getAcceleration();

    void setAcceleration(Vector2 acc);

    Vector2 getVelocity();


    void setVelocity(Vector2 velocity);

    Vector2 getLocation();

    void setLocation(Vector2 location);

    void setSimulationResultReciver(SimulationResultReciver simulationResultReciver);

    void setMotionRestrctionControl(MotionRestrctionControl motionRestrctionControl);

    void printBasicInfo();
}
