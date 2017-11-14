package com.sunday.game.World.Senario;

public class CoordinateSystem {
    public static int PPU = 16;
    public static int MPU = 1;

    public enum Unit {
        Pixel, UnitOne, Meter
    }

    public static float convertPixelToUnit(int pixel) {
        return ((1.0f) * pixel) / PPU;
    }

    public static float convertMeterToUnit(int meter) {
        return ((1.0f) * meter) / MPU;
    }

}
