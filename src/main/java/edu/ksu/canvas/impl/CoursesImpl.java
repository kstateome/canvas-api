package edu.ksu.canvas.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import edu.ksu.canvas.interfaces.Courses;
import edu.ksu.canvas.model.Course;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.util.CanvasURLBuilder;

public class CoursesImpl extends BaseImpl implements Courses {

    public CoursesImpl(String canvasBaseUrl, Integer apiVersion, String oauthToken) {
        super(canvasBaseUrl, apiVersion, oauthToken);
    }

    @Override
    public Optional<Course> retrieveCourse(String courseId) throws IOException {
        System.out.println("geting course " + courseId);
        String url = CanvasURLBuilder.buildCanvasUrl(canvasBaseUrl, apiVersion, "courses/" + courseId, Collections.emptyMap());

        return retrieveCourseFromCanvas(oauthToken, url);
    }

    private Optional<Course> retrieveCourseFromCanvas(String oauthToken, String url) throws IOException {
        Response response = canvasMessenger.getFromCanvas(oauthToken, url);
        //LOG.info(response.getContent());
        if (response.getErrorHappened() || response.getResponseCode() != 200) {
            return Optional.empty();
        }
        return responseParser.parseToObject(Course.class, response);
    }
}
