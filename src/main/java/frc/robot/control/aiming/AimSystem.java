package frc.robot.control.aiming;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation3d;
import frc.robot.control.geometry.FieldGeometry;
import frc.robot.control.targeting.TargetSelector;

public class AimSystem {
    public static ShotParameters calculate(Pose2d currentPose) {
        Translation3d targetPose = TargetSelector.getTargetPose(currentPose);

        return new ShotParameters(
            FieldGeometry.toTargetHeading(currentPose, targetPose),
            ShotCalculator.getOptimalSetpoint(currentPose, targetPose)
        );
    }
}
