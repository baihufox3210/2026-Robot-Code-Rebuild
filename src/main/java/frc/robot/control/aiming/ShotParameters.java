package frc.robot.control.aiming;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation3d;

public record ShotParameters(
    Rotation2d targetHeading,
    double targetVelocity,
    Translation3d targetPose
) {}
