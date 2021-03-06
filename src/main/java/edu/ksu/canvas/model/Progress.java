package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasObject;

import java.io.Serializable;
import java.util.Date;

/**
 * API for querying the progress of asynchronous API operations.
 * id: the ID of the Progress object
 * contextId        the context owning the job.
 * userId           the id of the user who started the job
 * tag              the type of operation
 * completion       percent completed
 * workflowState    the state of the job one of 'queued', 'running', 'completed', 'failed'
 * createdAt        the time the job was created
 * updatedAt        the time the job was last updated
 * message          optional details about the job
 * url              url where a progress update can be retrieved
 */
@CanvasObject(postKey = "progress")
public class Progress implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long contextId;
    private String contextType;
    private Long userId;
    private String tag;
    private Double completion;
    private String workflowState;
    private Date createdAt;
    private Date updatedAt;
    private String message;
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContextId() {
        return contextId;
    }

    public void setContextId(Long contextId) {
        this.contextId = contextId;
    }

    public String getContextType() {
        return contextType;
    }

    public void setContextType(String contextType) {
        this.contextType = contextType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Double getCompletion() {
        return completion;
    }

    public void setCompletion(Double completion) {
        this.completion = completion;
    }

    public String getWorkflowState() {
        return workflowState;
    }

    public void setWorkflowState(String workflowState) {
        this.workflowState = workflowState;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
