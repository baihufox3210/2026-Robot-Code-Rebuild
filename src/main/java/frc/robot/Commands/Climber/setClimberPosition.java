package frc.robot.Commands.Climber;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber.Climber;

public class setClimberPosition extends Command {
    private double position;

    private final Climber climber;

    public setClimberPosition(double position) {
        this.position = position;
        this.climber = Climber.getInstance();

        addRequirements(climber);
    }

    @Override
    public void initialize() {
        climber.setPosition(position);
    }

    @Override
    public boolean isFinished() {
        return climber.isClimberAtPosition(position);
    }

    @Override
    public void end(boolean interrupted) {
        if(!interrupted) climber.stop();
    }
    
}
