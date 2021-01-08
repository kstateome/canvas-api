package edu.ksu.canvas.model;

import com.google.gson.annotations.SerializedName;
import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

import java.io.Serializable;

/**
 * Class to represent Canvas user communication channels. See the
 * <a href="https://canvas.instructure.com/doc/api/communication_channels.html">Communication Channels API</a>
 * documentation.
 */
@CanvasObject(postKey = "communication_channel")
@SuppressWarnings("serial")
public class CommunicationChannel extends BaseCanvasModel implements Serializable {

    public enum WorkflowState {
        @SerializedName("active")
        ACTIVE,
        @SerializedName("unconfirmed")
        UNCONFIRMED;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    public enum Type {
        @SerializedName("email")
        EMAIL,
        @SerializedName("push")
        PUSH,
        @SerializedName("sms")
        SMS,
        @SerializedName("twitter")
        TWITTER;

        @Override
        public String toString() { return name().toLowerCase(); }
    }

    private String id;
    private String userId;
    private String address;
    private Type type;
    private Integer position;
    private WorkflowState workflowState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @CanvasField(overrideObjectKey = "user", postKey = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public WorkflowState getWorkflowState() {
        return workflowState;
    }

    public void setWorkflowState(WorkflowState workflowState) {
        this.workflowState = workflowState;
    }

}
