package edu.ksu.canvas.calendar;

import edu.ksu.canvas.CanvasApiFactory;
import edu.ksu.canvas.interfaces.CalendarReader;
import edu.ksu.canvas.interfaces.CalendarWriter;
import edu.ksu.canvas.model.CalendarEvent;
import edu.ksu.canvas.oauth.NonRefreshableOauthToken;
import edu.ksu.canvas.requestOptions.DeleteCalendarEventOptions;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CalendarIT {

    private CanvasApiFactory canvasApiFactory;
    private NonRefreshableOauthToken nonRefreshableOauthToken;
    private String course;
    private String user;
    private CalendarWriter writer;
    private CalendarReader reader;


    @Before
    public void setUp() throws IOException {
        // Load from root of project
        File config = new File("integration.properties");
        if (!config.isFile()) {
            throw new FileNotFoundException("Failed to find configuration: "+ config.getAbsolutePath());
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

        reader = canvasApiFactory.getReader(CalendarReader.class, nonRefreshableOauthToken);
        writer = canvasApiFactory.getWriter(CalendarWriter.class, nonRefreshableOauthToken);

    }

    @Test
    public void testCreateReadDelete() throws IOException {
        CalendarEvent event = new CalendarEvent();
        event.setContextCode("user_"+ user);
        event.setTitle("Test Event");
        event.setStartAt(Instant.now());
        event.setEndAt(Instant.now().plus(1, ChronoUnit.HOURS));
        Optional<CalendarEvent> writtenCalendarEvent = writer.createCalendarEvent(event);

        assertTrue(writtenCalendarEvent.isPresent());

        Integer id = writtenCalendarEvent.get().getId();
        Optional<CalendarEvent> readCalendarEvent = reader.getCalendarEvent(id);
        assertTrue(readCalendarEvent.isPresent());
        assertEquals(writtenCalendarEvent, readCalendarEvent);

        Optional<CalendarEvent> deletedCalendarEvent = writer.deleteCalendarEvent(new DeleteCalendarEventOptions(id));
        assertTrue(deletedCalendarEvent.isPresent());
        assertEquals(CalendarEvent.WorkflowState.DELETED, deletedCalendarEvent.get().getWorkflowState());
    }

}
