package edu.ksu.canvas.tests.calendar;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.CalendarEventImpl;
import edu.ksu.canvas.interfaces.CalendarWriter;
import edu.ksu.canvas.model.CalendarEvent;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.requestOptions.DeleteCalendarEventOptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalendarWriterUTest extends CanvasTestBase {

    @Autowired
    private FakeRestClient fakeRestClient;
    private CalendarWriter calendarWriter;

    @BeforeEach
    void setupData() {
        calendarWriter = new CalendarEventImpl(baseUrl, apiVersion, SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    void testAddCalendarEvent() throws IOException {
        String url = baseUrl + "/api/v1/calendar_events";
        fakeRestClient.addSuccessResponse(url, "SampleJson/calendar/CreateCalendarEvent.json");
        CalendarEvent event = new CalendarEvent();
        event.setTitle("New Event");
        event.setLocationName("Somewhere");
        event.setStartAt(Instant.parse("2018-01-26T12:00:00Z"));
        event.setEndAt(Instant.parse("2018-01-26T13:00:00Z"));
        event.setContextCode("user_1");
        Optional<CalendarEvent> calendarEvent = calendarWriter.createCalendarEvent(event);
        CalendarEvent returnedEvent = calendarEvent.orElseThrow(RuntimeException::new);
        assertEquals(Long.valueOf(1), returnedEvent.getId());
    }

    @Test
    void testAddCalendarEventNoContext() throws IOException {
        CalendarEvent event = new CalendarEvent();
        event.setTitle("New Event");
        event.setLocationName("Somewhere");
        event.setStartAt(Instant.parse("2018-01-26T12:00:00Z"));
        event.setEndAt(Instant.parse("2018-01-26T13:00:00Z"));
        assertThrows(NullPointerException.class, () -> {
            calendarWriter.createCalendarEvent(event);
        });
    }

    @Test
    void testEditCalendarEvent() throws IOException {
        String url = baseUrl + "/api/v1/calendar_events/1";
        fakeRestClient.addSuccessResponse(url, "SampleJson/calendar/EditCalendarEvent.json");
        CalendarEvent event = new CalendarEvent();
        event.setTitle("Edit Event");
        event.setId(1L);
        Optional<CalendarEvent> editedEventOpt = calendarWriter.editCalendarEvent(event);
        CalendarEvent editedEvent = editedEventOpt.orElseThrow(RuntimeException::new);
        assertEquals("Edit Event", editedEvent.getTitle());
    }

    @Test
    void testDeleteCalendarEvent() throws IOException {
        String url = baseUrl + "/api/v1/calendar_events/1";
        fakeRestClient.addSuccessResponse(url, "SampleJson/calendar/DeleteCalendarEvent.json");
        Optional <CalendarEvent> deleteEventOpt =
            calendarWriter.deleteCalendarEvent(new DeleteCalendarEventOptions(1L).cancelReason("Some reason"));
        CalendarEvent deleteEvent = deleteEventOpt.orElseThrow(RuntimeException::new);
        assertEquals("Delete Event", deleteEvent.getTitle());
    }


}
