package com.sunday.game.PhysicalEmulation;

import com.badlogic.gdx.math.Vector2;

public class PhysicalEmulationTest {
    public static void main(String[] arg) {
        Entity entity = new Entity();
        entity.setAcceleration(new Vector2(-.001f, -.001f));
        entity.setVelocity(new Vector2(0.1f, 0.2f));
        entity.setLocation(new Vector2(50.0f, 50.0f));
        entity.setMotionRestrctionControl(new MotionRestrctionControl() {
            @Override
            public boolean checkHasInterput(Entity entity) {
                System.out.println("checkHasInterput");
                entity.printBasicInfo();
               return entity.getVelocity().x< 0.01f;
            }
        });
        entity.executeMove();
    }
}
