package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.FeatureFlag;

import java.io.IOException;
import java.util.Optional;

public interface FeatureFlagWriter extends CanvasWriter<FeatureFlag, FeatureFlagWriter > {

    Optional<FeatureFlag> updateCourseFeatureFlag(String courseId, String feature, FeatureFlag.State state) throws IOException;
    Optional<FeatureFlag> updateAccountFeatureFlag(String accountId, String feature, FeatureFlag.State state) throws IOException;
    Optional<FeatureFlag> updateUserFeatureFlag(String userId, String feature, FeatureFlag.State state) throws IOException;

}
