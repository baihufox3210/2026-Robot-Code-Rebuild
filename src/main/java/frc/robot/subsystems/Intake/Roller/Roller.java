package frc.robot.subsystems.Intake.Roller;

import com.GFL.lib.Factory.MotorFactory;
import com.GFL.lib.hardware.interfaces.GenericMotor;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Roller extends SubsystemBase {
    private static Roller instance;

    private final GenericMotor rollerMotor;
    
    private Roller() {
        rollerMotor = MotorFactory.createMotor(
            RollerConstant.rollerMotorID,
            RollerConstant.rollerMotorModel,
            RollerConfig.getRollerMotorConfig()
        );
    }

    private void set(double percent) {
        rollerMotor.set(percent);
    }

    public void intaking() {
        set(RollerConstant.intakeSpeed);
    }

    public void stop() {
        rollerMotor.stop();
    }

    public static Roller getInstance() {
        if(instance == null) instance = new Roller();
        return instance;
    }
}
