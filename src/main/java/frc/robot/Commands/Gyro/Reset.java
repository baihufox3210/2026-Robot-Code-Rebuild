package frc.robot.Commands.Gyro;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain.Drivetrain;

public class Reset extends Command {
    private final Drivetrain drivetrain;

    public Reset() {
        this.drivetrain = Drivetrain.getInstance();
    }

    @Override
    public void initialize() {
        drivetrain.resetHeading();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
