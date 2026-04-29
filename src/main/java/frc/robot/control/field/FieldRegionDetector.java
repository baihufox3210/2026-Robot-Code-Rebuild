package frc.robot.control.field;

import static edu.wpi.first.units.Units.Meters;

import java.util.function.Supplier;

import edu.wpi.first.math.geometry.Pose2d;
import frc.robot.RobotConstants;

public class FieldRegionDetector {
    private final Supplier<Pose2d> poseSupplier;

    public FieldRegionDetector(Supplier<Pose2d> poseSupplier) {
        this.poseSupplier = poseSupplier;
    }
    
    public FieldRegion getCurrentRegion() {
        Pose2d currentPose = poseSupplier.get();

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
