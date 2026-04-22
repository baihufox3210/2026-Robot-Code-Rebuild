package frc.robot.subsystems.Drivetrain;

import static edu.wpi.first.units.Units.Inches;
import static edu.wpi.first.units.Units.Meters;

import com.GFL.lib.Factory.GyroFactory.GyroModel;
import com.GFL.lib.Factory.MotorFactory.MotorModel;
import com.GFL.lib.hardware.config.MotorConfig.SensorSource;
import com.pathplanner.lib.config.PIDConstants;
import com.pathplanner.lib.controllers.PPHolonomicDriveController;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;

public class DrivetrainConstants {
    public static final MotorModel driveMotorModel = MotorModel.Neo;
    public static final MotorModel steerMotorModel = MotorModel.Neo550;

    public static final GyroModel gyroModel = GyroModel.Pigeon2;

    public static final int gyroID = 1;
    
    public static final int[] driveMotorID = {11, 12, 13, 14};
    public static final int[] steerMotorID = {21, 22, 23, 24};

    public static final int[] canCoderID = {31, 32, 33, 34};

    public static final double[] zeroOffsets = {0.6521126, 0.5067503, 0.8277588, 0.6364698};

    public static final double trackWidth = 0.635;
    public static final double wheelBase = 0.635;

    public static final SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
        new Translation2d(wheelBase / 2, trackWidth / 2),
        new Translation2d(wheelBase / 2, -trackWidth / 2),
        new Translation2d(-wheelBase / 2, trackWidth / 2),
        new Translation2d(-wheelBase / 2, -trackWidth / 2)
    );

    public static final PPHolonomicDriveController holonomicDriveController = new PPHolonomicDriveController(
        new PIDConstants(2.0, 0.0, 0.0),
        new PIDConstants(2.0, 0.0, 0.0)
    );

    public static final Pose2d initialPose = new Pose2d(0, 0, Rotation2d.kZero);

    public static final class driveMotorConstants {
        public static final double gearRatio = 5.08;

        public static final double maxSpeedMetersPerSecond = 5.0;

        public static final double wheelCircumference = Inches.of(3).times(Math.PI).in(Meters);

        public static final double positionConversionFactory = 1 / gearRatio * wheelCircumference;
        public static final double velocityConversionFactory = positionConversionFactory / 60;

        public static final double kP = 0.15;
        public static final double kI = 0.0;
        public static final double kD = 0.1;
    }

    public static final class steerMotorConstants {
        public static final boolean encoderInverted = true;

        public static final double gearRatio = 25;

        public static final double maxSpeedMetersPerSecond = 20.0;

        public static final double positionConversionFactory = 2 * Math.PI;
        public static final double velocityConversionFactory = positionConversionFactory / 60;

        public static final double positionWarpMin = -Math.PI;
        public static final double positionWarpMax = Math.PI;

        public static final double kP = 1.0;
        public static final double kI = 0.0;
        public static final double kD = 0.1;

        public static final SensorSource sensorSource = SensorSource.ENCODER_PORT;
    }
}