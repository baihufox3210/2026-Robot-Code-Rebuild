package frc.robot.control.aiming.model;

import edu.wpi.first.math.interpolation.InterpolatingDoubleTreeMap;

public class ShotModel {
    private final InterpolatingDoubleTreeMap distanceMap;
    private final InterpolatingDoubleTreeMap heightMap;

    public ShotModel() {
        distanceMap = new InterpolatingDoubleTreeMap();
        heightMap = new InterpolatingDoubleTreeMap();

        distanceMap.put(2.40, 60.0);
        distanceMap.put(3.00, 70.0);
        distanceMap.put(3.60, 80.0);
        distanceMap.put(4.30, 90.0);
        distanceMap.put(5.00, 100.0);

        heightMap.put(1.60, 60.0);
        heightMap.put(1.95, 70.0);
        heightMap.put(2.40, 80.0);
        heightMap.put(2.75, 90.0);
        heightMap.put(2.95, 100.0);
    }

    public double getByDistance(double distance) {
        return distanceMap.get(distance);
    }

    public double getByHeight(double height) {
        return heightMap.get(height);
    }
}
