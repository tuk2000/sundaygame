package com.sunday.game.PhysicalEmulation;

import com.badlogic.gdx.math.Vector2;

public class Entity {
    private Vector2 acc;
    private Vector2 velocity;
    private Vector2 location;
    private MotionRestrctionControl motionRestrctionControl;
    //only used in the class
    private Boolean Interrupted;
    private Vector2 locCpy;
    private TimeSlot timeSlot;
    private long slot;


    public Entity() {
        Interrupted = true;
        acc = new Vector2(.0f, .0f);
        velocity = new Vector2(.0f, .0f);
        location=new Vector2(.0f, .0f);
        locCpy=new Vector2(location);
        timeSlot = new TimeSlot(10);
        slot=0;
    }

    public Vector2 getAcceleration() {
        return acc;
    }

    public void setAcceleration(Vector2 acc) {
        Interrupted=true;
        slot=0;
        this.acc = acc;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        Interrupted=true;
        slot=0;
        this.velocity = velocity;
    }

    public void executeMove() {
        Interrupted=false;
        while (!Interrupted ) {
            if(motionRestrctionControl!=null){
                Interrupted=motionRestrctionControl.checkHasInterput(this);
                if(Interrupted) return;
            }
            runSimulation();
        }
    }

    private void runSimulation() {
        double slotssqrt;
        if (timeSlot.TickTock) {
            slot++;
            slotssqrt = Math.pow(slot,2);
            velocity.add(acc);
            location.set((float) (.5f * acc.x * slotssqrt + locCpy.x), (float) (.5f * acc.y * slotssqrt + locCpy.y));
        }
    }

    public Vector2 getLocation() {
        return location;
    }

    public void setLocation(Vector2 location) {
        Interrupted=true;
        slot=0;
        this.location = location;
        locCpy = new Vector2(location);
    }

    public void setMotionRestrctionControl(MotionRestrctionControl motionRestrctionControl) {
        this.motionRestrctionControl = motionRestrctionControl;
    }
    public void printBasicInfo(){
        System.out.println(slot);
        System.out.println(acc.toString());
        System.out.println(velocity.toString());
        System.out.println(location.toString());
        System.out.println();
    }
}
