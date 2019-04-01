package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.CalendarEvent;
import edu.ksu.canvas.requestOptions.ListCalendarEventsOptions;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Methods to query and read calendar events from Canvas.
 *
 * @see <a href="https://canvas.instructure.com/doc/api/calendar_events.html">Canvas calendar event documentation</a>
 *
 */
public interface CalendarReader extends CanvasReader<CalendarEvent, CalendarReader> {

    /**
     * Retrieve calendar events for the current user.
     *
     * @param options Options class to capture optional parameters to this API call
     * @return A list of events matching the query parameters
     * @throws IOException When there is an error communicating with Canvas
     */
    List<CalendarEvent> listCurrentUserCalendarEvents(ListCalendarEventsOptions options) throws IOException;

    /**
     * Retrieve calendar events for a specified user.
     *
     * To read another user's events you must be an administrator or observer.
     * @param userId Canvas ID or sis_user_id:xxx of the user to query
     * @param options Additional options to modify the calendar query
     * @return A list of events matching the query parameters
     * @throws IOException When there is an error communicating with Canvas
     */
    List<CalendarEvent> listCalendarEvents(String userId, ListCalendarEventsOptions options) throws  IOException;

    /**
     * Retrieve a specific calendar event from Canvas.
     * @param id Canvas ID of the calendar event
     * @return The requested calendar event object
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<CalendarEvent> getCalendarEvent(Integer id) throws IOException;

}
