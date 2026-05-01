package frc.robot.subsystems.Shooter.Flywheel;

import com.GFL.lib.Factory.MotorFactory;
import com.GFL.lib.hardware.interfaces.GenericEncoder;
import com.GFL.lib.hardware.interfaces.GenericMotor;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.ToleranceUtil;

public class Flywheel extends SubsystemBase {
    private static Flywheel instance;

    private final GenericMotor flywheelMotor;
    private final GenericEncoder flywheelEncoder;

    private Flywheel() {
        flywheelMotor = MotorFactory.createMotor(
            FlywheelConstants.flywheelMotorID,
            FlywheelConstants.flywheelMotorModel,
            FlywheelConfig.getFlywheelMotorConfig()
        );

        flywheelEncoder = flywheelMotor.getEncoder();
        flywheelMotor.configure();
    }

    public void setVelocity(double targetVelocity) {
        flywheelMotor.setVelocity(targetVelocity);
    }

    public boolean atTargetVelocity(double targetVelocity) {
        return ToleranceUtil.isNear(flywheelEncoder.getVelocity(), targetVelocity, FlywheelConstants.toleranceVelocity);
    }

    public void stop() {
        flywheelMotor.stop();
    }

    public static Flywheel getInstance() {
        if(instance == null) instance = new Flywheel();
        return instance; 
    }
}
