package frc.robot.utils;

import static edu.wpi.first.units.Units.Meters;

import edu.wpi.first.math.geometry.Pose2d;
import frc.robot.RobotConstants;

public class FieldZone {
    public static Zone getCurrentZone(Pose2d currentPose) {
        double x = currentPose.getX();

        double redStartLine = RobotConstants.fieldSize.getX() - RobotConstants.allianceDepth.in(Meters);
        double blueStartLine = RobotConstants.allianceDepth.in(Meters);

        if(x < blueStartLine) return Zone.BLUE_ALLIANCE;
        else if(x > redStartLine) return Zone.RED_ALLIANCE;
        else return Zone.NEUTRAL;
    }

    public enum Zone {
        RED_ALLIANCE, NEUTRAL, BLUE_ALLIANCE
    };
}
