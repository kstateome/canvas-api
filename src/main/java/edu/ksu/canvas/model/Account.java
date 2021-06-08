package edu.ksu.canvas.model;

import edu.ksu.canvas.annotation.CanvasField;
import edu.ksu.canvas.annotation.CanvasObject;

import java.io.Serializable;

/**
 * Class to represent Canvas Accounts.
 * See <a href="https://canvas.instructure.com/doc/api/accounts.html">Canvas accounts</a> documentation.
 */
@CanvasObject(postKey = "account")
public class Account extends BaseCanvasModel implements Serializable {
    public static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Long parentAccountId; // The account's parent ID, or null if this is the root account
    private Long rootAccountId; // The ID of the root account, or null if this is the root account
    private Long defaultStorageQuotaMb;
    private Long defaultUserStorageQuotaMb;
    private Long defaultGroupStorageQuotaMb;
    private String defaultTimeZone;
    private String sisAccountId;
    private String integrationId; // currently unused, expect it to be null
    private String sisImportId;
    private String ltiGuid;
    // The state of the account. Can be 'active' or 'deleted'.
    private String workflowState; //todo: Should be an enum


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @CanvasField(postKey = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentAccountId() {
        return parentAccountId;
    }

    public void setParentAccountId(Long parentAccountId) {
        this.parentAccountId = parentAccountId;
    }

    public Long getRootAccountId() {
        return rootAccountId;
    }

    public void setRootAccountId(Long rootAccountId) {
        this.rootAccountId = rootAccountId;
    }

    @CanvasField(postKey = "default_storage_quota_mb")
    public Long getDefaultStorageQuotaMb() {
        return defaultStorageQuotaMb;
    }

    public void setDefaultStorageQuotaMb(Long defaultStorageQuotaMb) {
        this.defaultStorageQuotaMb = defaultStorageQuotaMb;
    }

    @CanvasField(postKey = "default_user_storage_quota_mb")
    public Long getDefaultUserStorageQuotaMb() {
        return defaultUserStorageQuotaMb;
    }

    public void setDefaultUserStorageQuotaMb(Long defaultUserStorageQuotaMb) {
        this.defaultUserStorageQuotaMb = defaultUserStorageQuotaMb;
    }

    @CanvasField(postKey = "default_group_storage_quota_mb")
    public Long getDefaultGroupStorageQuotaMb() {
        return defaultGroupStorageQuotaMb;
    }

    public void setDefaultGroupStorageQuotaMb(Long defaultGroupStorageQuotaMb) {
        this.defaultGroupStorageQuotaMb = defaultGroupStorageQuotaMb;
    }

    @CanvasField(postKey = "default_time_zone")
    public String getDefaultTimeZone() {
        return defaultTimeZone;
    }

    public void setDefaultTimeZone(String defaultTimeZone) {
        this.defaultTimeZone = defaultTimeZone;
    }

    @CanvasField(postKey = "sis_account_id")
    public String getSisAccountId() {
        return sisAccountId;
    }

    public void setSisAccountId(String sisAccountId) {
        this.sisAccountId = sisAccountId;
    }

    public String getIntegrationId() {
        return integrationId;
    }

    public void setIntegrationId(String integrationId) {
        this.integrationId = integrationId;
    }

    public String getSisImportId() {
        return sisImportId;
    }

    public void setSisImportId(String sisImportId) {
        this.sisImportId = sisImportId;
    }

    public String getLtiGuid() {
        return ltiGuid;
    }

    public void setLtiGuid(String ltiGuid) {
        this.ltiGuid = ltiGuid;
    }

    public String getWorkflowState() {
        return workflowState;
    }

    public void setWorkflowState(String workflowState) {
        this.workflowState = workflowState;
    }
}
