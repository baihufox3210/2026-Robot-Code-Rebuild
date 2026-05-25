package frc.robot.Commands.Shooter;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.control.aiming.ShotParameters;
import frc.robot.subsystems.Shooter.Feeder.Feeder;
import frc.robot.subsystems.Shooter.Flywheel.Flywheel;

public class Shoot extends Command {
    private final Feeder feeder;
    private final Flywheel flywheel;

    private final Supplier<ShotParameters> shotParamsSupplier;

    public Shoot(Supplier<ShotParameters> shotParamsSupplier) {
        this.feeder = Feeder.getInstance();
        this.flywheel = Flywheel.getInstance();

        this.shotParamsSupplier = shotParamsSupplier;

        addRequirements(feeder, flywheel);
    }

    @Override
    public void execute() {
        var shotParameter = shotParamsSupplier.get();
        double targetVelocity = shotParameter.targetVelocity();
        
        flywheel.setVelocity(targetVelocity);

        if(flywheel.atTargetVelocity(targetVelocity)) feeder.shooting();
        else feeder.stop();
    }

    @Override
    public void end(boolean interrupted) {
        feeder.stop();
        flywheel.stop();
    }
}
