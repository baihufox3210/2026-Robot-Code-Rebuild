package frc.robot.control.field;

import static edu.wpi.first.units.Units.Meters;

import edu.wpi.first.math.geometry.Pose2d;
import frc.robot.RobotConstants;

public class FieldRegionDetector {   
    public static FieldRegion getCurrentRegion(Pose2d currentPose) {
        double x = currentPose.getX();

        double redStartLine = RobotConstants.fieldSize.getX() - RobotConstants.allianceDepth.in(Meters);
        double blueStartLine = RobotConstants.allianceDepth.in(Meters);

        if(x < blueStartLine) return FieldRegion.BlueAlliance;
        if(x > redStartLine) return FieldRegion.RedAlliance;
        return FieldRegion.Neutral;
    }

    public enum FieldRegion {
        RedAlliance, Neutral, BlueAlliance;
    }
}
