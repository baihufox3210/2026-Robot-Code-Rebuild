package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;

import dev.doglog.DogLog;
import dev.doglog.DogLogOptions;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.Commands.Climber.Climbing;
import frc.robot.Commands.Climber.ToggleClimberPosition;
import frc.robot.Commands.Drivetrain.Drive;
import frc.robot.Commands.Drivetrain.DriveToAim;
import frc.robot.Commands.Intake.Pivot.TogglePivotPosition;
import frc.robot.Commands.Intake.Roller.Intaking;
import frc.robot.Commands.Shooter.Shoot;
import frc.robot.control.aiming.AimSystem;
import frc.robot.subsystems.Drivetrain.Drivetrain;
import frc.robot.subsystems.Vision.Vision;

public class RobotContainer {
	private final CommandJoystick controller = new CommandJoystick(0);
	private final Drivetrain drivetrain = Drivetrain.getInstance();

	private final AimSystem aimSystem;

	private SendableChooser<Command> autoChooser;

  	public RobotContainer() {
		new Vision(drivetrain::addVisionMeasurement);

		aimSystem = new AimSystem(drivetrain::getPose);

		drivetrain.setDefaultCommand(new Drive(controller));
		drivetrain.configurePathPlanner();

		configureAutonomous();
		configureLogging();
    	configureBindings();
  	}

  	private void configureBindings() {
		controller.button(5).onTrue(new TogglePivotPosition());
		
		controller.button(2).onTrue(new ToggleClimberPosition());
		controller.button(4).onTrue(new Climbing());

		controller.button(3).toggleOnTrue(new Intaking());

		controller.button(6).whileTrue(
			Commands.parallel(
				new DriveToAim(controller, aimSystem::calculate),
				new Shoot(aimSystem::calculate).onlyIf(drivetrain::isAtHeading)
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