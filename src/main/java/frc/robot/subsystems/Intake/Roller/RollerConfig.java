package frc.robot.subsystems.Intake.Roller;

import com.GFL.lib.hardware.config.MotorConfig;
import com.GFL.lib.hardware.config.MotorConfig.NeutralMode;

public class RollerConfig {
    public static MotorConfig getRollerMotorConfig() {
        MotorConfig config = new MotorConfig();

        config.setNeutralMode(NeutralMode.COAST);
        
        return config;
    }
}
