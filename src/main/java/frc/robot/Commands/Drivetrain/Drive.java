package frc.robot.Commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Drivetrain.Drivetrain;
import frc.robot.utils.InputTransform;

public class Drive extends Command {
    private final Drivetrain drivetrain;
    private final CommandJoystick controller;

    public Drive(CommandJoystick controller) {
        this.controller = controller;
        this.drivetrain = Drivetrain.getInstance();

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        double xSpeed = -InputTransform.applyDeadband(controller.getY());
        double ySpeed = -InputTransform.applyDeadband(controller.getX());
        double rot = -InputTransform.applyDeadband(controller.getZ());

        drivetrain.drive(xSpeed, ySpeed, rot);
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();
    }
}
