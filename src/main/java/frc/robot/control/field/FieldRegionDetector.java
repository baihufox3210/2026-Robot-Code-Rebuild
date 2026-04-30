package frc.robot.control.field;

import static edu.wpi.first.units.Units.Meters;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj.DriverStation;
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

        public DriverStation.Alliance getAlliance() {
            switch(this) {
                case RedAlliance: return DriverStation.Alliance.Red;
                case BlueAlliance: return DriverStation.Alliance.Blue;
                default: return null;
            }
        }

        public boolean matches(DriverStation.Alliance alliance) {
            return getAlliance() != null && getAlliance() == alliance;
        }
    }
}
