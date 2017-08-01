package com.sunday.game.PhysicalEmulation;

public interface MotionRestrctionControl {
    //return true when the entity satisfiy the restrictions
    boolean checkRestrictionRoutine(EntityInterface entity);
}
