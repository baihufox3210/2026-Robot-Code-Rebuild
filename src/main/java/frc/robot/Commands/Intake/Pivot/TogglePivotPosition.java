package frc.robot.Commands.Intake.Pivot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake.Pivot.Pivot;
import frc.robot.subsystems.Intake.Pivot.Pivot.PivotMode;

public class TogglePivotPosition extends Command {
    private final Pivot pivot;
    private PivotMode targetMode;

    public TogglePivotPosition() {
        pivot = Pivot.getInstance();
    }

    @Override
    public void initialize() {
        if(pivot.getCurrentMode() == PivotMode.RETRACT) targetMode = PivotMode.DOWN;
        else targetMode = PivotMode.RETRACT;

        new setPivotPosition(targetMode.getPosition()).schedule();

        pivot.setCurrentMode(targetMode);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
