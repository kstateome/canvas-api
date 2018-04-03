package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.CalendarEvent;
import edu.ksu.canvas.requestOptions.ListCalendarEventsOptions;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CalendarReader extends CanvasReader<CalendarEvent, CalendarReader> {

    List<CalendarEvent> listCurrentUserCalendarEvents(ListCalendarEventsOptions options) throws IOException;

    List<CalendarEvent> listCalendarEvents(String userId, ListCalendarEventsOptions options) throws  IOException;

    Optional<CalendarEvent> getCalendarEvent(String id) throws IOException;

}
