package edu.ksu.canvas.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class ExternalTool extends BaseCanvasModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String description;
    private String url;
    private String domain;
    private String privacyLevel;
    private String consumerKey;
    private String sharedSecret;
    private Date createdAt;
    private Date updatedAt;
    private String workflowState;
    private String iconUrl;
    private String text;
    private Boolean notSelectable;
    private String configType;
    private String configXml;
    private String configUrl;
    private Map<String, String> customFields;
    //These navigation settings are problematic. The possible values in these maps are documented
    //at https://canvas.instructure.com/doc/api/external_tools.html#method.external_tools.create
    //It looks like they are all just string values that could go into a Map<String, String>
    //or a custom object of some kind. Unfortunately there are additional undocumented values
    //which can be nested objects. The example I have right now is a course_navigation element
    //that has this inside of it: "custom_fields": { "Domain_URL":"https://domain.example.com/"}
    private Map<String, Object> accountNavigation;
    private Map<String, Object> userNavigation;
    private Map<String, Object> courseNavigation;
    private Map<String, Object> editorButton;
    private Map<String, Object> resourceSelection;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPrivacyLevel() {
        return privacyLevel;
    }

    public void setPrivacyLevel(String privacyLevel) {
        this.privacyLevel = privacyLevel;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getSharedSecret() {
        return sharedSecret;
    }

    public void setSharedSecret(String sharedSecret) {
        this.sharedSecret = sharedSecret;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getWorkflowState() {
        return workflowState;
    }

    public void setWorkflowState(String workflowState) {
        this.workflowState = workflowState;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getNotSelectable() {
        return notSelectable;
    }

    public void setNotSelectable(Boolean notSelectable) {
        this.notSelectable = notSelectable;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getConfigXml() {
        return configXml;
    }

    public void setConfigXml(String configXml) {
        this.configXml = configXml;
    }

    public String getConfigUrl() {
        return configUrl;
    }

    public void setConfigUrl(String configUrl) {
        this.configUrl = configUrl;
    }

    /**
     * A map of Canvas specific custom fields that will be added to the LTI launch request.
     * See <a href="https://canvas.instructure.com/doc/api/file.tools_variable_substitutions.html">Variable Subsitution</a> docs
     * @return Map of custom variables configured for this tool
     */
    public Map<String, String> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Map<String, String> customFields) {
        this.customFields = customFields;
    }

    public Map<String, Object> getAccountNavigation() {
        return accountNavigation;
    }

    public void setAccountNavigation(Map<String, Object> accountNavigation) {
        this.accountNavigation = accountNavigation;
    }

    public Map<String, Object> getUserNavigation() {
        return userNavigation;
    }

    public void setUserNavigation(Map<String, Object> userNavigation) {
        this.userNavigation = userNavigation;
    }

    public Map<String, Object> getCourseNavigation() {
        return courseNavigation;
    }

    public void setCourseNavigation(Map<String, Object> courseNavigation) {
        this.courseNavigation = courseNavigation;
    }

    public Map<String, Object> getEditorButton() {
        return editorButton;
    }

    public void setEditorButton(Map<String, Object> editorButton) {
        this.editorButton = editorButton;
    }

    public Map<String, Object> getResourceSelection() {
        return resourceSelection;
    }

    public void setResourceSelection(Map<String, Object> resourceSelection) {
        this.resourceSelection = resourceSelection;
    }
}
