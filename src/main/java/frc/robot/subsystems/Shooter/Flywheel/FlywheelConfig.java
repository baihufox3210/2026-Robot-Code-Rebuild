package frc.robot.subsystems.Shooter.Flywheel;

import com.GFL.lib.hardware.config.MotorConfig;

public class FlywheelConfig {
    public static MotorConfig getFlywheelMotorConfig() {
        MotorConfig config = new MotorConfig();

        config.setInverted(FlywheelConstants.inverted);
        config.setNeutralMode(FlywheelConstants.flywheelMotorNeutralMode);

        config.withKP(FlywheelConstants.kP);
        config.withKV(FlywheelConstants.kV);

        return config;
    }
}
