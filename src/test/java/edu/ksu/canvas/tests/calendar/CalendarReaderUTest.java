package edu.ksu.canvas.tests.calendar;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.CalendarEventImpl;
import edu.ksu.canvas.interfaces.CalendarReader;
import edu.ksu.canvas.model.CalendarEvent;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.requestOptions.ListCalendarEventsOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalendarReaderUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private CalendarReader calendarReader;

    @BeforeEach
    void setupData() {
        calendarReader = new CalendarEventImpl(baseUrl,apiVersion,SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    void testGettingCalendarForCurrentUser() throws IOException {
        String url = baseUrl + "/api/v1/calendar_events";
        fakeRestClient.addSuccessResponse(url, "SampleJson/calendar/CalendarEvents.json");
        List<CalendarEvent> userCalendarEvents = calendarReader.listCurrentUserCalendarEvents(new ListCalendarEventsOptions());
        assertEquals(1, userCalendarEvents.size(), "expected that user had 1 calendar event");
        CalendarEvent event = userCalendarEvents.get(0);
        assertEquals("Testing", event.getTitle());
        assertEquals("Somewhere", event.getLocationName());
        assertEquals("23 High Street, City, Code", event.getLocationAddress());
        assertEquals("2018-01-26T00:00:00Z", event.getStartAt().toString());
        assertEquals("2018-01-26T00:00:00Z", event.getEndAt().toString());
        assertEquals(CalendarEvent.WorkflowState.ACTIVE, event.getWorkflowState());
        assertEquals("2018-01-26T15:14:00Z", event.getCreatedAt().toString());
        assertEquals("2018-01-29T11:02:41Z", event.getUpdatedAt().toString());
        assertEquals(Boolean.TRUE, event.getAllDay());
        assertEquals("2018-01-26", event.getAllDayDate().toString());
        assertEquals("<p>Testing Description.</p>", event.getDescription());
        assertEquals(Long.valueOf(0), event.getChildEventsCount());
        assertEquals("user_73", event.getAllContextCodes());
        assertEquals("user_73", event.getContextCode());
        assertEquals(Boolean.FALSE, event.getHidden());
        assertFalse(event.getUrl().isEmpty());
        assertFalse(event.getHtmlUrl().isEmpty());
    }

    @Test
    void testGettingCalendarForUser() throws IOException {
        String url = baseUrl + "/api/v1/users/73/calendar_events";
        fakeRestClient.addSuccessResponse(url, "SampleJson/calendar/CalendarEvents.json");
        List<CalendarEvent> userCalendarEvents = calendarReader.listCalendarEvents("73", new ListCalendarEventsOptions());
        assertEquals(1, userCalendarEvents.size(), "expected that user had 1 calendar event");
        CalendarEvent event = userCalendarEvents.get(0);
        assertEquals("Testing", event.getTitle());
    }

    @Test
    void testGetCalendarEvent() throws IOException {
        String url = baseUrl + "/api/v1/calendar_events/95";
        fakeRestClient.addSuccessResponse(url, "SampleJson/calendar/CalendarEvent.json");
        Optional<CalendarEvent> calendarEvent = calendarReader.getCalendarEvent(95L);
        assertTrue(calendarEvent.isPresent());
        CalendarEvent event = calendarEvent.get();
        assertEquals(Long.valueOf(95), event.getId());
    }

    @Test
    void testGetCalendarEventMissing() throws IOException {
        // This doesn't feel right, having a event missing shouldn't be handled through an exception as it's
        // an expected situation. If the API has optionals I would have expected the optional to be empty.
        String url = baseUrl + "/api/v1/calendar_events/1";
        fakeRestClient.add404Response(url, "SampleJson/calendar/CalendarNotFound.json");
        assertThrows(IOException.class, () -> {
            calendarReader.getCalendarEvent(1L);
        });

    }

}
