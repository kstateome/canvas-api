package edu.ksu.canvas.model;

import java.util.Date;
import java.util.Map;

public class ExternalTool extends BaseCanvasModel {

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
    private String iconUrl;
    private String text;
    private Boolean notSelectable;
    private String configType;
    private String configXml;
    private String configUrl;
    //These navigation settings could maybe be split into their own objects
    //instead of just being maps but I'm not sure if it is worth it.
    //For possible values, see the API docs for the "Create external tool"
    //API call: https://canvas.instructure.com/doc/api/external_tools.html#method.external_tools.create
    private Map<String, String> customFields;
    private Map<String, String> accountNavigation;
    private Map<String, String> userNavigation;
    private Map<String, String> courseNavigation;
    private Map<String, String> editorButton;
    private Map<String, String> resourceSelection;

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

    public void setConsumer_key(String consumerKey) {
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

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
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

    public Map<String, String> getAccountNavigation() {
        return accountNavigation;
    }

    public void setAccountNavigation(Map<String, String> accountNavigation) {
        this.accountNavigation = accountNavigation;
    }

    public Map<String, String> getUserNavigation() {
        return userNavigation;
    }

    public void setUserNavigation(Map<String, String> userNavigation) {
        this.userNavigation = userNavigation;
    }

    public Map<String, String> getCourseNavigation() {
        return courseNavigation;
    }

    public void setCourseNavigation(Map<String, String> courseNavigation) {
        this.courseNavigation = courseNavigation;
    }

    public Map<String, String> getEditorButton() {
        return editorButton;
    }

    public void setEditorButton(Map<String, String> editorButton) {
        this.editorButton = editorButton;
    }

    public Map<String, String> getResourceSelection() {
        return resourceSelection;
    }

    public void setResourceSelection(Map<String, String> resourceSelection) {
        this.resourceSelection = resourceSelection;
    }
}
