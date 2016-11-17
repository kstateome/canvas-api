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

    private Integer id;
    private String name;
    private Integer parentAccountId; // The account's parent ID, or null if this is the root account
    private Integer rootAccountId; // The ID of the root account, or null if this is the root account
    private Integer defaultStorageQuotaMb;
    private Integer defaultUserStorageQuotaMb;
    private Integer defaultGroupStorageQuotaMb;
    private String defaultTimeZone;
    private String sisAccountId;
    private String integrationId; // currently unused, expect it to be null
    private String sisImportId;
    private String ltiGuid;
    // The state of the account. Can be 'active' or 'deleted'.
    private String workflowState; //todo: Should be an enum


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @CanvasField(postKey = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentAccountId() {
        return parentAccountId;
    }

    public void setParentAccountId(Integer parentAccountId) {
        this.parentAccountId = parentAccountId;
    }

    public Integer getRootAccountId() {
        return rootAccountId;
    }

    public void setRootAccountId(Integer rootAccountId) {
        this.rootAccountId = rootAccountId;
    }

    @CanvasField(postKey = "default_storage_quota_mb")
    public Integer getDefaultStorageQuotaMb() {
        return defaultStorageQuotaMb;
    }

    public void setDefaultStorageQuotaMb(Integer defaultStorageQuotaMb) {
        this.defaultStorageQuotaMb = defaultStorageQuotaMb;
    }

    @CanvasField(postKey = "default_user_storage_quota_mb")
    public Integer getDefaultUserStorageQuotaMb() {
        return defaultUserStorageQuotaMb;
    }

    public void setDefaultUserStorageQuotaMb(Integer defaultUserStorageQuotaMb) {
        this.defaultUserStorageQuotaMb = defaultUserStorageQuotaMb;
    }

    @CanvasField(postKey = "default_group_storage_quota_mb")
    public Integer getDefaultGroupStorageQuotaMb() {
        return defaultGroupStorageQuotaMb;
    }

    public void setDefaultGroupStorageQuotaMb(Integer defaultGroupStorageQuotaMb) {
        this.defaultGroupStorageQuotaMb = defaultGroupStorageQuotaMb;
    }

    @CanvasField(postKey = "default_time_zone")
    public String getDefaultTimeZone() {
        return defaultTimeZone;
    }

    public void setDefaultTimeZone(String defaultTimeZone) {
        this.defaultTimeZone = defaultTimeZone;
    }

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
