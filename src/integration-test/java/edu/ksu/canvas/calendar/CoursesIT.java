package edu.ksu.canvas.calendar;

import edu.ksu.canvas.CanvasApiFactory;
import edu.ksu.canvas.interfaces.*;
import edu.ksu.canvas.model.CalendarEvent;
import edu.ksu.canvas.model.Course;
import edu.ksu.canvas.model.Section;
import edu.ksu.canvas.oauth.NonRefreshableOauthToken;
import edu.ksu.canvas.requestOptions.DeleteCalendarEventOptions;
import edu.ksu.canvas.requestOptions.GetSingleCourseOptions;
import edu.ksu.canvas.requestOptions.ListCalendarEventsOptions;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CoursesIT {

    private CanvasApiFactory canvasApiFactory;
    private NonRefreshableOauthToken nonRefreshableOauthToken;
    private String course;
    private String user;
    private String account;
    private long term;
    private CourseWriter writer;
    private CourseReader reader;


    @Before
    public void setUp() throws IOException {
        // Load from root of project
        File config = new File("integration.properties");
        if (!config.isFile()) {
            throw new FileNotFoundException("Failed to find configuration: " + config.getAbsolutePath());
        }
        Properties properties = new Properties();
        properties.load(new FileInputStream(config));

        String url = properties.getProperty("url");
        Objects.requireNonNull(url, "Property 'url' must be set in config file.");
        canvasApiFactory = new CanvasApiFactory(url);

        String token = properties.getProperty("token");
        Objects.requireNonNull(token, "Property 'token' must be set in config file.");
        nonRefreshableOauthToken = new NonRefreshableOauthToken(token);

        user = properties.getProperty("user");
        Objects.requireNonNull(user, "Property 'user' must be set in config file.");

        course = properties.getProperty("course");
        Objects.requireNonNull(course, "Property 'course' must be set in config file.");

        account = properties.getProperty("account");
        Objects.requireNonNull(course, "Property 'account' must be set in config file.");

        term = Long.parseLong(properties.getProperty("term"));
        Objects.requireNonNull(course, "Property 'term' must be set in config file.");

        reader = canvasApiFactory.getReader(CourseReader.class, nonRefreshableOauthToken);
        writer = canvasApiFactory.getWriter(CourseWriter.class, nonRefreshableOauthToken);
    }

    @Test
    public void testCreateReadDelete() throws IOException {
        Course course = new Course();
        course.setSisCourseId(UUID.randomUUID().toString());
        course.setName("Integration Course Test");
        course.setEnrollmentTermId(term);
        Course createdCourse = writer.createCourse(account, course).orElseThrow(RuntimeException::new);

        Course readCourse = reader.getSingleCourse(new GetSingleCourseOptions(createdCourse.getId().toString())).orElseThrow(RuntimeException::new);

        assertEquals(term, readCourse.getEnrollmentTermId());
        assertEquals("Integration Course Test", readCourse.getName());

        assertTrue(writer.deleteCourse(createdCourse.getId().toString()));
    }

}
