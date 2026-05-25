package frc.robot.control.aiming;

import java.util.function.Supplier;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation3d;
import frc.robot.control.geometry.FieldGeometry;
import frc.robot.control.targeting.TargetSelector;

public class AimSystem {
    private Supplier<Pose2d> poseSupplier;

    public AimSystem(Supplier<Pose2d> poseSupplier) {
        this.poseSupplier = poseSupplier;
    }

    public ShotParameters calculate() {
        Pose2d currentPose = poseSupplier.get();
        Translation3d targetPose = TargetSelector.getTargetPose(currentPose);

        return new ShotParameters(
            FieldGeometry.toTargetHeading(currentPose, targetPose),
            ShotCalculator.getOptimalSetpoint(currentPose, targetPose),
            targetPose
        );
    }
}
