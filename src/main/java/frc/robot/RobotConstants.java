package frc.robot;

import static edu.wpi.first.units.Units.Meters;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;

public class RobotConstants {
    public static final double deadband = 0.1;
    public static final double deltaSecond = 0.02;

    public static final Translation2d fieldSize = new Translation2d(16.54, 8.07);
    public static final Translation3d allianceHub = new Translation3d(4.07, 4.03, 1.9);

    public static final Distance allianceDepth = Meters.of(4.03);

    public static final PowerDistribution PDH = new PowerDistribution(50, ModuleType.kRev);
}
