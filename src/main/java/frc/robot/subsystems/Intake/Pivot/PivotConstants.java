package frc.robot.subsystems.Intake.Pivot;

import static edu.wpi.first.units.Units.Degrees;

import com.GFL.lib.Factory.MotorFactory.MotorModel;

import edu.wpi.first.units.measure.Angle;

public class PivotConstants {
    public static final MotorModel pivotMotorModel = MotorModel.NeoVortex;

    public static final int pivotMotorID = 31;

    public static final double gearRatio = 25 * 34 / 20.0;

    public static final double positionConversationFactory = 2 * Math.PI / gearRatio;
    public static final double velocityConversationFactory = positionConversationFactory / 60;

    public static final double kP = 0.25;
    public static final double kV = 0.05;
    public static final double kS = 0.0;

    public static final Angle downAngle = Degrees.of(100);
    public static final Angle tolerance = Degrees.of(3);
}
