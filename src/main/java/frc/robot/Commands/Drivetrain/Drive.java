package frc.robot.Commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Drivetrain.Drivetrain;
import frc.robot.utils.InputTransform;

public class Drive extends Command {
    private final Drivetrain drivetrain;
    private final CommandXboxController controller;

    public Drive(CommandXboxController controller) {
        this.controller = controller;
        this.drivetrain = Drivetrain.getInstance();

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        double xSpeed = InputTransform.applyDeadband(controller.getRightY());
        double ySpeed = InputTransform.applyDeadband(controller.getRightX());
        double rot = InputTransform.applyDeadband(controller.getLeftX());

        drivetrain.drive(xSpeed, ySpeed, rot);
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();
    }
}
