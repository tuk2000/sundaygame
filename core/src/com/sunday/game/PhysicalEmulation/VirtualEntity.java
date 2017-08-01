package com.sunday.game.PhysicalEmulation;

import com.badlogic.gdx.math.Vector2;

public  class VirtualEntity implements EntityInterface {
    static private TimeSlot timeSlot;
    protected MotionRestrctionControl motionRestrctionControl;
    protected Vector2 acc;
    protected Vector2 velocity;
    protected Vector2 location;
    protected Vector2 locCpy;
    protected long slot;

    VirtualEntity() {
        acc = new Vector2(.0f, .0f);
        velocity = new Vector2(.0f, .0f);
        location = new Vector2(.0f, .0f);
        locCpy = new Vector2(location);
        timeSlot = new TimeSlot(10);
        slot = 0;
    }

    protected static void copyEntityFormAToB(VirtualEntity virtualEntityA, VirtualEntity virtualEntityB) {
        virtualEntityB.acc.set(virtualEntityA.acc);
        virtualEntityB.velocity.set(virtualEntityA.velocity);
        virtualEntityB.location.set(virtualEntityA.location);
        virtualEntityB.locCpy.set(virtualEntityA.locCpy);
        virtualEntityB.slot = virtualEntityA.slot;
        virtualEntityB.motionRestrctionControl = virtualEntityA.motionRestrctionControl;
    }

    protected static void runSingleSimulationFromAtoB(VirtualEntity virtualEntityA, VirtualEntity virtualEntityB) {
        double slotssqrt;
        if (timeSlot.TickTock) {
            virtualEntityB.slot = virtualEntityA.slot + 1;
            slotssqrt = Math.pow(virtualEntityB.slot, 2);
            virtualEntityB.velocity.add(virtualEntityA.acc);
            virtualEntityB.location.set((float) (.5f * virtualEntityA.acc.x * slotssqrt + virtualEntityA.locCpy.x), (float) (.5f * virtualEntityA.acc.y * slotssqrt + virtualEntityA.locCpy.y));
        }
    }

    public Vector2 getAcceleration() {
        return acc;
    }

    public void setAcceleration(Vector2 acc) {
        slot = 0;
        this.acc.set(acc);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        slot = 0;
        this.velocity.set(velocity);
    }

    public Vector2 getLocation() {
        return location;
    }

    public void setLocation(Vector2 location) {
        slot = 0;
        this.location.set(location);
        locCpy.set(location);
    }

    protected boolean checkRestriction() {
        System.out.println(this.toString() + "  checkRestriction()");
        boolean RestrctionSatisfied = true;
        if (motionRestrctionControl != null) {
            RestrctionSatisfied = motionRestrctionControl.checkRestrictionRoutine(this);
        }
        return RestrctionSatisfied;
    }

    public void setMotionRestrctionControl(MotionRestrctionControl motionRestrctionControl) {
        this.motionRestrctionControl = motionRestrctionControl;
    }

    public void printBasicInfo() {
        System.out.println(slot);
        System.out.println(acc.toString());
        System.out.println(velocity.toString());
        System.out.println(location.toString());
        System.out.println();
    }

}
