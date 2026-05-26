package frc.robot.subsystems.Drivetrain;

import com.GFL.lib.Factory.GyroFactory;
import com.GFL.lib.hardware.config.GyroConfig;
import com.GFL.lib.hardware.interfaces.GenericGyro;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.config.RobotConfig;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotConstants;
import frc.robot.subsystems.Drivetrain.DrivetrainConstants.driveMotorConstants;
import frc.robot.subsystems.Drivetrain.DrivetrainConstants.steerMotorConstants;
import frc.robot.subsystems.Drivetrain.module.SwerveModule;

public class Drivetrain extends SubsystemBase {
    private static Drivetrain instance;

    private final GenericGyro gyro;
    private final SwerveModule[] swerveModules;

    private final SwerveDrivePoseEstimator poseEstimator;

    private final Field2d field;

    private Drivetrain() {
        gyro = GyroFactory.createGyro(DrivetrainConstants.gyroID, DrivetrainConstants.gyroModel, new GyroConfig());

        swerveModules = new SwerveModule[4];
        for(int i = 0; i < 4; i++) {
            swerveModules[i] = new SwerveModule(
                DrivetrainConstants.driveMotorID[i],
                DrivetrainConstants.steerMotorID[i],
                DrivetrainConstants.zeroOffsets[i]
            );
        }

        poseEstimator = new SwerveDrivePoseEstimator(
            DrivetrainConstants.kinematics,
            getHeading(),
            getModulePositions(),
            RobotConstants.initialPose
        );

        field = new Field2d();
    }

    @Override
    public void periodic() {
        poseEstimator.update(getHeading(), getModulePositions());
        field.setRobotPose(getPose());
        log();
    }
    
    private void log() {
        SmartDashboard.putData("Drivetrain/Field", field);
        SmartDashboard.putNumber("Drivetrain/Heading", getHeading().getDegrees());
    }

    public void configurePathPlanner() {
        try {
            AutoBuilder.configure(
                this::getPose,
                this::resetPose,
                this::getRobotSpeeds,
                (speeds, feedforwards) -> drive(speeds),
                DrivetrainConstants.holonomicDriveController,
                RobotConfig.fromGUISettings(),
                () -> {
                    DriverStation.Alliance alliance = DriverStation.getAlliance().orElse(DriverStation.Alliance.Blue);
                    return alliance == DriverStation.Alliance.Red;
                },
                this
            );
        }
        catch(Exception e) {
            DriverStation.reportError("Failed to load PathPlanner: " + e.getMessage(), e.getStackTrace());
        }
    }

    private ChassisSpeeds getRobotSpeeds() {
        return DrivetrainConstants.kinematics.toChassisSpeeds(getModuleStates());
    }

    private SwerveModulePosition[] getModulePositions() {
        SwerveModulePosition[] positions = new SwerveModulePosition[4];
        for(int i = 0; i < 4; i++) positions[i] = swerveModules[i].getPosition();
        return positions;
    }

    private SwerveModuleState[] getModuleStates() {
        SwerveModuleState[] states = new SwerveModuleState[4];
        for(int i = 0; i < 4; i++) states[i] = swerveModules[i].getState();
        return states;
    }

    public Rotation2d getHeading() {
        return gyro.getRotation2d();
    }

    public void resetHeading() {
        gyro.reset();
    }

    public Pose2d getPose() {
        return poseEstimator.getEstimatedPosition();
    }

    public void resetPose(Pose2d pose) {
        poseEstimator.resetPosition(
            getHeading(),
            getModulePositions(),
            pose
        );
    }

    public void drive(double xSpeed, double ySpeed, double rot) {
        ChassisSpeeds chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
            xSpeed * driveMotorConstants.maxSpeedMetersPerSecond,
            ySpeed * driveMotorConstants.maxSpeedMetersPerSecond,
            rot * steerMotorConstants.maxAngularSpeedRadPerSec,
            getHeading()
        );

        drive(chassisSpeeds);
    }

    public void drive(double xSpeed, double ySpeed, Rotation2d targetHeading) {
        double rot = DrivetrainConstants.rotationPID.calculate(getHeading().getRadians(), targetHeading.getRadians());

        ChassisSpeeds chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
            xSpeed * driveMotorConstants.maxSpeedMetersPerSecond,
            ySpeed * driveMotorConstants.maxSpeedMetersPerSecond,
            rot * steerMotorConstants.maxAngularSpeedRadPerSec,
            getHeading()
        );

        drive(chassisSpeeds);
    }

    private void drive(ChassisSpeeds chassisSpeeds) {
        ChassisSpeeds discretizedSpeeds = ChassisSpeeds.discretize(chassisSpeeds, RobotConstants.deltaSecond);

        var swerveModuleStates = DrivetrainConstants.kinematics.toSwerveModuleStates(discretizedSpeeds);
        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, driveMotorConstants.maxSpeedMetersPerSecond);

        setSwerveModuleStates(swerveModuleStates);
    }

    public boolean isAtHeading() {
        return DrivetrainConstants.rotationPID.atSetpoint();
    }

    private void setSwerveModuleStates(SwerveModuleState[] desiredStates) {
        for(int i = 0; i < 4; i++) {
            swerveModules[i].setDesiredState(desiredStates[i]);
        }
    }

    public void stop() {
        for(SwerveModule module : swerveModules) {
            module.stop();
        }
    }

    public void addVisionMeasurement(Pose2d visionMeasurement, double timestampSeconds, Matrix<N3, N1> stdDevs) {
        poseEstimator.addVisionMeasurement(visionMeasurement, timestampSeconds, stdDevs);
    }

    public static Drivetrain getInstance() {
        if(instance == null) instance = new Drivetrain();
        return instance;
    }
}
