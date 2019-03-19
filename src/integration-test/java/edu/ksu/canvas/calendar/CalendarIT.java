package edu.ksu.canvas.calendar;

import edu.ksu.canvas.CanvasApiFactory;
import edu.ksu.canvas.interfaces.CalendarReader;
import edu.ksu.canvas.interfaces.CalendarWriter;
import edu.ksu.canvas.interfaces.SectionWriter;
import edu.ksu.canvas.model.CalendarEvent;
import edu.ksu.canvas.model.Section;
import edu.ksu.canvas.oauth.NonRefreshableOauthToken;
import edu.ksu.canvas.requestOptions.DeleteCalendarEventOptions;
import edu.ksu.canvas.requestOptions.ListCalendarEventsOptions;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

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

        reader = canvasApiFactory.getReader(CalendarReader.class, nonRefreshableOauthToken);
        writer = canvasApiFactory.getWriter(CalendarWriter.class, nonRefreshableOauthToken);

    }

    @Test
    public void testCreateReadDelete() throws IOException {
        CalendarEvent event = new CalendarEvent();
        event.setContextCode("user_" + user);
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

    @Test
    public void testRepeatingEvents() throws IOException {
        String uniqueKey = UUID.randomUUID().toString();
        {
            CalendarEvent event = new CalendarEvent();
            event.setTitle("Test Event");
            event.setDescription(uniqueKey);
            event.setContextCode("course_" + course);
            event.setStartAt(Instant.now());
            event.setEndAt(Instant.now().plus(1, ChronoUnit.HOURS));
            event.setDuplicateCount(9);
            event.setDuplicateFrequency(CalendarEvent.Frequency.DAILY);
            event.setDuplicateInterval(1);
            event.setDuplicateAppend(true);
            writer.createCalendarEvent(event);
        }

        List<CalendarEvent> calendarEvents = reader.listCurrentUserCalendarEvents(
                new ListCalendarEventsOptions()
                        .contextCodes(Collections.singletonList("course_" + course))
                        .includeAllEvents(true)
        );
        int count = 0;
        for (CalendarEvent event: calendarEvents) {
            if (event.getDescription() != null && event.getDescription().equals(uniqueKey)) {
                count++;
                writer.deleteCalendarEvent(new DeleteCalendarEventOptions(event.getId()));
            }
        }
        assertEquals(10, count);
    }

    @Test
    public void testSectionEvents() throws IOException {
        SectionWriter sectionWriter = canvasApiFactory.getWriter(SectionWriter.class, nonRefreshableOauthToken);

        Long section1Id, section2Id;

        {
            Section section = new Section();
            section.setName("Test 1");
            Optional<Section> sectionCreated = sectionWriter.createSection(course, section, false);
            section1Id = sectionCreated.get().getId();
        }
        {
            Section section = new Section();
            section.setName("Test 2");
            Optional<Section> sectionCreated = sectionWriter.createSection(course, section, false);
            section2Id = sectionCreated.get().getId();
        }


        CalendarEvent event = new CalendarEvent();
        List<CalendarEvent.ChildEvent> children = new ArrayList<>();
        event.setTitle("Test Event");
        event.setStartAt(Instant.now().plus(1, ChronoUnit.HOURS));
        event.setEndAt(Instant.now().plus(2, ChronoUnit.HOURS));
        event.setContextCode("course_"+course);
        event.setChildEventsData(children);
        Map<String, String> sectionToTitle = new HashMap<>();
        {
            CalendarEvent.ChildEvent child = new CalendarEvent.ChildEvent();
            child.setContextCode("course_section_"+ section1Id);
            child.setStartAt(Instant.now().plus(1, ChronoUnit.HOURS));
            child.setEndAt(Instant.now().plus(2, ChronoUnit.HOURS));
            children.add(child);
            sectionToTitle.put(child.getContextCode(), event.getTitle()+ " (Test 1)");
        }
        {
            CalendarEvent.ChildEvent child = new CalendarEvent.ChildEvent();
            child.setContextCode("course_section_"+ section2Id);
            child.setStartAt(Instant.now().plus(2, ChronoUnit.HOURS));
            child.setEndAt(Instant.now().plus(3, ChronoUnit.HOURS));
            children.add(child);
            sectionToTitle.put(child.getContextCode(), event.getTitle()+ " (Test 2)");
        }
        Optional<CalendarEvent> eventWritten = writer.createCalendarEvent(event);
        assertTrue(eventWritten.isPresent());

        Optional<CalendarEvent> eventReadOpt = reader.getCalendarEvent(eventWritten.get().getId());
        assertTrue(eventReadOpt.isPresent());

        CalendarEvent eventRead = eventReadOpt.get();
        assertEquals("Test Event", eventRead.getTitle());
        List<CalendarEvent> childEvents = eventRead.getChildEvents();
        assertEquals(2, childEvents.size());
        for (CalendarEvent childEvent: childEvents) {
            assertEquals(sectionToTitle.get(childEvent.getContextCode()), childEvent.getTitle());
        }
        // Deleting the section deletes the event.
        sectionWriter.deleteSection(section1Id.toString());
        sectionWriter.deleteSection(section2Id.toString());
    }


}
