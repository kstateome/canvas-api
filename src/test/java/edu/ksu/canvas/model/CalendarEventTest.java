package edu.ksu.canvas.model;

import org.junit.Test;

import java.time.Instant;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class CalendarEventTest {

    @Test
    public void testCopyConstructorEmpty() {
        CalendarEvent event = new CalendarEvent();
        CalendarEvent other = new CalendarEvent(event);
        assertEquals(event, other);
    }

    @Test
    public void testCopyConstructorCreationFields() {
        CalendarEvent event = new CalendarEvent();
        event.setId(1);
        event.setTitle("Title");
        event.setStartAt(Instant.now());
        event.setEndAt(Instant.now());
        event.setDescription("The description");
        event.setLocationName("Location name");
        event.setLocationAddress("Location address");
        event.setContextCode("context_code");
        {
            CalendarEvent.ChildEvent child = new CalendarEvent.ChildEvent();
            child.setContextCode("context_code");
            child.setStartAt(Instant.now());
            child.setEndAt(Instant.now());
            event.setChildEventsData(Collections.singletonList(child));
        }

        CalendarEvent other = new CalendarEvent(event);
        assertEquals(event, other);
    }
}
