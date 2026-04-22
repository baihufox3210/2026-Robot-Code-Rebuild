package frc.robot.subsystems.Intake.Pivot;

import static edu.wpi.first.units.Units.Radians;

import com.GFL.lib.Factory.MotorFactory;
import com.GFL.lib.hardware.interfaces.GenericEncoder;
import com.GFL.lib.hardware.interfaces.GenericMotor;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.ToleranceUtil;

public class Pivot extends SubsystemBase {
    private static Pivot instance;

    private final GenericMotor pivotMotor;
    private final GenericEncoder pivotEncoder;

    private PivotMode currentMode;

    private Pivot() {
        pivotMotor = MotorFactory.createMotor(
            PivotConstants.pivotMotorID,
            PivotConstants.pivotMotorModel,
            PivotConfig.getPivotMotorConfig()
        );

        pivotEncoder = pivotMotor.getEncoder();

        pivotMotor.configure();

        currentMode = PivotMode.RETRACT;
    }

    public PivotMode getCurrentMode() {
        return currentMode;
    }

    public void setCurrentMode(PivotMode targetMode) {
        this.currentMode = targetMode;
    }

    public void setPosition(double position) {
        pivotMotor.setPosition(position);
    }

    public boolean isPivotAtPosition(double position) {
        return ToleranceUtil.isNear(pivotEncoder.getPosition(), position, PivotConstants.tolerance.in(Radians));
    }

    public void stop() {
        pivotMotor.stop();
    }

    public static Pivot getInstance() {
        if(instance == null) instance = new Pivot();
        return instance;
    }

    public enum PivotMode {
        DOWN(PivotConstants.downAngle.in(Radians)),
        RETRACT(0);

        private final double position;

        PivotMode(double position) {
            this.position = position;
        }

        public double getPosition() {
            return position;
        }
    }
}
