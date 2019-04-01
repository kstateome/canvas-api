package edu.ksu.canvas.requestOptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;

/**
 * These are the options for listing calendar events.
 * The same options are used for both the current user and a specific user.
 */
public class ListCalendarEventsOptions extends BaseOptions {

    public enum Type {
        EVENT, ASSIGNMENT;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    public enum Exclude {
        DESCRIPTION, CHILD_EVENTS, ASSIGNMENT;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    public ListCalendarEventsOptions startDate(LocalDate startDate) {
        addSingleItem("start_date", DateTimeFormatter.ISO_LOCAL_DATE.format(startDate));
        return this;
    }

    public ListCalendarEventsOptions startDate(TemporalAccessor startDate) {
        addSingleItem("start_date", DateTimeFormatter.ISO_INSTANT.format(startDate));
        return this;
    }

    public ListCalendarEventsOptions endDate(LocalDate endDate) {
        addSingleItem("start_date", DateTimeFormatter.ISO_LOCAL_DATE.format(endDate));
        return this;
    }

    public ListCalendarEventsOptions endDate(TemporalAccessor endDate) {
        addSingleItem("start_date", DateTimeFormatter.ISO_INSTANT.format(endDate));
        return this;
    }

    public ListCalendarEventsOptions includeUndated(boolean include) {
        addSingleItem("undated", Boolean.toString(include));
        return this;
    }

    public ListCalendarEventsOptions includeAllEvents(boolean all) {
        addSingleItem("all_events", Boolean.toString(all));
        return this;
    }

    // TODO could have limiting to 10 and building of strings in here.
    public ListCalendarEventsOptions contextCodes(List<String> contextCodes) {
        optionsMap.put("context_codes[]", contextCodes);
        return this;
    }

    public ListCalendarEventsOptions excludes(List<Exclude> excludes) {
        addEnumList("excludes", excludes);
        return this;
    }

}
