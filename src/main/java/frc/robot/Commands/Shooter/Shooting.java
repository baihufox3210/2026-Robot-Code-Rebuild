package frc.robot.Commands.Shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter.Feeder.Feeder;
import frc.robot.subsystems.Shooter.Flywheel.Flywheel;

public class Shooting extends Command {
    private final Feeder feeder;
    private final Flywheel flywheel;

    public Shooting() {
        this.feeder = Feeder.getInstance();
        this.flywheel = Flywheel.getInstance();

        addRequirements(feeder, flywheel);
    }

    @Override
    public void execute() {
        feeder.shooting();
        flywheel.shooting();
    }

    @Override
    public void end(boolean interrupted) {
        feeder.stop();
    }
}
