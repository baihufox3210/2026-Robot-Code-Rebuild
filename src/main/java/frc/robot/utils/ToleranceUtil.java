package frc.robot.utils;

public class ToleranceUtil {
    public static boolean isNear(double current, double target, double tolerance) {
        return Math.abs(current - target) <= tolerance;
    }
}
