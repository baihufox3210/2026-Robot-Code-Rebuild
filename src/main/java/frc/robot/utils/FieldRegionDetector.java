package frc.robot.utils;

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

        if(x < blueStartLine) return FieldRegion.BLUE;
        if(x > redStartLine) return FieldRegion.RED;
        return FieldRegion.NEUTRAL;
    }

    public enum FieldRegion {
        RED, NEUTRAL, BLUE
    };
}
