package frc.robot.subsystems.Intake.Roller;

import com.GFL.lib.Factory.MotorFactory.MotorModel;
import com.GFL.lib.hardware.config.MotorConfig.NeutralMode;

public class RollerConstants {
    public static final MotorModel rollerMotorModel = MotorModel.Krakenx60;

    public static final NeutralMode rollerMotorNeutralMode = NeutralMode.COAST;
    public static final int rollerMotorID = 32;

    public static final double intakeSpeed = 0.5;
}
