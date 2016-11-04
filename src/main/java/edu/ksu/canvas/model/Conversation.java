package edu.ksu.canvas.model;

import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Conversation extends BaseCanvasModel {

    private Integer id;
    private String subject;
    private ConversationMessageState workflowState;
    private String lastMessage;
    private Date startAt;
    private Integer messageCount;
    private Boolean subscribed;
    @SerializedName("private") //can't name a variable "private" in java
    private Boolean isPrivate;
    private Boolean starred;
    private Object properties;  //TODO: Refine this object type
    private List<Integer> audience;
    private Object audienceContexts; //TODO: Refine this object type
    private String avatarUrl;
    private Object participants; //TODO: Refine this object type
    private Boolean visible;
    private String contextName;

    public enum ConversationMessageState {
        unread, read, archived;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public ConversationMessageState getWorkflowState() {
        return workflowState;
    }

    public void setWorkflowState(ConversationMessageState workflowState) {
        this.workflowState = workflowState;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Integer getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }

    public Boolean getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public Boolean getStarred() {
        return starred;
    }

    public void setStarred(Boolean starred) {
        this.starred = starred;
    }

    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object properties) {
        this.properties = properties;
    }

    public List<Integer> getAudience() {
        return audience;
    }

    public void setAudience(List<Integer> audience) {
        this.audience = audience;
    }

    public Object getAudienceContexts() {
        return audienceContexts;
    }

    public void setAudienceContexts(Object audienceContexts) {
        this.audienceContexts = audienceContexts;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Object getParticipants() {
        return participants;
    }

    public void setParticipants(Object participants) {
        this.participants = participants;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public String getContextName() {
        return contextName;
    }

    public void setContextName(String contextName) {
        this.contextName = contextName;
    }
}
