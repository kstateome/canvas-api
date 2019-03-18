package edu.ksu.canvas.requestOptions;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class UpdateCalendarEventOptions extends BaseOptions {

    public enum Frequency {
        DAILY, WEEKLY, MONTHLY;

        @Override
        public String toString() {
            return name().toLowerCase();
        }

    }

    public UpdateCalendarEventOptions contextCode(String contextCode) {
        addSingleItem("calendar_event[context_code]", contextCode);
        return this;
    }

    public UpdateCalendarEventOptions title(String title) {
        addSingleItem("calendar_event[title]", title);
        return this;
    }

    public UpdateCalendarEventOptions description(String description) {
        addSingleItem("calendar_event[description]", description);
        return this;
    }

    public UpdateCalendarEventOptions startAt(Instant startAt) {
        addSingleItem("calendar_event[start_at]", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(startAt));
        return this;
    }

    public UpdateCalendarEventOptions endAt(Instant endAt) {
        addSingleItem("calendar_event[end_at]", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(endAt));
        return this;
    }

    public UpdateCalendarEventOptions locationName(String locationName) {
        addSingleItem("calendar_event[location_name]", locationName);
        return this;
    }

    public UpdateCalendarEventOptions locationAddress(String locationAddress) {
        addSingleItem("calendar_event[location_address]", locationAddress);
        return this;
    }

    public UpdateCalendarEventOptions timeZoneEdited(TimeZone timeZoneEdited) {
        addSingleItem("calendar_event[time_zone_edited]", timeZoneEdited.getID());
        return this;
    }

    // TODO child_event_data


}
