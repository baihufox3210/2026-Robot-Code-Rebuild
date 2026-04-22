package frc.robot.Commands.Shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter.Flywheel.Flywheel;

public class SpinningIdle extends Command {
    private final Flywheel flywheel;

    public SpinningIdle() {
        flywheel = Flywheel.getInstance();
        addRequirements(flywheel);
    }

    @Override
    public void execute() {
        flywheel.spinningIdle();
    }

    @Override
    public void end(boolean interrupted) {
        if(!interrupted) flywheel.stop();
    }
}
