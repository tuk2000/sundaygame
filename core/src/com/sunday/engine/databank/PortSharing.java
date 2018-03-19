package com.sunday.engine.databank;

public interface PortSharing {
    void connectWith(Port port);

    void disconnectWith(Port port);
}
