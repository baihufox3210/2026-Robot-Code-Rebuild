package frc.robot.subsystems.Shooter.Feeder;

import com.GFL.lib.hardware.config.MotorConfig;

public class FeederConfig {
    public static MotorConfig getElevatorMotorConfig() {
        MotorConfig config = new MotorConfig();

        config.setNeutralMode(FeederConstants.elevatorMotorModNeutralMode);

        return config;
    }

    public static MotorConfig getIndexerMotorConfig() {
        MotorConfig config = new MotorConfig();

        config.setNeutralMode(FeederConstants.indexerMotorNeutralMode);

        return config;
    }
}
