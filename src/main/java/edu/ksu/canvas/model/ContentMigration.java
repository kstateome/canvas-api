package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

import java.io.Serializable;
import java.time.Instant;

@CanvasObject(postKey = "content_migration")
public class ContentMigration extends BaseCanvasModel implements Serializable {
    private Long id;
    private String migrationType;
    private String migrationTypeTitle;
    private String migrationIssuesUrl;
    private Integer migrationIssuesCount;
    private String progressUrl;
    private Long userId;

    private Instant createdAt;
    private Instant startedAt;
    private Instant finishedAt;

    public enum WorkflowState {
        pre_processing, pre_processed, running, waiting_for_select, completed, failed;

        @Override
        public String toString() { return name().toLowerCase(); }

    }

    private WorkflowState workflowState;

    /**
     * This will need to be converted to FileUpload object
     **/
    private Object preAttachment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMigrationType() {
        return migrationType;
    }

    public void setMigrationType(String migrationType) {
        this.migrationType = migrationType;
    }

    public String getMigrationTypeTitle() {
        return migrationTypeTitle;
    }

    public void setMigrationTypeTitle(String migrationTypeTitle) {
        this.migrationTypeTitle = migrationTypeTitle;
    }

    public String getMigrationIssuesUrl() {
        return migrationIssuesUrl;
    }

    public void setMigrationIssuesUrl(String migrationIssuesUrl) {
        this.migrationIssuesUrl = migrationIssuesUrl;
    }

    public Integer getMigrationIssuesCount() {
        return migrationIssuesCount;
    }

    public void setMigrationIssuesCount(Integer migrationIssuesCount) {
        this.migrationIssuesCount = migrationIssuesCount;
    }

    public String getProgressUrl() {
        return progressUrl;
    }

    public void setProgressUrl(String progressUrl) {
        this.progressUrl = progressUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @CanvasField(postKey = "workflow_state")
    public WorkflowState getWorkflowState() {
        return workflowState;
    }

    public void setWorkflowState(WorkflowState workflowState) {
        this.workflowState = workflowState;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Instant startedAt) {
        this.startedAt = startedAt;
    }

    public Instant getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Instant finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Object getPreAttachment() {
        return preAttachment;
    }

    public void setPreAttachment(Object preAttachment) {
        this.preAttachment = preAttachment;
    }
}