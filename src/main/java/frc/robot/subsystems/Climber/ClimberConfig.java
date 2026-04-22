package frc.robot.subsystems.Climber;

import com.GFL.lib.hardware.config.MotorConfig;

public class ClimberConfig {
    public static MotorConfig getClimberMotorConfig() {
        MotorConfig config = new MotorConfig();

        config.setInverted(true);

        config.setConversion(
            ClimberConstants.positionConversationFactory,
            ClimberConstants.velocityConversationFactory
        );

        config.setEncoderInverted(true);

        config.withKP(ClimberConstants.kP);
        config.withKV(ClimberConstants.kV);
        
        return config;
    }
}
