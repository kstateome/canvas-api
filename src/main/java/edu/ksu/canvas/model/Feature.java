package edu.ksu.canvas.model;

import java.io.Serializable;
import java.time.Instant;

public class Feature extends BaseCanvasModel implements Serializable {

    // In the documentation this is called "name" but the API returns it as "feature"
    private String feature;
    private String displayName;
    private String appliesTo;
    private Instant enableAt;
    private FeatureFlag featureFlag;
    private boolean rootOptIn;
    private boolean beta;
    private boolean autoexpand;
    private boolean development;
    private String releaseNotesUrl;

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAppliesTo() {
        return appliesTo;
    }

    public void setAppliesTo(String appliesTo) {
        this.appliesTo = appliesTo;
    }

    public Instant getEnableAt() {
        return enableAt;
    }

    public void setEnableAt(Instant enableAt) {
        this.enableAt = enableAt;
    }

    public FeatureFlag getFeatureFlag() {
        return featureFlag;
    }

    public void setFeatureFlag(FeatureFlag featureFlag) {
        this.featureFlag = featureFlag;
    }

    public boolean isRootOptIn() {
        return rootOptIn;
    }

    public void setRootOptIn(boolean rootOptIn) {
        this.rootOptIn = rootOptIn;
    }

    public boolean isBeta() {
        return beta;
    }

    public void setBeta(boolean beta) {
        this.beta = beta;
    }

    public boolean isAutoexpand() {
        return autoexpand;
    }

    public void setAutoexpand(boolean autoexpand) {
        this.autoexpand = autoexpand;
    }

    public boolean isDevelopment() {
        return development;
    }

    public void setDevelopment(boolean development) {
        this.development = development;
    }

    public String getReleaseNotesUrl() {
        return releaseNotesUrl;
    }

    public void setReleaseNotesUrl(String releaseNotesUrl) {
        this.releaseNotesUrl = releaseNotesUrl;
    }
}
