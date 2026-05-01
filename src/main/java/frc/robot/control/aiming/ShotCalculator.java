package frc.robot.control.aiming;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation3d;
import frc.robot.control.aiming.model.ShotModel;
import frc.robot.control.geometry.FieldGeometry;

public class ShotCalculator {
    private static final ShotModel shotModel = new ShotModel();

    public static double getOptimalSetpoint(Pose2d robotPose, Translation3d targetPose) {
        Translation3d targetDisplacement = FieldGeometry.toTargetVector(robotPose, targetPose);

        double distanceDemand = shotModel.getByDistance(targetDisplacement.toTranslation2d().getNorm());
        double heightDemand = shotModel.getByHeight(targetDisplacement.getZ());

        return Math.max(distanceDemand, heightDemand);
    }
}