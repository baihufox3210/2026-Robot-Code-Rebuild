package frc.robot.subsystems.Shooter.Flywheel;

import com.GFL.lib.Factory.MotorFactory.MotorModel;
import com.GFL.lib.hardware.config.MotorConfig.NeutralMode;

public class FlywheelConstants {
    public static final MotorModel flywheelMotorModel = MotorModel.Krakenx60;

    public static final NeutralMode flywheelMotorNeutralMode = NeutralMode.COAST;

    public static final int flywheelMotorID = 41;

    public static final boolean inverted = true;

    public static final double kP = 1.0;
    public static final double kV = 0.12;

    public static final double idleSpeed = 0.3;
    public static final double shootingSpeed = 0.8;
}
