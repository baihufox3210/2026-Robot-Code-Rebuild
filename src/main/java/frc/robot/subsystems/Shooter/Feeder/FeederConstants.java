package frc.robot.subsystems.Shooter.Feeder;

import com.GFL.lib.Factory.MotorFactory.MotorModel;
import com.GFL.lib.hardware.config.MotorConfig.NeutralMode;

public class FeederConstants {
    public static final MotorModel elevatorMotorModel = MotorModel.Neo;
    public static final MotorModel indexerMotorModel = MotorModel.Krakenx60;

    public static final NeutralMode elevatorMotorModNeutralMode = NeutralMode.COAST;
    public static final NeutralMode indexerMotorNeutralMode = NeutralMode.COAST;

    public static final int elevatorMotorID = 42;
    public static final int indexerMotorID = 43;

    public static final double elevatorMotorSpeed = 0.6;
    public static final double indexerMotorSpeed = 0.6;
}
