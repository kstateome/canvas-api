package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.FeatureFlag;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

public interface FeatureFlagReader extends CanvasReader<FeatureFlag, FeatureFlagReader> {

    Optional<FeatureFlag> getCourseFeatureFlag(String courseId, String feature) throws IOException, URISyntaxException, ParseException;
    Optional<FeatureFlag> getAccountFeatureFlag(String accountId, String feature) throws IOException, URISyntaxException, ParseException;
    Optional<FeatureFlag> getUserFeatureFlag(String userId, String feature) throws IOException, URISyntaxException, ParseException;

}
