package frc.robot.subsystems.Vision;

import java.util.List;
import java.util.Optional;

import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {
    private final PhotonCamera camera;
    private final VisionEstimateConsumer estimateConsumer;

    private Matrix<N3, N1> currentStdDevs;
    private PhotonPoseEstimator poseEstimator;

    public Vision(VisionEstimateConsumer estimateConsumer) {
        this.estimateConsumer = estimateConsumer;

        currentStdDevs = VisionConstants.singleTagStdDevs;

        camera = new PhotonCamera(VisionConstants.cameraName);
        poseEstimator =  createPhotonPoseEstimator();
    }

    private PhotonPoseEstimator createPhotonPoseEstimator() {
        try {
            return new PhotonPoseEstimator(
                AprilTagFieldLayout.loadField(AprilTagFields.k2026RebuiltWelded),
                VisionConstants.robotToCamera
            );
        }
        catch(Exception e) {
            DriverStation.reportError("Failed to load AprilTag Field layout", false);
            return null;
        }
    }

    @Override
    public void periodic() {
        Optional<PhotonPipelineResult> result = getLatestResult();
        if(result.isEmpty()) return;

        var latestResult = result.get();
        if(!isValidVisionResult(latestResult)) return;

        Optional<EstimatedRobotPose> poseEstimate = getEstimatedRobotPose(latestResult);
        updateEstimeationStdDevs(poseEstimate, latestResult.getTargets());
        poseEstimate.ifPresent(this::publishVisionEstimate);
    }

    private Optional<PhotonPipelineResult> getLatestResult() {
        var results = camera.getAllUnreadResults();

        if(results.isEmpty()) return Optional.empty();
        return Optional.of(results.get(results.size() - 1));
    }

    private boolean isValidVisionResult(PhotonPipelineResult result) {
        if(!result.hasTargets()) return false;

        if(result.getTargets().size() == 1) {
            double ambiguity = result.getBestTarget().getPoseAmbiguity();
            if(ambiguity > 0.2) return false;
        }

        return true;
    }

    private Optional<EstimatedRobotPose> getEstimatedRobotPose(PhotonPipelineResult result) {
        if(poseEstimator == null) return Optional.empty();

        Optional<EstimatedRobotPose> estimate = poseEstimator.estimateCoprocMultiTagPose(result);
        if(estimate.isEmpty()) estimate = poseEstimator.estimateLowestAmbiguityPose(result);

        return estimate;
    }

    private void updateEstimeationStdDevs(Optional<EstimatedRobotPose> estimated, List<PhotonTrackedTarget> targets) {
        if(estimated.isEmpty()) {
            currentStdDevs = VisionConstants.singleTagStdDevs;
            return;
        }

        int visibleTagCount = 0;
        double totalDistance = 0;

        Pose2d estimatedPose2d = estimated.get().estimatedPose.toPose2d();

        for(PhotonTrackedTarget target : targets) {
            var tagPose = poseEstimator.getFieldTags().getTagPose(target.getFiducialId());

            if(tagPose.isEmpty()) continue;

            visibleTagCount++;
            totalDistance += tagPose.get().toPose2d().getTranslation().getDistance(estimatedPose2d.getTranslation());
        }

        if(visibleTagCount == 0) {
            currentStdDevs = VisionConstants.singleTagStdDevs;
            return;
        }

        double averageDistance = totalDistance / visibleTagCount;
        Matrix<N3, N1> stdDevs = VisionConstants.singleTagStdDevs;

        if(visibleTagCount > 1) stdDevs = VisionConstants.multiTagStdDevs;
        else if(visibleTagCount == 1 && averageDistance > 4) stdDevs = VecBuilder.fill(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        else stdDevs = stdDevs.times(1 + (averageDistance * averageDistance / 30));

        currentStdDevs = stdDevs;
    }

    private void publishVisionEstimate(EstimatedRobotPose estimate) {
        estimateConsumer.accept(
            estimate.estimatedPose.toPose2d(),
            estimate.timestampSeconds,
            currentStdDevs
        );
    }

    @FunctionalInterface
    public static interface VisionEstimateConsumer {
        public void accept(Pose2d pose, double timestamp, Matrix<N3, N1> estimationStdDevs);
    }

}
