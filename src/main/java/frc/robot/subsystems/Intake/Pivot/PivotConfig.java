package frc.robot.subsystems.Intake.Pivot;

import com.GFL.lib.hardware.config.MotorConfig;

public class PivotConfig {
    public static MotorConfig getPivotMotorConfig() {
        MotorConfig config = new MotorConfig();

        config.setConversion(
            PivotConstants.positionConversationFactory,
            PivotConstants.velocityConversationFactory
        );

        config.withKP(PivotConstants.kP);
        config.withKV(PivotConstants.kV);
        config.withKS(PivotConstants.kS);

        return config;
    }   
}
