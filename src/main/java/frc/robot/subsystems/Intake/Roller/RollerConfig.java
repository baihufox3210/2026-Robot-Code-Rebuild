package frc.robot.subsystems.Intake.Roller;

import com.GFL.lib.hardware.config.MotorConfig;

public class RollerConfig {
    public static MotorConfig getRollerMotorConfig() {
        MotorConfig config = new MotorConfig();

        config.setNeutralMode(RollerConstants.rollerMotorNeutralMode);
        
        return config;
    }
}
