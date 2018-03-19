package com.sunday.engine.databank;

public interface SystemPortSharing {
    void connectWith(SystemPort systemPort);

    void disconnectWith(SystemPort systemPort);
}
