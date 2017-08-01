package com.sunday.game.Player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.sunday.game.PhysicalEmulation.Entity;
import com.sunday.game.PhysicalEmulation.EntityInterface;
import com.sunday.game.PhysicalEmulation.Gravity;
import com.sunday.game.PhysicalEmulation.MotionRestrctionControl;

public class SpriteMotion {
    private Sprite sprite;
    private static Rectangle restrictionRect= new Rectangle(-.01f, -.01f, 1040, 600);
    private Entity entity;
    public static MotionRestrctionControl jump = new MotionRestrctionControl() {
        @Override
        public boolean checkRestrictionRoutine(EntityInterface entity) {
            entity.printBasicInfo();
            return entity.getVelocity().len() > .0f & checkInRect(entity,restrictionRect);
        }
    };

    public SpriteMotion(Sprite sprite) {
        this.sprite = sprite;
        entity = new Entity();
    }

    private static  boolean checkInRect(EntityInterface entity , Rectangle rectangle) {
        boolean inRect = false;
        Vector2 loc = entity.getLocation();
        if (loc.x > rectangle.x & loc.x < rectangle.width & loc.y > rectangle.y & loc.y < rectangle.height)
            inRect = true;
        return inRect;
    }

    public Entity getEntity() {
        return entity;
    }

    public void execute(MotionRestrctionControl motionRestrctionControl) {
        System.out.println(sprite.getX() + " " + sprite.getY());
        entity.setLocation(new Vector2(100, 0));
        entity.setAcceleration(Gravity.DefaultGravity);
        entity.setMotionRestrctionControl(motionRestrctionControl);
        entity.executeMove();
    }

}
