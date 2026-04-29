package frc.robot.control.targeting;

import edu.wpi.first.math.geometry.Rotation2d;

public record ShotParameters(
    Rotation2d targetHeading,
    double shooterSpeed
) {}
