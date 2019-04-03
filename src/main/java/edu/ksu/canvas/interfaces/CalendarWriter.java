package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.CalendarEvent;
import edu.ksu.canvas.requestOptions.DeleteCalendarEventOptions;

import java.io.IOException;
import java.util.Optional;

/**
 * Methods to manipulate calendar events in Canvas.
 * @see <a href="https://canvas.instructure.com/doc/api/calendar_events.html">Canvas calendar event documentation</a>
 *
 */
public interface CalendarWriter extends CanvasWriter<CalendarEvent, CalendarWriter> {

    /**
     * Delete a calendar event from Canvas.
     * @param options Options class containing required and optional parameters for the delete action
     * @return The deleted calendar event
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<CalendarEvent> deleteCalendarEvent(DeleteCalendarEventOptions options) throws IOException;

    /**
     * Create a new calendar event in Canvas.
     * @param calendarEvent Calendar event object to persist to Canvas. Context code is required.
     * @return The newly created calendar event
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<CalendarEvent> createCalendarEvent(CalendarEvent calendarEvent) throws IOException;

    /**
     * Modify a calendar event in Canvas.
     * @param calendarEvent An existing calendar event to modify
     * @return The updated calendar event
     * @throws IOException When there is an error communicating with Canvas
     */
    Optional<CalendarEvent> editCalendarEvent(CalendarEvent calendarEvent) throws IOException;

}
