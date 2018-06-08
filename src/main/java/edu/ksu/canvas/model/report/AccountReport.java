package edu.ksu.canvas.model.report;

import java.io.Serializable;
import java.net.URI;
import java.util.Date;
import java.util.Map;

/**
 * A class to represent a report object in Canvas.
 * <p>
 * Note:  this class is not annotated with {@link edu.ksu.canvas.annotation.CanvasObject} due to the fact that
 * we would not use this object to post form parameters; we would instead rely on the {@link edu.ksu.canvas.requestOptions.AccountReportOptions}
 * class to generate any form parameters.
 */
public class AccountReport implements Serializable {
    private Integer id;
    private Integer progress;
    private Map<String, Object> parameters;
    private Integer currentLine;
    private String status;
    private Date createdAt;
    private Date startedAt;
    private Date endedAt;
    private URI fileUrl;
    private AccountReportAttachment attachment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public Integer getCurrentLine() {
        return currentLine;
    }

    public void setCurrentLine(Integer currentLine) {
        this.currentLine = currentLine;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(Date endedAt) {
        this.endedAt = endedAt;
    }

    public URI getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(URI fileUrl) {
        this.fileUrl = fileUrl;
    }

    public AccountReportAttachment getAttachment() {
        return attachment;
    }

    public void setAttachment(AccountReportAttachment attachment) {
        this.attachment = attachment;
    }
}
