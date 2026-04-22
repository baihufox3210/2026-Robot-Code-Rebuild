package frc.robot.subsystems.Drivetrain.module;

import com.GFL.lib.Factory.MotorFactory;
import com.GFL.lib.hardware.interfaces.GenericEncoder;
import com.GFL.lib.hardware.interfaces.GenericMotor;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.subsystems.Drivetrain.DrivetrainConstants;

public class SwerveModule {
    private final GenericMotor driveMotor;
    private final GenericMotor steerMotor;

    private final GenericEncoder driveEncoder;
    private final GenericEncoder steerEncoder;

    private SwerveModuleState desiredState;

    public SwerveModule(int driveMotorID, int steerMotorID, double zeroOffset) {
        driveMotor = MotorFactory.createMotor(driveMotorID, DrivetrainConstants.driveMotorModel, DrivetrainConfig.getDriveMotorConfig());
        steerMotor = MotorFactory.createMotor(steerMotorID, DrivetrainConstants.steerMotorModel, DrivetrainConfig.getSteerMotorConfig(zeroOffset));

        driveEncoder = driveMotor.getEncoder();
        steerEncoder = steerMotor.getEncoder();

        driveMotor.configure();
        steerMotor.configure();

        resetEncoder();

        desiredState = new SwerveModuleState();
        desiredState.angle = new Rotation2d(steerEncoder.getPosition());
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(
            driveEncoder.getVelocity(),
            new Rotation2d(steerEncoder.getPosition())
        );
    }

    public SwerveModulePosition getPosition() {
        return new SwerveModulePosition(
            driveEncoder.getPosition(),
            new Rotation2d(steerEncoder.getPosition())
        );
    }

    public void resetEncoder() {
        driveEncoder.setPosition(0);
    }

    public void setDesiredState(SwerveModuleState desiredState) {
        desiredState.optimize(new Rotation2d(steerEncoder.getPosition()));

        driveMotor.setVelocity(desiredState.speedMetersPerSecond);
        steerMotor.setPosition(desiredState.angle.getRadians());

        this.desiredState = desiredState;
    }

    public void stop() {
        driveMotor.stop();
        steerMotor.stop();
    }
}
