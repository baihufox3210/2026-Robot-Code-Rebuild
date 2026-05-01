package frc.robot.control.aiming;

import edu.wpi.first.math.geometry.Rotation2d;

public record ShotParameters(
    Rotation2d targetHeading,
    double targetVelocity
) {}
