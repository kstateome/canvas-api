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
    //These should ideally be more strictly typed.
    //I'm having trouble finding out exactly what possible values can be in here though.
    //Some seem to have sub-nested structures that the API docs don't talk about.
    private Map<String, Object> customFields;
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

    public Map<String, Object> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Map<String, Object> customFields) {
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
