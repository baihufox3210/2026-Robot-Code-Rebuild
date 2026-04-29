package frc.robot.control.field;

import edu.wpi.first.math.geometry.Translation3d;
import frc.robot.RobotConstants;

public class FieldTransformer {
    public static Translation3d mirror(Translation3d pose) {
        return new Translation3d(RobotConstants.fieldSize.getX() - pose.getX(), pose.getY(), pose.getZ());
    }
}