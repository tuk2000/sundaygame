package com.sunday.game.World.View.Views;

import com.sunday.game.World.View.View;
import com.sunday.game.World.View.ViewType;

public class RoleView implements View {
    private ViewType viewType;

    public RoleView(ViewType viewType) {
        this.viewType = viewType;
    }

    @Override
    public ViewType getViewType() {
        return viewType;
    }

    @Override
    public void setVisible(boolean visible) {

    }
}
