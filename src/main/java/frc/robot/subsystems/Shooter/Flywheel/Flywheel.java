package frc.robot.subsystems.Shooter.Flywheel;

import com.GFL.lib.Factory.MotorFactory;
import com.GFL.lib.hardware.interfaces.GenericMotor;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Flywheel extends SubsystemBase {
    private static Flywheel instance;

    private final GenericMotor flywheelMotor;

    private Flywheel() {
        flywheelMotor = MotorFactory.createMotor(
            FlywheelConstants.flywheelMotorID,
            FlywheelConstants.flywheelMotorModel,
            FlywheelConfig.getFlywheelMotorConfig()
        );

        flywheelMotor.configure();
    }

    public void spinningIdle() {
        set(FlywheelConstants.idleSpeed);
    }

    public void shooting() {
        set(FlywheelConstants.shootingSpeed);
    }

    private void set(double percent) {
        flywheelMotor.set(percent);
    }

    public void stop() {
        flywheelMotor.stop();
    }

    public static Flywheel getInstance() {
        if(instance == null) instance = new Flywheel();
        return instance; 
    }
}
