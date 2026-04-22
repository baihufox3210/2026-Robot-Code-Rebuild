package frc.robot.Commands.Climber;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber.Climber;
import frc.robot.subsystems.Climber.Climber.ClimberMode;

public class ToggleClimberPosition extends Command {
    private final Climber climber;
    private ClimberMode targetMode;

    public ToggleClimberPosition() {
        climber = Climber.getInstance();
    }

    @Override
    public void initialize() {
        if(climber.getCurrentMode() == ClimberMode.RETRACT) targetMode = ClimberMode.RAISE;
        else targetMode = ClimberMode.RETRACT;
        
        new setClimberPosition(targetMode.getPosition()).schedule();

        climber.setCurrentMode(targetMode);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
