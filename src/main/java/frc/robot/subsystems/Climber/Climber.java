package frc.robot.subsystems.Climber;

import com.GFL.lib.Factory.MotorFactory;
import com.GFL.lib.hardware.interfaces.GenericEncoder;
import com.GFL.lib.hardware.interfaces.GenericMotor;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.ToleranceUtil;

public class Climber extends SubsystemBase {
    private static Climber instance;
    
    private final GenericMotor climberMotor;
    private final GenericEncoder climberEncoder;

    private ClimberMode currentMode;

    private Climber() {
        climberMotor = MotorFactory.createMotor(
            ClimberConstants.climberMotorID,
            ClimberConstants.climberMotorModel,
            ClimberConfig.getClimberMotorConfig()
        );

        climberEncoder = climberMotor.getEncoder();

        climberMotor.configure();

        currentMode = ClimberMode.RETRACT;
    }

    public ClimberMode getCurrentMode() {
        return currentMode;
    }

    public void setCurrentMode(ClimberMode targetMode) {
        this.currentMode = targetMode;
    }

    public void setPosition(double position) {
        climberMotor.setPosition(position);
    }

    public boolean isClimberAtPosition(double position) {
        return ToleranceUtil.isNear(climberEncoder.getPosition(), position, ClimberConstants.tolerance);
    }

    public void stop() {
        climberMotor.stop();
    }

    public static Climber getInstance() {
        if(instance == null) instance = new Climber();
        return instance;
    }

    public enum ClimberMode {
        RAISE(ClimberConstants.raiseLength),
        CLIMB(ClimberConstants.climbLength),
        RETRACT(0);

        private final double position;

        ClimberMode(double position) {
            this.position = position;
        }

        public double getPosition() {
            return position;
        }
    }
}