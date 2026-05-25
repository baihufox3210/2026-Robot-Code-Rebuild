package frc.robot.Commands.Drivetrain;

import java.util.function.Supplier;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.control.aiming.ShotParameters;
import frc.robot.subsystems.Drivetrain.Drivetrain;
import frc.robot.utils.InputTransform;

public class DriveToAim extends Command {
    private final Drivetrain drivetrain;

    private final Supplier<ShotParameters> shotParamsSupplier;
    private final CommandXboxController controller;

    public DriveToAim(CommandXboxController controller, Supplier<ShotParameters> shotParamsSupplier) {
        this.drivetrain = Drivetrain.getInstance();

        this.controller = controller;
        this.shotParamsSupplier = shotParamsSupplier;
        
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        var shotParameter = shotParamsSupplier.get();
        Rotation2d targetHeading = shotParameter.targetHeading();

        double xSpeed = InputTransform.applyDeadband(controller.getRightY());
        double ySpeed = InputTransform.applyDeadband(controller.getRightX());

        drivetrain.drive(xSpeed, ySpeed, targetHeading);
    }
}
