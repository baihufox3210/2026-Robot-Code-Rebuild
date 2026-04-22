package frc.robot.Commands.Intake.Roller;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake.Roller.Roller;

public class Intaking extends Command {
    private final Roller roller;

    public Intaking() {
        this.roller = Roller.getInstance();
        addRequirements(roller);
    }

    @Override
    public void execute() {
        roller.intaking();
    }

    @Override
    public void end(boolean interrupted) {
        roller.stop();
    }
}
