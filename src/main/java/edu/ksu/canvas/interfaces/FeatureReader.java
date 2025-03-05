package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.Feature;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface FeatureReader extends CanvasReader<Feature, FeatureReader> {

    List<Feature> getCourseFeatures(String courseId) throws IOException, URISyntaxException, ParseException;

    List<Feature> getAccountFeatures(String accountId) throws IOException, URISyntaxException, ParseException;

    List<Feature> getUserFeatures(String userId) throws IOException, URISyntaxException, ParseException;

}
