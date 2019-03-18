package edu.ksu.canvas.tests.calendar;

import edu.ksu.canvas.CanvasTestBase;
import edu.ksu.canvas.impl.CalendarEventImpl;
import edu.ksu.canvas.interfaces.CalendarReader;
import edu.ksu.canvas.model.CalendarEvent;
import edu.ksu.canvas.model.Course;
import edu.ksu.canvas.net.FakeRestClient;
import edu.ksu.canvas.requestOptions.ListCalendarEventsOptions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CalendarReaderUTest extends CanvasTestBase {
    @Autowired
    private FakeRestClient fakeRestClient;
    private CalendarReader calendarReader;

    @Before
    public void setupData() {
        calendarReader = new CalendarEventImpl(baseUrl,apiVersion,SOME_OAUTH_TOKEN, fakeRestClient, SOME_CONNECT_TIMEOUT, SOME_READ_TIMEOUT, DEFAULT_PAGINATION_PAGE_SIZE, false);
    }

    @Test
    public void testGettingCalendarForCurrentUser() throws IOException {
        String url = baseUrl + "/api/v1/calendar_events";
        fakeRestClient.addSuccessResponse(url, "SampleJson/calendar/CalendarEvents.json");
        List<CalendarEvent> userCalendarEvents = calendarReader.listCurrentUserCalendarEvents(new ListCalendarEventsOptions());
        Assert.assertEquals("expected that user had 1 calendar event", 1, userCalendarEvents.size());
        CalendarEvent event = userCalendarEvents.get(0);
        Assert.assertEquals("Testing", event.getTitle());
        Assert.assertEquals("Somewhere", event.getLocationName());
        Assert.assertEquals("23 High Street, City, Code", event.getLocationAddress());
        Assert.assertEquals("2018-01-26T00:00:00Z", event.getStartAt().toString());
        Assert.assertEquals("2018-01-26T00:00:00Z", event.getEndAt().toString());
        Assert.assertEquals(CalendarEvent.WorkflowState.ACTIVE, event.getWorkflowState());
        Assert.assertEquals("2018-01-26T15:14:00Z", event.getCreatedAt().toString());
        Assert.assertEquals("2018-01-29T11:02:41Z", event.getUpdatedAt().toString());
        Assert.assertEquals(Boolean.TRUE, event.getAllDay());
        Assert.assertEquals("2018-01-26", event.getAllDayDate().toString());
        Assert.assertEquals("<p>Testing Description.</p>", event.getDescription());
        Assert.assertEquals(Integer.valueOf(0), event.getChildEventsCount());
        Assert.assertEquals("user_73", event.getAllContextCodes());
        Assert.assertEquals("user_73", event.getContextCode());
        Assert.assertEquals(Boolean.FALSE, event.getHidden());
        Assert.assertFalse(event.getUrl().isEmpty());
        Assert.assertFalse(event.getHtmlUrl().isEmpty());
    }

    @Test
    public void testGettingCalendarForUser() throws IOException {
        String url = baseUrl + "/api/v1/users/73/calendar_events";
        fakeRestClient.addSuccessResponse(url, "SampleJson/calendar/CalendarEvents.json");
        List<CalendarEvent> userCalendarEvents = calendarReader.listCalendarEvents("73", new ListCalendarEventsOptions());
        Assert.assertEquals("expected that user had 1 calendar event", 1, userCalendarEvents.size());
        CalendarEvent event = userCalendarEvents.get(0);
        Assert.assertEquals("Testing", event.getTitle());
    }

    @Test
    public void testGetCalendarEvent() throws IOException {
        String url = baseUrl + "/api/v1/calendar_events/95";
        fakeRestClient.addSuccessResponse(url, "SampleJson/calendar/CalendarEvent.json");
        Optional<CalendarEvent> calendarEvent = calendarReader.getCalendarEvent(95);
        Assert.assertTrue(calendarEvent.isPresent());
        CalendarEvent event = calendarEvent.get();
        Assert.assertEquals(Integer.valueOf(95), event.getId());
    }

    @Test(expected = IOException.class)
    public void testGetCalendarEventMissing() throws IOException {
        // This doesn't feel right, having a event missing shouldn't be handled through an exception as it's
        // an expected situation. If the API has optionals I would have expected the optional to be empty.
        String url = baseUrl + "/api/v1/calendar_events/1";
        fakeRestClient.add404Response(url, "SampleJson/calendar/CalendarNotFound.json");
        calendarReader.getCalendarEvent(1);
    }

}
