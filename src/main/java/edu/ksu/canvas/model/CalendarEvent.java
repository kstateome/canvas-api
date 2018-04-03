package edu.ksu.canvas.model;

import com.google.gson.annotations.SerializedName;
import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    }

    public enum ParticipantType {
        @SerializedName("Group")
        GROUP,
        @SerializedName("User")
        USER;
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
    private String url;
    private String htmlUrl;
    private LocalDate allDayDate;
    private Boolean allDay = true;
    private Instant createdAt;
    private Instant updatedAt;
    private String appointmentGroupId;
    private String appointmentGroupUrl;
    private Boolean reservation;
    private ParticipantType participantType;
    private Integer participantsPerAppointment;
    private Integer availableSlots;
    private User user;
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
        // The non-modifiable properties.
        this.effectiveContextCode = other.effectiveContextCode;
        this.allContextCodes = other.allContextCodes;
        this.workflowState = other.workflowState;
        this.hidden = other.hidden;
        this.parentEventId = other.parentEventId;
        this.childEventsCount = other.childEventsCount;
        this.childEvents = new ArrayList<>();
        for(CalendarEvent child: other.childEvents) {
            this.childEvents.add(new CalendarEvent(child));
        }
        this.url = other.url;
        this.htmlUrl = other.htmlUrl;
        this.allDayDate = other.allDayDate;
        this.allDay = other.allDay;
        this.createdAt = other.createdAt;
        this.updatedAt = other.updatedAt;
        this.appointmentGroupId = other.appointmentGroupId;
        this.appointmentGroupUrl = other.appointmentGroupUrl;
        this.reservation = other.reservation;
        this.participantType = other.participantType;
        this.availableSlots = other.availableSlots;
        this.user = new User(other.user);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalendarEvent that = (CalendarEvent) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (startAt != null ? !startAt.equals(that.startAt) : that.startAt != null) return false;
        if (endAt != null ? !endAt.equals(that.endAt) : that.endAt != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (locationName != null ? !locationName.equals(that.locationName) : that.locationName != null) return false;
        if (locationAddress != null ? !locationAddress.equals(that.locationAddress) : that.locationAddress != null)
            return false;
        if (contextCode != null ? !contextCode.equals(that.contextCode) : that.contextCode != null) return false;
        if (effectiveContextCode != null ? !effectiveContextCode.equals(that.effectiveContextCode) : that.effectiveContextCode != null)
            return false;
        if (allContextCodes != null ? !allContextCodes.equals(that.allContextCodes) : that.allContextCodes != null)
            return false;
        if (workflowState != that.workflowState) return false;
        if (hidden != null ? !hidden.equals(that.hidden) : that.hidden != null) return false;
        if (parentEventId != null ? !parentEventId.equals(that.parentEventId) : that.parentEventId != null)
            return false;
        if (childEventsCount != null ? !childEventsCount.equals(that.childEventsCount) : that.childEventsCount != null)
            return false;
        if (childEvents != null ? !childEvents.equals(that.childEvents) : that.childEvents != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (htmlUrl != null ? !htmlUrl.equals(that.htmlUrl) : that.htmlUrl != null) return false;
        if (allDayDate != null ? !allDayDate.equals(that.allDayDate) : that.allDayDate != null) return false;
        if (allDay != null ? !allDay.equals(that.allDay) : that.allDay != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;
        if (appointmentGroupId != null ? !appointmentGroupId.equals(that.appointmentGroupId) : that.appointmentGroupId != null)
            return false;
        if (appointmentGroupUrl != null ? !appointmentGroupUrl.equals(that.appointmentGroupUrl) : that.appointmentGroupUrl != null)
            return false;
        if (reservation != null ? !reservation.equals(that.reservation) : that.reservation != null) return false;
        if (participantType != that.participantType) return false;
        if (participantsPerAppointment != null ? !participantsPerAppointment.equals(that.participantsPerAppointment) : that.participantsPerAppointment != null)
            return false;
        if (availableSlots != null ? !availableSlots.equals(that.availableSlots) : that.availableSlots != null)
            return false;
        return user != null ? user.equals(that.user) : that.user == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (startAt != null ? startAt.hashCode() : 0);
        result = 31 * result + (endAt != null ? endAt.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (locationName != null ? locationName.hashCode() : 0);
        result = 31 * result + (locationAddress != null ? locationAddress.hashCode() : 0);
        result = 31 * result + (contextCode != null ? contextCode.hashCode() : 0);
        result = 31 * result + (effectiveContextCode != null ? effectiveContextCode.hashCode() : 0);
        result = 31 * result + (allContextCodes != null ? allContextCodes.hashCode() : 0);
        result = 31 * result + (workflowState != null ? workflowState.hashCode() : 0);
        result = 31 * result + (hidden != null ? hidden.hashCode() : 0);
        result = 31 * result + (parentEventId != null ? parentEventId.hashCode() : 0);
        result = 31 * result + (childEventsCount != null ? childEventsCount.hashCode() : 0);
        result = 31 * result + (childEvents != null ? childEvents.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (htmlUrl != null ? htmlUrl.hashCode() : 0);
        result = 31 * result + (allDayDate != null ? allDayDate.hashCode() : 0);
        result = 31 * result + (allDay != null ? allDay.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (appointmentGroupId != null ? appointmentGroupId.hashCode() : 0);
        result = 31 * result + (appointmentGroupUrl != null ? appointmentGroupUrl.hashCode() : 0);
        result = 31 * result + (reservation != null ? reservation.hashCode() : 0);
        result = 31 * result + (participantType != null ? participantType.hashCode() : 0);
        result = 31 * result + (participantsPerAppointment != null ? participantsPerAppointment.hashCode() : 0);
        result = 31 * result + (availableSlots != null ? availableSlots.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
