package com.sunday.game.PhysicalEmulation;

import com.badlogic.gdx.math.Vector2;

class VirtualEntity implements EntityInterface {
    static private TimeSlot timeSlot;
    protected MotionRestrctionControl motionRestrctionControl;
    protected SimulationResultReciver simulationResultReciver;
    protected Vector2 acc;
    protected Vector2 velocity;
    protected Vector2 location;
    protected Vector2 locCpy;
    protected long slot;
    private static long SlotPeriodePerSecond =5;
    private static float SingleSlotTime =(1.0f/25);
    //private static float SingleSlotTime =(1.0f/SlotPeriodePerSecond);

    protected VirtualEntity() {
        acc = new Vector2(.0f, .0f);
        velocity = new Vector2(.0f, .0f);
        location = new Vector2(.0f, .0f);
        locCpy = new Vector2(location);
        timeSlot = new TimeSlot((int)(1000/SlotPeriodePerSecond));
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
        long slottmp;
        double time;
        double timesqrt;
        Vector2 acctmp, vtmp, original;
        double x, y;
        if (timeSlot.TickTock) {
            vtmp = new Vector2(virtualEntityA.getVelocity());

            slottmp = virtualEntityA.slot + 1;
            time =slottmp* SingleSlotTime;
            timesqrt = Math.pow(time, 2);
            acctmp =new Vector2(virtualEntityA.acc) ;
            original = virtualEntityA.locCpy;
            vtmp.add(acctmp.x* SingleSlotTime,acctmp.y* SingleSlotTime);

            x = vtmp.x * time - .5f * acctmp.x * timesqrt + original.x;
            y = vtmp.y * time - .5f * acctmp.y * timesqrt + original.y;

            virtualEntityB.slot = slottmp;
            virtualEntityB.velocity.set(vtmp);
            virtualEntityB.location.set((float) x, (float) y);
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
        } else {
            System.out.println("motionRestrctionControl = null");
        }
        return RestrctionSatisfied;
    }

    @Override
    public void newTimeSlot(){

    }

    @Override
    public void setSimulationResultReciver(SimulationResultReciver simulationResultReciver){
        this.simulationResultReciver=simulationResultReciver;
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
