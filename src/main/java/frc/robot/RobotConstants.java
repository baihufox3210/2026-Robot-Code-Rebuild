package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;

public class RobotConstants {
    public static final double deadband = 0.1;
    public static final double deltaSecond = 0.02;

    public static boolean isRedAlliance() {
        var alliance = DriverStation.getAlliance();

        if(alliance.isPresent()) return alliance.get() == DriverStation.Alliance.Red;
        return false;
    }
}
