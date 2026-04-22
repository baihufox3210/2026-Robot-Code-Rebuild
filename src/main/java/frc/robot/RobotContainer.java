package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Commands.Climber.Climbing;
import frc.robot.Commands.Climber.ToggleClimberPosition;
import frc.robot.Commands.Drivetrain.Drive;
import frc.robot.Commands.Intake.Roller.Intaking;
import frc.robot.Commands.Shooter.Shooting;
import frc.robot.Commands.Shooter.SpinningIdle;
import frc.robot.subsystems.Drivetrain.Drivetrain;
import frc.robot.subsystems.Shooter.Flywheel.Flywheel;

public class RobotContainer {
  	private final CommandXboxController controller = new CommandXboxController(0);

	private final Drivetrain drivetrain = Drivetrain.getInstance();
	private final Flywheel flywheel = Flywheel.getInstance();

  	public RobotContainer() {
		drivetrain.setDefaultCommand(new Drive(controller));
		flywheel.setDefaultCommand(new SpinningIdle());

    	configureBindings();

		drivetrain.configurePathPlanner();
  	}

  	private void configureBindings() {
		controller.leftBumper().onTrue(new ToggleClimberPosition());
		
		controller.b().onTrue(new ToggleClimberPosition());
		controller.x().onTrue(new Climbing());

		controller.a().toggleOnTrue(new Intaking());

		controller.rightBumper().whileTrue(
			Commands.parallel(
				new Shooting()
			)
		);
	}

  	public Command getAutonomousCommand() {
    	return Commands.print("No autonomous command configured");
	}
}