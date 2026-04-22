package frc.robot.Commands.Intake.Pivot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake.Pivot.Pivot;

public class setPivotPosition extends Command {
    private double position;

    private final Pivot pivot;

    public setPivotPosition(double position) {
        this.position = position;
        this.pivot = Pivot.getInstance();

        addRequirements(pivot);
    }

    @Override
    public void initialize() {
        pivot.setPosition(position);
    }

    @Override
    public boolean isFinished() {
        return pivot.isPivotAtPosition(position);
    }

    @Override
    public void end(boolean interrupted) {
        if(!interrupted) pivot.stop();
    }
}
