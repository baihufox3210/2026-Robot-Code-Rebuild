package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.path.PathPlannerPath;

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
import frc.robot.Commands.Intake.Pivot.TogglePivotPosition;
import frc.robot.Commands.Intake.Roller.Intaking;
import frc.robot.Commands.Shooter.Shooting;
import frc.robot.Commands.Shooter.SpinningIdle;
import frc.robot.subsystems.Drivetrain.Drivetrain;
import frc.robot.subsystems.Shooter.Flywheel.Flywheel;
import frc.robot.subsystems.Vision.Vision;

public class RobotContainer {
  	private final CommandXboxController controller = new CommandXboxController(0);

	private final Drivetrain drivetrain = Drivetrain.getInstance();
	private final Flywheel flywheel = Flywheel.getInstance();

	private SendableChooser<Command> autoChooser;

  	public RobotContainer() {
		new Vision(drivetrain::addVisionMeasurement);

		drivetrain.setDefaultCommand(new Drive(controller));
		flywheel.setDefaultCommand(new SpinningIdle());

		drivetrain.configurePathPlanner();

		configureAutonomous();
		configureLogging();
    	configureBindings();
  	}

  	private void configureBindings() {
		controller.leftBumper().onTrue(new TogglePivotPosition());
		
		controller.b().onTrue(new ToggleClimberPosition());
		controller.x().onTrue(new Climbing());

		controller.a().toggleOnTrue(new Intaking());

		controller.rightBumper().whileTrue(
			Commands.parallel(
				new Shooting()
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
    	try {
			PathPlannerPath path = PathPlannerPath.fromPathFile("Example Path");
			return Commands.parallel(
				AutoBuilder.followPath(path),
				new Shooting().withTimeout(5)
			);
		}
		catch (Exception e) {
			return Commands.print("No autonomous command configured");
		}
	}
}