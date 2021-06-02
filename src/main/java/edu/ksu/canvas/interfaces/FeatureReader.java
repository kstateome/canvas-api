package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.Feature;

import java.io.IOException;
import java.util.List;

public interface FeatureReader extends CanvasReader<Feature, FeatureReader> {

    List<Feature> getCourseFeatures(String courseId) throws IOException;

    List<Feature> getAccountFeatures(String accountId) throws IOException;

    List<Feature> getUserFeatures(String userId) throws IOException;

}
