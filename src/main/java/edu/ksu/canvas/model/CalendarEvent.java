package edu.ksu.canvas.model;

import com.google.gson.annotations.SerializedName;
import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;
import org.apache.commons.lang3.text.WordUtils;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class to represent Canvas calendar event.
 * See <a href="https://canvas.instructure.com/doc/api/calendar_events.html">Canvas calendar event</a> documentation.
 * There looks to be a strange case where an event when read includes timezone, but when written it doesn't.
 *
 */
@CanvasObject(postKey = "calendar_event")
public class CalendarEvent extends BaseCanvasModel implements Serializable {

    public static final long serialVersionUID = 1L;

    public enum WorkflowState {
        @SerializedName("active")
        ACTIVE,
        @SerializedName("locked")
        LOCKED,
        @SerializedName("deleted")
        DELETED;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    public enum ParticipantType {
        @SerializedName("Group")
        GROUP,
        @SerializedName("User")
        USER;

        @Override
        public String toString() { return WordUtils.capitalizeFully(name()); }
    }

    public enum Frequency {
        @SerializedName("daily")
        DAILY,
        @SerializedName("weekly")
        WEEKLY,
        @SerializedName("monthly")
        MONTHLY;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    @CanvasObject(postKey = "")
    public static class ChildEvent extends BaseCanvasModel implements Serializable {

        public static final long serialVersionUID = 1L;

        private Instant startAt;
        private Instant endAt;
        private String contextCode;

        public ChildEvent() {
        }

        public ChildEvent(ChildEvent other) {
            this.startAt = other.startAt;
            this.endAt = other.endAt;
            this.contextCode = other.contextCode;
        }

        @CanvasField(postKey = "start_at")
        public Instant getStartAt() {
            return startAt;
        }

        public void setStartAt(Instant startAt) {
            this.startAt = startAt;
        }

        @CanvasField(postKey = "end_at")
        public Instant getEndAt() {
            return endAt;
        }

        public void setEndAt(Instant endAt) {
            this.endAt = endAt;
        }

        @CanvasField(postKey = "context_code")
        public String getContextCode() {
            return contextCode;
        }

        public void setContextCode(String contextCode) {
            this.contextCode = contextCode;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ChildEvent that = (ChildEvent) o;
            return Objects.equals(startAt, that.startAt) &&
                    Objects.equals(endAt, that.endAt) &&
                    Objects.equals(contextCode, that.contextCode);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startAt, endAt, contextCode);
        }
    }

    private Integer id;
    private String title;
    // These are both considered to be in UTC.
    private Instant startAt;
    private Instant endAt;
    private String description;
    private String locationName;
    private String locationAddress;
    private String contextCode;
    private String effectiveContextCode;
    // TODO could be a collection if we do parsing of the string.
    private String allContextCodes;
    private WorkflowState workflowState;
    private Boolean hidden;
    private String parentEventId;
    private Integer childEventsCount;
    private List<CalendarEvent> childEvents;
    private List<ChildEvent> childEventsData;
    private String url;
    private String htmlUrl;
    private LocalDate allDayDate;
    private Boolean allDay;
    private Instant createdAt;
    private Instant updatedAt;
    private String appointmentGroupId;
    private String appointmentGroupUrl;
    private Boolean reservation;
    private ParticipantType participantType;
    private Integer participantsPerAppointment;
    private Integer availableSlots;
    private User user;

    private Integer duplicateCount;
    private Integer duplicateInterval;
    private Frequency duplicateFrequency;
    private Boolean duplicateAppend;
    // TODO Group models aren't yet done.
    // private Group group;

    public CalendarEvent() {
    }

