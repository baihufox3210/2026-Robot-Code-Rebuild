package frc.robot.subsystems.Shooter.Feeder;

import com.GFL.lib.Factory.MotorFactory;
import com.GFL.lib.hardware.interfaces.GenericMotor;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Feeder extends SubsystemBase {
    private static Feeder instance;

    private final GenericMotor elevatorMotor;
    private final GenericMotor indexerMotor;

    private Feeder() {
        elevatorMotor = MotorFactory.createMotor(
            FeederConstants.elevatorMotorID,
            FeederConstants.elevatorMotorModel,
            FeederConfig.getElevatorMotorConfig()
        );

        indexerMotor = MotorFactory.createMotor(
            FeederConstants.indexerMotorID,
            FeederConstants.indexerMotorModel,
            FeederConfig.getIndexerMotorConfig()
        );

        elevatorMotor.configure();
        indexerMotor.configure();
    }

    public void shooting() {
        set(FeederConstants.elevatorMotorSpeed, FeederConstants.indexerMotorSpeed);
    }

    private void set(double elevatorMotorSpeed, double indexerMotorSpeed) {
        elevatorMotor.set(elevatorMotorSpeed);
        indexerMotor.set(indexerMotorSpeed);
    }

    public void stop() {
        elevatorMotor.stop();
        indexerMotor.stop();
    }

    public static Feeder getInstance() {
        if(instance == null) instance = new Feeder();
        return instance;
    }
}
