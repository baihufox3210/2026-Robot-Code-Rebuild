package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Commands.Drivetrain.Drive;
import frc.robot.subsystems.Drivetrain.Drivetrain;

public class RobotContainer {
  	private final CommandXboxController controller = new CommandXboxController(0);

	private final Drivetrain drivetrain = Drivetrain.getInstance();

  	public RobotContainer() {
		drivetrain.setDefaultCommand(new Drive(controller));
    	configureBindings();

		drivetrain.configurePathPlanner();
  	}

  	private void configureBindings() {}

  	public Command getAutonomousCommand() {
    	return Commands.print("No autonomous command configured");
	}
}