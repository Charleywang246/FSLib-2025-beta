package frc.FSLib2025beta.math;

public class FSMath {
    
    public boolean isWithin (double value, double min, double max) {
        return Math.max(min, value) == Math.min(value, max);
    }
    
    public double clamp (double value, double min, double max) {
        return Math.min(Math.max(value, min), max);
    }

    public double clamp (int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

    public static double constrainAngleDegrees(double angle) {
        return Math.atan2(Math.sin(angle / 180.0 * Math.PI), Math.cos(angle / 180.0 * Math.PI)) * 180 / Math.PI;
    }


}