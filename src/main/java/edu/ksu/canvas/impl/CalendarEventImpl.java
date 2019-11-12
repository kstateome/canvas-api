package edu.ksu.canvas.impl;

import com.google.gson.reflect.TypeToken;
import edu.ksu.canvas.interfaces.CalendarReader;
import edu.ksu.canvas.interfaces.CalendarWriter;
import edu.ksu.canvas.model.CalendarEvent;
import edu.ksu.canvas.net.Response;
import edu.ksu.canvas.net.RestClient;
import edu.ksu.canvas.oauth.OauthToken;
import edu.ksu.canvas.requestOptions.DeleteCalendarEventOptions;
import edu.ksu.canvas.requestOptions.ListCalendarEventsOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class CalendarEventImpl extends BaseImpl<CalendarEvent, CalendarReader, CalendarWriter> implements CalendarReader, CalendarWriter {

    private static  final Logger LOG = LoggerFactory.getLogger(CalendarEventImpl.class);

    /**
     * Construct a new CanvasApi class with an OAuth token
     *
     * @param canvasBaseUrl      The base URL of your canvas instance
     * @param apiVersion         The version of the Canvas API (currently 1)
     * @param oauthToken         OAuth token to use when executing API calls
     * @param restClient         a RestClient implementation to use when talking to Canvas
     * @param connectTimeout     Timeout in seconds to use when connecting
     * @param readTimeout        Timeout in seconds to use when waiting for data to come back from an open connection
     * @param paginationPageSize How many objects to request per page on paginated requests
     * @param serializeNulls     Whether to serialize null values out to Canvas, blanking them out
     */
    public CalendarEventImpl(String canvasBaseUrl, Integer apiVersion, OauthToken oauthToken, RestClient restClient, int connectTimeout, int readTimeout, Integer paginationPageSize, Boolean serializeNulls) {
        super(canvasBaseUrl, apiVersion, oauthToken, restClient, connectTimeout, readTimeout, paginationPageSize, serializeNulls);
    }

    @Override
    protected Type listType() {
        return new TypeToken<List<CalendarEvent>>(){}.getType();
    }

    @Override
    protected Class<CalendarEvent> objectType() {
        return CalendarEvent.class;
    }

    @Override
    public List<CalendarEvent> listCurrentUserCalendarEvents(ListCalendarEventsOptions options) throws IOException {
        LOG.debug("List calendar events for current user");
        String url = buildCanvasUrl("calendar_events", options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    public List<CalendarEvent> listCalendarEvents(String userId, ListCalendarEventsOptions options) throws  IOException {
        LOG.debug("List calendar events for "+ userId);
        String url = buildCanvasUrl("users/" + userId + "/calendar_events", options.getOptionsMap());
        return getListFromCanvas(url);
    }

    @Override
    public Optional<CalendarEvent> getCalendarEvent(Integer id) throws IOException {
        LOG.debug("Getting calendar event: "+ id);
        String url = buildCanvasUrl("/calendar_events/"+ id.toString(), Collections.emptyMap());
        return getFromCanvas(url);
    }

    @Override
    public Optional<CalendarEvent> deleteCalendarEvent(DeleteCalendarEventOptions options) throws IOException {
        LOG.debug("Deleting calendar event: "+ options.getId());
        String url = buildCanvasUrl("/calendar_events/"+ options.getId(), Collections.emptyMap());
        Response response = canvasMessenger.deleteFromCanvas(oauthToken, url, options.getOptionsMap());
        LOG.debug("response " + response.toString());
        if(response.getErrorHappened() || response.getResponseCode() != 200){
            LOG.debug("Failed to delete assignment, error message: " + response.toString());
            return Optional.empty();
        }
        return responseParser.parseToObject(CalendarEvent.class, response);
    }

    @Override
    public Optional<CalendarEvent> createCalendarEvent(CalendarEvent calendarEvent) throws IOException {
        LOG.debug("Creating calendar event.");
        String url = buildCanvasUrl("calendar_events", Collections.emptyMap());
        Objects.requireNonNull(calendarEvent.getContextCode(), "contextCode must be set to create a calendar event.");
        Map<String, List<String>> parameters = calendarEvent.toPostMap(false);
        addChildData(calendarEvent, parameters);
        Response response = canvasMessenger.sendToCanvas(oauthToken, url, parameters);
        return responseParser.parseToObject(CalendarEvent.class, response);
    }


    @Override
    public Optional<CalendarEvent> editCalendarEvent(CalendarEvent calendarEvent) throws IOException {
        LOG.debug("Modify calendar event " + calendarEvent.getId());
        String url = buildCanvasUrl("calendar_events/"+ calendarEvent.getId(), Collections.emptyMap());
        Map<String, List<String>> parameters = calendarEvent.toPostMap(false);
        addChildData(calendarEvent, parameters);
        Response response = canvasMessenger.putToCanvas(oauthToken, url, parameters);
        return responseParser.parseToObject(CalendarEvent.class, response);
    }


    private void addChildData(CalendarEvent calendarEvent, Map<String, List<String>> parameters) {
        // This adds in the child events
        List<CalendarEvent.ChildEvent> childEventsData = calendarEvent.getChildEventsData();
        if (childEventsData == null) {
            return;
        }
        for(int i = 0; i < childEventsData.size(); i++) {
            CalendarEvent.ChildEvent childEvent = childEventsData.get(i);
            Map<String, List<String>> additional  = childEvent.toPostMap(false);
            String newKey = "calendar_event[child_event_data]["+ i+ "]";
            additional.forEach((key, value) -> parameters.putIfAbsent(newKey+key, value));
        }
    }
}
