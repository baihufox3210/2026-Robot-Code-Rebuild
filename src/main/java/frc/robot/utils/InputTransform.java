package frc.robot.utils;

import edu.wpi.first.math.MathUtil;
import frc.robot.RobotConstants;

public class InputTransform {
    public static double applyDeadband(double input) {
        return MathUtil.applyDeadband(input, RobotConstants.deadband);
    }
}
