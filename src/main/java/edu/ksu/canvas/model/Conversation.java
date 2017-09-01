package edu.ksu.canvas.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import edu.ksu.canvas.annotation.CanvasObject;

@CanvasObject(postKey="conversation")
public class Conversation extends BaseCanvasModel implements Serializable {
    public static final long serialVersionUID = 1L;

    private Integer id;
    private String subject;
    private ConversationMessageState workflowState;
    private String lastMessage;
    private Date lastMessageAt;
    private String lastAuthoredMessage;
    private Date lastAuthoredMessageAt;
    private Integer messageCount;
    private Boolean subscribed;
    @SerializedName("private") //can't name a variable "private" in java
    private Boolean isPrivate;
    private Boolean starred;
    private List<ConversationFlags> properties;
    private List<Integer> audience;
    private Object audienceContexts; //TODO: Refine this object type
    private String avatarUrl;
    private List<MessageParticipant> participants;
    private Boolean visible;
    private String contextName;
    private List<Message> messages;

    public enum ConversationMessageState { unread, read, archived; }

    public enum ConversationFlags { last_author, attachments, media_objects }

    public class Message {
        private Integer id;
        private Integer authorId;
        private Date createdAt;
        private Boolean generated;
        private String body;
        private List<Object> forwardedMessages;
        private List<String> attachments;
        private Object mediaComment;
        private List<Integer> participatingUserIds;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getAuthorId() {
            return authorId;
        }

        public void setAuthorId(Integer authorId) {
            this.authorId = authorId;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public Boolean getGenerated() {
            return generated;
        }

        public void setGenerated(Boolean generated) {
            this.generated = generated;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public List<Object> getForwardedMessages() {
            return forwardedMessages;
        }

        public void setForwardedMessages(List<Object> forwardedMessages) {
            this.forwardedMessages = forwardedMessages;
        }

        public List<String> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<String> attachments) {
            this.attachments = attachments;
        }

        public Object getMediaComment() {
            return mediaComment;
        }

        public void setMediaComment(Object mediaComment) {
            this.mediaComment = mediaComment;
        }

        public List<Integer> getParticipatingUserIds() {
            return participatingUserIds;
        }

        public void setParticipatingUserIds(List<Integer> participatingUserIds) {
            this.participatingUserIds = participatingUserIds;
        }
    }

    public class MessageParticipant implements Serializable {
        private static final long serialVersionUID = 1L;

        private Integer id;
        private String name;
        private Object commonCourses; //TODO: Refine this object type
        private Object commonGroups; //TODO: Refine this object type
        private String avatarUrl;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getCommonCourses() {
            return commonCourses;
        }

        public void setCommonCourses(Object commonCourses) {
            this.commonCourses = commonCourses;
        }

        public Object getCommonGroups() {
            return commonGroups;
        }

        public void setCommonGroups(List<Object> commonGroups) {
            this.commonGroups = commonGroups;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }
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

    public Date getLastMessageAt() {
        return lastMessageAt;
    }

    public void getLastMessageAt(Date lastMessageAt) {
        this.lastMessageAt = lastMessageAt;
    }

    public String getLastAuthoredMessage() {
        return lastAuthoredMessage;
    }

    public void setLastAuthoredMessage(String lastAuthoredMessage) {
        this.lastAuthoredMessage = lastAuthoredMessage;
    }

    public Date getLastAuthoredMessageAt() {
        return lastAuthoredMessageAt;
    }

    public void setLastAuthoredMessageAt(Date lastAuthoredMessageAt) {
        this.lastAuthoredMessageAt = lastAuthoredMessageAt;
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

    public List<ConversationFlags> getProperties() {
        return properties;
    }

    public void setProperties(List<ConversationFlags> properties) {
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

    public List<MessageParticipant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<MessageParticipant> participants) {
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

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
