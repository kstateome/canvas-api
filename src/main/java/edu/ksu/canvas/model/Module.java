package edu.ksu.canvas.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Module extends BaseCanvasModel implements Serializable {
    public static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Long position;
    private Date unlockAt;
    private Boolean requireSequentialProgress;
    private Boolean publishFinalGrade;
    private List<Long> prerequisiteModuleIds;
    private String state;
    private Date completedAt;
    private Boolean published;
    private Long itemsCount;
    private String itemsUrl;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(final Long position) {
        this.position = position;
    }

    public Date getUnlockAt() {
        return unlockAt;
    }

    public void setUnlockAt(final Date unlockAt) {
        this.unlockAt = unlockAt;
    }

    public Boolean getRequireSequentialProgress() {
        return requireSequentialProgress;
    }

    public void setRequireSequentialProgress(final Boolean requireSequentialProgress) {
        this.requireSequentialProgress = requireSequentialProgress;
    }

    public Boolean getPublishFinalGrade() {
        return publishFinalGrade;
    }

    public void setPublishFinalGrade(final Boolean publishFinalGrade) {
        this.publishFinalGrade = publishFinalGrade;
    }

    public List<Long> getPrerequisiteModuleIds() {
        return prerequisiteModuleIds;
    }

    public void setPrerequisiteModuleIds(final List<Long> prerequisiteModuleIds) {
        this.prerequisiteModuleIds = prerequisiteModuleIds;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(final Date completedAt) {
        this.completedAt = completedAt;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(final Boolean published) {
        this.published = published;
    }

    public Long getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(final Long itemsCount) {
        this.itemsCount = itemsCount;
    }

    public String getItemsUrl() {
        return itemsUrl;
    }

    public void setItemsUrl(final String itemsUrl) {
        this.itemsUrl = itemsUrl;
    }
}
