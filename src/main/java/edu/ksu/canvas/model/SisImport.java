package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * Class to represent Canvas Sis Imports.
 * See <a href="https://canvas.instructure.com/doc/api/sis_imports.html#SisImport">SisImport</a> documentation.
 */
 @CanvasObject(postKey = "sis_import")
public class SisImport extends BaseCanvasModel implements Serializable {
    public static final long serialVersionUID = 1L;

    private Long id;

    private Instant createdAt;
    private Instant startedAt;
    private Instant endedAt;
    private Instant updatedAt;

    private Integer progress;
    private String workflowState;
    private Object data;
    private Boolean batchMode;
    private String batchModeTermId;
    private Boolean multiTermBatchMode;
    private Boolean overrideSisStickiness;
    private Boolean addSisStickiness;
    private Boolean clearSisStickiness;
    private String diffingDataSetIdentifier;
    private Long diffedAgainstImportId;
    private String diffingDropStatus;
    private Boolean skipDeletes;
    private Integer changeThreshold;
    private Integer diffRowCountThreshold;

    private List<List<String>> processingWarnings = null;
    private List<List<String>> processingErrors = null;
    private Object errorsAttachment;
    private User user;
    private Object csvAttachments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    @CanvasField(postKey = "created_at")
    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @CanvasField(postKey = "started_at")
    public Instant getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Instant startedAt) {
        this.startedAt = startedAt;
    }

    @CanvasField(postKey = "updated_at")
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @CanvasField(postKey = "ended_at")
    public Instant getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(Instant endedAt) {
        this.endedAt = endedAt;
    }

    @CanvasField(postKey = "workflow_state")
    public String getWorkflowState() {
        return workflowState;
    }

    public void setWorkflowState(String workflowState) {
        this.workflowState = workflowState;
    }

    @CanvasField(postKey = "processing_warnings")
    public List<List<String>> getProcessingWarnings() {
        return processingWarnings;
    }

    public void setProcessingWarnings(List<List<String>> processingWarnings) {
        this.processingWarnings = processingWarnings;
    }

    @CanvasField(postKey = "processing_errors")
    public List<List<String>> getProcessingErrors() {
        return processingErrors;
    }

    public void setProcessingErrors(List<List<String>> processingErrors) {
        this.processingErrors = processingErrors;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @CanvasField(postKey = "errors_attachment")
    public Object getErrorsAttachment() {
        return errorsAttachment;
    }

    public void setErrorsAttachment(Object errorsAttachment) {
        this.errorsAttachment = errorsAttachment;
    }

    @CanvasField(postKey = "csv_attachments")
    public Object getCsvAttachments() {
        return csvAttachments;
    }

    public void setCsvAttachments(Object csvAttachments) {
        this.csvAttachments = csvAttachments;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @CanvasField(postKey = "batch_mode")
    public Boolean getBatchMode() {
        return batchMode;
    }

    public void setBatchMode(Boolean batchMode) {
        this.batchMode = batchMode;
    }

    @CanvasField(postKey = "batch_mode_term_id")
    public String getBatchModeTermId() {
        return batchModeTermId;
    }

    public void setBatchModeTermId(String batchModeTermId) {
        this.batchModeTermId = batchModeTermId;
    }

    @CanvasField(postKey = "multi_term_batch_mode")
    public Object getMultiTermBatchMode() {
        return multiTermBatchMode;
    }

    public void setMultiTermBatchMode(Boolean multiTermBatchMode) {
        this.multiTermBatchMode = multiTermBatchMode;
    }

    @CanvasField(postKey = "override_sis_stickiness")
    public Boolean getOverrideSisStickiness() {
        return overrideSisStickiness;
    }

    public void setOverrideSisStickiness(Boolean overrideSisStickiness) {
        this.overrideSisStickiness = overrideSisStickiness;
    }

    @CanvasField(postKey = "add_sis_stickiness")
    public Boolean getAddSisStickiness() {
        return addSisStickiness;
    }

    public void setAddSisStickiness(Boolean addSisStickiness) {
        this.addSisStickiness = addSisStickiness;
    }

    @CanvasField(postKey = "clear_sis_stickiness")
    public Boolean getClearSisStickiness() {
        return clearSisStickiness;
    }

    public void setClearSisStickiness(Boolean clearSisStickiness) {
        this.clearSisStickiness = clearSisStickiness;
    }

    @CanvasField(postKey = "diffing_data_set_identifier")
    public String getDiffingDataSetIdentifier() {
        return diffingDataSetIdentifier;
    }

    public void setDiffingDataSetIdentifier(String diffingDataSetIdentifier) {
        this.diffingDataSetIdentifier = diffingDataSetIdentifier;
    }

    @CanvasField(postKey = "diffed_against_import_id")
    public Long getDiffedAgainstImportId() {
        return diffedAgainstImportId;
    }

    public void setDiffedAgainstImportId(Long diffedAgainstImportId) {
        this.diffedAgainstImportId = diffedAgainstImportId;
    }

    @CanvasField(postKey = "diffing_drop_status")
    public String getDiffingDropStatus() {
        return diffingDropStatus;
    }

    public void setDiffingDropStatus(String diffingDropStatus) {
        this.diffingDropStatus = diffingDropStatus;
    }

    @CanvasField(postKey = "skip_deletes")
    public Boolean getSkipDeletes() {
        return skipDeletes;
    }

    @CanvasField(postKey = "skip_deletes")
    public void setSkipDeletes(Boolean skipDeletes) {
        this.skipDeletes = skipDeletes;
    }

    @CanvasField(postKey = "change_threshold")
    public Integer getChangeThreshold() {
        return changeThreshold;
    }

    public void setChangeThreshold(Integer changeThreshold) {
        this.changeThreshold = changeThreshold;
    }

    @CanvasField(postKey = "diff_row_count_threshold")
    public Integer getDiffRowCountThreshold() {
        return diffRowCountThreshold;
    }

    public void setDiffRowCountThreshold(Integer diffRowCountThreshold) {
        this.diffRowCountThreshold = diffRowCountThreshold;
    }

}
