package com.sunday.engine.model;

public class CoordinateSystem {
    public static int PPU = 16;
    public static int MPU = 1;

    public static float convertPixelToUnit(int pixel) {
        return ((1.0f) * pixel) / PPU;
    }

    public static float convertMeterToUnit(int meter) {
        return ((1.0f) * meter) / MPU;
    }

}
