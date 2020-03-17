package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.CourseReader;
import edu.ksu.canvas.interfaces.CourseSettingsReader;
import edu.ksu.canvas.interfaces.CourseSettingsWriter;
import edu.ksu.canvas.model.CourseSettings;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CourseSettingsImpl extends BaseImpl<CourseSettings, CourseSettingsReader, CourseSettingsWriter> implements CourseSettingsReader, CourseSettingsWriter {
    private static final Logger LOG = LoggerFactory.getLogger(CourseReader.class);

    public CourseSettingsImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient,
                      int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout,
                paginationPageSize, serializeNulls);
    }

    @Override
    public Optional<CourseSettings> getCourseSettings(String courseId) throws IOException {
        LOG.debug("getting course settings for " + courseId);
        String url = buildCanvasUrl(String.format("courses/%s/settings", courseId), Collections.emptyMap());
        Response response =  canvasMessenger.getSingleResponseFromCanvas(oauthToken, url);
        return responseParser.parseToObject(CourseSettings.class, response);
    }

    @Override
    public Optional<CourseSettings> updateCourseSettings(String courseId, CourseSettings settings) throws IOException {
        LOG.debug("updating course settings for " + courseId);
        String url = buildCanvasUrl(String.format("courses/%s/settings", courseId), Collections.emptyMap());
        Response response = canvasMessenger.putToCanvas(oauthToken, url, settings.toPostMap(serializeNulls));
        return responseParser.parseToObject(CourseSettings.class, response);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<CourseSettings>>(){}.getType();
    }

    @Override
    protected Class<CourseSettings> objectType() {
        return CourseSettings.class;
    }
}
