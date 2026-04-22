package frc.robot.subsystems.Climber;

import com.GFL.lib.Factory.MotorFactory.MotorModel;

public class ClimberConstants {
    public static final MotorModel climberMotorModel = MotorModel.NeoVortex;

    public static final int climberMotorID = 51;

    public static final double gearRatio = 100;
    public static final double wheelRadius = 1.8;

    public static final boolean inverted = true;
    public static final boolean encoderInverted = true;

    public static final double positionConversationFactory = 2 * Math.PI * wheelRadius / gearRatio;
    public static final double velocityConversationFactory = positionConversationFactory / 60;

    public static final double tolerance = 0.5;

    public static final double kP = 0.5;
    public static final double kV = 0.1;
    
    public static final double climbLength = 8;
    public static final double raiseLength = 25;
}
