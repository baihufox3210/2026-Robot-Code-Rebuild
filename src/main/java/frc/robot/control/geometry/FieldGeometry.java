package frc.robot.control.geometry;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;

public class FieldGeometry {
    public static Translation3d toTargetVector(Pose2d robotPose, Translation3d targetPose) {
        Translation2d delta = targetPose.toTranslation2d().minus(robotPose.getTranslation());
        return new Translation3d(delta.getX(), delta.getY(), targetPose.getZ());
    }

    public static Rotation2d toTargetHeading(Pose2d robotPose, Translation3d targetPose) {
        Translation2d delta = targetPose.toTranslation2d().minus(robotPose.getTranslation());
        return delta.getAngle();
    }
}