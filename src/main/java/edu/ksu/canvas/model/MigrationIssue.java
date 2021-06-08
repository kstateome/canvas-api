package edu.ksu.canvas.model;

import java.io.Serializable;

import edu.ksu.canvas.annotation.CanvasObject;

/**
 * Class to represent Canvas migration issues.
 * See <a href="https://canvas.instructure.com/doc/api/content_migrations.html#MigrationIssue">Migration Issue</a> documentation.
 */
 @CanvasObject(postKey = "migration_issue")
public class MigrationIssue extends BaseCanvasModel implements Serializable {
    public static final long serialVersionUID = 1L;

    private Long id;
    private String contentMigrationUrl;
    private String description;
    private String workflowState;
    private String fixIssueHtmlUrl;
    private String issueType;
    private String errorReportHtmlUrl;
    private String errorMessage;
    private String createdAt;
    private String updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContentMigrationUrl() {
        return contentMigrationUrl;
    }

    public void setContentMigrationUrl(String contentMigrationUrl) {
        this.contentMigrationUrl = contentMigrationUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorkflowState() {
        return workflowState;
    }

    public void setWorkflowState(String workflowState) {
        this.workflowState = workflowState;
    }

    public String getFixIssueHtmlUrl() {
        return fixIssueHtmlUrl;
    }

    public void setFixIssueHtmlUrl(String fixIssueHtmlUrl) {
        this.fixIssueHtmlUrl = fixIssueHtmlUrl;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getErrorReportHtmlUrl() {
        return errorReportHtmlUrl;
    }

    public void setErrorReportHtmlUrl(String errorReportHtmlUrl) {
        this.errorReportHtmlUrl = errorReportHtmlUrl;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
