package edu.ksu.canvas.requestOptions;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class CreateCalendarEventOptions extends BaseOptions {

    public enum Frequency {
        DAILY, WEEKLY, MONTHLY;

        @Override
        public String toString() {
            return name().toLowerCase();
        }

    }

    public CreateCalendarEventOptions contextCode(String contextCode) {
        addSingleItem("calendar_event[context_code]", contextCode);
        return this;
    }

    public CreateCalendarEventOptions title(String title) {
        addSingleItem("calendar_event[title]", title);
        return this;
    }

    public CreateCalendarEventOptions description(String description) {
        addSingleItem("calendar_event[description]", description);
        return this;
    }

    public CreateCalendarEventOptions startAt(Instant startAt) {
        addSingleItem("calendar_event[start_at]", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(startAt));
        return this;
    }

    public CreateCalendarEventOptions endAt(Instant endAt) {
        addSingleItem("calendar_event[end_at]", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(endAt));
        return this;
    }

    public CreateCalendarEventOptions locationName(String locationName) {
        addSingleItem("calendar_event[location_name]", locationName);
        return this;
    }

    public CreateCalendarEventOptions locationAddress(String locationAddress) {
        addSingleItem("calendar_event[location_address]", locationAddress);
        return this;
    }

    public CreateCalendarEventOptions timeZoneEdited(TimeZone timeZoneEdited) {
        addSingleItem("calendar_event[time_zone_edited]", timeZoneEdited.getID());
        return this;
    }

    // TODO child_event_data


    public CreateCalendarEventOptions duplicateCount(int count) {
        addSingleItem("calendar_event[duplicate][count]", Integer.toString(count));
        return this;
    }

    public CreateCalendarEventOptions duplicateInterval(int interval) {
        addSingleItem("calendar_event[duplicate][interval]", Integer.toString(interval));
        return this;
    }

    public CreateCalendarEventOptions duplicateFrequency(Frequency frequency) {
        addSingleItem("calendar_event[duplicate][frequency]", frequency.toString());
        return this;
    }

    public CreateCalendarEventOptions duplicateAppendIterator(boolean appendIterator) {
        addSingleItem("calendar_event[duplicate][append_iterator]", Boolean.toString(appendIterator));
        return this;
    }


}
