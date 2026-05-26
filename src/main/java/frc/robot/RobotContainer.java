package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;

import dev.doglog.DogLog;
import dev.doglog.DogLogOptions;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Commands.Climber.Climbing;
import frc.robot.Commands.Climber.ToggleClimberPosition;
import frc.robot.Commands.Drivetrain.Drive;
import frc.robot.Commands.Drivetrain.DriveToAim;
import frc.robot.Commands.Gyro.Reset;
import frc.robot.Commands.Intake.Pivot.TogglePivotPosition;
import frc.robot.Commands.Intake.Roller.Intaking;
import frc.robot.Commands.Shooter.Shoot;
import frc.robot.control.aiming.AimSystem;
import frc.robot.subsystems.Drivetrain.Drivetrain;
import frc.robot.subsystems.Vision.Vision;

public class RobotContainer {
	private final CommandXboxController controller = new CommandXboxController(0);
	private final Drivetrain drivetrain = Drivetrain.getInstance();

	private final AimSystem aimSystem;

	private SendableChooser<Command> autoChooser;

  	public RobotContainer() {
		new Vision(drivetrain::addVisionMeasurement);

		aimSystem = new AimSystem(drivetrain::getPose);

		SmartDashboard.putNumber("targetPose x", aimSystem.calculate().targetPose().getX());
		SmartDashboard.putNumber("targetPose y", aimSystem.calculate().targetPose().getY());

		drivetrain.setDefaultCommand(new Drive(controller));
		drivetrain.configurePathPlanner();

		configureAutonomous();
		configureLogging();
    	configureBindings();
  	}

  	private void configureBindings() {
		controller.leftBumper().onTrue(new TogglePivotPosition());
		
		controller.x().onTrue(new ToggleClimberPosition());
		controller.b().onTrue(new Climbing());

		controller.a().toggleOnTrue(new Intaking());

		controller.y().onTrue(new Reset());

		controller.rightBumper().whileTrue(
			Commands.parallel(
				new DriveToAim(controller, aimSystem::calculate),
				new Shoot(aimSystem::calculate)
			)
		);
	}

	private void configureAutonomous() {
		autoChooser = AutoBuilder.buildAutoChooser();
		SmartDashboard.putData("Auto Chooser", autoChooser);
	}

	private void configureLogging() {
    DogLog.setOptions(
        new DogLogOptions()
            .withCaptureConsole(true)
            .withCaptureDs(true)
            .withNtPublish(true)
            .withCaptureNt(true)
            .withLogExtras(true)
    );

    DogLog.setPdh(RobotConstants.PDH);
}

  	public Command getAutonomousCommand() {
    	return autoChooser.getSelected();
	}
}