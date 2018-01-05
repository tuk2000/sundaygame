package com.sunday.game.engine.examples.enemy;

import com.sunday.game.engine.view.RoleAbstractView;

public class EnemyView extends RoleAbstractView {
    private SawAnimation sawAnimation;

    public EnemyView() {
        sawAnimation = new SawAnimation();
    }
}
