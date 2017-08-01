package com.sunday.game.PhysicalEmulation;

import com.badlogic.gdx.math.Vector2;

public class Entity implements EntityInterface {
    //only used in the class
    private Boolean Interrupted;
    private VirtualEntity shadowEntityFirst;
    private VirtualEntity shadowEntitySecond;
    private VirtualEntity currentEntity;
    private VirtualEntity nextEntity;

    public Entity() {
        Interrupted = true;
        shadowEntityFirst = new VirtualEntity();
        currentEntity = shadowEntityFirst;
        shadowEntitySecond = new VirtualEntity();
        nextEntity = shadowEntitySecond;
    }

    private void syncShadows() {
        VirtualEntity.copyEntityFormAToB(currentEntity, nextEntity);
    }

    @Override
    public Vector2 getAcceleration() {
        return currentEntity.getAcceleration();
    }

    public void setAcceleration(Vector2 acc) {
        Interrupted = true;
        currentEntity.setAcceleration(acc);
        syncShadows();
    }

    @Override
    public Vector2 getVelocity() {
        return currentEntity.getVelocity();
    }


    public void setVelocity(Vector2 velocity) {
        Interrupted = true;
        currentEntity.setVelocity(velocity);
        syncShadows();
    }

    @Override
    public Vector2 getLocation() {
        return currentEntity.getLocation();
    }

    public void setLocation(Vector2 location) {
        Interrupted = true;
        currentEntity.setLocation(location);
        syncShadows();
    }

    @Override
    public void setMotionRestrctionControl(MotionRestrctionControl motionRestrctionControl) {
        currentEntity.setMotionRestrctionControl(motionRestrctionControl);
        syncShadows();
    }

    @Override
    public void printBasicInfo() {
        currentEntity.printBasicInfo();
    }

    public void executeMove() {

        boolean RestrictionSuccessed = true;
        Interrupted = false;

        RestrictionSuccessed = currentEntity.checkRestriction();

        if (!RestrictionSuccessed) {
            return;
        }

        while (!Interrupted) {

            VirtualEntity.runSingleSimulationFromAtoB(currentEntity, nextEntity);
            RestrictionSuccessed = nextEntity.checkRestriction();

            if (RestrictionSuccessed) {
                nextStepConfig();
            } else {
                Interrupted = true;
            }
        }
    }

    private void nextStepConfig() {

        if (currentEntity.equals(shadowEntityFirst)) {
            currentEntity = shadowEntitySecond;
            nextEntity = shadowEntityFirst;
        } else {
            currentEntity = shadowEntityFirst;
            nextEntity = shadowEntitySecond;
        }
    }

}
