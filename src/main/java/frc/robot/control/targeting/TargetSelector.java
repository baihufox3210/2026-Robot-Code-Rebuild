package frc.robot.control.targeting;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.RobotConstants;
import frc.robot.control.field.FieldRegionDetector;
import frc.robot.control.field.FieldTransformer;

public class TargetSelector {
    public static Translation3d getTargetPose(Pose2d currentPose) {       
        var alliance = DriverStation.getAlliance().orElse(DriverStation.Alliance.Blue);

        Translation3d baseTarget = selectBaseTarget(currentPose, alliance);
        return alliance == DriverStation.Alliance.Blue ? baseTarget : FieldTransformer.mirror(baseTarget);
    }

    private static Translation3d selectBaseTarget(Pose2d currentPose, DriverStation.Alliance alliance) {
        var region = FieldRegionDetector.getCurrentRegion(currentPose);

        if(region.matches(alliance)) return RobotConstants.allianceHub;
        return isRightSide(currentPose) ? RobotConstants.rightShotPoint : RobotConstants.leftShotPoint;
    }

    private static boolean isRightSide(Pose2d pose) {
        return pose.getY() < RobotConstants.fieldSize.getY() / 2;
    }
}