package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.FeatureFlag;

import java.io.IOException;
import java.util.Optional;

public interface FeatureFlagReader extends CanvasReader<FeatureFlag, FeatureFlagReader> {

    Optional<FeatureFlag> getCourseFeatureFlag(String courseId, String feature) throws IOException;
    Optional<FeatureFlag> getAccountFeatureFlag(String accountId, String feature) throws IOException;
    Optional<FeatureFlag> getUserFeatureFlag(String userId, String feature) throws IOException;

}
