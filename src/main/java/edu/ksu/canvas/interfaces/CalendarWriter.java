package edu.ksu.canvas.interfaces;

import edu.ksu.canvas.model.CalendarEvent;
import edu.ksu.canvas.requestOptions.DeleteCalendarEventOptions;

import java.io.IOException;
import java.util.Optional;

public interface CalendarWriter extends CanvasWriter<CalendarEvent, CalendarWriter> {

    Optional<CalendarEvent> deleteCalendarEvent(DeleteCalendarEventOptions options) throws IOException;

    Optional<CalendarEvent> createCalendarEvent(CalendarEvent calendarEvent) throws IOException;

    Optional<CalendarEvent> editCalendarEvent(CalendarEvent calendarEvent) throws IOException;

}
