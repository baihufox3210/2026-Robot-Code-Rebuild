package frc.robot.subsystems.Drivetrain.module;

import com.GFL.lib.hardware.config.MotorConfig;

import frc.robot.subsystems.Drivetrain.DrivetrainConstants.driveMotorConstants;
import frc.robot.subsystems.Drivetrain.DrivetrainConstants.steerMotorConstants;

public class DrivetrainConfig {
    public static MotorConfig getDriveMotorConfig() {
        MotorConfig config = new MotorConfig();

        config.setGearRatio(driveMotorConstants.gearRatio);

        config.setConversion(
            driveMotorConstants.positionConversionFactory,
            driveMotorConstants.velocityConversionFactory
        );

        config.withKP(driveMotorConstants.kP);
        config.withKI(driveMotorConstants.kI);
        config.withKD(driveMotorConstants.kD);

        return config;
    }

    public static MotorConfig getSteerMotorConfig(double zeroOffset) {
        MotorConfig config = new MotorConfig();

        config.setGearRatio(steerMotorConstants.gearRatio);

        config.setConversion(
            steerMotorConstants.positionConversionFactory,
            steerMotorConstants.velocityConversionFactory
        );

        config.setEncoderInverted(steerMotorConstants.encoderInverted);

        config.setContinuousInput(
            steerMotorConstants.positionWarpMin,
            steerMotorConstants.positionWarpMax
        );

        config.withKP(steerMotorConstants.kP);
        config.withKI(steerMotorConstants.kI);
        config.withKD(steerMotorConstants.kD);

        config.setZeroOffset(zeroOffset);

        config.setSensorSource(steerMotorConstants.sensorSource);
        
        return config;
    }
}