    public CalendarEvent(CalendarEvent other) {
        this.id = other.id;
        this.title = other.title;
        this.startAt = other.startAt;
        this.endAt = other.endAt;
        this.description = other.description;
        this.locationName = other.locationName;
        this.locationAddress = other.locationAddress;
        this.contextCode = other.contextCode;
        if (other.childEventsData != null) {
            this.childEventsData = new ArrayList<>();
            for (ChildEvent child : other.childEventsData) {
                this.childEventsData.add(new ChildEvent(child));
            }
        }
        this.allDay = other.allDay;
        // Just valid on creation
        this.duplicateCount = other.duplicateCount;
        this.duplicateInterval = other.duplicateInterval;
        this.duplicateFrequency = other.duplicateFrequency;
        this.duplicateAppend = other.duplicateAppend;

        // The non-modifiable properties (just read back from canvas).
        this.effectiveContextCode = other.effectiveContextCode;
        this.allContextCodes = other.allContextCodes;
        this.workflowState = other.workflowState;
        this.hidden = other.hidden;
        this.parentEventId = other.parentEventId;
        this.childEventsCount = other.childEventsCount;
        if (other.childEvents != null) {
            this.childEvents = new ArrayList<>();
            for (CalendarEvent child : other.childEvents) {
                this.childEvents.add(new CalendarEvent(child));
            }
        }
        this.url = other.url;
        this.htmlUrl = other.htmlUrl;
        this.allDayDate = other.allDayDate;
        this.createdAt = other.createdAt;
        this.updatedAt = other.updatedAt;
        this.appointmentGroupId = other.appointmentGroupId;
        this.appointmentGroupUrl = other.appointmentGroupUrl;
        this.reservation = other.reservation;
        this.participantsPerAppointment = other.participantsPerAppointment;
        this.participantType = other.participantType;
        this.availableSlots = other.availableSlots;
        if (other.user != null) {
            this.user = new User(other.user);
        }
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @CanvasField(postKey = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @CanvasField(postKey = "start_at")
    public Instant getStartAt() {
        return startAt;
    }

    public void setStartAt(Instant startAt) {
        this.startAt = startAt;
    }

    @CanvasField(postKey = "end_at")
    public Instant getEndAt() {
        return endAt;
    }

    public void setEndAt(Instant endAt) {
        this.endAt = endAt;
    }

    @CanvasField(postKey = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @CanvasField(postKey = "location_name")
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @CanvasField(postKey = "location_address")
    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    @CanvasField(postKey = "context_code")
    public String getContextCode() {
        return contextCode;
    }

    public void setContextCode(String contextCode) {
        this.contextCode = contextCode;
    }

    public String getEffectiveContextCode() {
        return effectiveContextCode;
    }

    public void setEffectiveContextCode(String effectiveContextCode) {
        this.effectiveContextCode = effectiveContextCode;
    }

    public String getAllContextCodes() {
        return allContextCodes;
    }

    public void setAllContextCodes(String allContextCodes) {
        this.allContextCodes = allContextCodes;
    }

    public WorkflowState getWorkflowState() {
        return workflowState;
    }

    public void setWorkflowState(WorkflowState workflowState) {
        this.workflowState = workflowState;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public String getParentEventId() {
        return parentEventId;
    }

    public void setParentEventId(String parentEventId) {
        this.parentEventId = parentEventId;
    }

    public Integer getChildEventsCount() {
        return childEventsCount;
    }

    public void setChildEventsCount(Integer childEventsCount) {
        this.childEventsCount = childEventsCount;
    }

    public List<CalendarEvent> getChildEvents() {
        return childEvents;
    }

    public void setChildEvents(List<CalendarEvent> childEvents) {
        this.childEvents = childEvents;
    }

    public String getUrl() {
        return url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public LocalDate getAllDayDate() {
        return allDayDate;
    }

    public void setAllDayDate(LocalDate allDayDate) {
        this.allDayDate = allDayDate;
    }

    @CanvasField(postKey = "all_day")
    public Boolean getAllDay() {
        return allDay;
    }

    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAppointmentGroupId() {
        return appointmentGroupId;
    }

    public void setAppointmentGroupId(String appointmentGroupId) {
        this.appointmentGroupId = appointmentGroupId;
    }

    public String getAppointmentGroupUrl() {
        return appointmentGroupUrl;
    }

    public void setAppointmentGroupUrl(String appointmentGroupUrl) {
        this.appointmentGroupUrl = appointmentGroupUrl;
    }

    public Boolean getReservation() {
        return reservation;
    }

    public void setReservation(Boolean reservation) {
        this.reservation = reservation;
    }

    public ParticipantType getParticipantType() {
        return participantType;
    }

    public void setParticipantType(ParticipantType participantType) {
        this.participantType = participantType;
    }

    public Integer getParticipantsPerAppointment() {
        return participantsPerAppointment;
    }

    public void setParticipantsPerAppointment(Integer participantsPerAppointment) {
        this.participantsPerAppointment = participantsPerAppointment;
    }

    public Integer getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(Integer availableSlots) {
        this.availableSlots = availableSlots;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @CanvasField(postKey = "count", overrideObjectKey = "calendar_event[duplicate]")
    public Integer getDuplicateCount() {
        return duplicateCount;
    }

    public void setDuplicateCount(Integer duplicateCount) {
        this.duplicateCount = duplicateCount;
    }

    @CanvasField(postKey = "interval", overrideObjectKey = "calendar_event[duplicate]")
    public Integer getDuplicateInterval() {
        return duplicateInterval;
    }

    public void setDuplicateInterval(Integer duplicateInterval) {
        this.duplicateInterval = duplicateInterval;
    }

    @CanvasField(postKey = "frequency", overrideObjectKey = "calendar_event[duplicate]")
    public Frequency getDuplicateFrequency() {
        return duplicateFrequency;
    }

    public void setDuplicateFrequency(Frequency duplicateFrequency) {
        this.duplicateFrequency = duplicateFrequency;
    }

    @CanvasField(postKey = "append_iterator", overrideObjectKey = "calendar_event[duplicate]")
    public Boolean getDuplicateAppend() {
        return duplicateAppend;
    }

    public void setDuplicateAppend(Boolean duplicateAppend) {
        this.duplicateAppend = duplicateAppend;
    }

    public List<ChildEvent> getChildEventsData() {
        return childEventsData;
    }

    public void setChildEventsData(List<ChildEvent> childEventsData) {
        this.childEventsData = childEventsData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalendarEvent that = (CalendarEvent) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(startAt, that.startAt) &&
                Objects.equals(endAt, that.endAt) &&
                Objects.equals(description, that.description) &&
                Objects.equals(locationName, that.locationName) &&
                Objects.equals(locationAddress, that.locationAddress) &&
                Objects.equals(contextCode, that.contextCode) &&
                Objects.equals(effectiveContextCode, that.effectiveContextCode) &&
                Objects.equals(allContextCodes, that.allContextCodes) &&
                workflowState == that.workflowState &&
                Objects.equals(hidden, that.hidden) &&
                Objects.equals(parentEventId, that.parentEventId) &&
                Objects.equals(childEventsCount, that.childEventsCount) &&
                Objects.equals(childEvents, that.childEvents) &&
                Objects.equals(childEventsData, that.childEventsData) &&
                Objects.equals(url, that.url) &&
                Objects.equals(htmlUrl, that.htmlUrl) &&
                Objects.equals(allDayDate, that.allDayDate) &&
                Objects.equals(allDay, that.allDay) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(appointmentGroupId, that.appointmentGroupId) &&
                Objects.equals(appointmentGroupUrl, that.appointmentGroupUrl) &&
                Objects.equals(reservation, that.reservation) &&
                participantType == that.participantType &&
                Objects.equals(participantsPerAppointment, that.participantsPerAppointment) &&
                Objects.equals(availableSlots, that.availableSlots) &&
                Objects.equals(user, that.user) &&
                Objects.equals(duplicateCount, that.duplicateCount) &&
                Objects.equals(duplicateInterval, that.duplicateInterval) &&
                duplicateFrequency == that.duplicateFrequency &&
                Objects.equals(duplicateAppend, that.duplicateAppend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, startAt, endAt, description, locationName, locationAddress, contextCode, effectiveContextCode, allContextCodes, workflowState, hidden, parentEventId, childEventsCount, childEvents, childEventsData, url, htmlUrl, allDayDate, allDay, createdAt, updatedAt, appointmentGroupId, appointmentGroupUrl, reservation, participantType, participantsPerAppointment, availableSlots, user, duplicateCount, duplicateInterval, duplicateFrequency, duplicateAppend);
    }
}
