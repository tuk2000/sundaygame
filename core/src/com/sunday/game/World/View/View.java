package com.sunday.game.World.View;

import java.util.ArrayList;

public interface View {
    //ArrayList<ViewLayer> getViewLayers();
    ViewType getViewType();

    void setVisible(boolean visible);
}
