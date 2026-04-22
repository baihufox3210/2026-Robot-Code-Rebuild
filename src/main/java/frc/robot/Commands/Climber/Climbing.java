package frc.robot.Commands.Climber;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climber.Climber;
import frc.robot.subsystems.Climber.Climber.ClimberMode;

public class Climbing extends Command {
    private final Climber climber;
    
    public Climbing() {
        climber = Climber.getInstance();
    }

    @Override
    public void initialize() {
        if(climber.getCurrentMode() == ClimberMode.RAISE) {
            ClimberMode targetMode = ClimberMode.CLIMB;

            new setClimberPosition(targetMode.getPosition());
            climber.setCurrentMode(targetMode);
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
